package br.com.aceleraprogramador.gerenciamento_pedidos.dto.request;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubCategoriaRegistroFinanceiroRequest {

    @NotBlank(message = "O nome da categoria é obrigatório.")
    @Size(max = 50, message = "O nome da categoria deve ter no máximo 50 caracteres.")
    private String nome;

    @Size(max = 50, message = "A descrição da categoria deve ter no máximo 250 caracteres.")
    private String descricao;

    @Schema(description = "ID da Categoria", example = "1")
    @NotNull(message = "O ID da cargoria é obrigatório")
    private Long idCategoria;
}
