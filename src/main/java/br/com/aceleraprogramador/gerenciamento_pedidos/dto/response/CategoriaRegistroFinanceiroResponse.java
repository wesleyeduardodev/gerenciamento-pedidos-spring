package br.com.aceleraprogramador.gerenciamento_pedidos.dto.response;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoriaRegistroFinanceiroResponse {
    private Long id;
    private String nome;
    private String descricao;
}
