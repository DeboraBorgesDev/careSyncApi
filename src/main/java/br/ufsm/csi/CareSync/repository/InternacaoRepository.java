package br.ufsm.csi.CareSync.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufsm.csi.CareSync.models.Internacao;

public interface InternacaoRepository extends JpaRepository<Internacao, UUID> {
    @Query(value = "SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END FROM internacao i WHERE i.id_paciente = :paciente AND i.data_saida IS NULL", nativeQuery = true)
    boolean existsByPacienteAndDataSaidaIsNull(UUID paciente);

    @Query(value="SELECT i.id, i.id_paciente, i.id_medico, i.motivo, i.diagnostico, i.data_entrada, i.data_saida, i.observacoes FROM internacao i WHERE i.id_paciente = :idPaciente", nativeQuery = true)
    ArrayList<Internacao> findByIdPaciente(UUID idPaciente);

    @Query(value="SELECT i.id, i.id_paciente, i.id_medico, i.motivo, i.diagnostico, i.data_entrada, i.data_saida, i.observacoes FROM internacao i WHERE i.id_medico = :idMedico", nativeQuery = true)
    ArrayList<Internacao> findByIdMedico(UUID idMedico);
}
