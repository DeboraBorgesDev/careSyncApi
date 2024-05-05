package br.ufsm.csi.CareSync.repository;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufsm.csi.CareSync.models.SinaisVitais;

public interface SinaisVitaisRepository extends JpaRepository<SinaisVitais, UUID> {
    @Query(value = "SELECT * FROM sinais_vitais s WHERE s.id_paciente = :idPaciente", nativeQuery = true)
    ArrayList<SinaisVitais> findByIdPaciente(UUID idPaciente);
    
}
