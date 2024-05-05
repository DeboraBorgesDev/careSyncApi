package br.ufsm.csi.CareSync.forms;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class SinaisForm {
    @NotNull(message = "O paciente é obrigatório")
    private UUID idPaciente;
    private UUID idProfissional;
    private Integer freqCardiaca;
    private Integer freqRespiratoria;
    private String pressaoArterial;
    private String constipacao;
    private Float glicemia;
    private Float temperatura;
    private Float oxigenacao;
    private Float peso;
    private String mobilidade;
    private String observacoes;
}
