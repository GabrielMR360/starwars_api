package br.com.gabrielmarcolino.starwarsapi.model.dto.request;

import br.com.gabrielmarcolino.starwarsapi.model.Inventario;
import br.com.gabrielmarcolino.starwarsapi.model.Item;
import br.com.gabrielmarcolino.starwarsapi.model.Rebelde;
import br.com.gabrielmarcolino.starwarsapi.model.enums.GeneroEnum;

import java.util.Arrays;
import java.util.List;

public record RebeldeRequest(String nome, int idade, GeneroEnum genero, LocalizacaoRequest localizacao) {
    public Rebelde toRebelde() {
        return new Rebelde(null,
                nome,
                idade,
                genero,
                localizacao.toLocalizacao(),
                new Inventario(null, gerarIventario()));
    }

    private List<Item> gerarIventario() {
        Item item1 = new Item(null, "Arma", 1, 4);
        Item item2 = new Item(null, "Munição", 100, 3);
        Item item3 = new Item(null, "Água", 5, 2);
        Item item4 = new Item(null, "Comida", 10, 1);

        return Arrays.asList(item1, item2, item3, item4);
    }
}
