package br.com.aceleraprogramador.gerenciamento_pedidos.adapter;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.CreateClienteRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.ClienteResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Cliente;
import lombok.experimental.UtilityClass;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ClienteAdapter {

    public static Cliente toEntity(CreateClienteRequest request) {
        return Cliente
                .builder()
                .nome(request.getNome())
                .email(request.getEmail())
                .cpf(request.getCpf())
                .endereco(request.getEndereco())
                .telefone(request.getTelefone())
                .profissao(request.getProfissao())
                .build();
    }

    public static ClienteResponse toResponse(Cliente cliente) {
        return ClienteResponse
                .builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .cpf(cliente.getCpf())
                .endereco(cliente.getEndereco())
                .telefone(cliente.getTelefone())
                .profissao(cliente.getProfissao())
                .build();
    }

    public static List<ClienteResponse> toResponseList(List<Cliente> clientes) {
        return clientes
                .stream()
                .map(ClienteAdapter::toResponse)
                .collect(Collectors.toList());
    }
}
