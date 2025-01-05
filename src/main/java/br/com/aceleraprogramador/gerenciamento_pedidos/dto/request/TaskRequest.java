package br.com.aceleraprogramador.gerenciamento_pedidos.dto.request;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskRequest {

    @Schema(description = "Nome da tarefa", example = "Ir ao banco")
    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
    private String title;

    @Schema(description = "Descrição da tarefa", example = "Resolver assuntos da conta")
    @Size(min = 3, max = 255, message = "O nome deve ter entre 3 e 255 caracteres.")
    private String description;

    @Schema(description = "Se a tarefa está completada ou não", example = "true")
    private Boolean completed;

    @Schema(description = "Se a tarefa tem alarme ou não", example = "true")
    private Boolean hasAlarm;

    @Schema(description = "Data hora do alarme")
    private String alarmTime;
}
