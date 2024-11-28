package br.com.aceleraprogramador.gerenciamento_pedidos.adapter;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.PedidoRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.PedidoResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.enuns.StatusPedido;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Cliente;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Pedido;
import br.com.aceleraprogramador.gerenciamento_pedidos.utils.DateTimeUtil;
import lombok.experimental.UtilityClass;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class PedidoAdapter {

    public static Pedido toEntity(PedidoRequest request) {
        return Pedido
                .builder()
                .cliente(Cliente.builder().id(request.getIdCliente()).build())
                .dataPedido(DateTimeUtil.toLocalDateTime(request.getDataPedido()))
                .status(StatusPedido.valueOf(request.getStatus()))
                .build();
    }

    public static PedidoResponse toResponse(Pedido entity) {
        return PedidoResponse
                .builder()
                .id(entity.getId())
                .cliente(ClienteAdapter.toResponse(entity.getCliente()))
                .dataPedido(DateTimeUtil.toString(entity.getDataPedido()))
                .status(entity.getStatus().name())
                .build();
    }

    public static List<PedidoResponse> toResponseList(List<Pedido> entities) {
        return entities
                .stream()
                .map(PedidoAdapter::toResponse)
                .collect(Collectors.toList());
    }
}
