package br.ufsm.csi.CareSync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.ufsm.csi.CareSync.exceptions.NotFoundException;
import br.ufsm.csi.CareSync.forms.SinaisForm;
import br.ufsm.csi.CareSync.models.Paciente;
import br.ufsm.csi.CareSync.models.SinaisVitais;
import br.ufsm.csi.CareSync.models.Usuario;
import br.ufsm.csi.CareSync.repository.PacienteRepository;
import br.ufsm.csi.CareSync.repository.SinaisVitaisRepository;
import br.ufsm.csi.CareSync.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class SinaisVitaisService {

    @Autowired
    private SinaisVitaisRepository sinaisVitaisRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseEntity<SinaisVitais> registrarSinais(SinaisForm sinaisForm) {
        try {
            Optional<Paciente> optionalPaciente = pacienteRepository.findById(sinaisForm.getIdPaciente());
            if (!optionalPaciente.isPresent()) {
                throw new NotFoundException("Paciente não encontrado com o ID fornecido");
            }
            Paciente paciente = optionalPaciente.get();

            Optional<Usuario> optionalProfissional = usuarioRepository.findById(sinaisForm.getIdProfissional());
            if (!optionalProfissional.isPresent()) {
                throw new NotFoundException("Profissional não encontrado com o ID fornecido");
            }
            Usuario profissional = optionalProfissional.get();

            SinaisVitais sinaisVitais = sinaisForm.toSinaisVitais(paciente, profissional);

            SinaisVitais savedSinais = sinaisVitaisRepository.save(sinaisVitais);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(savedSinais);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<ArrayList<SinaisVitais>> listarSinaisPorPaciente(UUID idPaciente) {
        try {
            ArrayList<SinaisVitais> sinais = sinaisVitaisRepository.findByIdPaciente(idPaciente);
            return ResponseEntity.ok(sinais);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<SinaisVitais> buscarSinaisPorId(UUID id) {
        try {
            Optional<SinaisVitais> optionalSinais = sinaisVitaisRepository.findById(id);
            if (!optionalSinais.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            SinaisVitais sinaisVitais = optionalSinais.get();
            return ResponseEntity.ok(sinaisVitais);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<Void> deletarSinais(UUID id) {
        try {
            sinaisVitaisRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<SinaisVitais> atualizarSinais(UUID id, SinaisForm sinaisForm) {
        try {
            Optional<SinaisVitais> optionalSinais = sinaisVitaisRepository.findById(id);
            if (!optionalSinais.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            SinaisVitais sinaisVitais = optionalSinais.get();

            sinaisVitais.atualizar(sinaisForm);

            SinaisVitais updatedSinais = sinaisVitaisRepository.save(sinaisVitais);

            return ResponseEntity.ok(updatedSinais);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
