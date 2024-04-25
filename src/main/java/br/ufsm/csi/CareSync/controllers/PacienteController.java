package br.ufsm.csi.CareSync.controllers;


import br.ufsm.csi.CareSync.forms.PacienteForm;
import br.ufsm.csi.CareSync.models.Paciente;
import br.ufsm.csi.CareSync.service.PacienteService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<?> criarPaciente(@Valid @RequestBody PacienteForm pacienteDTO, UriComponentsBuilder uri) {
        return pacienteService.criarPaciente(pacienteDTO, uri);
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes() {
        return pacienteService.listarPacientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPacientePorId(@PathVariable UUID id) {
        return pacienteService.buscarPacientePorId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarPaciente(@PathVariable UUID id, @RequestBody PacienteForm pacienteDTO) {
        return pacienteService.editarPaciente(id, pacienteDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPaciente(@PathVariable UUID id) {
        return pacienteService.deletarPaciente(id);
    }
}
