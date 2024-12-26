package br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.dto.response;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MulttDigitalClienteResponse {
    @JsonProperty("id")
    private String id;
}
