package br.ufsm.csi.CareSync.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufsm.csi.CareSync.models.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, UUID>{

    
} 
