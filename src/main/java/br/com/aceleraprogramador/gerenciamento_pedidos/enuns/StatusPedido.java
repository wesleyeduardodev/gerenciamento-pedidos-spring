package br.com.aceleraprogramador.gerenciamento_pedidos.enuns;

public enum StatusPedido {
    PENDENTE,
    PROCESSANDO,
    AGUARDANDO_PAGAMENTO,
    COBRANCA_REGISTRADA,
    EM_TRANSPORTE,
    CONCLUIDO,
    CANCELADO,
    DEVOLVIDO,
    FALHA_NA_ENTREGA;
}
