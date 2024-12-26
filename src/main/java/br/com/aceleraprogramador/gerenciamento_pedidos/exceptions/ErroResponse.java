package br.com.aceleraprogramador.gerenciamento_pedidos.exceptions;
import br.com.aceleraprogramador.gerenciamento_pedidos.utils.DateTimeUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    private LocalDateTime dateTime;

    private String path;
    private List<CampoErroResponse> camposErroResponse;

    public String getData(){
        return DateTimeUtil.toString(dateTime);
    }
}
