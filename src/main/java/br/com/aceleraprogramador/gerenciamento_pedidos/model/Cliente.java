package br.com.aceleraprogramador.gerenciamento_pedidos.model;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cliente")
@NamedNativeQuery(
        name = "Cliente.findClienteResponseByProfissaoNative",
        query = "SELECT id, nome, email, telefone, endereco, profissao FROM cliente WHERE profissao = :profissao",
        resultSetMapping = "ClienteResponseMapping"
)
@SqlResultSetMapping(
        name = "ClienteResponseMapping",
        classes = @ConstructorResult(
                targetClass = br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.ClienteResponse.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "nome", type = String.class),
                        @ColumnResult(name = "email", type = String.class),
                        @ColumnResult(name = "telefone", type = String.class),
                        @ColumnResult(name = "endereco", type = String.class),
                        @ColumnResult(name = "profissao", type = String.class)
                }
        )
)
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "cpf", nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "profissao")
    private String profissao;
}
