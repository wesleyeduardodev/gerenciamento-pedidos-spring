package br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.api;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.beanvalidation.IntegrationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MulttDigitalHelper {

    private final RestTemplate restTemplate;

    public <T> T executeRequest(
            String url,
            HttpMethod method,
            Object requestBody,
            Class<T> responseType,
            Map<String, String> headers) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            headers.forEach(httpHeaders::add);
            HttpEntity<Object> requestEntity = new HttpEntity<>(requestBody, httpHeaders);
            ResponseEntity<T> response = restTemplate.exchange(url, method, requestEntity, responseType);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw new IntegrationException("API Error: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new IntegrationException("Unexpected Error: " + e.getMessage(), e);
        }
    }
}
