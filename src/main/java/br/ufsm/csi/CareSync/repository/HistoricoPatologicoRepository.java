package br.ufsm.csi.CareSync.repository;

import java.util.UUID;

import br.ufsm.csi.CareSync.models.HistoricoPatologico;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HistoricoPatologicoRepository extends   JpaRepository<HistoricoPatologico, UUID> {
    @Query(value="SELECT h.id, h.id_paciente, h.queixa_principal, h.data_atualizacao, h.historia_doenca_atual, h.possui_diabetes, h.possui_hipertensao, h.possui_dislipidemia, h.medicacao_continua, h.medicacoes, h.internado_anteriormente, h.cirurgias_anteriormente, h.cirurgias_anteriores_observacoes, h.vacinas, h.alergias FROM historico_patologico h WHERE h.id_paciente = :idPaciente", nativeQuery = true)
    ArrayList<HistoricoPatologico> findByIdPaciente(UUID idPaciente);
}