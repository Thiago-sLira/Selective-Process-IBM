package com.api.Selective.Process.IBM.Models;

public class Candidato {
    private final String nome;
    private final int id;
    private String status = "Recebido";

    public  Candidato(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }
}
