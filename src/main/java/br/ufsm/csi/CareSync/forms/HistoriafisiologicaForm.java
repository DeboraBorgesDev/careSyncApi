package br.ufsm.csi.CareSync.forms;

import java.util.UUID;

import br.ufsm.csi.CareSync.models.HistoriaFisiologica;
import br.ufsm.csi.CareSync.models.Paciente;
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

    public HistoriaFisiologica toHistoriaFisiologica( Paciente paciente) {
        HistoriaFisiologica historia = new HistoriaFisiologica();
        historia.setPaciente(paciente);
        historia.setExperienciaParto(this.experienciaParto);
        historia.setMetodoParto(this.metodoParto);
        historia.setSaudeInfancia(this.saudeInfancia);
        return historia;
    }
}
