package br.com.gabrielmarcolino.starwarsapi.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class InventarioItemId implements Serializable {
    private Long inventarioId;
    private Long itemId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InventarioItemId that)) return false;
        return Objects.equals(inventarioId, that.inventarioId) && Objects.equals(itemId, that.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventarioId, itemId);
    }
}
