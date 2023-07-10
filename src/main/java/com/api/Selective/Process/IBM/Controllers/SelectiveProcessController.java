package com.api.Selective.Process.IBM.Controllers;

import com.api.Selective.Process.IBM.Models.Candidato;
import com.api.Selective.Process.IBM.Models.CodCandidato;
import com.api.Selective.Process.IBM.Models.Segundo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hiring")
public class SelectiveProcessController {

    @Autowired
    private Segundo selectiveProcess;

    @PostMapping("/start")
    public ResponseEntity<Integer> iniciarProcesso(@RequestBody Candidato candidato) {
        String nome = candidato.getNome();

        try {
            int codCandidato = this.selectiveProcess.iniciarProcesso(nome);

            return new ResponseEntity<>(codCandidato, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/schedule")
    public ResponseEntity<String> marcarEntrevista(@RequestBody CodCandidato codCandidatoReq) {
        int codCandidato = codCandidatoReq.getCodCandidato();

        try {
            this.selectiveProcess.marcarEntrevista(codCandidato);

            return new ResponseEntity<>("Entrevista marcada para o candidato.", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/disqualify")
    public ResponseEntity<String> desqualificarCandidato(@RequestBody CodCandidato codCandidatoReq) {
        int codCandidato = codCandidatoReq.getCodCandidato();

        try {
            this.selectiveProcess.desqualificarCandidato(codCandidato);

            return new ResponseEntity<>("Candidato desqualificado com sucesso.", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/approve")
    public ResponseEntity<String> aprovarCandidato(@RequestBody CodCandidato codCandidatoReq) {
        int codCandidato = codCandidatoReq.getCodCandidato();

        try {
            this.selectiveProcess.aprovarCandidato(codCandidato);

            return new ResponseEntity<>("Candidato aprovado com sucesso.", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/status/candidate/{id}")
    public ResponseEntity<String> verificarStatusCandidato(@PathVariable("id") int codCandidato) {
        try {
            String candidatoStatus = this.selectiveProcess.verificarStatusCandidato(codCandidato);

            return new ResponseEntity<>(candidatoStatus, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/approved")    public ResponseEntity<?> obterAprovados() {
        try {
            List<String> candidatoAprovados = this.selectiveProcess.obterAprovados();

            return new ResponseEntity<>(candidatoAprovados, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}