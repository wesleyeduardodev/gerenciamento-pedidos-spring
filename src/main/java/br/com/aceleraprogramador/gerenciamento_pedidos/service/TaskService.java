package br.com.aceleraprogramador.gerenciamento_pedidos.service;
import br.com.aceleraprogramador.gerenciamento_pedidos.adapter.TaskAdapter;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.TaskRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.TaskResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.exceptions.RecursoNaoEncontradoException;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Task;
import br.com.aceleraprogramador.gerenciamento_pedidos.repository.TaskRepository;
import br.com.aceleraprogramador.gerenciamento_pedidos.utils.ObjectMapperUtilsConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskResponse criarTask(TaskRequest request) {

        log.info("Criando uma nova Task...");
        log.info("JSON: {}", ObjectMapperUtilsConfig.pojoParaJson(request));

        Task task = TaskAdapter.toEntity(request);
        taskRepository.save(task);
        TaskResponse taskResponse = TaskAdapter.toResponse(task);

        log.info("Task criado com sucesso...");

        return taskResponse;
    }

    public List<TaskResponse> buscarTodasAsTasks() {
        log.info("Buscando todos as tasks...");
        List<Task> tasks = taskRepository.findAll();
        log.info("Tarefas retornadas com sucesso.");
        return TaskAdapter.toResponseList(tasks);
    }

    public TaskResponse buscarTaskPorId(Long id) {
        log.info("Buscando tarefa com ID:{}", id);
        Task task = buscarEntidadeTarefaPorId(id);
        TaskResponse taskResponse = TaskAdapter.toResponse(task);
        log.info("Tarefa retornado com sucesso.");
        return taskResponse;
    }

    public void atualizarTarefa(Long id, TaskRequest request) {
        log.info("Atualizando tarefa com ID: {}", id);
        log.info("JSON: {}", ObjectMapperUtilsConfig.pojoParaJson(request));
        Task task = buscarEntidadeTarefaPorId(id);
        TaskAdapter.toUpdate(task, request);
        taskRepository.save(task);
        log.info("tarefa atualizada com sucesso.");
    }

    public void removerTarefa(Long id) {
        log.info("Removendo tarefa com ID: {}", id);
        Task task = buscarEntidadeTarefaPorId(id);
        taskRepository.delete(task);
        log.info("tarefa removida com sucesso.");
    }

    private Task buscarEntidadeTarefaPorId(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty()) {
            String erro = "Tarefa não encontrado com o ID: " + id;
            throw new RecursoNaoEncontradoException(erro);
        } else {
            return task.get();
        }
    }
}
