package br.ufsm.csi.CareSync.models;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.ufsm.csi.CareSync.exceptions.NotFoundException;
import br.ufsm.csi.CareSync.forms.UsuarioForm;
import br.ufsm.csi.CareSync.repository.PermissaoRepository;
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

    @Autowired
    private PermissaoRepository permissaoRepository;

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

    public void editar(UsuarioForm userForm, Usuario usuarioOptional) {
        if (userForm.getNome() != null) {
            this.nome = userForm.getNome();
        }
        if (userForm.getEmail() != null) {
            this.email = userForm.getEmail();
        }
        if (userForm.getSenha() != null) {
            this.senha = userForm.getSenha();
        }
        if (userForm.isMedico() != usuarioOptional.isMedico()) {
            this.isMedico = userForm.isMedico();
        }
        if (userForm.getCrm() != null) {
            this.crm = userForm.getCrm();
        }
        if (userForm.getCpf() != null) {
            this.cpf = userForm.getCpf();
        }
        if (userForm.isEnfermeiro() != usuarioOptional.isEnfermeiro()) {
            this.isEnfermeiro = userForm.isEnfermeiro();
        }
        if (userForm.getCoren() != null) {
            this.coren = userForm.getCoren();
        }
        if (userForm.isEstudante() != usuarioOptional.isEstudante()) {
            this.isEstudante = userForm.isEstudante();
        }
        if (userForm.getMatricula() != null) {
            this.matricula = userForm.getMatricula();
        }
        if (userForm.getPermissao() != null) {
            UUID permissaoUuid = UUID.fromString(userForm.getPermissao());
            Permissao permissao = permissaoRepository.findById(permissaoUuid)
                .orElseThrow(() -> new NotFoundException("Permissão não encontrada"));
            this.permissao = permissao;
        }
    }
    

    
}
