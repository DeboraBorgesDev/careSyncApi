package br.ufsm.csi.CareSync.forms;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;




@Getter
@Setter
public class UsuarioForm {

  
    @NotBlank(message = "O nome é obrigatório")
    @NotNull
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String senha;

    private boolean isMedico;

    @Size(max = 20, message = "O CRM deve ter no máximo 20 caracteres")
    private String crm;

    @Pattern(regexp = "\\d{11}", message = "CPF inválido")
    private String cpf;

    private boolean isEnfermeiro;

    @Size(max = 20, message = "O COREN deve ter no máximo 20 caracteres")
    private String coren;

    private boolean isEstudante;

    @Size(max = 20, message = "A matrícula deve ter no máximo 20 caracteres")
    private String matricula;

    @NotBlank(message = "A permissão é obrigatória")
    private String permissao;

    public UsuarioForm(String nome, String email, String senha, boolean isMedico, String crm, String cpf,
            boolean isEnfermeiro, String coren, boolean isEstudante, String matricula, String permissao) {
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
    
}
