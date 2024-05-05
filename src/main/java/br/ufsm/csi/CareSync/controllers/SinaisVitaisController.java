package br.ufsm.csi.CareSync.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.ufsm.csi.CareSync.forms.SinaisForm;
import br.ufsm.csi.CareSync.models.SinaisVitais;
import br.ufsm.csi.CareSync.service.SinaisVitaisService;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("sinais")
public class SinaisVitaisController {

    @Autowired
    private  SinaisVitaisService sinaisVitaisService;

    @PostMapping("/registrar")
    public ResponseEntity<SinaisVitais> registrarSinais(@RequestBody @Valid SinaisForm sinaisForm) {
        return sinaisVitaisService.registrarSinais(sinaisForm);
    }

    @GetMapping("/paciente/{id}")
    public ResponseEntity<ArrayList<SinaisVitais>> listarSinaisPorPaciente(@PathVariable UUID id) {
        return sinaisVitaisService.listarSinaisPorPaciente(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SinaisVitais> buscarSinaisPorId(@PathVariable UUID id) {
        return sinaisVitaisService.buscarSinaisPorId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSinais(@PathVariable UUID id) {
        return sinaisVitaisService.deletarSinais(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SinaisVitais> atualizarSinais(@PathVariable UUID id, @RequestBody SinaisForm sinaisForm) {
        return sinaisVitaisService.atualizarSinais(id, sinaisForm);
    }
}
