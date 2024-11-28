
package br.com.aceleraprogramador.gerenciamento_pedidos.dto.response;

import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoResponse {

    private Long id;
    private ClienteResponse cliente;
    private String dataPedido;
    private String status;
    private List<ItemPedidoResponse> itens;

    private BigDecimal getValorTotal() {
        return itens.stream()
                .map(ItemPedidoResponse::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
