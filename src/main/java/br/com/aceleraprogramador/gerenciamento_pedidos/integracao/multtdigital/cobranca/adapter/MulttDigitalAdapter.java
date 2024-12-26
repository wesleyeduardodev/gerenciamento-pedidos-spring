package br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.adapter;
import br.com.aceleraprogramador.gerenciamento_pedidos.enuns.TipoPagamento;
import br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.dto.request.MulttDigitalClienteRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.dto.request.MulttDigitalCobrancaRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.utils.DateUtil;
import lombok.experimental.UtilityClass;
import java.math.BigDecimal;
import java.time.LocalDate;

@UtilityClass
public class MulttDigitalAdapter {

    public static MulttDigitalCobrancaRequest preencherCobrancaRequest(String customer, BigDecimal valor, TipoPagamento tipoPagamento, LocalDate dataVencimento) {
        return MulttDigitalCobrancaRequest
                .builder()
                .customer(customer)
                .billingType(tipoPagamento.name())
                .value(valor)
                .dueDate(DateUtil.convertLocalDateToString(dataVencimento))
                .build();
    }

    public static MulttDigitalCobrancaRequest preencherCobrancaRequest(BigDecimal valor, TipoPagamento tipoPagamento, LocalDate dataVencimento) {
        return MulttDigitalCobrancaRequest
                .builder()
                .billingType(tipoPagamento.name())
                .value(valor)
                .dueDate(DateUtil.convertLocalDateToString(dataVencimento))
                .build();
    }

    public static MulttDigitalClienteRequest preencherClienteRequest(
            String externalReference,
            String name,
            String cpfCnpj,
            String email,
            String mobilePhone,
            boolean notificationDisabled) {
        return MulttDigitalClienteRequest
                .builder()
                .externalReference(externalReference)
                .name(name)
                .cpfCnpj(cpfCnpj)
                .email(email)
                .mobilePhone(mobilePhone)
                .notificationDisabled(notificationDisabled)
                .build();
    }
}
