package br.ufsm.csi.CareSync.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufsm.csi.CareSync.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID>{
    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);
    
}
