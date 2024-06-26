package br.ufsm.csi.CareSync.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

    @Autowired
    public PermissaoRepository permissaoRepository;

    @Autowired
    public UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public ResponseEntity<?> newUser(UsuarioForm userForm, UriComponentsBuilder uriComponentsBuilder, BindingResult bindingResult) {
        try {
            if (usuarioRepository.existsByEmail(userForm.getEmail())) {
                throw new EmailException("O e-mail já está em uso");
            }
    
            if (usuarioRepository.existsByCpf(userForm.getCpf())) {
                throw new CpfException("O CPF já está cadastrado");
            }
    
            UUID permissaoUuid = UUID.fromString(userForm.getPermissao());
            Optional<Permissao> optionalPermissao = permissaoRepository.findById(permissaoUuid);
            Permissao permissao = optionalPermissao.orElseThrow(() -> new NotFoundException("Permissão não encontrada"));
    
            Usuario novoUsuario = userForm.toUsuario(permissao);
            novoUsuario.setSenha(passwordEncoder.encode(userForm.getSenha()));

            Usuario savedUser = usuarioRepository.save(novoUsuario);
            URI uri = uriComponentsBuilder.path("/user/{id}").buildAndExpand(savedUser.getId()).toUri();
            return ResponseEntity.created(uri).body(savedUser);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<?> editarUsuario(UUID id, UsuarioForm userForm) {
        try {
            Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
            if (!optionalUsuario.isPresent()) {
                return ResponseEntity.internalServerError().build();
            }
    
            Usuario usuario = optionalUsuario.get();
    
            editar(userForm, usuario);
    
            Usuario savedUser = usuarioRepository.save(usuario);
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
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
        try {
            
            Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
            return usuarioOptional.map(ResponseEntity::ok)
                    .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        
    }

    public void editar(UsuarioForm userForm, Usuario usuario) {
        if (userForm.getNome() != null) {
            usuario.setNome(userForm.getNome());
        }
        if (userForm.getEmail() != null) {
            usuario.setEmail(userForm.getEmail());
        }
        if (userForm.getSenha() != null) {
            usuario.setSenha(passwordEncoder.encode(userForm.getSenha()));
        }
        if (userForm.isMedico() != usuario.isMedico()) {
            usuario.setMedico(userForm.isMedico());
        }
        if (userForm.getCrm() != null) {
            usuario.setCrm(userForm.getCrm());
        }
        if (userForm.getCpf() != null) {
            usuario.setCpf(userForm.getCpf());
        }
        if (userForm.isEnfermeiro() != usuario.isEnfermeiro()) {
            usuario.setEnfermeiro(userForm.isEnfermeiro());
        }
        if (userForm.getCoren() != null) {
            usuario.setCoren(userForm.getCoren());
        }
        if (userForm.isEstudante() != usuario.isEstudante()) {
            usuario.setEstudante(userForm.isEstudante());
        }
        if (userForm.getMatricula() != null) {
            usuario.setMatricula(userForm.getMatricula());
        }
        if (userForm.getPermissao() != null) {
            UUID permissaoUuid = UUID.fromString(userForm.getPermissao());
            Permissao permissao = permissaoRepository.findById(permissaoUuid)
                .orElseThrow(() -> new NotFoundException("Permissão não encontrada"));
            usuario.setPermissao(permissao);
        }
    }
}
