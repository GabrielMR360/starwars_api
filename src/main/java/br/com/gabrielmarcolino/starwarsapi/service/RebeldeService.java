package br.com.gabrielmarcolino.starwarsapi.service;

import br.com.gabrielmarcolino.starwarsapi.model.Inventario;
import br.com.gabrielmarcolino.starwarsapi.model.InventarioItem;
import br.com.gabrielmarcolino.starwarsapi.model.Localizacao;
import br.com.gabrielmarcolino.starwarsapi.model.Rebelde;
import br.com.gabrielmarcolino.starwarsapi.model.dto.request.*;
import br.com.gabrielmarcolino.starwarsapi.repository.RebeldeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RebeldeService {
    private final RebeldeRepository rebeldeRepository;

    public Rebelde salvar(RebeldeRequest rebeldeRequest) {
        return rebeldeRepository.save(rebeldeRequest.toRebelde());
    }

    public Rebelde buscarPorId(Long id) {
        return rebeldeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID " + id + " não encontrado."));
    }

    public void reportarTraidor(Long id) {
        Rebelde rebelde = buscarPorId(id);

        rebelde.setQuantidadeReports(rebelde.getQuantidadeReports() + 1);
        if (rebelde.getQuantidadeReports() >= 3) {
            rebelde.setTraidor(true);
        }

        rebeldeRepository.save(rebelde);
    }

    public Rebelde atualizarLocalizacao(Long id, LocalizacaoRequest localizacaoRequest) {
        Rebelde rebelde = buscarPorId(id);
        Localizacao localizacao = rebelde.getLocalizacao();
        Long localizacaoId = localizacao.getId();

        localizacao.setLatitude(localizacaoRequest.latitude());
        localizacao.setLongitude(localizacaoRequest.longitude());
        localizacao.setNome(localizacaoRequest.nome());
        localizacao.setId(localizacaoId);

        rebelde.setLocalizacao(localizacao);
        return rebeldeRepository.save(rebelde);
    }

    public void negociarItens(NegociacaoRequest negociacaoRequest) {
        Rebelde rebeldeNegociante = buscarPorId(negociacaoRequest.idNegociante());
        Rebelde rebeldeRecebedor = buscarPorId(negociacaoRequest.idRecebedor());

        List<InventarioItem> itensRebeldeNegociante = rebeldeNegociante.getInventario().getItens();
        List<InventarioItem> itensRebeldeRecebedor = rebeldeRecebedor.getInventario().getItens();

        List<InventarioItemRequest> itensNegociante = negociacaoRequest.itensNegociante();
        List<InventarioItemRequest> itensRecebedor = negociacaoRequest.itensRecebedor();

        validarTraidor(rebeldeNegociante, rebeldeRecebedor);
        validarInventario(rebeldeNegociante.getInventario(), rebeldeRecebedor.getInventario());
        validarValoresNegociacao(itensNegociante, itensRecebedor);

        validarItens(itensRebeldeNegociante, itensRebeldeRecebedor, itensRecebedor);
        validarItens(itensRebeldeRecebedor, itensRebeldeNegociante, itensNegociante);

        rebeldeRepository.save(rebeldeNegociante);
        rebeldeRepository.save(rebeldeRecebedor);
    }

    private void validarTraidor(Rebelde rebeldeNegociante, Rebelde rebeldeRecebedor) {
        if (rebeldeNegociante.isTraidor() || rebeldeRecebedor.isTraidor()) {
            throw new IllegalArgumentException("Algum dos rebeldes é um traidor");
        }
    }

    private void validarInventario(Inventario inventarioNegociante, Inventario inventarioRecebedor) {
        if (inventarioNegociante.getItens().isEmpty() || inventarioRecebedor.getItens().isEmpty()) {
            throw new IllegalArgumentException("Algum dos invetários está vazio");
        }
    }

    private void validarValoresNegociacao(List<InventarioItemRequest> itensNegociante, List<InventarioItemRequest> itensRecebedor) {
        Integer pontosNegociante = itensNegociante.stream()
                .map(inventarioItem -> inventarioItem.quantidade() * inventarioItem.item().getPontos())
                .reduce(0, (a, b) -> a + b);

        Integer pontosRecebedor = itensRecebedor.stream()
                .map(inventarioItem -> inventarioItem.quantidade() * inventarioItem.item().getPontos())
                .reduce(0, (a, b) -> a + b);

        if (pontosNegociante.compareTo(pontosRecebedor) != 0) {
            throw new IllegalArgumentException("Os pontos para negociação são inválidos");
        }
    }

    private void validarItens(List<InventarioItem> itensRebelde, List<InventarioItem> itensRecebedor, List<InventarioItemRequest> itensNegociacao) {
        itensNegociacao.forEach(itensNegociante -> {
            boolean possuiItem = itensRebelde.stream().anyMatch(itemRebeldeRecebedor -> {
                if (itensNegociante.item().getNome().equals(itemRebeldeRecebedor.getItem().getNome())) {
                    if (itensNegociante.quantidade() > itemRebeldeRecebedor.getQuantidadeItem()) {
                        throw new RuntimeException("O rebelde recebedor não possui a quantidade de itens solicitado.");
                    }

                    adicionarItens(itensRebelde, itensNegociante.item(), itensNegociante.quantidade());
                    removerItens(itensRecebedor, itensNegociante.item(), itensNegociante.quantidade());
                    return true;
                }
                return false;
            });

            if (!possuiItem) {
                throw new IllegalArgumentException("O item " + itensNegociante.item().getNome() + " não foi encontrado.");
            }
        });
    }

    private void adicionarItens(List<InventarioItem> itensRebelde, ItemRequest item, Integer quantidade) {
        itensRebelde.forEach(itemRebelde -> {
            if (itemRebelde.getItem().getNome().equals(item.getNome())) {
                itemRebelde.setQuantidadeItem(itemRebelde.getQuantidadeItem() + quantidade);
            }
        });
    }

    private void removerItens(List<InventarioItem> itensRebelde, ItemRequest item, Integer quantidade) {
        itensRebelde.forEach(itemRebelde -> {
            if (itemRebelde.getItem().getNome().equals(item.getNome())) {
                itemRebelde.setQuantidadeItem(itemRebelde.getQuantidadeItem() - quantidade);
            }
        });
    }
}
