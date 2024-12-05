package br.com.aceleraprogramador.gerenciamento_pedidos.dto.request;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioRequest {

    @Schema(description = "Nome completo do cliente", example = "Wesley Eduardo de Oliveira Melo")
    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres.")
    private String nome;

    @Schema(description = "Endereço de e-mail do cliente", example = "wesleyeduardo.dev@gmail.com")
    @NotBlank(message = "O email é obrigatório")
    @Size(min = 3, max = 100, message = "O email deve ter entre 3 e 100 caracteres.")
    @Email(message = "O email deve ser válido")
    private String email;

    @Schema(description = "senha do usuario", example = "teste123")
    @NotBlank(message = "O email é obrigatório")
    @Size(min = 3, max = 255, message = "O email deve ter entre 3 e 255 caracteres.")
    private String senha;

    @ArraySchema(schema = @Schema(name = "roles", description = "roles deve ser ROLE_USUARIO,ROLE_ADMINISTRADOR,ROLE_GERENTE", implementation = String.class))
    @Size(min = 1, message = "O array deve conter no mínimo 1 registro.")
    @Valid
    private List<String> roles;
}
