package br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.dto.response;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistraCobrancaResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("status")
    private String status;

    @JsonProperty("invoiceUrl")
    private String invoiceUrl;

    @JsonProperty("pixCopiaECola")
    private String pixCopiaECola;

    @JsonProperty("encodedImage")
    private String encodedImage;
}
