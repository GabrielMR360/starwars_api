package br.com.gabrielmarcolino.starwarsapi.service;

import br.com.gabrielmarcolino.starwarsapi.exception.BaseException;
import br.com.gabrielmarcolino.starwarsapi.exception.enums.ErroEnum;
import br.com.gabrielmarcolino.starwarsapi.model.InventarioItem;
import br.com.gabrielmarcolino.starwarsapi.model.Localizacao;
import br.com.gabrielmarcolino.starwarsapi.model.Rebelde;
import br.com.gabrielmarcolino.starwarsapi.model.dto.request.InventarioItemRequest;
import br.com.gabrielmarcolino.starwarsapi.model.dto.request.LocalizacaoRequest;
import br.com.gabrielmarcolino.starwarsapi.model.dto.request.NegociacaoRequest;
import br.com.gabrielmarcolino.starwarsapi.model.dto.request.RebeldeRequest;
import br.com.gabrielmarcolino.starwarsapi.repository.RebeldeRepository;
import br.com.gabrielmarcolino.starwarsapi.validation.item.ItemValidador;
import br.com.gabrielmarcolino.starwarsapi.validation.rebelde.RebeldeValidador;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RebeldeService {
    private final RebeldeRepository rebeldeRepository;
    private final List<RebeldeValidador> rebeldeValidador;
    private final List<ItemValidador> itemValidador;
    private final InventarioService inventarioService;

    public Rebelde salvar(RebeldeRequest rebeldeRequest) {
        return rebeldeRepository.save(rebeldeRequest.toRebelde());
    }

    public Rebelde buscarPorId(Long id) {
        return rebeldeRepository.findById(id).orElseThrow(() -> new BaseException(ErroEnum.REBELDE_NAO_ENCONTRADO));
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

        rebeldeValidador.forEach(validador -> validador.validar(rebeldeNegociante, rebeldeRecebedor));

        List<InventarioItem> itensRebeldeNegociante = rebeldeNegociante.getInventario().getItens();
        List<InventarioItem> itensRebeldeRecebedor = rebeldeRecebedor.getInventario().getItens();

        List<InventarioItemRequest> itensNegociante = negociacaoRequest.itensNegociante();
        List<InventarioItemRequest> itensRecebedor = negociacaoRequest.itensRecebedor();

        itemValidador.forEach(validador -> {
            validador.validar(itensNegociante, itensRecebedor);
            validador.validar(itensRebeldeNegociante, itensRebeldeRecebedor, itensNegociante);
            validador.validar(itensRebeldeRecebedor, itensRebeldeNegociante, itensRecebedor);
        });

        inventarioService.trocarItens(itensRebeldeNegociante, itensRebeldeRecebedor, negociacaoRequest);
        rebeldeRepository.save(rebeldeNegociante);
        rebeldeRepository.save(rebeldeRecebedor);
    }

    public List<Rebelde> findAll() {
        return rebeldeRepository.findAll();
    }
}
