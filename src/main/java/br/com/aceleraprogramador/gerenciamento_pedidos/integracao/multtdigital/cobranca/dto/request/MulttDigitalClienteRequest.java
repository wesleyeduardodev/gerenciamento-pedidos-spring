package br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.dto.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MulttDigitalClienteRequest {

    @JsonProperty("externalReference")
    private String externalReference;

    @JsonProperty("name")
    private String name;

    @JsonProperty("cpfCnpj")
    private String cpfCnpj;

    @JsonProperty("email")
    private String email;

    @JsonProperty("mobilePhone")
    private String mobilePhone;

    @JsonProperty("notificationDisabled")
    private boolean notificationDisabled;
}
