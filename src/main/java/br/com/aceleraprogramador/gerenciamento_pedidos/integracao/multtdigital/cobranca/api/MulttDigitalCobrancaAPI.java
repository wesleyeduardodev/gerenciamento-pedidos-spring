package br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.api;
import br.com.aceleraprogramador.gerenciamento_pedidos.exceptions.MulttDigitalInteracaoException;
import br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.config.MulttDigitalConfig;
import br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.dto.request.MulttDigitalCobrancaRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.dto.response.MulttDigitalCobrancaResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.utils.ObjectMapperUtilsConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class MulttDigitalCobrancaAPI {

    private final MulttDigitalConfig multtDigitalConfig;
    private final MulttDigitalHelper multtDigitalHelper;

    public MulttDigitalCobrancaResponse registrarCobranca(MulttDigitalCobrancaRequest request) {
        try {

            log.info("Resgistrando cobrança na API...");
            log.info("Request registrarCobranca JSON: {}", ObjectMapperUtilsConfig.pojoParaJson(request));

            String url = multtDigitalConfig.getBaseUrl() + "/cobrancas/v1";
            Map<String, String> headers = Map.of(
                    "accept", "application/json",
                    "Authorization", "Bearer " + multtDigitalConfig.getToken(),
                    "Content-Type", "application/json"
            );

            MulttDigitalCobrancaResponse responseBody = multtDigitalHelper.executeRequest(url, HttpMethod.POST, request, MulttDigitalCobrancaResponse.class, headers);

            log.info("Cobrança registrado com sucesso na API.");
            log.info("Response registrarCobranca JSON: {}", ObjectMapperUtilsConfig.pojoParaJson(responseBody));

            return responseBody;

        } catch (Exception e) {
            log.error("Erro ao registrar cobrança na API. {}", e.getMessage());
            throw new MulttDigitalInteracaoException(e.getMessage());
        }
    }
}
