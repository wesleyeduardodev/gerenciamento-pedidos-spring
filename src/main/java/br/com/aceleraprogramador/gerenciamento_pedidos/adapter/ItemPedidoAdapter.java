package br.com.aceleraprogramador.gerenciamento_pedidos.adapter;

import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.ItemPedidoRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.ItemPedidoResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.ItemPedido;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Pedido;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Produto;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ItemPedidoAdapter {

    public static ItemPedido toEntity(ItemPedidoRequest request) {
        return ItemPedido
                .builder()
                .produto(Produto.builder().id(request.getIdProduto()).build())
                .quantidade(request.getQuantidade())
                .precoUnitario(request.getPrecoUnitario())
                .build();
    }

    public static ItemPedido toEntity(Pedido pedido, ItemPedidoRequest request) {
        return ItemPedido
                .builder()
                .produto(Produto.builder().id(request.getIdProduto()).build())
                .quantidade(request.getQuantidade())
                .precoUnitario(request.getPrecoUnitario())
                .pedido(pedido)
                .build();
    }

    public static List<ItemPedido> toEntities(List<ItemPedidoRequest> requests) {
        return requests
                .stream()
                .map(ItemPedidoAdapter::toEntity)
                .collect(Collectors.toList());
    }

    public static List<ItemPedido> toEntities(Pedido pedido, List<ItemPedidoRequest> requests) {
        return requests
                .stream()
                .map(i -> toEntity(pedido, i))
                .collect(Collectors.toList());
    }

    public static ItemPedidoResponse toResponse(ItemPedido entity) {
        return ItemPedidoResponse
                .builder()
                .id(entity.getId())
                .idPedido(entity.getPedido().getId())
                .idProduto(entity.getProduto().getId())
                .quantidade(entity.getQuantidade())
                .precoUnitario(entity.getPrecoUnitario())
                .build();
    }

    public static List<ItemPedidoResponse> toResponseList(List<ItemPedido> entities) {
        return entities
                .stream()
                .map(ItemPedidoAdapter::toResponse)
                .collect(Collectors.toList());
    }
}
