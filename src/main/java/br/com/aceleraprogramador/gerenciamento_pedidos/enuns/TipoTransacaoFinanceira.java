package br.com.aceleraprogramador.gerenciamento_pedidos.enuns;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum TipoTransacaoFinanceira {

    PIX(0, "Pix"),
    CARTAO_CREDITO(1, "Cartão de Crédito"),
    CARTAO_DEBITO(2, "Cartão de Débito"),
    DINHEIRO(3, "Dinheiro"),
    BOLETO(4, "Boleto");

    private final int codigo;
    private final String descricao;

    public static TipoTransacaoFinanceira valueOfCodigo(Integer codigo) {
        return Arrays.stream(TipoTransacaoFinanceira.values())
                .filter(tipo -> tipo.getCodigo() == codigo)
                .findFirst()
                .orElse(null);
    }
}
