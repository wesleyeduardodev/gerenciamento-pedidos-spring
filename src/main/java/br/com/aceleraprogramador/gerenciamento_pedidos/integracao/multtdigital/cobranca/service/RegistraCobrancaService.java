package br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.service;
import br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.dto.request.RegistraCobrancaRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.dto.response.RegistraCobrancaResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.utils.ObjectMapperUtilsConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistraCobrancaService {

    private final RestTemplate restTemplate;

    @Value("${api.multt.base-url}")
    private String baseUrl;

    @Value("${api.multt.auth-token}")
    private String token;

    public RegistraCobrancaResponse criarCobranca(RegistraCobrancaRequest registraCobrancaRequest) {
        try {

            log.info("Criando cobrança no ambiente de integração de pagamentos...");
            log.info("Request criarPagamento JSON: {}", ObjectMapperUtilsConfig.pojoParaJson(registraCobrancaRequest));

            HttpHeaders headers = new HttpHeaders();
            headers.add("accept", "application/json");
            headers.add("Authorization", "Bearer " + token);
            headers.add("Content-Type", "application/json");

            HttpEntity<RegistraCobrancaRequest> requestEntity = new HttpEntity<>(registraCobrancaRequest, headers);

            String url = baseUrl + "/cobrancas/v1";

            ResponseEntity<RegistraCobrancaResponse> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    requestEntity,
                    RegistraCobrancaResponse.class
            );

            RegistraCobrancaResponse responseBody = response.getBody();
            log.info("Cobrança registrado com sucesso no ambiente de integração de pagamentos.");
            log.info("Response criarPagamento JSON: {}", ObjectMapperUtilsConfig.pojoParaJson(registraCobrancaRequest));

            return responseBody;

        } catch (Exception e) {
            log.error("Erro ao criar cobrança no ambiente de pagamentos. {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
