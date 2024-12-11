package br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.service;
import br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.dto.request.PagamentoIntegracaoRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.dto.response.PagamentoIntegracaoResponse;
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
public class IntegracaoPagamentoService {

    private final RestTemplate restTemplate;

    @Value("${api.multt.base-url}")
    private String baseUrl;

    @Value("${api.multt.auth-token}")
    private String token;

    public PagamentoIntegracaoResponse criarPagamento(PagamentoIntegracaoRequest pagamentoIntegracaoRequest) {
        try {

            log.info("Criando pagamento no ambiente de integração de pagamentos...");
            log.info("JSON: {}", ObjectMapperUtilsConfig.pojoParaJson(pagamentoIntegracaoRequest));

            HttpHeaders headers = new HttpHeaders();
            headers.add("accept", "application/json");
            headers.add("Authorization", "Bearer " + token);
            headers.add("Content-Type", "application/json");

            HttpEntity<PagamentoIntegracaoRequest> requestEntity = new HttpEntity<>(pagamentoIntegracaoRequest, headers);

            String url = baseUrl + "/cobrancas/v1";

            ResponseEntity<PagamentoIntegracaoResponse> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    requestEntity,
                    PagamentoIntegracaoResponse.class
            );

            log.info("Pagamento registrado com sucesso no ambiente de integração de pagamentos.");

            return response.getBody();

        } catch (Exception e) {
            log.error("Erro ao criar pagamento no ambiente de pagamentos. {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
