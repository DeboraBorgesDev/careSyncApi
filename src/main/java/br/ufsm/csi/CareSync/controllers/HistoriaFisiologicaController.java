package br.ufsm.csi.CareSync.controllers;

import br.ufsm.csi.CareSync.forms.HistoriafisiologicaForm;
import br.ufsm.csi.CareSync.models.HistoriaFisiologica;
import br.ufsm.csi.CareSync.service.HistoriaFisiologicaService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/historia-fisiologica")
public class HistoriaFisiologicaController {

    @Autowired
    private HistoriaFisiologicaService historiaFisiologicaService;

    @PostMapping
    public ResponseEntity<?> criarHistoriaFisiologica(@RequestBody @Valid HistoriafisiologicaForm historiaFisiologica) {
        return historiaFisiologicaService.criarHistoriaFisiologica(historiaFisiologica);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarHistoriaFisiologica(@RequestBody HistoriafisiologicaForm historiaFisiologica, @PathVariable UUID id) {
        return historiaFisiologicaService.editarHistoriaFisiologica(historiaFisiologica, id);
    }

    @GetMapping
    public ResponseEntity<List<HistoriaFisiologica>> listarHistoriasFisiologicas() {
        return historiaFisiologicaService.listarHistoriasFisiologicas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarHistoriaFisiologicaPorId(@PathVariable UUID id) {
        return historiaFisiologicaService.buscarHistoriaFisiologicaPorId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarHistoriaFisiologica(@PathVariable UUID id) {
        return historiaFisiologicaService.deletarHistoriaFisiologica(id);
    }
}
