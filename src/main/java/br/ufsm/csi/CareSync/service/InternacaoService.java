package br.ufsm.csi.CareSync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import br.ufsm.csi.CareSync.models.Internacao;
import br.ufsm.csi.CareSync.exceptions.GenericException;
import br.ufsm.csi.CareSync.exceptions.NotFoundException;
import br.ufsm.csi.CareSync.forms.InternacaoForm;
import br.ufsm.csi.CareSync.models.Paciente;
import br.ufsm.csi.CareSync.models.Usuario;
import br.ufsm.csi.CareSync.repository.InternacaoRepository;
import br.ufsm.csi.CareSync.repository.PacienteRepository;
import br.ufsm.csi.CareSync.repository.UsuarioRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class InternacaoService {

    @Autowired
    private  InternacaoRepository internacaoRepository;
    @Autowired
    private  PacienteRepository pacienteRepository;
    @Autowired
    private  UsuarioRepository usuarioRepository;


    public ResponseEntity<Internacao> criarInternacao(InternacaoForm internacaoForm) {
        Paciente paciente = pacienteRepository.findById(internacaoForm.getIdPaciente())
                .orElseThrow(() -> new NotFoundException("Paciente não encontrado com o ID fornecido"));

        boolean hasActiveInternacao = internacaoRepository.existsByPacienteAndDataSaidaIsNull(paciente.getId());
        if (hasActiveInternacao) {
            throw new GenericException("O paciente já possui uma internação ativa");
        }

        Usuario medico = usuarioRepository.findById(internacaoForm.getIdMedico())
                .orElseThrow(() -> new NotFoundException("Médico não encontrado com o ID fornecido"));

        if(!medico.isMedico()){
            throw new GenericException("O usuário não pode criar uma nova internação. O usuário não é médico");
        }

        Internacao internacao = new Internacao();
        internacao.setPaciente(paciente);
        internacao.setDataEntrada(LocalDateTime.now());
        internacao.setMotivo(internacaoForm.getMotivo());
        internacao.setDiagnostico(internacaoForm.getDiagnostico());
        internacao.setObservacoes(internacaoForm.getObservacoes());
        internacao.setMedico(medico);

        return new ResponseEntity<>(internacaoRepository.save(internacao), HttpStatus.CREATED);
    }

    public ResponseEntity<Internacao> buscarInternacaoPorId(UUID id) {
        Internacao internacao = internacaoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Internação não encontrada com o ID fornecido"));
        return ResponseEntity.ok(internacao);
    }

    public ResponseEntity<List<Internacao>> listarInternacoes() {
        List<Internacao> internacoes = internacaoRepository.findAll();
        return ResponseEntity.ok(internacoes);
    }

    public ResponseEntity<List<Internacao>> listarInternacoesByPaciente(UUID id) {
        List<Internacao> internacoes = internacaoRepository.findByIdPaciente(id);
        return ResponseEntity.ok(internacoes);
    }

    public ResponseEntity<List<Internacao>> listarInternacoesByMedico(UUID id) {
        List<Internacao> internacoes = internacaoRepository.findByIdMedico(id);
        return ResponseEntity.ok(internacoes);
    }

    public ResponseEntity<Internacao> atualizarInternacao(UUID id, InternacaoForm internacaoForm) {
        Internacao internacao = internacaoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Internação não encontrada com o ID fornecido"));

                if (internacaoForm.getIdMedico() != null && !internacao.getMedico().getId().equals(internacaoForm.getIdMedico())) {
                    Usuario medico = usuarioRepository.findById(internacaoForm.getIdMedico())
                            .orElseThrow(() -> new NotFoundException("Médico não encontrado com o ID fornecido"));
                    internacao.setMedico(medico);
                }
                
                if (internacaoForm.getMotivo() != null && !internacao.getMotivo().equals(internacaoForm.getMotivo())) {
                    internacao.setMotivo(internacaoForm.getMotivo());
                }
                
                if (internacaoForm.getDiagnostico() != null && !internacao.getDiagnostico().equals(internacaoForm.getDiagnostico())) {
                    internacao.setDiagnostico(internacaoForm.getDiagnostico());
                }
                
                if (internacaoForm.getObservacoes() != null && !internacao.getObservacoes().equals(internacaoForm.getObservacoes())) {
                    internacao.setObservacoes(internacaoForm.getObservacoes());
                }
                

        return ResponseEntity.ok(internacaoRepository.save(internacao));
    }

    public ResponseEntity<Void> deletarInternacao(UUID id) {
        internacaoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> darAlta(UUID id) {
        try {
            Internacao internacao = internacaoRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Internação não encontrada com o ID fornecido"));
    
            internacao.setDataSaida(LocalDateTime.now());
            internacaoRepository.save(internacao);
    
            return ResponseEntity.ok(internacao);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
