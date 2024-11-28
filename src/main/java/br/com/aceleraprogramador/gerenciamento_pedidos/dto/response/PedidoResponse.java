
package br.com.aceleraprogramador.gerenciamento_pedidos.dto.response;
import lombok.*;

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
}
