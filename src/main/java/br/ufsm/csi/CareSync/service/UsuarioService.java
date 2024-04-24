package br.ufsm.csi.CareSync.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.util.UriComponentsBuilder;

import br.ufsm.csi.CareSync.exceptions.CpfException;
import br.ufsm.csi.CareSync.exceptions.EmailException;
import br.ufsm.csi.CareSync.exceptions.NotFoundException;
import br.ufsm.csi.CareSync.forms.UsuarioForm;
import br.ufsm.csi.CareSync.models.Permissao;
import br.ufsm.csi.CareSync.models.Usuario;
import br.ufsm.csi.CareSync.repository.PermissaoRepository;
import br.ufsm.csi.CareSync.repository.UsuarioRepository;


import java.util.*;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

    @Autowired
    public PermissaoRepository permissaoRepository;

    @Autowired
    public UsuarioRepository usuarioRepository;

    @Transactional
    public ResponseEntity<?> newUser(UsuarioForm userForm, UriComponentsBuilder uriComponentsBuilder, BindingResult bindingResult) {
        if (usuarioRepository.existsByEmail(userForm.getEmail())) {
            throw new EmailException(userForm.getEmail());
        }
  
        if (usuarioRepository.existsByCpf(userForm.getCpf())) {
            throw new CpfException(userForm.getCpf());
        }

        UUID permissaoUuid = UUID.fromString(userForm.getPermissao());
  
      Permissao permissao = permissaoRepository.findById(permissaoUuid).orElse(null);

      Usuario novoUsuario = new Usuario(
              userForm.getNome(),
              userForm.getEmail(),
              userForm.getSenha(),
              userForm.isMedico(),
              userForm.getCrm(),
              userForm.getCpf(),
              userForm.isEnfermeiro(),
              userForm.getCoren(),
              userForm.isEstudante(),
              userForm.getMatricula(),
              permissao
      );

      try {
          Usuario savedUser = usuarioRepository.save(novoUsuario);
          URI uri = uriComponentsBuilder.path("/user/{id}").buildAndExpand(savedUser.getId()).toUri();
          return ResponseEntity.created(uri).body(savedUser);
      } catch (Exception e) {
          return ResponseEntity.internalServerError().build();
      }
  }

  public ResponseEntity<?> editarUsuario(UUID id, UsuarioForm userForm) {
    Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
    UUID permissaoUuid = UUID.fromString(userForm.getPermissao());
    if (optionalUsuario.isPresent()) {
        Usuario usuario = optionalUsuario.get();
        
         if (userForm.getNome() != null && !Objects.equals(usuario.getNome(), userForm.getNome())) {
            usuario.setNome(userForm.getNome());
        }
        if (userForm.getEmail() != null && !Objects.equals(usuario.getEmail(), userForm.getEmail())) {
            if (usuarioRepository.existsByEmail(userForm.getEmail())) {
                throw new EmailException(userForm.getEmail());
            } else {
                usuario.setEmail(userForm.getEmail());
            }
        }
        if (userForm.getSenha() != null && !Objects.equals(usuario.getSenha(), userForm.getSenha())) {
            usuario.setSenha(userForm.getSenha());
        }
        if (!Objects.equals(usuario.isMedico(), userForm.isMedico())) {
            usuario.setMedico(userForm.isMedico());
        }
        if (userForm.getCrm() != null && !Objects.equals(usuario.getCrm(), userForm.getCrm())) {
            usuario.setCrm(userForm.getCrm());
        }
        if (userForm.getCpf() != null && !Objects.equals(usuario.getCpf(), userForm.getCpf())) {
            if (usuarioRepository.existsByCpf(userForm.getCpf())) {
                throw new CpfException(userForm.getCpf());
            } else {
                usuario.setCpf(userForm.getCpf());
            }
        }
        if (!Objects.equals(usuario.isEnfermeiro(), userForm.isEnfermeiro())) {
            usuario.setEnfermeiro(userForm.isEnfermeiro());
        }
        if (userForm.getCoren() != null && !Objects.equals(usuario.getCoren(), userForm.getCoren())) {
            usuario.setCoren(userForm.getCoren());
        }
        if (!Objects.equals(usuario.isEstudante(), userForm.isEstudante())) {
            usuario.setEstudante(userForm.isEstudante());
        }
        if (userForm.getMatricula() != null && !Objects.equals(usuario.getMatricula(), userForm.getMatricula())) {
            usuario.setMatricula(userForm.getMatricula());
        }
        if (userForm.getPermissao() != null  && !Objects.equals(usuario.getPermissao().getId(), permissaoUuid)) {
            Permissao permissao = permissaoRepository.findById(usuario.getPermissao().getId()).orElse(null);
            usuario.setPermissao(permissao);
        }

        try {
            Usuario savedUser = usuarioRepository.save(usuario);
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Falha ao editar usuário");
        }
    } else {
        return ResponseEntity.notFound().build();
    }
}


    public ResponseEntity<?> deleteUsuario(UUID id) {
        try {
            if (!usuarioRepository.existsById(id)) {
                throw new NotFoundException("Usuário não encontrado");
            }
            usuarioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<List<Usuario>> listarUsuarios() {
        try {
            List<Usuario> usuarios = usuarioRepository.findAll();
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<?> listarUsuarioPorId(UUID id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (!usuarioOptional.isPresent()) {
            throw new NotFoundException("Usuário não encontrado");
          
        } else {
            Usuario usuario = usuarioOptional.get();
            return ResponseEntity.ok(usuario);
        }
    }
    
}
