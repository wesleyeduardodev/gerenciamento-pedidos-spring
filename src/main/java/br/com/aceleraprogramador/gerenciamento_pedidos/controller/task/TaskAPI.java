package br.com.aceleraprogramador.gerenciamento_pedidos.controller.task;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.TaskRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.TaskResponse;
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

public interface TaskAPI {


    @Operation(summary = "Criar uma Tarefa")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Tarefa criado com sucesso.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = TaskResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request - Requisição inválida", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErroResponse.class)))
    })
    TaskResponse criarTarefa(TaskRequest request);

    @Operation(summary = "Buscar todos os Tarefas por parâmetros")
    @ApiResponse(responseCode = "200", description = "Tarefas Retornados com sucesso.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = TaskResponse.class))))
    List<TaskResponse> buscarTodosAsTarefas();


    @Operation(summary = "Buscar Tarefa por ID")
    @ApiResponse(responseCode = "200", description = "Tarefa Retornado com sucesso.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = TaskResponse.class)))
    TaskResponse buscarTarefaPorId(@Parameter(description = "Id do Tarefa", required = true) Long id);


    @Operation(summary = "Atualizar todos os dados da Tarefa")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Tarefa atualizado com sucesso.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "400", description = "Bad Request - Requisição inválida", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErroResponse.class)))
    })
    void atualizarTarefa(@Parameter(description = "Id do Tarefa", required = true) Long id, TaskRequest request);

    @Operation(summary = "Remover Tarefa por ID")
    @ApiResponse(responseCode = "204", description = "Tarefa removido com sucesso.")
    void removerTarefa(@Parameter(description = "Id do Tarefa", required = true) Long id);
}
