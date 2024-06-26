package br.ufsm.csi.CareSync.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Getter
@Setter
@Table(name = "permissao")
public class Permissao implements GrantedAuthority{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private UUID id;

@NotBlank
@NotNull
@Column(name = "nome_permissao")
    private String nome;

    public Permissao(UUID id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Permissao() {
    }

    @Override
    public String getAuthority() {
        return this.nome;
    }

    
}
