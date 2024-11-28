package br.com.aceleraprogramador.gerenciamento_pedidos.adapter;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.FornecedorRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.FornecedorResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Fornecedor;
import lombok.experimental.UtilityClass;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class FornecedorAdapter {

    public static Fornecedor toEntity(FornecedorRequest request) {
        return Fornecedor
                .builder()
                .nome(request.getNome())
                .cnpj(request.getCnpj())
                .contato(request.getContato())
                .endereco(request.getEndereco())
                .build();
    }

    public static void toUpdate(Fornecedor entity, FornecedorRequest request) {
        entity.setNome(request.getNome());
        entity.setCnpj(request.getCnpj());
        entity.setContato(request.getContato());
        entity.setEndereco(request.getEndereco());
    }

    public static FornecedorResponse toResponse(Fornecedor entity) {
        return FornecedorResponse
                .builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .cnpj(entity.getCnpj())
                .contato(entity.getContato())
                .endereco(entity.getEndereco())
                .build();
    }

    public static List<FornecedorResponse> toResponseList(List<Fornecedor> entities) {
        return entities
                .stream()
                .map(FornecedorAdapter::toResponse)
                .collect(Collectors.toList());
    }
}
