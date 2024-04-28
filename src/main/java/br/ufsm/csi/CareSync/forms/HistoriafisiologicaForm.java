package br.ufsm.csi.CareSync.forms;

import java.util.UUID;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistoriafisiologicaForm {

    @NotNull(message = "O ID do paciente é obrigatório")
    private UUID pacienteId;

    private String metodoParto;

    private String experienciaParto;

    private String saudeInfancia;
}
