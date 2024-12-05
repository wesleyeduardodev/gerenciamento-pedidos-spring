package br.com.aceleraprogramador.gerenciamento_pedidos.dto.response;
import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioResponse {
    private Long id;
    private String nome;
    private String email;
    private List<String> roles;
}
