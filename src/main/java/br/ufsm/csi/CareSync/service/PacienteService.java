package br.ufsm.csi.CareSync.service;

import br.ufsm.csi.CareSync.forms.PacienteForm;
import br.ufsm.csi.CareSync.exceptions.CpfException;
import br.ufsm.csi.CareSync.exceptions.NotFoundException;
import br.ufsm.csi.CareSync.models.Paciente;
import br.ufsm.csi.CareSync.repository.PacienteRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
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

        Paciente paciente = new Paciente(
                UUID.randomUUID(), // Gera um UUID único para o paciente
                pacienteForm.getNome(),
                pacienteForm.getDataNascimento(),
                pacienteForm.getCpf(),
                pacienteForm.getSexo(),
                pacienteForm.getEstadoCivil(),
                pacienteForm.getPossuiFilhos(),
                pacienteForm.getProfissao(),
                pacienteForm.getReligiao(),
                pacienteForm.getNivelEnsino()
        );

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
        if (!pacienteOptional.isPresent()) {
            throw new NotFoundException("Paciente não encontrado");
        } else {
            Paciente paciente = pacienteOptional.get();
            return ResponseEntity.ok(paciente);
        }
    }

    public ResponseEntity<?> editarPaciente(UUID id, PacienteForm pacienteForm) {

        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);

        if (optionalPaciente.isPresent()) {
            Paciente paciente = optionalPaciente.get();

            if (pacienteForm.getNome() != null && !Objects.equals(paciente.getNome(), pacienteForm.getNome())) {
                paciente.setNome(pacienteForm.getNome());
            }
            if (pacienteForm.getDataNascimento() != null && !Objects.equals(paciente.getDataNascimento(), pacienteForm.getDataNascimento())) {
                paciente.setDataNascimento(pacienteForm.getDataNascimento());
            }
            if (pacienteForm.getCpf() != null && !Objects.equals(paciente.getCpf(), pacienteForm.getCpf())) {
                if (pacienteRepository.existsByCpf(pacienteForm.getCpf())) {
                    throw new CpfException(pacienteForm.getCpf());
                } else {
                    paciente.setCpf(pacienteForm.getCpf());
                }
            }
            if (pacienteForm.getSexo() != null && !Objects.equals(paciente.getSexo(), pacienteForm.getSexo())) {
                paciente.setSexo(pacienteForm.getSexo());
            }
            if (pacienteForm.getEstadoCivil() != null && !Objects.equals(paciente.getEstadoCivil(), pacienteForm.getEstadoCivil())) {
                paciente.setEstadoCivil(pacienteForm.getEstadoCivil());
            }
            if (pacienteForm.getPossuiFilhos() != null && !Objects.equals(paciente.getPossuiFilhos(), pacienteForm.getPossuiFilhos())) {
                paciente.setPossuiFilhos(pacienteForm.getPossuiFilhos());
            }
            if (pacienteForm.getProfissao() != null && !Objects.equals(paciente.getProfissao(), pacienteForm.getProfissao())) {
                paciente.setProfissao(pacienteForm.getProfissao());
            }
            if (pacienteForm.getReligiao() != null && !Objects.equals(paciente.getReligiao(), pacienteForm.getReligiao())) {
                paciente.setReligiao(pacienteForm.getReligiao());
            }
            if (pacienteForm.getNivelEnsino() != null && !Objects.equals(paciente.getNivelEnsino(), pacienteForm.getNivelEnsino())) {
                paciente.setNivelEnsino(pacienteForm.getNivelEnsino());
            }

            try {
                Paciente savedPaciente = pacienteRepository.save(paciente);
                return ResponseEntity.ok(savedPaciente);
            } catch (Exception e) {
                return ResponseEntity.internalServerError().body("Falha ao editar paciente");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

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
