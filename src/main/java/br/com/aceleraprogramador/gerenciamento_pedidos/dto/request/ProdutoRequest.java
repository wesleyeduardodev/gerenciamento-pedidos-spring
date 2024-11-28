package br.com.aceleraprogramador.gerenciamento_pedidos.dto.request;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoRequest {

    @Schema(description = "Nome do produto", example = "Smartphone Samsung Galaxy")
    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
    private String nome;

    @Schema(description = "Descrição detalhada do produto", example = "Smartphone com 128GB de armazenamento e 6GB de RAM")
    @Size(min = 3, max = 255, message = "A descrição deve ter entre 3 e 255 caracteres.")
    private String descricao;

    @Schema(description = "Preço do produto", example = "1500.00")
    @NotNull(message = "O preço é obrigatório")
    @Digits(integer = 10, message = "A quantidade máxima de digitos é 10 na parte inteira e 2 na parte decimal", fraction = 2)
    @DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que 0.")
    private BigDecimal preco;

    @Schema(description = "ID do fornecedor do produto", example = "1")
    @NotNull(message = "O ID do fornecedor é obrigatório")
    private Long idFornecedor;
}
