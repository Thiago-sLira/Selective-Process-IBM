package com.api.Selective.Process.IBM.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CodCandidato {
    @JsonProperty("codCandidato")
    private int codCandidato;

    public int getCodCandidato() {
        return codCandidato;
    }

    public void setCodCandidato(int codCandidato) {
        this.codCandidato = codCandidato;
    }
}