package br.com.aceleraprogramador.gerenciamento_pedidos.model;
import br.com.aceleraprogramador.gerenciamento_pedidos.enuns.StatusPedido;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @Column(name = "data_pedido", nullable = false)
    private LocalDateTime dataPedido;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Column(name = "status", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens;
}
