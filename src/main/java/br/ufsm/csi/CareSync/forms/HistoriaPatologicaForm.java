package br.ufsm.csi.CareSync.forms;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistoriaPatologicaForm {
    @NotNull(message = "O id do paciente é obrigatório")
    private UUID idPaciente;
    
    @NotBlank(message = "Queixa principal é obrigatória")
    private String queixaPrincipal;

    private String historiaDoencaAtual;
    
    private Boolean possuiDiabetes = false;
    
    private Boolean possuiHipertensao = false;
    
    private Boolean possuiDislipidemia = false;
    
    private Boolean medicacaoContinua = false;
    
    private String medicacoes;
    
    private Boolean internadoAnteriormente = false;
  
    private Boolean cirurgiasAnteriormente = false;
    
    private String cirurgiasAnterioresObservacoes;
    
    private String vacinas;
    
    private String alergias;
    
}
