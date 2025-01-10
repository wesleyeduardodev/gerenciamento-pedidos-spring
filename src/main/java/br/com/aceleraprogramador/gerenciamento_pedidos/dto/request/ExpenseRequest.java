package br.com.aceleraprogramador.gerenciamento_pedidos.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseRequest {

    @NotNull(message = "O valor é obrigatório.")
    @Min(value = 0, message = "O valor deve ser maior ou igual a 0.")
    private BigDecimal valor;

    @NotNull(message = "O ID da categoria é obrigatório.")
    private Long categoriaId;

    @NotNull(message = "A data e hora são obrigatórias.")
    private LocalDateTime dataHora;
}
