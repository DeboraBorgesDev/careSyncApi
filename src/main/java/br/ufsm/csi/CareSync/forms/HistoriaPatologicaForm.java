package br.ufsm.csi.CareSync.forms;

import java.util.UUID;

import br.ufsm.csi.CareSync.models.HistoricoPatologico;
import br.ufsm.csi.CareSync.models.Paciente;
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

    public HistoricoPatologico toHistoricoPatologico(Paciente paciente){
        HistoricoPatologico historia = new HistoricoPatologico();

        historia.setPaciente(paciente);
        historia.setQueixaPrincipal(this.queixaPrincipal);
        historia.setHistoriaDoencaAtual(this.historiaDoencaAtual);
        historia.setPossuiDiabetes(this.possuiDiabetes);
        historia.setPossuiHipertensao(this.possuiHipertensao);
        historia.setPossuiDislipidemia(this.possuiDislipidemia);
        historia.setMedicacaoContinua(this.medicacaoContinua);
        historia.setMedicacoes(this.medicacoes);
        historia.setInternadoAnteriormente(this.internadoAnteriormente);
        historia.setCirurgiasAnteriormente(this.cirurgiasAnteriormente);
        historia.setCirurgiasAnterioresObservacoes(this.cirurgiasAnterioresObservacoes);
        historia.setVacinas(this.vacinas);
        historia.setAlergias(this.alergias);

        return historia;
    }
    
}
