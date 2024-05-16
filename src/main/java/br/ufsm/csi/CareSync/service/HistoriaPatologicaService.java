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
            HistoricoPatologico historia = historiaPatologicaForm.toHistoricoPatologico(pacienteOptional.get());

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
                atualizar(historiaPatologica, hp);

                HistoricoPatologico savedHistoria = historiaPatologicaRepository.save(hp);
                return ResponseEntity.ok(savedHistoria);

            } else {
                throw new NotFoundException("História Patológica não encontrada");
            }
        } catch (Exception e) {
            throw new GenericException("Falha ao editar história");
        }
    }

    private void atualizar(HistoriaPatologicaForm historiaPatologicaForm, HistoricoPatologico historicoPatologico) {
        if (historiaPatologicaForm.getQueixaPrincipal() != null && !Objects.equals(historiaPatologicaForm.getQueixaPrincipal(), historicoPatologico.getQueixaPrincipal())) {
            historicoPatologico.setQueixaPrincipal(historiaPatologicaForm.getQueixaPrincipal());
        }
    
        if (historiaPatologicaForm.getHistoriaDoencaAtual() != null && !Objects.equals(historiaPatologicaForm.getHistoriaDoencaAtual(), historicoPatologico.getHistoriaDoencaAtual())) {
            historicoPatologico.setHistoriaDoencaAtual(historiaPatologicaForm.getHistoriaDoencaAtual());
        }
    
        if (!Objects.equals(historiaPatologicaForm.getPossuiDiabetes(), historicoPatologico.getPossuiDiabetes())) {
            historicoPatologico.setPossuiDiabetes(historiaPatologicaForm.getPossuiDiabetes());
        }
    
        if (!Objects.equals(historiaPatologicaForm.getPossuiHipertensao(), historicoPatologico.getPossuiHipertensao())) {
            historicoPatologico.setPossuiHipertensao(historiaPatologicaForm.getPossuiHipertensao());
        }
    
        if ( !Objects.equals(historiaPatologicaForm.getPossuiDislipidemia(), historicoPatologico.getPossuiDislipidemia())) {
            historicoPatologico.setPossuiDislipidemia(historiaPatologicaForm.getPossuiDislipidemia());
        }
    
        if (!Objects.equals(historiaPatologicaForm.getMedicacaoContinua(), historicoPatologico.getMedicacaoContinua())) {
            historicoPatologico.setMedicacaoContinua(historiaPatologicaForm.getMedicacaoContinua());
        }
    
        if (historiaPatologicaForm.getMedicacoes() != null && !Objects.equals(historiaPatologicaForm.getMedicacoes(), historicoPatologico.getMedicacoes())) {
            historicoPatologico.setMedicacoes(historiaPatologicaForm.getMedicacoes());
        }
    
        if (!Objects.equals(historiaPatologicaForm.getInternadoAnteriormente(), historicoPatologico.getInternadoAnteriormente())) {
            historicoPatologico.setInternadoAnteriormente(historiaPatologicaForm.getInternadoAnteriormente());
        }
    
        if (!Objects.equals(historiaPatologicaForm.getCirurgiasAnteriormente(), historicoPatologico.getCirurgiasAnteriormente())) {
            historicoPatologico.setCirurgiasAnteriormente(historiaPatologicaForm.getCirurgiasAnteriormente());
        }
    
        if (historiaPatologicaForm.getCirurgiasAnterioresObservacoes() != null && !Objects.equals(historiaPatologicaForm.getCirurgiasAnterioresObservacoes(), historicoPatologico.getCirurgiasAnterioresObservacoes())) {
            historicoPatologico.setCirurgiasAnterioresObservacoes(historiaPatologicaForm.getCirurgiasAnterioresObservacoes());
        }
    
        if (historiaPatologicaForm.getVacinas() != null && !Objects.equals(historiaPatologicaForm.getVacinas(), historicoPatologico.getVacinas())) {
            historicoPatologico.setVacinas(historiaPatologicaForm.getVacinas());
        }
    
        if (historiaPatologicaForm.getAlergias() != null && !Objects.equals(historiaPatologicaForm.getAlergias(), historicoPatologico.getAlergias())) {
            historicoPatologico.setAlergias(historiaPatologicaForm.getAlergias());
        }
    } 
}
