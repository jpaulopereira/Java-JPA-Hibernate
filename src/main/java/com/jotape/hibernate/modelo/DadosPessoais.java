package com.jotape.hibernate.modelo;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class DadosPessoais implements Serializable {

    private String nome;
    private String cpf;

    public DadosPessoais() {
    }

    public DadosPessoais(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
