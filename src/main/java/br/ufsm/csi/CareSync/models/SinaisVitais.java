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
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sinais_vitais")
@Getter
@Setter
public class SinaisVitais {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_profissional")
    private Usuario profissional;

    @Column(name = "data_hora")
    
    private Date dataHora;

    @Column(name = "freq_cardiaca")
    private Integer freqCardiaca;

    @Column(name = "freq_respiratoria")
    private Integer freqRespiratoria;

    @Column(name = "pressao_arterial")
    private String pressaoArterial;

    @Column(name = "constipacao",  columnDefinition = "TEXT")
    private String constipacao;

    @Column(name = "glicemia")
    private Float glicemia;

    @Column(name = "temperatura")
    private Float temperatura;

    @Column(name = "oxigenacao")
    private Float oxigenacao;

    @Column(name = "peso")
    private Float peso;

    @Column(name = "mobilidade",  columnDefinition = "TEXT")
    private String mobilidade;

    @Column(name = "observacoes", columnDefinition = "TEXT")
    private String observacoes;

    public SinaisVitais(UUID id, Paciente paciente, Usuario profissional, Date dataHora, Integer freqCardiaca,
            Integer freqRespiratoria, String pressaoArterial, String constipacao, Float glicemia, Float temperatura,
            Float oxigenacao, Float peso, String mobilidade, String observacoes) {
        this.id = id;
        this.paciente = paciente;
        this.profissional = profissional;
        this.dataHora = dataHora;
        this.freqCardiaca = freqCardiaca;
        this.freqRespiratoria = freqRespiratoria;
        this.pressaoArterial = pressaoArterial;
        this.constipacao = constipacao;
        this.glicemia = glicemia;
        this.temperatura = temperatura;
        this.oxigenacao = oxigenacao;
        this.peso = peso;
        this.mobilidade = mobilidade;
        this.observacoes = observacoes;
    }

    public SinaisVitais() {}

}