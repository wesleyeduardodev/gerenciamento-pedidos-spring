package br.com.aceleraprogramador.gerenciamento_pedidos.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseResponse {

    private Long id;
    private BigDecimal valor;
    private String categoria;
    private LocalDateTime dataHora;
}
