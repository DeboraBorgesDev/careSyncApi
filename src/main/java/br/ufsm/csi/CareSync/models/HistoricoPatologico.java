package br.ufsm.csi.CareSync.models;


import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "historico_patologico")
public class HistoricoPatologico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @Column(name = "queixa_principal", columnDefinition = "TEXT")
    private String queixaPrincipal;

    @Column(name = "historia_doenca_atual", columnDefinition = "TEXT")
    private String historiaDoencaAtual;

    @Column(name = "possui_diabetes")
    private Boolean possuiDiabetes;

    @Column(name = "possui_hipertensao")
    private Boolean possuiHipertensao;

    @Column(name = "possui_dislipidemia")
    private Boolean possuiDislipidemia;

    @Column(name = "medicacao_continua")
    private Boolean medicacaoContinua;

    @Column(name = "medicacoes", columnDefinition = "TEXT")
    private String medicacoes;

    @Column(name = "internado_anteriormente")
    private Boolean internadoAnteriormente;

    @Column(name = "cirurgias_anteriormente")
    private Boolean cirurgiasAnteriormente;

    @Column(name = "cirurgias_anteriores_observacoes", columnDefinition = "TEXT")
    private String cirurgiasAnterioresObservacoes;

    @Column(name = "vacinas", columnDefinition = "TEXT")
    private String vacinas;

    @Column(name = "alergias", columnDefinition = "TEXT")
    private String alergias;

    @Column(name = "data_atualizacao")
    
    private Date dataAtualizacao;

    public HistoricoPatologico(UUID id, Paciente paciente, String queixaPrincipal, String historiaDoencaAtual,
            Boolean possuiDiabetes, Boolean possuiHipertensao, Boolean possuiDislipidemia, Boolean medicacaoContinua,
            String medicacoes, Boolean internadoAnteriormente, Boolean cirurgiasAnteriormente,
            String cirurgiasAnterioresObservacoes, String vacinas, String alergias, Date dataAtualizacao) {
        this.id = id;
        this.paciente = paciente;
        this.queixaPrincipal = queixaPrincipal;
        this.historiaDoencaAtual = historiaDoencaAtual;
        this.possuiDiabetes = possuiDiabetes;
        this.possuiHipertensao = possuiHipertensao;
        this.possuiDislipidemia = possuiDislipidemia;
        this.medicacaoContinua = medicacaoContinua;
        this.medicacoes = medicacoes;
        this.internadoAnteriormente = internadoAnteriormente;
        this.cirurgiasAnteriormente = cirurgiasAnteriormente;
        this.cirurgiasAnterioresObservacoes = cirurgiasAnterioresObservacoes;
        this.vacinas = vacinas;
        this.alergias = alergias;
        this.dataAtualizacao = dataAtualizacao;
    }

    public HistoricoPatologico() {}

}
