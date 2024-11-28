
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
    private Long idPedido;
    private Long idProduto;
    private Integer quantidade;
    private BigDecimal precoUnitario;

    public BigDecimal getValorTotal(){
        return precoUnitario.multiply(new BigDecimal(quantidade));
    }
}
