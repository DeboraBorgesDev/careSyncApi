package br.ufsm.csi.CareSync.models;


import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @Column(length = 14, unique = true)
    private String cpf;

    @Column(length = 10)
    private String sexo;

    @Column(name = "estado_civil", length = 20)
    private String estadoCivil;

    @Column(name = "possui_filhos")
    private Boolean possuiFilhos;

    @Column(length = 100)
    private String profissao;

    @Column(length = 50)
    private String religiao;

    @Column(name = "nivel_ensino", length = 50)
    private String nivelEnsino;

    @Column(name = "possui_acompanhante")
    private Boolean possuiAcompanhante;

    @Column(name = "nome_acompanhante", length = 255)
    private String nomeAcompanhante;

    public Paciente(UUID id, String nome, Date dataNascimento, String cpf, String sexo, String estadoCivil,
            Boolean possuiFilhos, String profissao, String religiao, String nivelEnsino, Boolean possuiAcompanhante,
            String nomeAcompanhante) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
        this.possuiFilhos = possuiFilhos;
        this.profissao = profissao;
        this.religiao = religiao;
        this.nivelEnsino = nivelEnsino;
        this.possuiAcompanhante = possuiAcompanhante;
        this.nomeAcompanhante = nomeAcompanhante;
    }

    public Paciente() {}

}
