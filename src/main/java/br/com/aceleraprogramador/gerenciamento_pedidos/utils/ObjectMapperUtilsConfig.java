package br.com.aceleraprogramador.gerenciamento_pedidos.utils;
import br.com.aceleraprogramador.gerenciamento_pedidos.exceptions.FalhaAoConverterJsonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class ObjectMapperUtilsConfig {

    public static String pojoParaJson(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Falha ao converter pojoParaJson.{}", e.getLocalizedMessage());
            throw new FalhaAoConverterJsonException(e.getLocalizedMessage());
        }
    }

    public static Object jsonParaPojo(String json, Class<?> objeto) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return objectMapper.readValue(json, objeto);
        } catch (JsonProcessingException e) {
            log.error("Falha ao converter jsonParaPojo.{}", e.getLocalizedMessage());
            throw new FalhaAoConverterJsonException(e.getLocalizedMessage());
        }
    }
}