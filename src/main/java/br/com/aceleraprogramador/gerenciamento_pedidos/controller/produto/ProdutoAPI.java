package br.com.aceleraprogramador.gerenciamento_pedidos.controller.produto;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.ProdutoRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.ProdutoResponse;
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
import java.math.BigDecimal;

public interface ProdutoAPI {


    @Operation(summary = "Criar um Produto")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Produto criado com sucesso.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProdutoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request - Requisição inválida", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErroResponse.class)))
    })
    ProdutoResponse criarProduto(ProdutoRequest request);

    @Operation(summary = "Buscar todos os Produtos por parâmetros")
    @ApiResponse(responseCode = "200", description = "Produtos Retornados com sucesso.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = PageResponse.class))))
    PageResponse<ProdutoResponse> buscarTodosOsProdutosPorParametros(@Parameter(description = "Id do Produto") Long id,
                                                                     @Parameter(description = "Nome do Produto") String nome,
                                                                     @Parameter(description = "Descrição do Produto") String descricao,
                                                                     @Parameter(description = "Preço do Produto") BigDecimal preco,
                                                                     @Parameter(description = "Id do Fornecedor do Produto") Long idFornecedor,
                                                                     @Parameter(description = "Número da página (padrão: 0)", schema = @Schema(type = "integer", defaultValue = "0")) Integer pageNumber,
                                                                     @Parameter(description = "Tamanho da página (padrão: 10)", schema = @Schema(type = "integer", defaultValue = "10")) Integer pageSize,
                                                                     @Parameter(description = "Campo a ser ordenado (padrão: id)", schema = @Schema(type = "string", defaultValue = "id")) String sortBy,
                                                                     @Parameter(description = "Direção da ordenação (padrão: ASC)", schema = @Schema(type = "string", defaultValue = "ASC")) String sortDirection);


    @Operation(summary = "Buscar Produto por ID")
    @ApiResponse(responseCode = "200", description = "Produto Retornado com sucesso.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProdutoResponse.class)))
    ProdutoResponse buscarProdutoPorId(@Parameter(description = "Id do Produto", required = true) Long id);


    @Operation(summary = "Atualizar todos os dados do Produto")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Produto atualizado com sucesso.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "400", description = "Bad Request - Requisição inválida", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErroResponse.class)))
    })
    void atualizarTodosOsDadosDoProduto(@Parameter(description = "Id do Produto", required = true) Long id, ProdutoRequest request);

    @Operation(summary = "Remover Produto por ID")
    @ApiResponse(responseCode = "204", description = "Produto removido com sucesso.")
    void removerProduto(@Parameter(description = "Id do Produto", required = true) Long id);
}
