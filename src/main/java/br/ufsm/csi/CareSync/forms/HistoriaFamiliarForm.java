package br.ufsm.csi.CareSync.forms;

import java.util.UUID;

import br.ufsm.csi.CareSync.models.HistoriaFamiliar;
import br.ufsm.csi.CareSync.models.Paciente;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistoriaFamiliarForm {
    @NotNull(message = "O ID do paciente é obrigatório")
    private UUID pacienteId;
    
    private Integer idadeMortePai;
    private String causaMortePai;
    private Integer idadeMorteMae;
    private String causaMorteMae;
    private String doencasMae;
    private String doencasPai;
    private Boolean filhosSaudaveis;
    private String filhosObservacoes;
    private String historicoSaudeParentes;


    public HistoriaFamiliarForm(UUID pacienteId, Integer idadeMortePai, String causaMortePai, Integer idadeMorteMae,
            String causaMorteMae, String doencasMae, String doencasPai, Boolean filhosSaudaveis,
            String filhosObservacoes, String historicoSaudeParentes) {
        this.pacienteId = pacienteId;
        this.idadeMortePai = idadeMortePai;
        this.causaMortePai = causaMortePai;
        this.idadeMorteMae = idadeMorteMae;
        this.causaMorteMae = causaMorteMae;
        this.doencasMae = doencasMae;
        this.doencasPai = doencasPai;
        this.filhosSaudaveis = filhosSaudaveis;
        this.filhosObservacoes = filhosObservacoes;
        this.historicoSaudeParentes = historicoSaudeParentes;
    }

    public HistoriaFamiliar toHistoriaFamiliar( Paciente paciente) {
        HistoriaFamiliar historiaFamiliar = new HistoriaFamiliar();
        historiaFamiliar.setPaciente(paciente);
        historiaFamiliar.setIdadeMortePai(this.idadeMortePai);
        historiaFamiliar.setCausaMortePai(this.causaMortePai);
        historiaFamiliar.setIdadeMorteMae(this.idadeMorteMae);
        historiaFamiliar.setCausaMorteMae(this.causaMorteMae);
        historiaFamiliar.setDoencasPai(this.doencasPai);
        historiaFamiliar.setDoencasMae(this.doencasMae);
        historiaFamiliar.setFilhosSaudaveis(this.filhosSaudaveis);
        historiaFamiliar.setFilhosObservacoes(this.filhosObservacoes);
        historiaFamiliar.setHistoricoSaudeParentes(this.historicoSaudeParentes);
        return historiaFamiliar;
    }


    
}
