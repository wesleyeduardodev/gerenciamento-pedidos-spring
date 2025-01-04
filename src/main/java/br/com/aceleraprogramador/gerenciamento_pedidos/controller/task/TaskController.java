package br.com.aceleraprogramador.gerenciamento_pedidos.controller.task;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.TaskRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.TaskResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.service.TaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Gerenciamento de Tarefas")
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController implements TaskAPI {

    private final TaskService taskService;

    @Override
    @PostMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public TaskResponse criarTarefa(@Valid @RequestBody TaskRequest request) {
        return taskService.criarTask(request);
    }

    @Override
    @GetMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public List<TaskResponse> buscarTodosAsTarefas() {
        return taskService.buscarTodasAsTasks();
    }

    @Override
    @GetMapping(value = "/v1/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public TaskResponse buscarTarefaPorId(@PathVariable Long id) {
        return taskService.buscarTaskPorId(id);
    }

    @Override
    @PutMapping(value = "/v1/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public void atualizarTarefa(@PathVariable Long id, @Valid @RequestBody TaskRequest request) {
        taskService.atualizarTarefa(id, request);
    }

    @DeleteMapping(value = "/v1/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public void removerTarefa(@PathVariable Long id) {
        taskService.removerTarefa(id);
    }
}
