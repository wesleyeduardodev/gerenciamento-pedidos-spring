package br.com.aceleraprogramador.gerenciamento_pedidos.controller.pedido;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.PedidoRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.PageResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.PedidoResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.exceptions.ErroResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;

public interface PedidoAPI {


    @Operation(summary = "Criar um Pedido")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PedidoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request - Requisição inválida", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErroResponse.class)))
    })
    PedidoResponse criarPedido(PedidoRequest request);

    @Operation(summary = "Buscar todos os Pedidos por parâmetros")
    @ApiResponse(responseCode = "200", description = "Pedidos Retornados com sucesso.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = PageResponse.class))))
    PageResponse<PedidoResponse> buscarTodosOsPedidosPorParametros(@Parameter(description = "Id do Pedido") Long id,
                                                                   @Parameter(description = "Id do Cliente") Long idCliente,
                                                                   @Parameter(description = "Status do Pedido") String status,
                                                                   @Parameter(description = "Data do Pedido") String dataPedido,
                                                                   @Parameter(description = "Número da página (padrão: 0)", schema = @Schema(type = "integer", defaultValue = "0")) Integer pageNumber,
                                                                   @Parameter(description = "Tamanho da página (padrão: 10)", schema = @Schema(type = "integer", defaultValue = "10")) Integer pageSize,
                                                                   @Parameter(description = "Campo a ser ordenado (padrão: id)", schema = @Schema(type = "string", defaultValue = "id")) String sortBy,
                                                                   @Parameter(description = "Direção da ordenação (padrão: ASC)", schema = @Schema(type = "string", defaultValue = "ASC")) String sortDirection);


    @Operation(summary = "Buscar Pedido por ID")
    @ApiResponse(responseCode = "200", description = "Pedido Retornado com sucesso.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PedidoResponse.class)))
    PedidoResponse buscarPedidoPorId(@Parameter(description = "Id do Pedido", required = true) Long id);


    @Operation(summary = "Atualizar status do Pedido")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pedido atualizado com sucesso.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "400", description = "Bad Request - Requisição inválida", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErroResponse.class)))
    })
    void atualizarStatusDoPedido(@Parameter(description = "Id do Pedido", required = true) Long id, @Parameter(description = "Status do Pedido", required = true) String status);

    @Operation(summary = "Remover Pedido por ID")
    @ApiResponse(responseCode = "204", description = "Pedido removido com sucesso.")
    void removerPedido(@Parameter(description = "Id do Pedido", required = true) Long id);
}
