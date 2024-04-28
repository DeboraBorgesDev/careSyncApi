package br.ufsm.csi.CareSync.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufsm.csi.CareSync.forms.HistoriaFamiliarForm;
import br.ufsm.csi.CareSync.service.HistoriaFamiliarService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/historia-familiar")
public class HistoriaFamiliarController {
    @Autowired
    private HistoriaFamiliarService historiaFamiliarService;

    @PostMapping
    public ResponseEntity<?> criarHistoriaFisiologica(@RequestBody @Valid HistoriaFamiliarForm historiaFamiliarForm) {
        return historiaFamiliarService.criarHistoriaFamiliar(historiaFamiliarForm);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarHistoriaFisiologica(@RequestBody HistoriaFamiliarForm historiaFamiliarForm, @PathVariable UUID id) {
        return historiaFamiliarService.editarHistoriaFamiliar(historiaFamiliarForm, id);
    }

    @GetMapping
    public ResponseEntity<?> listarHistorias() {
        return historiaFamiliarService.listarHistoriasFamiliares();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarHistoriaById(@PathVariable UUID id) {
        return historiaFamiliarService.buscarHistoriaFamiliarPorId(id);
    }
}
