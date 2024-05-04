package br.ufsm.csi.CareSync.forms;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InternacaoForm {
    @NotNull(message = "O id do paciente é obrigatório")
    private UUID idPaciente;

    @NotNull(message = "O motivo da internação é obrigatório")
    private String motivo;

    private String diagnostico;

    private String observacoes;

    @NotNull(message = "O id do médico é obrigatório")
    private UUID idMedico;
    
}



