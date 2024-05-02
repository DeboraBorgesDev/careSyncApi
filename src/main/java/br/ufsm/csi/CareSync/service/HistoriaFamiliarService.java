package br.ufsm.csi.CareSync.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.ufsm.csi.CareSync.exceptions.GenericException;
import br.ufsm.csi.CareSync.exceptions.NotFoundException;
import br.ufsm.csi.CareSync.forms.HistoriaFamiliarForm;
import br.ufsm.csi.CareSync.models.HistoriaFamiliar;
import br.ufsm.csi.CareSync.models.Paciente;
import br.ufsm.csi.CareSync.repository.HistoriaFamiliarRepository;
import br.ufsm.csi.CareSync.repository.PacienteRepository;
import jakarta.transaction.Transactional;

@Service
public class HistoriaFamiliarService {

      @Autowired
    private HistoriaFamiliarRepository historiaFamiliarRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public ResponseEntity<?> criarHistoriaFamiliar(HistoriaFamiliarForm historiaFamiliarForm) {
        try {
            if (historiaFamiliarRepository.existsByPacienteId(historiaFamiliarForm.getPacienteId())) {
                throw new NotFoundException("Este paciente já possui uma história familiar associada");
            }

            Paciente paciente = pacienteRepository.findById(historiaFamiliarForm.getPacienteId())
                    .orElseThrow(() -> new NotFoundException("Paciente não encontrado"));

            HistoriaFamiliar historiaFamiliar = new HistoriaFamiliar();
            historiaFamiliar.setPaciente(paciente);
            historiaFamiliar.setIdadeMortePai(historiaFamiliarForm.getIdadeMortePai());
            historiaFamiliar.setCausaMortePai(historiaFamiliarForm.getCausaMortePai());
            historiaFamiliar.setIdadeMorteMae(historiaFamiliarForm.getIdadeMorteMae());
            historiaFamiliar.setCausaMorteMae(historiaFamiliarForm.getCausaMorteMae());
            historiaFamiliar.setDoencasPai(historiaFamiliarForm.getDoencasPai());
            historiaFamiliar.setDoencasMae(historiaFamiliarForm.getDoencasMae());
            historiaFamiliar.setFilhosSaudaveis(historiaFamiliarForm.getFilhosSaudaveis());
            historiaFamiliar.setFilhosObservacoes(historiaFamiliarForm.getFilhosObservacoes());
            historiaFamiliar.setHistoricoSaudeParentes(historiaFamiliarForm.getHistoricoSaudeParentes());

            HistoriaFamiliar savedHistoriaFamiliar = historiaFamiliarRepository.save(historiaFamiliar);

            return ResponseEntity.ok(savedHistoriaFamiliar);
        } catch (Exception e) {
            throw new GenericException("Falha ao criar história familiar");
        }
    }

    @Transactional
    public ResponseEntity<?> editarHistoriaFamiliar(HistoriaFamiliarForm historiaFamiliarForm, UUID id) {
        try {
            Optional<HistoriaFamiliar> hOptional = historiaFamiliarRepository.findById(id);
            HistoriaFamiliar hf = hOptional.get();

            if (hOptional.isPresent()) {
                if(historiaFamiliarForm.getDoencasMae()  != null && !Objects.equals(hf.getCausaMortePai(), historiaFamiliarForm.getCausaMortePai())){
                    hf.setCausaMortePai(historiaFamiliarForm.getCausaMortePai());
                }

                if(historiaFamiliarForm.getDoencasPai() != null && !Objects.equals(historiaFamiliarForm.getDoencasPai(), hf.getDoencasPai())){
                    hf.setDoencasPai(historiaFamiliarForm.getDoencasPai());
                }

                if(historiaFamiliarForm.getIdadeMorteMae() != null && !Objects.equals(historiaFamiliarForm.getIdadeMortePai(), hf.getIdadeMortePai())){
                    hf.setIdadeMortePai(historiaFamiliarForm.getIdadeMortePai());
                }

                if(historiaFamiliarForm.getDoencasMae()  != null && !Objects.equals(hf.getCausaMorteMae(), historiaFamiliarForm.getCausaMorteMae())){
                    hf.setCausaMorteMae(historiaFamiliarForm.getCausaMorteMae());
                }

                if(historiaFamiliarForm.getDoencasMae() != null && !Objects.equals(historiaFamiliarForm.getDoencasMae(), hf.getDoencasMae())){
                    hf.setDoencasMae(historiaFamiliarForm.getDoencasMae());
                }

                if(historiaFamiliarForm.getIdadeMorteMae() != null && !Objects.equals(historiaFamiliarForm.getIdadeMorteMae(), hf.getIdadeMorteMae())){
                    hf.setIdadeMorteMae(historiaFamiliarForm.getIdadeMorteMae());
                }

                if(!Objects.equals(hf.getFilhosSaudaveis(), historiaFamiliarForm.getFilhosSaudaveis())){
                    hf.setFilhosSaudaveis(historiaFamiliarForm.getFilhosSaudaveis());
                }

                if(historiaFamiliarForm.getFilhosObservacoes() != null && !Objects.equals(hf.getFilhosObservacoes(), historiaFamiliarForm.getFilhosObservacoes())){
                    hf.setFilhosObservacoes(historiaFamiliarForm.getFilhosObservacoes());
                }

                if(historiaFamiliarForm.getHistoricoSaudeParentes() != null && !Objects.equals(hf.getHistoricoSaudeParentes(), historiaFamiliarForm.getHistoricoSaudeParentes())){
                    hf.setHistoricoSaudeParentes(historiaFamiliarForm.getHistoricoSaudeParentes());
                }

                HistoriaFamiliar savedHistoria = historiaFamiliarRepository.save(hf);
                return ResponseEntity.ok(savedHistoria);
            } else {
                throw new NotFoundException("História Familiar não encontrada.");
            }
        } catch (Exception e){
            throw new GenericException("Falha ao editar história");
        }
    }

     public ResponseEntity<List<HistoriaFamiliar>> listarHistoriasFamiliares() {
        try {
            List<HistoriaFamiliar> historias = historiaFamiliarRepository.findAll();
            return ResponseEntity.ok(historias);
        } catch (Exception e) {
            throw new GenericException("Falha ao listar as histórias familiares");
        }
    }

        public ResponseEntity<?> buscarHistoriaFamiliarPorId(UUID id) {
        try {
            Optional<HistoriaFamiliar> historiaOptional = historiaFamiliarRepository.findByIdPaciente(id);
            if (!historiaOptional.isPresent()) {
                throw new NotFoundException("História familiar não encontrada");
            } else {
                HistoriaFamiliar historia = historiaOptional.get();
                return ResponseEntity.ok(historia);
            }
        } catch (Exception e){
            throw new GenericException("Erro ao buscar história familar");
        }
    }
    
}
