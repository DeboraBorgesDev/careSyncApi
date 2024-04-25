package br.ufsm.csi.CareSync.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.ufsm.csi.CareSync.forms.UsuarioForm;
import br.ufsm.csi.CareSync.models.Usuario;
import br.ufsm.csi.CareSync.service.UsuarioService;
import jakarta.validation.Valid;

import java.util.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/novo")
    public ResponseEntity<?> criarUsuario(@RequestBody @Valid UsuarioForm userForm, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {
        return usuarioService.newUser(userForm, uriComponentsBuilder, bindingResult);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarUsuario(@PathVariable UUID id, @Valid @RequestBody UsuarioForm userForm) {
        return usuarioService.editarUsuario(id, userForm );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable UUID id) {
        return usuarioService.deleteUsuario(id);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodosUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarUsuarioPorId(@PathVariable UUID id) {
        return usuarioService.listarUsuarioPorId(id);
    }
    
}
