package br.ufsm.csi.CareSync.forms;


import java.util.Date;

import br.ufsm.csi.CareSync.models.Paciente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteForm {

    @NotBlank(message = "O nome é obrigatório")
    @NotNull(message = "O nome é obrigatório")
    private String nome;

    @NotNull(message = "O nome é obrigatório")
    @Past(message = "A data de nascimento não pode ser maior do que a atual")
    private Date dataNascimento;

    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")
    @NotNull(message = "O cpf é obrigatório")
    @NotBlank(message = "O cpf é obrigatório")
    private String cpf;

    
    @Size(max = 1)
    @NotNull(message = "O gênero é obrigatório")
    @NotBlank(message = "O gênero é obrigatório")
    private String sexo;

    @Size(max = 20)
    private String estadoCivil;

    private Boolean possuiFilhos;

    @Size(max = 100)
    private String profissao;

    @Size(max = 50)
    private String religiao;

    @Size(max = 50)
    private String nivelEnsino;

      public Paciente toPaciente() {
        Paciente paciente = new Paciente();
        paciente.setNome(this.nome);
        paciente.setDataNascimento(this.dataNascimento);
        paciente.setCpf(this.cpf);
        paciente.setSexo(this.sexo);
        paciente.setEstadoCivil(this.estadoCivil);
        paciente.setPossuiFilhos(this.possuiFilhos);
        paciente.setProfissao(this.profissao);
        paciente.setReligiao(this.religiao);
        paciente.setNivelEnsino(this.nivelEnsino);
        return paciente;
    }
}
