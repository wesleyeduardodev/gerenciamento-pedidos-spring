package br.com.aceleraprogramador.gerenciamento_pedidos.exceptions;

public class FalhaAoConverterJsonException extends RuntimeException {

    public FalhaAoConverterJsonException(String mensagem) {
        super("Falha ao converter classe para json. Detalhes:" + mensagem);
    }
}

