package br.com.gabrielmarcolino.starwarsapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "inventario_item")
public class InventarioItem {
    @EmbeddedId
    private InventarioItemId id;

    @ManyToOne
    @MapsId("inventarioId")
    @JoinColumn(name = "inventario_id")
    private Inventario inventario;

    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "quantidade_item")
    private Integer quantidadeItem;

    @Override
    public String toString() {
        return "InventarioItem{" +
                "id=" + id +
                ", item=" + item +  // Evita referenciar 'inventario' diretamente
                ", quantidadeItem=" + quantidadeItem +
                '}';
    }
}
