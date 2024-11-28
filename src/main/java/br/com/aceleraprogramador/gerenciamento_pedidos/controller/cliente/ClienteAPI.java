package br.com.aceleraprogramador.gerenciamento_pedidos.controller.cliente;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.CreateClienteRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.UpdateClienteRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.ClienteResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.PageResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.exceptions.ErroResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import java.util.List;

public interface ClienteAPI {


    @Operation(summary = "Criar um cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ClienteResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request - Requisição inválida", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErroResponse.class)))
    })
    ClienteResponse criarCliente(CreateClienteRequest request);

    @Operation(summary = "Buscar todos os cliente")
    @ApiResponse(responseCode = "200", description = "Clientes Retornados com sucesso.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = PageResponse.class))))
    PageResponse<ClienteResponse> buscarTodosOsClientes(@Parameter(description = "Número da página (padrão: 0)", schema = @Schema(type = "integer", defaultValue = "0")) Integer pageNumber,
                                                        @Parameter(description = "Tamanho da página (padrão: 10)", schema = @Schema(type = "integer", defaultValue = "10")) Integer pageSize,
                                                        @Parameter(description = "Campo a ser ordenado (padrão: id)", schema = @Schema(type = "string", defaultValue = "id")) String sortBy,
                                                        @Parameter(description = "Direção da ordenação (padrão: ASC)", schema = @Schema(type = "string", defaultValue = "ASC")) String sortDirection);

    @Operation(summary = "Buscar todos os cliente por parâmetros")
    @ApiResponse(responseCode = "200", description = "Clientes Retornados com sucesso.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = PageResponse.class))))
    PageResponse<ClienteResponse> buscarTodosOsClientesPorParametros(@Parameter(description = "Id do cliente") Long id,
                                                                     @Parameter(description = "Nome do cliente") String nome,
                                                                     @Parameter(description = "Email do cliente") String email,
                                                                     @Parameter(description = "Telefone do cliente") String telefone,
                                                                     @Parameter(description = "Endereço do cliente") String endereco,
                                                                     @Parameter(description = "Profissão do cliente") String profissao,
                                                                     @Parameter(description = "Número da página (padrão: 0)", schema = @Schema(type = "integer", defaultValue = "0")) Integer pageNumber,
                                                                     @Parameter(description = "Tamanho da página (padrão: 10)", schema = @Schema(type = "integer", defaultValue = "10")) Integer pageSize,
                                                                     @Parameter(description = "Campo a ser ordenado (padrão: id)", schema = @Schema(type = "string", defaultValue = "id")) String sortBy,
                                                                     @Parameter(description = "Direção da ordenação (padrão: ASC)", schema = @Schema(type = "string", defaultValue = "ASC")) String sortDirection);

    @Operation(summary = "Buscar cliente por ID")
    @ApiResponse(responseCode = "200", description = "Cliente Retornado com sucesso.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ClienteResponse.class)))
    ClienteResponse buscarClientePorId(@Parameter(description = "Id do cliente", required = true) Long idCliente);

    @Operation(summary = "Buscar clientes por nome")
    @ApiResponse(responseCode = "200", description = "Clientes Retornado com sucesso.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ClienteResponse.class)))
    List<ClienteResponse> buscarClientesPorNome(String nome);

    @Operation(summary = "Buscar clientes por email")
    @ApiResponse(responseCode = "200", description = "Clientes Retornado com sucesso.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ClienteResponse.class)))
    List<ClienteResponse> buscarClientesPorEmail(String email);

    @Operation(summary = "Buscar clientes por profissao")
    @ApiResponse(responseCode = "200", description = "Clientes Retornado com sucesso.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ClienteResponse.class)))
    List<ClienteResponse> buscarClientesPorProfissao(String profissao);

    @Operation(summary = "Buscar clientes por nome, email ou  profissao")
    @ApiResponse(responseCode = "200", description = "Clientes Retornado com sucesso.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ClienteResponse.class)))
    List<ClienteResponse> buscarClientePorNomeEmailProfissao(String nome, String email, String profissao);

    @Operation(summary = "Atualizar todos os dados do cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cliente atualizado com sucesso.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ClienteResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request - Requisição inválida", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErroResponse.class)))
    })
    void atualizarTodosOsDadosDoCliente(@Parameter(description = "Id do cliente", required = true) Long idCliente, UpdateClienteRequest request);

    @Operation(summary = "Atualizar parcialmente os dados do cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cliente parcialmente atualizado com sucesso.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ClienteResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request - Requisição inválida", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErroResponse.class)))
    })
    ClienteResponse atualizarParcialmenteOsDadosDoCliente(@Parameter(description = "Id do cliente", required = true) Long idCliente, UpdateClienteRequest request);

    @Operation(summary = "Remover cliente por ID")
    @ApiResponse(responseCode = "204", description = "Cliente removido com sucesso.")
    void removerCliente(@Parameter(description = "Id do cliente", required = true) Long idCliente);
}
