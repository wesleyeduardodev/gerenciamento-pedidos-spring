package br.com.aceleraprogramador.gerenciamento_pedidos.controller.usuario;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.UsuarioRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.PageResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.UsuarioResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.exceptions.ErroResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;

public interface UsuarioAPI {

    @Operation(summary = "Criar um usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuários criado com sucesso.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request - Requisição inválida", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErroResponse.class)))
    })
    UsuarioResponse criarUsuario(UsuarioRequest request);

    @Operation(summary = "Buscar usuário por ID")
    @ApiResponse(responseCode = "200", description = "Usuário Retornado com sucesso.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UsuarioResponse.class)))
    UsuarioResponse buscarUsuarioPorId(@Parameter(description = "Id do usuário", required = true) Long id);

    @Operation(summary = "Buscar todos os Usuario")
    @ApiResponse(responseCode = "200", description = "Usuarios Retornados com sucesso.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = PageResponse.class))))
    PageResponse<UsuarioResponse> buscarTodosOsUsuarios(@Parameter(description = "Número da página (padrão: 0)", schema = @Schema(type = "integer", defaultValue = "0")) Integer pageNumber,
                                                        @Parameter(description = "Tamanho da página (padrão: 10)", schema = @Schema(type = "integer", defaultValue = "10")) Integer pageSize,
                                                        @Parameter(description = "Campo a ser ordenado (padrão: id)", schema = @Schema(type = "string", defaultValue = "id")) String sortBy,
                                                        @Parameter(description = "Direção da ordenação (padrão: ASC)", schema = @Schema(type = "string", defaultValue = "ASC")) String sortDirection);

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuários atualizado com sucesso.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request - Requisição inválida", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErroResponse.class)))
    })
    void atualizarTodosOsDadosDoUsuario(@Parameter(description = "Id do Usuário", required = true) Long id, UsuarioRequest request);

    @Operation(summary = "Remover usuário por ID")
    @ApiResponse(responseCode = "204", description = "Usuário removido com sucesso.")
    void removerUsuario(@Parameter(description = "Id do usuário", required = true) Long id);
}
