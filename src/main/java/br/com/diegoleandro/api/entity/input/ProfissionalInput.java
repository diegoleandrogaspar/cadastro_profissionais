package br.com.diegoleandro.api.entity.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfissionalInput {

    @NotBlank
    private String nome;

    @NotBlank
    @Pattern(regexp = "^(Desenvolvedor|Designer|Suporte|Tester)$", message = "Cargo deve ser 'Desenvolvedor', 'Designer', 'Suporte' ou 'Tester'")
    private String cargo;

    @NotBlank
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Formato de data inválido. Use o formato 'yyyy-MM-dd'.")
    private String nascimento;

    public ProfissionalInput() {
    }

    public ProfissionalInput(String nome, String cargo, String nascimento) {
        this.nome = nome;
        this.cargo = cargo;
        this.nascimento = nascimento;
    }
}
