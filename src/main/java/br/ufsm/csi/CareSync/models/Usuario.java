package br.ufsm.csi.CareSync.models;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Getter
@Setter
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(name = "is_medico")
    private boolean isMedico;

    @Column(length = 20)
    private String crm;

    @Column(length = 14)
    private String cpf;

    @Column(name = "is_enfermeiro")
    private boolean isEnfermeiro;

    @Column(length = 20)
    private String coren;

    @Column(name = "is_estudante")
    private boolean isEstudante;

    @Column(length = 20)
    private String matricula;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_permissao")
    private Permissao permissao;

    public Usuario( String nome, String email, String senha, boolean isMedico, String crm, String cpf,
            boolean isEnfermeiro, String coren, boolean isEstudante, String matricula, Permissao permissao) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.isMedico = isMedico;
        this.crm = crm;
        this.cpf = cpf;
        this.isEnfermeiro = isEnfermeiro;
        this.coren = coren;
        this.isEstudante = isEstudante;
        this.matricula = matricula;
        this.permissao = permissao;
    }

    public Usuario() {
    }

    

    
}
