
package br.com.aceleraprogramador.gerenciamento_pedidos.dto.response;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FornecedorResponse {
    private Long id;
    private String nome;
    private String cnpj;
    private String contato;
    private String endereco;
}
