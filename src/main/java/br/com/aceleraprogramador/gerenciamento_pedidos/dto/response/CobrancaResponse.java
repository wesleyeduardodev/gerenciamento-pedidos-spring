package br.com.aceleraprogramador.gerenciamento_pedidos.dto.response;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CobrancaResponse {
    private String status;
    private String urlPagamento;
    private String pixCopiaECola;
}
