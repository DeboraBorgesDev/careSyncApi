package br.ufsm.csi.CareSync.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufsm.csi.CareSync.models.HistoriaFamiliar;


public interface HistoriaFamiliarRepository extends JpaRepository<HistoriaFamiliar, UUID>{
    @Query(value = "SELECT CASE WHEN COUNT(hf) > 0 THEN true ELSE false END FROM historia_familiar hf WHERE hf.id_paciente = :idPaciente", nativeQuery = true)
    boolean existsByPacienteId(UUID idPaciente);

    @Query(value = "SELECT id, id_paciente, idade_morte_pai, causa_morte_pai, idade_morte_mae, causa_morte_mae, doencas_mae, doencas_pai, filhos_saudaveis, filhos_observacoes, historico_saude_parentes FROM historia_familiar WHERE historia_familiar.id_paciente = :idPaciente", nativeQuery = true)
    Optional<HistoriaFamiliar> findByIdPaciente(UUID idPaciente);

}