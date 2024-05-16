package br.ufsm.csi.CareSync.models;

import java.util.Objects;
import java.util.UUID;

import br.ufsm.csi.CareSync.forms.HistoriaFamiliarForm;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "historia_familiar")
@Getter
@Setter
public class HistoriaFamiliar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @Column(name = "idade_morte_pai")
    private Integer idadeMortePai;

    @Column(name = "causa_morte_pai", columnDefinition = "TEXT")
    private String causaMortePai;

    @Column(name = "idade_morte_mae")
    private Integer idadeMorteMae;

    @Column(name = "causa_morte_mae", columnDefinition = "TEXT")
    private String causaMorteMae;

    @Column(name = "doencas_mae", columnDefinition = "TEXT")
    private String doencasMae;

    @Column(name = "doencas_pai", columnDefinition = "TEXT")
    private String doencasPai;

    @Column(name = "filhos_saudaveis")
    private Boolean filhosSaudaveis;

    @Column(name = "filhos_observacoes", columnDefinition = "TEXT")
    private String filhosObservacoes;

    @Column(name = "historico_saude_parentes", columnDefinition = "TEXT")
    private String historicoSaudeParentes;

    public HistoriaFamiliar(UUID id, Paciente paciente, Integer idadeMortePai, String causaMortePai,
            Integer idadeMorteMae, String causaMorteMae, String doencasMae, String doencasPai,
            Boolean filhosSaudaveis, String filhosObservacoes, String historicoSaudeParentes) {
        this.id = id;
        this.paciente = paciente;
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

    public HistoriaFamiliar() {}

    public void atualizar(HistoriaFamiliarForm form) {
        if (Objects.nonNull(form.getIdadeMortePai())) {
            this.idadeMortePai = form.getIdadeMortePai();
        }
    
        if (Objects.nonNull(form.getCausaMortePai())) {
            this.causaMortePai = form.getCausaMortePai();
        }
    
        if (Objects.nonNull(form.getIdadeMorteMae())) {
            this.idadeMorteMae = form.getIdadeMorteMae();
        }
    
        if (Objects.nonNull(form.getCausaMorteMae())) {
            this.causaMorteMae = form.getCausaMorteMae();
        }
    
        if (Objects.nonNull(form.getDoencasPai())) {
            this.doencasPai = form.getDoencasPai();
        }
    
        if (Objects.nonNull(form.getDoencasMae())) {
            this.doencasMae = form.getDoencasMae();
        }
    
        if (Objects.nonNull(form.getFilhosSaudaveis())) {
            this.filhosSaudaveis = form.getFilhosSaudaveis();
        }
    
        if (Objects.nonNull(form.getFilhosObservacoes())) {
            this.filhosObservacoes = form.getFilhosObservacoes();
        }
    
        if (Objects.nonNull(form.getHistoricoSaudeParentes())) {
            this.historicoSaudeParentes = form.getHistoricoSaudeParentes();
        }
    }
    

}
