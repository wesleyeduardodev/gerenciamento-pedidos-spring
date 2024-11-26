package br.com.aceleraprogramador.gerenciamento_pedidos.model;
import br.com.aceleraprogramador.gerenciamento_pedidos.enuns.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="roles")
public class ModelRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role name;
}
