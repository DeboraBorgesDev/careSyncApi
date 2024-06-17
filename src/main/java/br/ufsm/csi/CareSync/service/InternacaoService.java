package br.ufsm.csi.CareSync.service;

import br.ufsm.csi.CareSync.forms.InternacaoForm;
import br.ufsm.csi.CareSync.models.Internacao;
import br.ufsm.csi.CareSync.models.Paciente;
import br.ufsm.csi.CareSync.models.Usuario;
import br.ufsm.csi.CareSync.repository.InternacaoRepository;
import br.ufsm.csi.CareSync.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class InternacaoService {

    @Autowired
    private InternacaoRepository internacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseEntity<?> criarInternacao(InternacaoForm internacaoForm) {
        try {
            Internacao internacao = toInternacao(internacaoForm);

            if (internacao.getMedico() != null && !internacao.getMedico().isMedico()) {
                return ResponseEntity.badRequest().body("O usuário não é um médico.");
            }

            boolean pacientePossuiInternacaoAtiva = internacaoRepository.existsByPacienteAndDataSaidaIsNull(internacao.getPaciente().getId());
            if (pacientePossuiInternacaoAtiva) {
                return ResponseEntity.badRequest().body("O paciente já possui uma internação ativa.");
            }

            internacaoRepository.save(internacao);

            URI location = URI.create("/internacoes/" + internacao.getId());
            return ResponseEntity.created(location).body(internacao);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao criar internação");
        }
    }

    public ResponseEntity<Internacao> buscarInternacaoPorId(UUID id) {
        try {
            return internacaoRepository.findById(id)
                    .map(internacao -> ResponseEntity.ok().body(internacao))
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<List<Internacao>> listarInternacoes() {
        try {
            List<Internacao> internacoes = internacaoRepository.findAll();
            return ResponseEntity.ok().body(internacoes);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<List<Internacao>> listarInternacoesByPaciente(UUID id) {
        try {
            List<Internacao> internacoes = internacaoRepository.findByIdPaciente(id);
            return ResponseEntity.ok().body(internacoes);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<List<Internacao>> listarInternacoesByMedico(UUID id) {
        try {
            List<Internacao> internacoes = internacaoRepository.findByIdMedico(id);
            return ResponseEntity.ok().body(internacoes);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<?> atualizarInternacao(UUID id, InternacaoForm internacaoForm) {
        try {
            return internacaoRepository.findById(id)
                    .map(internacao -> {
                        atualizar(internacao, internacaoForm);
                        internacaoRepository.save(internacao);
                        return ResponseEntity.ok(internacao);
                    })
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao atualizar internação");
        }
    }

    public ResponseEntity<Void> deletarInternacao(UUID id) {
        try {
            if (internacaoRepository.existsById(id)) {
                internacaoRepository.deleteById(id);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<?> darAlta(UUID id) {
        try {
            return internacaoRepository.findById(id)
                    .map(internacao -> {
                        internacao.setDataSaida(LocalDateTime.now());
                        internacaoRepository.save(internacao);
                        return ResponseEntity.ok().body(internacao);
                    })
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    private Internacao toInternacao(InternacaoForm form) {
        Internacao internacao = new Internacao();
        internacao.setId(UUID.randomUUID());
        internacao.setDataEntrada(LocalDateTime.now());
        internacao.setMotivo(form.getMotivo());
        internacao.setDiagnostico(form.getDiagnostico());
        internacao.setObservacoes(form.getObservacoes());

        if (form.getIdPaciente() != null) {
            Paciente paciente = new Paciente();
            paciente.setId(form.getIdPaciente());
            internacao.setPaciente(paciente);
        }

        if (form.getIdMedico() != null) {
            Usuario medico = usuarioRepository.findById(form.getIdMedico()).orElse(null);
            internacao.setMedico(medico);
        }

        return internacao;
    }

    private void atualizar(Internacao internacao, InternacaoForm form) {
        if (form.getMotivo() != null) {
            internacao.setMotivo(form.getMotivo());
        }
        if (form.getDiagnostico() != null) {
            internacao.setDiagnostico(form.getDiagnostico());
        }
        if (form.getObservacoes() != null) {
            internacao.setObservacoes(form.getObservacoes());
        }
        if (form.getIdMedico() != null) {
            Usuario medico = usuarioRepository.findById(form.getIdMedico()).orElse(null);
            internacao.setMedico(medico);
        }
    }
}
