package br.com.aceleraprogramador.gerenciamento_pedidos.service;
import br.com.aceleraprogramador.gerenciamento_pedidos.adapter.CobrancaClientePedidoAdapter;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.CobrancaClienteResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.enuns.TipoPagamento;
import br.com.aceleraprogramador.gerenciamento_pedidos.exceptions.MulttDigitalInteracaoException;
import br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.adapter.MulttDigitalAdapter;
import br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.api.MulttDigitalFacade;
import br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.dto.request.MulttDigitalClienteRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.dto.request.MulttDigitalCobrancaRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.dto.response.MulttDigitalCobrancaResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegistrarCobrancaClientePedidoService {

    private final MulttDigitalFacade multtDigitalFacade;

    public CobrancaClienteResponse registrarCobrancaClientePedido(Pedido pedido) {
        try {

            MulttDigitalClienteRequest clienteRequest = preencherClienteRequest(pedido);

            MulttDigitalCobrancaRequest cobrancaRequest = preencherCobrancaRequest(
                    pedido.getValorTotal(),
                    pedido.getDataPedido().toLocalDate()
            );

            MulttDigitalCobrancaResponse multtDigitalCobrancaResponse = multtDigitalFacade
                    .registrarCobrancaCliente(clienteRequest, cobrancaRequest);

            return preencherCobrancaClienteResponse(multtDigitalCobrancaResponse);
        } catch (Exception e) {
            log.error("Erro ao registrar cobrança: {}", e.getMessage());
            throw new MulttDigitalInteracaoException("Erro ao registrar cobrança: " + e.getMessage());
        }
    }

    private MulttDigitalClienteRequest preencherClienteRequest(Pedido pedido) {
        return MulttDigitalAdapter.preencherClienteRequest(
                pedido.getId().toString(),
                pedido.getCliente().getNome(),
                pedido.getCliente().getCpf(),
                pedido.getCliente().getEmail(),
                pedido.getCliente().getTelefone(),
                true
        );
    }

    private MulttDigitalCobrancaRequest preencherCobrancaRequest(BigDecimal valorTotal, LocalDate dataVencimento) {
        return MulttDigitalAdapter.preencherCobrancaRequest(
                valorTotal,
                TipoPagamento.PIX,
                dataVencimento
        );
    }

    private CobrancaClienteResponse preencherCobrancaClienteResponse(MulttDigitalCobrancaResponse multtDigitalCobrancaResponse) {
        return CobrancaClientePedidoAdapter.preencherRespostaRegistroPagamento(
                multtDigitalCobrancaResponse.getStatus(),
                multtDigitalCobrancaResponse.getInvoiceUrl(),
                multtDigitalCobrancaResponse.getPixCopiaECola(),
                multtDigitalCobrancaResponse.getEncodedImage()
        );
    }
}
