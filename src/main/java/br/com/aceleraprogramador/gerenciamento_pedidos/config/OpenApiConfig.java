package br.com.aceleraprogramador.gerenciamento_pedidos.config;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

@OpenAPIDefinition(
        info = @Info(
                title = "API Gerenciamento de Pedidos",
                version = "0.0.1",
                description = "API para gerenciar pedidos",
                contact = @Contact(name = "Acelera Programador", email = "wesley@aceleraprogramador.com.br", url = "https://multt.digital/area-de-membros/aceleraprogramador"),
                license = @License(name = "Licença MIT", url = "https://opensource.org/licenses/MIT")
        )
)
@Configuration
public class OpenApiConfig {

    @Bean
    public OperationCustomizer customizeGlobalResponses() {
        return (Operation operation, HandlerMethod handlerMethod) -> {
            ApiResponses apiResponses = operation.getResponses();
            apiResponses.addApiResponse("401", new ApiResponse().description("Unauthorized - Acesso não autorizado"));
            apiResponses.addApiResponse("500", new ApiResponse().description("Internal server erro - Erro interno do servidor"));
            return operation;
        };
    }
}