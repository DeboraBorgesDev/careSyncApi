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

import br.ufsm.csi.CareSync.forms.HistoriaPatologicaForm;
import br.ufsm.csi.CareSync.service.HistoriaPatologicaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/historico-patologico")
public class HistoricoPatologicoController {
    @Autowired
    private HistoriaPatologicaService historiaPatologicaService;

    @PostMapping
    public ResponseEntity<?> criarHistoriaFisiologica(@RequestBody @Valid HistoriaPatologicaForm historiaPatologicaForm) {
        return historiaPatologicaService.criarHistoriaPatologica(historiaPatologicaForm);
    }

    @GetMapping("/paciente/{id}")
    public ResponseEntity<?> listarHistoriasByPacienteId(@PathVariable UUID id) {
        return historiaPatologicaService.getHistoriasPorIdPaciente(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getHistoriaById(@PathVariable UUID id) {
        return historiaPatologicaService.getHistoriaPatologicaPorId(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> editarHistoriaFisiologica(@RequestBody HistoriaPatologicaForm historiaFisiologica, @PathVariable UUID id) {
        return historiaPatologicaService.editarHistoriaPatologica(historiaFisiologica, id);
    }
    
}
