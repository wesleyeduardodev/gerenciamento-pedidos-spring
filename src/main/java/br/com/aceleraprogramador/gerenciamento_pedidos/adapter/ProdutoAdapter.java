package br.com.aceleraprogramador.gerenciamento_pedidos.adapter;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.ProdutoRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.ProdutoResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Fornecedor;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Produto;
import lombok.experimental.UtilityClass;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ProdutoAdapter {

    public static Produto toEntity(ProdutoRequest request) {
        return Produto
                .builder()
                .nome(request.getNome())
                .descricao(request.getDescricao())
                .preco(request.getPreco())
                .fornecedor(Fornecedor.builder().id(request.getIdFornecedor()).build())
                .build();
    }

    public static ProdutoResponse toResponse(Produto entity) {
        return ProdutoResponse
                .builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .descricao(entity.getDescricao())
                .preco(entity.getPreco())
                .idFornecedor(entity.getFornecedor().getId())
                .build();
    }

    public static void toUpdate(Produto entity, ProdutoRequest request) {
        entity.setNome(request.getNome());
        entity.setDescricao(request.getDescricao());
        entity.setPreco(request.getPreco());
        entity.setFornecedor(Fornecedor.builder().id(request.getIdFornecedor()).build());
    }

    public static List<ProdutoResponse> toResponseList(List<Produto> entities) {
        return entities
                .stream()
                .map(ProdutoAdapter::toResponse)
                .collect(Collectors.toList());
    }
}
