package br.com.aceleraprogramador.gerenciamento_pedidos.model;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "subcategoria_registro_financeiro")
public class SubCategoriaRegistroFinanceiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, unique = true, length = 50)
    private String nome;

    @Column(name = "descricao", length = 250)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_categoria_registro_financeiro", nullable = false)
    private CategoriaRegistroFinanceiro categoria;

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
