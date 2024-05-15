package br.ufsm.csi.CareSync.models;


import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import br.ufsm.csi.CareSync.forms.PacienteForm;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "paciente")
@Getter
@Setter
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

    public Paciente(UUID id, String nome, Date dataNascimento, String cpf, String sexo, String estadoCivil,
            Boolean possuiFilhos, String profissao, String religiao, String nivelEnsino) {
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
    }

    public Paciente() {}

    public void atualizar(Paciente paciente, PacienteForm pacienteForm) {
        if (pacienteForm.getNome() != null) {
            paciente.setNome(pacienteForm.getNome());
        }
        if (pacienteForm.getDataNascimento() != null) {
            paciente.setDataNascimento(pacienteForm.getDataNascimento());
        }
        if (pacienteForm.getCpf() != null) {
            paciente.setCpf(pacienteForm.getCpf());
        }
        if (pacienteForm.getSexo() != null) {
            paciente.setSexo(pacienteForm.getSexo());
        }
        if (pacienteForm.getEstadoCivil() != null) {
            paciente.setEstadoCivil(pacienteForm.getEstadoCivil());
        }
        if (pacienteForm.getPossuiFilhos() != null && !Objects.equals(pacienteForm.getPossuiFilhos(), paciente.getPossuiFilhos())) {
            paciente.setPossuiFilhos(pacienteForm.getPossuiFilhos());
        }
        if (pacienteForm.getProfissao() != null) {
            paciente.setProfissao(pacienteForm.getProfissao());
        }
        if (pacienteForm.getReligiao() != null) {
            paciente.setReligiao(pacienteForm.getReligiao());
        }
        if (pacienteForm.getNivelEnsino() != null) {
            paciente.setNivelEnsino(pacienteForm.getNivelEnsino());
        }
    }

}
