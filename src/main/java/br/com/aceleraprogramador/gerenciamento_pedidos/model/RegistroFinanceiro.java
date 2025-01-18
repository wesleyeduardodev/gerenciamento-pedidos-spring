package br.com.aceleraprogramador.gerenciamento_pedidos.model;
import br.com.aceleraprogramador.gerenciamento_pedidos.enuns.TipoRegistroFinanceiro;
import br.com.aceleraprogramador.gerenciamento_pedidos.enuns.TipoTransacaoFinanceira;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "registro_financeiro")
public class RegistroFinanceiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false, length = 50)
    private String titulo;

    @Column(name = "descricao", length = 100)
    private String descricao;

    @Enumerated
    @Column(name = "tipo_registro", nullable = false)
    private TipoRegistroFinanceiro tipoRegistro;

    @Enumerated
    @Column(name = "tipo_transacao", nullable = false)
    private TipoTransacaoFinanceira tipoTransacao;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_categoria", nullable = false)
    private CategoriaRegistroFinanceiro categoria;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_subcategoria")
    private SubCategoriaRegistroFinanceiro subCategoria;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(name = "data_transacao", nullable = false)
    private LocalDateTime dataTransacao;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @Column(name = "data_alteracao")
    @UpdateTimestamp
    private LocalDateTime dataAlteracao;
}
