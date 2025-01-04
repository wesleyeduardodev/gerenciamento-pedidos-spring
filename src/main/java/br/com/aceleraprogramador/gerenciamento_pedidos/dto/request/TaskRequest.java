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
    @Size(min = 3, max = 255, message = "O nome deve ter entre 3 e 100 caracteres.")
    private String name;

    @Schema(description = "Se a tarefa está completada ou não", example = "true")
    private boolean isCompleted;
}
