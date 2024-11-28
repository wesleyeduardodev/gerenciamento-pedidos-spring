
package br.com.aceleraprogramador.gerenciamento_pedidos.dto.response;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemPedidoResponse {
    private Long id;
    private PedidoResponse pedido;
    private ProdutoResponse produto;
    private Integer quantidade;
    private BigDecimal precoUnitario;
}
