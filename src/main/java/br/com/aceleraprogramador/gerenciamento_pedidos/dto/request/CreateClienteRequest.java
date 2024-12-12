package br.com.aceleraprogramador.gerenciamento_pedidos.dto.request;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateClienteRequest {

    @Schema(description = "Nome completo do cliente", example = "Wesley Eduardo de Oliveira Melo")
    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres.")
    private String nome;

    @Schema(description = "Endereço de e-mail do cliente", example = "wesleyeduardo.dev@gmail.com")
    @NotBlank(message = "O email é obrigatório")
    @Size(min = 3, max = 100, message = "O email deve ter entre 3 e 100 caracteres.")
    @Email(message = "O email deve ser válido")
    private String email;

    @Schema(description = "CPF do cliente", example = "06516512441")
    @Size(min =11, max = 11, message = "O cpf deve ter entre 11 caracteres.")
    private String cpf;

    @Schema(description = "Número de telefone do cliente no formato (XX) XXXXX-XXXX", example = "(11) 98765-4321")
    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "O telefone deve estar no formato (XX) XXXXX-XXXX.")
    private String telefone;

    @Schema(description = "Endereço do Cliente", example = "Rua A, Santa Luzia - MA")
    private String endereco;

    @Schema(description = "Profissão do Cliente", example = "Desenvolvedor")
    private String profissao;
}
