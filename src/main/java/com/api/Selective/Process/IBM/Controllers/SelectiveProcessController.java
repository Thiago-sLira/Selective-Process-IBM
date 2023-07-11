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
    private Segundo processoSeletivo;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/start")
    public ResponseEntity<Integer> iniciarProcesso(@RequestBody Candidato candidato) {
        String nome = candidato.getNome();

        try {
            int codCandidato = this.processoSeletivo.iniciarProcesso(nome);

            return new ResponseEntity<>(codCandidato, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/schedule")
    public ResponseEntity<String> marcarEntrevista(@RequestBody CodCandidato codCandidatoReq) {
        int codCandidato = codCandidatoReq.getCodCandidato();

        try {
            this.processoSeletivo.marcarEntrevista(codCandidato);

            return new ResponseEntity<>("Entrevista marcada para o candidato.", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/disqualify")
    public ResponseEntity<String> desqualificarCandidato(@RequestBody CodCandidato codCandidatoReq) {
        int codCandidato = codCandidatoReq.getCodCandidato();

        try {
            this.processoSeletivo.desqualificarCandidato(codCandidato);

            return new ResponseEntity<>("Candidato desqualificado com sucesso.", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/approve")
    public ResponseEntity<String> aprovarCandidato(@RequestBody CodCandidato codCandidatoReq) {
        int codCandidato = codCandidatoReq.getCodCandidato();

        try {
            this.processoSeletivo.aprovarCandidato(codCandidato);

            return new ResponseEntity<>("Candidato aprovado com sucesso.", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/status/candidate/{id}")
    public ResponseEntity<String> verificarStatusCandidato(@PathVariable("id") int codCandidato) {
        try {
            String candidatoStatus = this.processoSeletivo.verificarStatusCandidato(codCandidato);

            return new ResponseEntity<>(candidatoStatus, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/approved")    public ResponseEntity<?> obterAprovados() {
        try {
            List<String> candidatoAprovados = this.processoSeletivo.obterAprovados();

            return new ResponseEntity<>(candidatoAprovados, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
