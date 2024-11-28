package br.com.aceleraprogramador.gerenciamento_pedidos.dto.request;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemPedidoRequest {

    @Schema(description = "ID do produto associado ao item", example = "101")
    @NotNull(message = "O ID do produto é obrigatório")
    private Long idProduto;

    @Schema(description = "Quantidade do produto no pedido", example = "3")
    @NotNull(message = "A quantidade é obrigatória")
    @Min(value = 1, message = "A quantidade mínima deve ser 1")
    private Integer quantidade;

    @Schema(description = "Preço unitário do produto no pedido", example = "50.00")
    @NotNull(message = "O preço unitário é obrigatório")
    @Digits(integer = 10, message = "A quantidade máxima de digitos é 10 na parte inteira e 2 na parte decimal", fraction = 2)
    @DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que 0.")
    private BigDecimal precoUnitario;
}
