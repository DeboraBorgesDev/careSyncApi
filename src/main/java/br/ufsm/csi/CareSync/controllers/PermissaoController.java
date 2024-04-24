package br.ufsm.csi.CareSync.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.ufsm.csi.CareSync.models.Permissao;
import br.ufsm.csi.CareSync.service.PermissaoService;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController {

    @Autowired
    private PermissaoService permissaoService;

    @GetMapping
    public ResponseEntity<List<Permissao>> listarPermissoes() {
        return permissaoService.listarPermissoes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Permissao> buscarPermissaoPorId(@PathVariable UUID id) {
        return permissaoService.buscarPermissaoPorId(id);
    }

    @PostMapping("")
    public ResponseEntity<?> criarPermissao(@RequestBody Permissao permissao, UriComponentsBuilder uriComponentsBuilder) {
        return permissaoService.criarPermissao(permissao, uriComponentsBuilder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarPermissao(@PathVariable UUID id, @RequestParam String nome) {
      return permissaoService.editarPermissao(id, nome);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPermissao(@PathVariable UUID id) {
        return  permissaoService.deletarPermissao(id);
    }
}
