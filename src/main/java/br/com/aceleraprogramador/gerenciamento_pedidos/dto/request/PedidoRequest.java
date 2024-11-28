package br.com.aceleraprogramador.gerenciamento_pedidos.dto.request;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.ItemPedido;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoRequest {

    @Schema(description = "ID do cliente que realizou o pedido", example = "1")
    @NotNull(message = "O ID do cliente é obrigatório")
    private Long idCliente;

    @Schema(example = "31/12/2024 11:45:29", description = "Data em que o pedido foi realizado.")
    @Pattern(
            regexp = "\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}",
            message = "A data deve estar no formato dd/MM/yyyy HH:mm:ss."
    )
    @NotNull(message = "A data do pedido é obrigatória")
    private String dataPedido;

    @Schema(example = "CONCLUIDO", description = "Status do pedido.")
    @Pattern(regexp = "PENDENTE|PROCESSANDO|EM_TRANSPORTE|CONCLUIDO|CANCELADO|DEVOLVIDO|FALHA_NA_ENTREGA", message = "status deve ser PENDENTE,PROCESSANDO,EM_TRANSPORTE,CONCLUIDO,CANCELADO,DEVOLVIDO,FALHA_NA_ENTREGA")
    @NotNull(message = "Não é permitido valor vazio.")
    private String status;

    @ArraySchema(schema = @Schema(name = "itens", description = "Itens do Pedido", implementation = ItemPedidoRequest.class))
    @Size(min = 1, message = "O array deve conter no mínimo 1 registro.")
    @Valid
    private List<ItemPedidoRequest> itens;
}
