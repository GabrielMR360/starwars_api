package br.com.gabrielmarcolino.starwarsapi.service;

import br.com.gabrielmarcolino.starwarsapi.model.Inventario;
import br.com.gabrielmarcolino.starwarsapi.model.Item;
import br.com.gabrielmarcolino.starwarsapi.model.Localizacao;
import br.com.gabrielmarcolino.starwarsapi.model.Rebelde;
import br.com.gabrielmarcolino.starwarsapi.model.dto.request.ItemRequest;
import br.com.gabrielmarcolino.starwarsapi.model.dto.request.LocalizacaoRequest;
import br.com.gabrielmarcolino.starwarsapi.model.dto.request.NegociacaoRequest;
import br.com.gabrielmarcolino.starwarsapi.model.dto.request.RebeldeRequest;
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

        List<Item> itensRebeldeNegociante = rebeldeNegociante.getInventario().getItens();
        List<Item> itensRebeldeRecebedor = rebeldeRecebedor.getInventario().getItens();

        validarTraidor(rebeldeNegociante, rebeldeRecebedor);
        validarInventario(rebeldeNegociante.getInventario(), rebeldeRecebedor.getInventario());
        validarValoresNegociacao(itensRebeldeNegociante, itensRebeldeRecebedor);

        validarItens(itensRebeldeNegociante, negociacaoRequest.itensRecebedor());
        validarItens(itensRebeldeRecebedor, negociacaoRequest.itensNegociante());
    }

    private void validarItens(List<Item> itensRebelde, List<ItemRequest> itensNegociancao) {
        itensNegociancao.forEach(itemNegociante -> {
            boolean possuiItem = itensRebelde.stream().anyMatch(itemRebeldeRecebedor -> {
                if (itemNegociante.getNome().equals(itemRebeldeRecebedor.getNome())) {
                    if (itemNegociante.getQuantidade() > itemRebeldeRecebedor.getQuantidade()) {
                        throw new RuntimeException("O rebelde recebedor não possui a quantidade de itens solicitado.");
                    }
                    return true;
                } else {
                    return false;
                }
            });

            if (!possuiItem) {
                throw new IllegalArgumentException("O item " + itemNegociante.getNome() + " não foi encontrado.");
            }
        });
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

    private void validarValoresNegociacao(List<Item> itensNegociante, List<Item> itensRecebedor) {
        Integer pontosNegociante = itensNegociante.stream()
                .map(item -> item.getPontos() * item.getQuantidade())
                .reduce(0, (a, b) -> a + b);

        Integer pontosRecebedor = itensRecebedor.stream()
                .map(item -> item.getPontos() * item.getQuantidade())
                .reduce(0, (a, b) -> a + b);

        if (pontosNegociante.compareTo(pontosRecebedor) != 0) {
            throw new IllegalArgumentException("Os pontos para negociação são inválidos");
        }
    }
}
