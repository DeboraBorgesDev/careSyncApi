package br.ufsm.csi.CareSync.service;

import java.util.Optional;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.ufsm.csi.CareSync.exceptions.GenericException;
import br.ufsm.csi.CareSync.exceptions.NotFoundException;
import br.ufsm.csi.CareSync.forms.HistoriaPatologicaForm;
import br.ufsm.csi.CareSync.models.HistoricoPatologico;
import br.ufsm.csi.CareSync.models.Paciente;
import br.ufsm.csi.CareSync.repository.HistoricoPatologicoRepository;
import br.ufsm.csi.CareSync.repository.PacienteRepository;
import jakarta.transaction.Transactional;

@Service
public class HistoriaPatologicaService {

    @Autowired
    private HistoricoPatologicoRepository historiaPatologicaRepository;
    @Autowired
    private PacienteRepository pacienteRepository;


    public ResponseEntity<?> criarHistoriaPatologica(HistoriaPatologicaForm historiaPatologicaForm) {
        try {
            Optional<Paciente> pacienteOptional = pacienteRepository.findById(historiaPatologicaForm.getIdPaciente());
        if (!pacienteOptional.isPresent()) {
            throw new NotFoundException("Paciente não encontrado");
        } else {
            HistoricoPatologico historia = new HistoricoPatologico();
            historia.setPaciente(pacienteOptional.get());
            historia.setQueixaPrincipal(historiaPatologicaForm.getQueixaPrincipal());
            historia.setHistoriaDoencaAtual(historiaPatologicaForm.getHistoriaDoencaAtual());
            historia.setPossuiDiabetes(historiaPatologicaForm.getPossuiDiabetes());
            historia.setPossuiHipertensao(historiaPatologicaForm.getPossuiHipertensao());
            historia.setPossuiDislipidemia(historiaPatologicaForm.getPossuiDislipidemia());
            historia.setMedicacaoContinua(historiaPatologicaForm.getMedicacaoContinua());
            historia.setMedicacoes(historiaPatologicaForm.getMedicacoes());
            historia.setInternadoAnteriormente(historiaPatologicaForm.getInternadoAnteriormente());
            historia.setCirurgiasAnteriormente(historiaPatologicaForm.getCirurgiasAnteriormente());
            historia.setCirurgiasAnterioresObservacoes(historiaPatologicaForm.getCirurgiasAnterioresObservacoes());
            historia.setVacinas(historiaPatologicaForm.getVacinas());
            historia.setAlergias(historiaPatologicaForm.getAlergias());

            HistoricoPatologico savedHistoria = historiaPatologicaRepository.save(historia);
            return ResponseEntity.ok(savedHistoria);

        }
        } catch (Exception e) {
            throw new GenericException("Falha ao criar nova história");

        }
    }

    public ResponseEntity<?> getHistoriaPatologicaPorId(UUID id) {
        try {
            Optional<HistoricoPatologico> historiaOptional = historiaPatologicaRepository.findById(id);
            if (!historiaOptional.isPresent()) {
                throw new NotFoundException("História patológica não encontrada");
            } else {
                HistoricoPatologico historia = historiaOptional.get();
                return ResponseEntity.ok(historia);
            }
        } catch (Exception e) {
            throw new GenericException("Falha ao obter história patológica por ID");
        }
    }

    public ResponseEntity<?> getHistoriasPorIdPaciente(UUID idPaciente) {
        try {
            ArrayList<HistoricoPatologico> historias = historiaPatologicaRepository.findByIdPaciente(idPaciente);            if (historias.isEmpty()) {
                throw new NotFoundException("Não foram encontradas histórias para o paciente com ID: " + idPaciente);
            }
            return ResponseEntity.ok(historias);
        } catch (Exception e) {
            throw new GenericException("Falha ao obter histórias do paciente com ID: " + idPaciente);
        }
    }

    @Transactional
    public ResponseEntity<?> editarHistoriaPatologica(HistoriaPatologicaForm historiaPatologica, UUID id) {
        try {
            Optional<HistoricoPatologico> hOptional = historiaPatologicaRepository.findById(id);
            HistoricoPatologico hp = hOptional.orElseThrow(() -> new NotFoundException("História patológica não encontrada."));

            if (hOptional.isPresent()) {
                if (historiaPatologica.getQueixaPrincipal() != null && !Objects.equals(historiaPatologica.getQueixaPrincipal(), hp.getQueixaPrincipal())) {
                    hp.setQueixaPrincipal(historiaPatologica.getQueixaPrincipal());

                }

                if (historiaPatologica.getHistoriaDoencaAtual() != null && !Objects.equals(historiaPatologica.getHistoriaDoencaAtual(), hp.getHistoriaDoencaAtual())) {
                    hp.setHistoriaDoencaAtual(historiaPatologica.getHistoriaDoencaAtual());
                }

                if (!Objects.equals(historiaPatologica.getPossuiDiabetes(), hp.getPossuiDiabetes()) && historiaPatologica.getPossuiDiabetes() != null) {
                    hp.setPossuiDiabetes(historiaPatologica.getPossuiDiabetes());
                }

                if (!Objects.equals(historiaPatologica.getPossuiHipertensao(), hp.getPossuiHipertensao()) && historiaPatologica.getPossuiHipertensao() != null) {
                    hp.setPossuiHipertensao(historiaPatologica.getPossuiHipertensao());
                }

                if (!Objects.equals(historiaPatologica.getPossuiDislipidemia(), hp.getPossuiDislipidemia()) && historiaPatologica.getPossuiDislipidemia() != null) {
                    hp.setPossuiDislipidemia(historiaPatologica.getPossuiDislipidemia());
                }

                if (!Objects.equals(historiaPatologica.getMedicacaoContinua(), hp.getMedicacaoContinua()) && historiaPatologica.getMedicacaoContinua() != null) {
                    hp.setMedicacaoContinua(historiaPatologica.getMedicacaoContinua());
                }

                if (!Objects.equals(historiaPatologica.getMedicacoes(), hp.getMedicacoes())) {
                    hp.setMedicacoes(historiaPatologica.getMedicacoes());
                }

                if (!Objects.equals(historiaPatologica.getInternadoAnteriormente(), hp.getInternadoAnteriormente()) && historiaPatologica.getInternadoAnteriormente() != null) {
                    hp.setInternadoAnteriormente(historiaPatologica.getInternadoAnteriormente());
                }

                if (!Objects.equals(historiaPatologica.getCirurgiasAnteriormente(), hp.getCirurgiasAnteriormente()) && historiaPatologica.getCirurgiasAnteriormente() != null) {
                    hp.setCirurgiasAnteriormente(historiaPatologica.getCirurgiasAnteriormente());
                }

                if (!Objects.equals(historiaPatologica.getCirurgiasAnterioresObservacoes(), hp.getCirurgiasAnterioresObservacoes())) {
                    hp.setCirurgiasAnterioresObservacoes(historiaPatologica.getCirurgiasAnterioresObservacoes());
                }

                if (!Objects.equals(historiaPatologica.getVacinas(), hp.getVacinas())) {
                    hp.setVacinas(historiaPatologica.getVacinas());
                }

                if (!Objects.equals(historiaPatologica.getAlergias(), hp.getAlergias())) {
                    hp.setAlergias(historiaPatologica.getAlergias());
                }

                HistoricoPatologico savedHistoria = historiaPatologicaRepository.save(hp);
                return ResponseEntity.ok(savedHistoria);

            } else {
                throw new NotFoundException("História Patológica não encontrada");
            }
        } catch (Exception e) {
            throw new GenericException("");
        }
    }


    
}
