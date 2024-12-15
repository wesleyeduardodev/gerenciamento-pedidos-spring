package br.com.aceleraprogramador.gerenciamento_pedidos.adapter;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.CobrancaResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.PedidoResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.enuns.TipoPagamento;
import br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.dto.request.ClienteCobrancaRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.dto.request.RegistraCobrancaRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.dto.response.ClienteCobrancaResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.dto.response.RegistraCobrancaResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Pedido;
import br.com.aceleraprogramador.gerenciamento_pedidos.utils.DateUtil;
import lombok.experimental.UtilityClass;
import java.time.LocalDate;

@UtilityClass
public class PagamentoAdapter {

    public static CobrancaResponse preencherRespostaRegistroPagamento(RegistraCobrancaResponse registraCobrancaResponse) {
        return CobrancaResponse
                .builder()
                .status(registraCobrancaResponse.getStatus())
                .urlPagamento(registraCobrancaResponse.getInvoiceUrl())
                .pixCopiaECola(registraCobrancaResponse.getPixCopiaECola())
                .build();
    }

    public static RegistraCobrancaRequest preencherPagamentoRequest(ClienteCobrancaResponse clienteCobrancaResponse, PedidoResponse pedidoResponse) {
        return RegistraCobrancaRequest
                .builder()
                .customer(clienteCobrancaResponse.getId())
                .billingType(TipoPagamento.PIX.name())
                .value(pedidoResponse.getValorTotal())
                .dueDate(DateUtil.convertLocalDateToString(LocalDate.now()))
                .build();
    }

    public static ClienteCobrancaRequest preencherClienteRequest(Pedido pedido) {
        return ClienteCobrancaRequest
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
