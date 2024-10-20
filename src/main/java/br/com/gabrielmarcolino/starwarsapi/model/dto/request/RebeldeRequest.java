package br.com.gabrielmarcolino.starwarsapi.model.dto.request;

import br.com.gabrielmarcolino.starwarsapi.model.*;
import br.com.gabrielmarcolino.starwarsapi.model.enums.GeneroEnum;

import java.util.Arrays;
import java.util.List;

public record RebeldeRequest(String nome, int idade, GeneroEnum genero, LocalizacaoRequest localizacao) {
    public Rebelde toRebelde() {
        Inventario inventario = new Inventario();
        List<Item> itens = gerarIventario();
        List<InventarioItem> inventarioItens = itens.stream()
                .map(item -> new InventarioItem(new InventarioItemId(), inventario, item, 1))
                .toList();
        inventario.setItens(inventarioItens);

        return new Rebelde(null,
                nome,
                idade,
                genero,
                localizacao.toLocalizacao(),
                inventario,
                0,
                false);
    }

    private List<Item> gerarIventario() {
        Item item1 = new Item(null, "Arma", 4);
        Item item2 = new Item(null, "Munição", 3);
        Item item3 = new Item(null, "Água", 2);
        Item item4 = new Item(null, "Comida", 1);

        return Arrays.asList(item1, item2, item3, item4);
    }
}
