package br.com.aceleraprogramador.gerenciamento_pedidos.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoriaRegistroFinanceiroRequest {

    @NotBlank(message = "O nome da categoria é obrigatório.")
    @Size(max = 50, message = "O nome da categoria deve ter no máximo 50 caracteres.")
    private String nome;
}