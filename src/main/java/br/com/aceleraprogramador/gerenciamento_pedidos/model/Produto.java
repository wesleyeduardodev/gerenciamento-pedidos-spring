package br.com.aceleraprogramador.gerenciamento_pedidos.model;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "preco", precision = 10, scale = 2, nullable = false)
    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name = "id_fornecedor", nullable = false)
    private Fornecedor fornecedor;
}
