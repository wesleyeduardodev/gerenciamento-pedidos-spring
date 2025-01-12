package br.com.aceleraprogramador.gerenciamento_pedidos.enuns;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum TipoRegistroFinanceiro {

    ENTRADA(0, "Entrada"),
    SAIDA(1, "Saída");

    private final int codigo;
    private final String descricao;

    public static TipoRegistroFinanceiro valueOfCodigo(Integer codigo) {
        return Arrays.stream(TipoRegistroFinanceiro.values())
                .filter(tipo -> tipo.getCodigo() == codigo)
                .findFirst()
                .orElse(null);
    }
}
