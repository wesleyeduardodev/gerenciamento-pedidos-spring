package br.com.aceleraprogramador.gerenciamento_pedidos.dto.response;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistroFinanceiroResponse {
    private Long id;
    private String titulo;
    private String descricao;
    private Integer tipoRegistro;
    private Integer tipoTransacao;
    private Long idCategoria;
    private String nomeCategoria;
    private BigDecimal valor;
    private String dataTransacao;
}
