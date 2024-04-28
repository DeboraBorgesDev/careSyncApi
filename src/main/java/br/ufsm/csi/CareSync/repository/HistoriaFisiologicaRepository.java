package br.ufsm.csi.CareSync.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufsm.csi.CareSync.models.HistoriaFisiologica;

public interface HistoriaFisiologicaRepository extends JpaRepository<HistoriaFisiologica, UUID>{
    @Query(value = "SELECT CASE WHEN COUNT(hf) > 0 THEN true ELSE false END FROM historia_fisiologica hf WHERE hf.id_paciente = :idPaciente", nativeQuery = true)
    boolean existsByPacienteId(UUID idPaciente);

    @Query(value = "SELECT hf.id, hf.id_paciente, hf.metodo_parto, hf.experiencia_parto, hf.saude_infancia FROM historia_familiar hf WHERE hf.id_paciente = :idPaciente", nativeQuery = true)
    Optional<HistoriaFisiologica> findByIdPaciente(UUID idPaciente);
}
