package br.com.aceleraprogramador.gerenciamento_pedidos.controller.fornecedor;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.FornecedorRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.FornecedorResponse;
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

public interface FornecedorAPI {


    @Operation(summary = "Criar um Fornecedor")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Fornecedor criado com sucesso.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = FornecedorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request - Requisição inválida", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErroResponse.class)))
    })
    FornecedorResponse criarFornecedor(FornecedorRequest request);

    @Operation(summary = "Buscar todos os fornecedores por parâmetros")
    @ApiResponse(responseCode = "200", description = "Fornecedores Retornados com sucesso.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = PageResponse.class))))
    PageResponse<FornecedorResponse> buscarTodosOsFornecedoresPorParametros(@Parameter(description = "Id do fornecedor") Long id,
                                                                            @Parameter(description = "Nome do fornecedor") String nome,
                                                                            @Parameter(description = "Cnpj do fornecedor") String cnpj,
                                                                            @Parameter(description = "Contato do fornecedor") String contato,
                                                                            @Parameter(description = "Endereço do fornecedor") String endereco,
                                                                            @Parameter(description = "Número da página (padrão: 0)", schema = @Schema(type = "integer", defaultValue = "0")) Integer pageNumber,
                                                                            @Parameter(description = "Tamanho da página (padrão: 10)", schema = @Schema(type = "integer", defaultValue = "10")) Integer pageSize,
                                                                            @Parameter(description = "Campo a ser ordenado (padrão: id)", schema = @Schema(type = "string", defaultValue = "id")) String sortBy,
                                                                            @Parameter(description = "Direção da ordenação (padrão: ASC)", schema = @Schema(type = "string", defaultValue = "ASC")) String sortDirection);


    @Operation(summary = "Buscar Forncedor por ID")
    @ApiResponse(responseCode = "200", description = "Fornecedor Retornado com sucesso.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = FornecedorResponse.class)))
    FornecedorResponse buscarFornecedorPorId(@Parameter(description = "Id do fornecedor", required = true) Long id);


    @Operation(summary = "Atualizar todos os dados do fornecedor")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Fornecedor atualizado com sucesso.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "400", description = "Bad Request - Requisição inválida", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErroResponse.class)))
    })
    void atualizarTodosOsDadosDoFornecedor(@Parameter(description = "Id do fornecedor", required = true) Long id, FornecedorRequest request);

    @Operation(summary = "Remover Fornecedor por ID")
    @ApiResponse(responseCode = "204", description = "Fornecedor removido com sucesso.")
    void removerFornecedor(@Parameter(description = "Id do fornecedor", required = true) Long id);
}
