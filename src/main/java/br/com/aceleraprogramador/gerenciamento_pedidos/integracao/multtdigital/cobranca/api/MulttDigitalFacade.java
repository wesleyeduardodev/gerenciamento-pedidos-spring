package br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.api;
import br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.dto.request.MulttDigitalClienteRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.dto.request.MulttDigitalCobrancaRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.dto.response.MulttDigitalClienteResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.dto.response.MulttDigitalCobrancaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MulttDigitalFacade {

    private final MulttDigitalClienteAPI clienteAPI;
    private final MulttDigitalCobrancaAPI cobrancaAPI;

    public MulttDigitalCobrancaResponse registrarCobrancaCliente(MulttDigitalClienteRequest clienteRequest, MulttDigitalCobrancaRequest cobrancaRequest) {
        MulttDigitalClienteResponse clienteResponse = clienteAPI.criarCliente(clienteRequest);
        cobrancaRequest.setCustomer(clienteResponse.getId());
        return cobrancaAPI.registrarCobranca(cobrancaRequest);
    }
}