package br.ufsm.csi.CareSync.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.core.Authentication;

import br.ufsm.csi.CareSync.forms.AutenticacaoForm;
import br.ufsm.csi.CareSync.models.Usuario;
import br.ufsm.csi.CareSync.repository.UsuarioRepository;
import br.ufsm.csi.CareSync.security.TokenServiceJWT;

@RestController
@RequestMapping("auth")
public class AutenticacaoController{

  private final AuthenticationManager authenticationManager;
  private final UsuarioRepository userRepository;

  public AutenticacaoController(AuthenticationManager authenticationManager, UsuarioRepository userRepository) {
    this.authenticationManager = authenticationManager;
    this.userRepository = userRepository;
  }



  @PostMapping("/login")
  public ResponseEntity<Object> auth(@RequestBody AutenticacaoForm user) {
    System.out.println(user.email());
    System.out.println(user.senha());
    try {
      final Authentication authenticate = authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(user.email(), user.senha()));

      if (authenticate.isAuthenticated()) {
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        Optional<Usuario> userAuthenticated = userRepository.findByEmail(user.email());
        String token = new TokenServiceJWT().gerarToken(userAuthenticated);
        userAuthenticated.get().setToken(token);

        return new ResponseEntity<>(userAuthenticated, HttpStatus.OK);
      }
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.internalServerError().body("Usuário ou senha incorretos");
    }
    return null;
  }
}

