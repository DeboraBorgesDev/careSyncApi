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
            Paciente paciente = pacienteRepository.findById(sinaisForm.getIdPaciente())
                    .orElseThrow(() -> new NotFoundException("Paciente não encontrado com o ID fornecido"));

            Usuario profissional = usuarioRepository.findById(sinaisForm.getIdProfissional())
                    .orElseThrow(() -> new NotFoundException("Profissional não encontrado com o ID fornecido"));

            SinaisVitais sinaisVitais = new SinaisVitais();
            sinaisVitais.setPaciente(paciente);
            sinaisVitais.setProfissional(profissional);
            sinaisVitais.setFreqCardiaca(sinaisForm.getFreqCardiaca());
            sinaisVitais.setFreqRespiratoria(sinaisForm.getFreqRespiratoria());
            sinaisVitais.setPressaoArterial(sinaisForm.getPressaoArterial());
            sinaisVitais.setConstipacao(sinaisForm.getConstipacao());
            sinaisVitais.setGlicemia(sinaisForm.getGlicemia());
            sinaisVitais.setTemperatura(sinaisForm.getTemperatura());
            sinaisVitais.setOxigenacao(sinaisForm.getOxigenacao());
            sinaisVitais.setPeso(sinaisForm.getPeso());
            sinaisVitais.setMobilidade(sinaisForm.getMobilidade());
            sinaisVitais.setObservacoes(sinaisForm.getObservacoes());

            SinaisVitais savedSinais = sinaisVitaisRepository.save(sinaisVitais);
            return new ResponseEntity<>(savedSinais, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ArrayList<SinaisVitais>> listarSinaisPorPaciente(UUID idPaciente) {
        try {
            ArrayList<SinaisVitais> sinais = sinaisVitaisRepository.findByIdPaciente(idPaciente);
            return new ResponseEntity<>(sinais, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<SinaisVitais> buscarSinaisPorId(UUID id) {
        try {
            SinaisVitais sinaisVitais = sinaisVitaisRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Sinais vitais não encontrados com o ID fornecido"));
            return new ResponseEntity<>(sinaisVitais, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> deletarSinais(UUID id) {
        try {
            sinaisVitaisRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<SinaisVitais> atualizarSinais(UUID id, SinaisForm sinaisForm) {
        try {
            SinaisVitais sinaisVitais = sinaisVitaisRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Sinais vitais não encontrados com o ID fornecido"));

            if (sinaisForm.getFreqCardiaca() != null) {
                sinaisVitais.setFreqCardiaca(sinaisForm.getFreqCardiaca());
            }

            if (sinaisForm.getFreqRespiratoria() != null) {
                sinaisVitais.setFreqRespiratoria(sinaisForm.getFreqRespiratoria());
            }

            if (sinaisForm.getPressaoArterial() != null) {
                sinaisVitais.setPressaoArterial(sinaisForm.getPressaoArterial());
            }

            if (sinaisForm.getConstipacao() != null) {
                sinaisVitais.setConstipacao(sinaisForm.getConstipacao());
            }

            if (sinaisForm.getGlicemia() != null) {
                sinaisVitais.setGlicemia(sinaisForm.getGlicemia());
            }

            if (sinaisForm.getTemperatura() != null) {
                sinaisVitais.setTemperatura(sinaisForm.getTemperatura());
            }

            if (sinaisForm.getOxigenacao() != null) {
                sinaisVitais.setOxigenacao(sinaisForm.getOxigenacao());
            }

            if (sinaisForm.getPeso() != null) {
                sinaisVitais.setPeso(sinaisForm.getPeso());
            }

            if (sinaisForm.getMobilidade() != null) {
                sinaisVitais.setMobilidade(sinaisForm.getMobilidade());
            }

            if (sinaisForm.getObservacoes() != null) {
                sinaisVitais.setObservacoes(sinaisForm.getObservacoes());
            }

            SinaisVitais updatedSinais = sinaisVitaisRepository.save(sinaisVitais);
            return new ResponseEntity<>(updatedSinais, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
