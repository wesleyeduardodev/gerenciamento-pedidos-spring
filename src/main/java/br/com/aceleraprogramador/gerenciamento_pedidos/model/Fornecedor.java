package br.com.aceleraprogramador.gerenciamento_pedidos.model;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "fornecedor")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "cnpj", nullable = false, length = 14)
    private String cnpj;

    @Column(name = "contato")
    private String contato;

    @Column(name = "endereco")
    private String endereco;
}
