package br.com.aceleraprogramador.gerenciamento_pedidos.exceptions;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErroResponse {
    private int status;
    private String mensagem;
    private LocalDateTime data;
    private String path;
    private List<CampoErroResponse> camposErroResponse;
}
