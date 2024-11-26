package br.com.aceleraprogramador.gerenciamento_pedidos.exceptions;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CampoErroResponse {
    private String campo;
    private List<String> erros;
}
