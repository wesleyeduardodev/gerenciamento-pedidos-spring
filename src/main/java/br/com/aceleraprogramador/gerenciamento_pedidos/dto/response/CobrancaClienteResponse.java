package br.com.aceleraprogramador.gerenciamento_pedidos.dto.response;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CobrancaClienteResponse {
    private String status;
    private String urlPagamento;
    private String pixCopiaECola;
    private String qrCode;
}
