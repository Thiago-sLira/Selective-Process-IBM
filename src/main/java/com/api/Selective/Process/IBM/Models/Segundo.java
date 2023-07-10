package com.api.Selective.Process.IBM.Models;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Segundo {

    private int candidatosRegistrados;
    private final List<Candidato> listaDeCandidatos;
    private final List<String> candidatosAprovados;
    public Segundo() {
        candidatosRegistrados = 0;
        listaDeCandidatos = new ArrayList<>();
        candidatosAprovados = new ArrayList<>();
    }

    public int iniciarProcesso(String nome) throws Exception {
        for (Candidato candidato : this.listaDeCandidatos) {
            if (candidato.getNome().equals(nome)) {
                throw new Exception("Candidato já participa do processo.");
            }
        }

        Candidato novoCandidato = new Candidato(nome, candidatosRegistrados);
        listaDeCandidatos.add(novoCandidato);
        this.candidatosRegistrados += 1;
        return novoCandidato.getId();
    }

    public void marcarEntrevista(int codCandidato) throws Exception {
        boolean candidatoEncontrado = false;

        for (Candidato candidato : this.listaDeCandidatos) {
            if (candidato.getId() == codCandidato) {
                if (candidato.getStatus().equals("Recebido")) {
                    candidato.setStatus("Qualificado");
                    candidatoEncontrado = true;
                    break;
                } else if (candidato.getStatus().equals("Qualificado")) {
                    throw new Exception("Candidato já tem entrevista marcada!");
                } else {
                    throw new Exception("Candidato já está aprovado!");
                }

            }
        }

        if (!candidatoEncontrado) {
            throw new Exception("Candidato não encontrado");
        }
    }

    public void desqualificarCandidato(int codCandidato) throws Exception {
        boolean candidatoEncontrado = false;
        Candidato candidatoRemovido = null;

        for (int i = 0; i < listaDeCandidatos.size(); i++) {
            if (listaDeCandidatos.get(i).getId() == codCandidato) {
                candidatoRemovido = listaDeCandidatos.get(i);
                listaDeCandidatos.remove(i);
                this.candidatosAprovados.remove(candidatoRemovido.getNome());
                candidatoEncontrado = true;
                break;
            }
        }

        if (!candidatoEncontrado) {
            throw new Exception("Candidato não encontrado");
        }
    }

    public String verificarStatusCandidato(int codCandidato) throws Exception {
        boolean candidatoEncontrado = false;
        String statusDoCandidato = "";

        for (Candidato candidato : this.listaDeCandidatos) {
            if (candidato.getId() == codCandidato) {
                statusDoCandidato = candidato.getStatus();
                candidatoEncontrado = true;
                break;
            }
        }

        if (!candidatoEncontrado) {
            throw new Exception("Candidato não encontrado");
        }

        return statusDoCandidato;
    }

    public void aprovarCandidato(int codCandidato) throws Exception {
        boolean candidatoEncontrado = false;

        for (Candidato candidato : this.listaDeCandidatos) {
            if (candidato.getId() == codCandidato) {
                if (this.candidatosAprovados.contains(candidato.getNome())) {
                    throw new Exception("Candidato já está aprovado!");
                } else if (!candidato.getStatus().equals("Qualificado")) {
                    throw new Exception("Candidato não qualificado para aprovação!");
                } else {
                    candidato.setStatus("Aprovado");
                    candidatoEncontrado = true;
                    this.candidatosAprovados.add(candidato.getNome());
                    break;
                }
            }
        }

        if (!candidatoEncontrado) {
            throw new Exception("Candidato não encontrado");
        }
    }

    public List<String> obterAprovados() {
        return this.candidatosAprovados;
    }
}
