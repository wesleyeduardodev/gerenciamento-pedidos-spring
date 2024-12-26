package br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.api;
import br.com.aceleraprogramador.gerenciamento_pedidos.exceptions.MulttDigitalInteracaoException;
import br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.config.MulttDigitalConfig;
import br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.dto.request.MulttDigitalClienteRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.dto.response.MulttDigitalClienteResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.utils.ObjectMapperUtilsConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class MulttDigitalClienteAPI {

    private final MulttDigitalConfig multtDigitalConfig;
    private final MulttDigitalHelper multtDigitalHelper;

    public MulttDigitalClienteResponse criarCliente(MulttDigitalClienteRequest request) {
        try {

            log.info("Criando cliente no ambiente de integração de pagamentos...");
            log.info("Request criarCliente JSON: {}", ObjectMapperUtilsConfig.pojoParaJson(request));

            String url = multtDigitalConfig.getBaseUrl() + "/clientes/v1";
            Map<String, String> headers = Map.of(
                    "accept", "application/json",
                    "Authorization", "Bearer " + multtDigitalConfig.getToken(),
                    "Content-Type", "application/json"
            );

            MulttDigitalClienteResponse responseBody = multtDigitalHelper.executeRequest(url, HttpMethod.POST, request, MulttDigitalClienteResponse.class, headers);

            log.info("Cliente criado com sucesso no ambiente de integração de pagamentos.");
            log.info("Response criarCliente JSON: {}", ObjectMapperUtilsConfig.pojoParaJson(responseBody));

            return responseBody;

        } catch (Exception e) {
            log.error("Erro ao criar cliente no ambiente de pagamentos. {}", e.getMessage());
            throw new MulttDigitalInteracaoException(e.getMessage());
        }
    }
}