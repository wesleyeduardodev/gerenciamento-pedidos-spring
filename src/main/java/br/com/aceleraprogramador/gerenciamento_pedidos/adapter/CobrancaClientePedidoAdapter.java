package br.com.aceleraprogramador.gerenciamento_pedidos.adapter;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.CobrancaClienteResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CobrancaClientePedidoAdapter {

    public static CobrancaClienteResponse preencherRespostaRegistroPagamento(
            String status,
            String urlPagamento,
            String pixCopiaECola,
            String qrCode) {
        return CobrancaClienteResponse
                .builder()
                .status(status)
                .urlPagamento(urlPagamento)
                .pixCopiaECola(pixCopiaECola)
                .qrCode(qrCode)
                .build();
    }
}
