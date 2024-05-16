package br.ufsm.csi.CareSync.service;

import br.ufsm.csi.CareSync.exceptions.GenericException;
import br.ufsm.csi.CareSync.exceptions.NotFoundException;
import br.ufsm.csi.CareSync.forms.HistoriafisiologicaForm;
import br.ufsm.csi.CareSync.models.HistoriaFisiologica;
import br.ufsm.csi.CareSync.models.Paciente;
import br.ufsm.csi.CareSync.repository.HistoriaFisiologicaRepository;
import br.ufsm.csi.CareSync.repository.PacienteRepository;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
public class HistoriaFisiologicaService {

    @Autowired
    private HistoriaFisiologicaRepository historiaFisiologicaRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    public ResponseEntity<?> criarHistoriaFisiologica(HistoriafisiologicaForm historiaFisiologica) {
        try {
            if (historiaFisiologicaRepository.existsByPacienteId(historiaFisiologica.getPacienteId())) {
                throw new NotFoundException("Este paciente já possui uma história fisiológica associada");
            }

            Paciente paciente = pacienteRepository.findById(historiaFisiologica.getPacienteId()).get();
            HistoriaFisiologica historia = historiaFisiologica.toHistoriaFisiologica(paciente);
    
            HistoriaFisiologica savedHistoria = historiaFisiologicaRepository.save(historia);
            return ResponseEntity.ok(savedHistoria);
        } catch (Exception e) {
            throw new GenericException("Falha ao criar nova história");

        }
    }

    @Transactional
    public ResponseEntity<?> editarHistoriaFisiologica(HistoriafisiologicaForm historiaFisiologica, UUID id) {
        try {
            Optional<HistoriaFisiologica> hOptional = historiaFisiologicaRepository.findById(id);
            HistoriaFisiologica hf = hOptional.get();

            if (hOptional.isPresent()) {
                hf.atualizar(historiaFisiologica);
                HistoriaFisiologica savedHistoria = historiaFisiologicaRepository.save(hf);
                return ResponseEntity.ok(savedHistoria);
            } else {
                throw new NotFoundException("História fisiologica não encontrada.");
            }
        } catch (Exception e){
            throw new GenericException("Falha ao editar história");
        }
    }

    public ResponseEntity<List<HistoriaFisiologica>> listarHistoriasFisiologicas() {
        try {
            List<HistoriaFisiologica> historias = historiaFisiologicaRepository.findAll();
            return ResponseEntity.ok(historias);
        } catch (Exception e) {
            throw new GenericException("Falha ao listar as histórias fisiológicas");
        }
    }

    public ResponseEntity<?> buscarHistoriaFisiologicaPorId(UUID id) {
        try {
            Optional<HistoriaFisiologica> historiaOptional = historiaFisiologicaRepository.findByIdPaciente(id);
            if (!historiaOptional.isPresent()) {
                throw new NotFoundException("História fisiológica não encontrada");
            } else {
                HistoriaFisiologica historia = historiaOptional.get();
                return ResponseEntity.ok(historia);
            }
        } catch (Exception e){
            throw new GenericException("Erro ao buscar história fisiológica");
        }
    }

    public ResponseEntity<?> deletarHistoriaFisiologica(UUID id) {
        try {
            if (!historiaFisiologicaRepository.existsById(id)) {
                throw new NotFoundException("História fisiológica não encontrada");
            }
            historiaFisiologicaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new GenericException("Falha ao deletar história");
        }
    }
}