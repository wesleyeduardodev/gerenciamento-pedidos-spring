package br.com.aceleraprogramador.gerenciamento_pedidos.dto.request;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistroFinanceiroRequest {

    @NotBlank(message = "O Título obrigatório.")
    @Size(max = 50, message = "O título do registro deve ter no máximo 50 caracteres.")
    private String titulo;

    @Size(max = 250, message = "A descrição do registro deve ter no máximo 250 caracteres.")
    private String descricao;

    @NotNull(message = "O tipo de registro é obrigatório.")
    @Min(value = 0, message = "O tipo de registro deve ser um número válido.")
    private Integer tipoRegistro;

    @NotNull(message = "O tipo de transação é obrigatório.")
    @Min(value = 0, message = "O tipo de transação deve ser um número válido.")
    private Integer tipoTransacao;

    @NotNull(message = "A categoria é obrigatória.")
    private Long idCategoria;

    @NotNull(message = "O valor é obrigatório.")
    @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero.")
    @Digits(integer = 8, fraction = 2, message = "O valor deve ter no máximo 8 dígitos inteiros e 2 casas decimais.")
    private BigDecimal valor;

    private String dataTransacao;
}
