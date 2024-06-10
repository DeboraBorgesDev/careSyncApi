package br.ufsm.csi.CareSync.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.ufsm.csi.CareSync.models.Usuario;
import br.ufsm.csi.CareSync.repository.UsuarioRepository;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (!usuario.isPresent()) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        } else {
           
            UserDetails user = User.withUsername(usuario.get().getUsername())
                    .password(usuario.get().getPassword())
                    .authorities(usuario.get().getAuthorities())
                    .build();
            return user;
        }
    }
    
}
