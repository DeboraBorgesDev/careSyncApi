package br.ufsm.csi.CareSync.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufsm.csi.CareSync.forms.InternacaoForm;
import br.ufsm.csi.CareSync.models.Internacao;
import br.ufsm.csi.CareSync.service.InternacaoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/internacao")
public class InternacaoController {
  

    @Autowired
    private InternacaoService internacaoService;

    @PostMapping("/criar")
    public ResponseEntity<?> criarInternacao(@RequestBody @Valid InternacaoForm internacaoForm) {
        return internacaoService.criarInternacao(internacaoForm);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarInternacaoPorId(@PathVariable("id") UUID id) {
        return internacaoService.buscarInternacaoPorId(id);

    }

    @GetMapping
    public ResponseEntity<List<Internacao>> listarInternacoes() {
       return internacaoService.listarInternacoes();
        }

    @GetMapping("/paciente/{id}")
    public ResponseEntity<List<Internacao>> listarInternacoesPorPaciente(@PathVariable("id") UUID id) {
        return  internacaoService.listarInternacoesByPaciente(id);
    }

    @GetMapping("/medico/{id}")
    public ResponseEntity<List<Internacao>> listarInternacoesPorMedico(@PathVariable("id") UUID id) {
        return internacaoService.listarInternacoesByMedico(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarInternacao(@PathVariable("id") UUID id, @RequestBody  InternacaoForm internacaoForm) {
        return internacaoService.atualizarInternacao(id, internacaoForm);
    }

    @PutMapping("/dar-alta/{id}")
    public ResponseEntity<?> altaInternacao(@PathVariable("id") UUID id) {
        return internacaoService.darAlta(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarInternacao(@PathVariable("id") UUID id) {
        return internacaoService.deletarInternacao(id);
 
    }
}
