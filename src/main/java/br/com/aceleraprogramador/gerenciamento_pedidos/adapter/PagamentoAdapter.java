package br.com.aceleraprogramador.gerenciamento_pedidos.adapter;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.PagamentoResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.PedidoResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.enuns.TipoPagamento;
import br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.dto.request.ClienteIntegracaoRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.dto.request.PagamentoIntegracaoRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.dto.response.ClienteIntegracaoResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.dto.response.PagamentoIntegracaoResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Pedido;
import br.com.aceleraprogramador.gerenciamento_pedidos.utils.DateUtil;
import lombok.experimental.UtilityClass;
import java.time.LocalDate;

@UtilityClass
public class PagamentoAdapter {

    public static PagamentoResponse preencherRespostaRegistroPagamento(PagamentoIntegracaoResponse pagamentoIntegracaoResponse) {
        return PagamentoResponse
                .builder()
                .status(pagamentoIntegracaoResponse.getStatus())
                .urlPagamento(pagamentoIntegracaoResponse.getInvoiceUrl())
                .pixCopiaECola(pagamentoIntegracaoResponse.getPixCopiaECola())
                .build();
    }

    public static PagamentoIntegracaoRequest preencherPagamentoRequest(ClienteIntegracaoResponse clienteIntegracaoResponse, PedidoResponse pedidoResponse) {
        return PagamentoIntegracaoRequest
                .builder()
                .customer(clienteIntegracaoResponse.getId())
                .billingType(TipoPagamento.PIX.name())
                .value(pedidoResponse.getValorTotal())
                .dueDate(DateUtil.convertLocalDateToString(LocalDate.now()))
                .build();
    }

    public static ClienteIntegracaoRequest preencherClienteRequest(Pedido pedido) {
        return ClienteIntegracaoRequest
                .builder()
                .externalReference(pedido.getId().toString())
                .name(pedido.getCliente().getNome())
                .cpfCnpj(pedido.getCliente().getCpf())
                .email(pedido.getCliente().getEmail())
                .mobilePhone(pedido.getCliente().getTelefone())
                .notificationDisabled(true)
                .build();
    }
}
