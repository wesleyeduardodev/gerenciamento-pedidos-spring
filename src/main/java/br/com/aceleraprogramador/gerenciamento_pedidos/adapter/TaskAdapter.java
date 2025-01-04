package br.com.aceleraprogramador.gerenciamento_pedidos.adapter;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.TaskRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.TaskResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Task;
import br.com.aceleraprogramador.gerenciamento_pedidos.utils.DateTimeUtil;
import lombok.experimental.UtilityClass;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class TaskAdapter {

    public static Task toEntity(TaskRequest request) {
        return Task
                .builder()
                .name(request.getName())
                .isCompleted(request.isCompleted())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static TaskResponse toResponse(Task entity) {
        return TaskResponse
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .isCompleted(entity.isCompleted())
                .createdAt(DateTimeUtil.toString(entity.getCreatedAt()))
                .updatedAt(DateTimeUtil.toString(entity.getUpdatedAt()))
                .build();
    }

    public static void toUpdate(Task entity, TaskRequest request) {
        entity.setName(request.getName());
        entity.setCompleted(request.isCompleted());
        entity.setUpdatedAt(LocalDateTime.now());
    }

    public static List<TaskResponse> toResponseList(List<Task> entities) {
        return entities
                .stream()
                .map(TaskAdapter::toResponse)
                .collect(Collectors.toList());
    }
}
