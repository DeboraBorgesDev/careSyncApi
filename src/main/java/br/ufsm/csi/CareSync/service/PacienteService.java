package br.ufsm.csi.CareSync.service;

import br.ufsm.csi.CareSync.exceptions.CpfException;
import br.ufsm.csi.CareSync.exceptions.NotFoundException;
import br.ufsm.csi.CareSync.forms.PacienteForm;
import br.ufsm.csi.CareSync.models.Paciente;
import br.ufsm.csi.CareSync.repository.PacienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional
    public ResponseEntity<?> criarPaciente(PacienteForm pacienteForm, UriComponentsBuilder uriComponentsBuilder) {
        if (pacienteRepository.existsByCpf(pacienteForm.getCpf())) {
            throw new CpfException(pacienteForm.getCpf());
        }

        Paciente paciente = pacienteForm.toPaciente();

        try {
            Paciente savedPaciente = pacienteRepository.save(paciente);
            URI uri = uriComponentsBuilder.path("/paciente/{id}").buildAndExpand(savedPaciente.getId()).toUri();
            return ResponseEntity.created(uri).body(savedPaciente);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<List<Paciente>> listarPacientes() {
        try {
            List<Paciente> pacientes = pacienteRepository.findAll();
            return ResponseEntity.ok(pacientes);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<?> buscarPacientePorId(UUID id) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        if (pacienteOptional.isEmpty()) {
            throw new NotFoundException("Paciente não encontrado");
        }
        Paciente paciente = pacienteOptional.get();
        return ResponseEntity.ok(paciente);
    }

    @Transactional
    public ResponseEntity<?> editarPaciente(UUID id, PacienteForm pacienteForm) {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);
        if (optionalPaciente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Paciente paciente = optionalPaciente.get();
        paciente.atualizar(paciente, pacienteForm);

        try {
            Paciente savedPaciente = pacienteRepository.save(paciente);
            return ResponseEntity.ok(savedPaciente);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Falha ao editar paciente");
        }
    }

    @Transactional
    public ResponseEntity<?> deletarPaciente(UUID id) {
        try {
            if (!pacienteRepository.existsById(id)) {
                throw new NotFoundException("Paciente não encontrado");
            }
            pacienteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
