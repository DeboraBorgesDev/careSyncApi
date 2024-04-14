package models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "historia_familiar")
public class HistoriaFamiliar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
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

}
