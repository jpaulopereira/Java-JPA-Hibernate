package com.jotape.hibernate.modelo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "categorias")
public class Categoria {

    @EmbeddedId
    private CategoriaId id;

    public Categoria() {
    }

    public Categoria(String nome) {
        this.id = new CategoriaId(nome, "tipo");
    }

    public String getNome() {
        return this.id.getNome();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(id, categoria.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
