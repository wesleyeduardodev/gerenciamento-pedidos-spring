package br.com.aceleraprogramador.gerenciamento_pedidos.dto.request;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FornecedorRequest {

    @Schema(description = "Nome completo do fornecedor", example = "João Silva")
    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres.")
    private String nome;

    @Schema(description = "CNPJ fornecedor", example = "12345678000200")
    @NotBlank(message = "O cnpj é obrigatório")
    @Size(min = 14, max = 14, message = "O cnpj deve ter exatamente 14 caracteres.")
    private String cnpj;

    @Schema(description = "Informações de contato do fornecedor", example = "(11) 98765-4321 / contato@fornecedores.com")
    @Size(min = 3, max = 255, message = "O contato deve ter entre 3 e 255 caracteres.")
    private String contato;

    @Schema(description = "Endereço do fornecedor", example = "Avenida Central, São Paulo - SP")
    @Size(min = 3, max = 255, message = "O endereço deve ter entre 3 e 150 caracteres.")
    private String endereco;
}
