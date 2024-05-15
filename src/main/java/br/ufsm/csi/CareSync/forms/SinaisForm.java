package br.ufsm.csi.CareSync.forms;

import java.util.UUID;

import br.ufsm.csi.CareSync.models.Paciente;
import br.ufsm.csi.CareSync.models.SinaisVitais;
import br.ufsm.csi.CareSync.models.Usuario;
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

    public SinaisVitais toSinaisVitais(Paciente paciente, Usuario profissional) {
        SinaisVitais sinaisVitais = new SinaisVitais();
        sinaisVitais.setPaciente(paciente);
        sinaisVitais.setProfissional(profissional);
        sinaisVitais.setFreqCardiaca(this.freqCardiaca);
        sinaisVitais.setFreqRespiratoria(this.freqRespiratoria);
        sinaisVitais.setPressaoArterial(this.pressaoArterial);
        sinaisVitais.setConstipacao(this.constipacao);
        sinaisVitais.setGlicemia(this.glicemia);
        sinaisVitais.setTemperatura(this.temperatura);
        sinaisVitais.setOxigenacao(this.oxigenacao);
        sinaisVitais.setPeso(this.peso);
        sinaisVitais.setMobilidade(this.mobilidade);
        sinaisVitais.setObservacoes(this.observacoes);
        return sinaisVitais;
    }
}


