package br.ufsm.csi.CareSync.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufsm.csi.CareSync.models.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, UUID> {

    boolean existsByCpf(String cpf);
} 
