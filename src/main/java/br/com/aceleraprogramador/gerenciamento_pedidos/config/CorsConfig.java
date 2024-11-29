package br.com.aceleraprogramador.gerenciamento_pedidos.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permite todos os endpoints
                .allowedOrigins("*") // Permite requisições de qualquer origem
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permite todos os métodos HTTP
                .allowedHeaders("*") // Permite todos os cabeçalhos
                .allowCredentials(false); // Desabilita cookies compartilhados
    }
}