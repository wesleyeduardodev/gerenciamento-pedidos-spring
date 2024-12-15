package br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.dto.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistraCobrancaRequest {

    @JsonProperty("customer")
    private String customer;

    @JsonProperty("billingType")
    private String billingType;

    @JsonProperty("value")
    private BigDecimal value;

    @JsonProperty("dueDate")
    private String dueDate;
}
