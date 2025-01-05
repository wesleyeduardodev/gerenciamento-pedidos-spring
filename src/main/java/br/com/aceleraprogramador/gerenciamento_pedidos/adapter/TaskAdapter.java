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
                .title(request.getTitle())
                .description(request.getDescription())
                .completed(request.getCompleted())
                .hasAlarm(request.getHasAlarm())
                .alarmTime(DateTimeUtil.fromIsoString(request.getAlarmTime()))
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static TaskResponse toResponse(Task entity) {
        return TaskResponse
                .builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .completed(entity.getCompleted())
                .hasAlarm(entity.getHasAlarm())
                .alarmTime(DateTimeUtil.toIsoString(entity.getAlarmTime()))
                .createdAt(DateTimeUtil.toString(entity.getCreatedAt()))
                .updatedAt(DateTimeUtil.toString(entity.getUpdatedAt()))
                .build();
    }

    public static void toUpdate(Task entity, TaskRequest request) {
        entity.setTitle(request.getTitle());
        entity.setDescription(request.getDescription());
        entity.setCompleted(request.getCompleted());
        entity.setHasAlarm(request.getHasAlarm());
        entity.setAlarmTime(DateTimeUtil.fromIsoString(request.getAlarmTime()));
        entity.setUpdatedAt(LocalDateTime.now());
    }

    public static List<TaskResponse> toResponseList(List<Task> entities) {
        return entities
                .stream()
                .map(TaskAdapter::toResponse)
                .collect(Collectors.toList());
    }
}
