package br.com.aceleraprogramador.gerenciamento_pedidos.exceptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> handleRecursoNaoEncontradoException(RecursoNaoEncontradoException ex, WebRequest request) {

        ErroResponse erroResponse = ErroResponse
                .builder()
                .status(HttpStatus.NOT_FOUND.value())
                .mensagem(ex.getMessage())
                .data(LocalDateTime.now())
                .path(request.getDescription(false))
                .build();

        log.error(ex.getMessage(), ex);

        return new ResponseEntity<>(erroResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroResponse> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {

        ErroResponse erroResponse = ErroResponse
                .builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .mensagem(ex.getMessage())
                .data(LocalDateTime.now())
                .path(request.getDescription(false))
                .build();

        log.error(ex.getMessage(), ex);

        return new ResponseEntity<>(erroResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> handleValidationException(MethodArgumentNotValidException ex, WebRequest request) {

        Map<String, List<String>> fieldErrorsMap = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())
                ));

        List<CampoErroResponse> fieldErrors = new ArrayList<>();

        for (Map.Entry<String, List<String>> entry : fieldErrorsMap.entrySet()) {
            CampoErroResponse campoErroResponse = CampoErroResponse
                    .builder()
                    .campo(entry.getKey())
                    .erros(entry.getValue())
                    .build();
            fieldErrors.add(campoErroResponse);
        }

        ErroResponse erroResponse = ErroResponse
                .builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .mensagem("Erro na validação de campos.")
                .camposErroResponse(fieldErrors)
                .data(LocalDateTime.now())
                .path(request.getDescription(false))
                .build();

        log.error(ex.getMessage(), ex);

        return new ResponseEntity<>(erroResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> handleGlobalException(Exception ex, WebRequest request) {

        ErroResponse erroResponse = ErroResponse
                .builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .mensagem(ex.getMessage())
                .data(LocalDateTime.now())
                .path(request.getDescription(false))
                .build();

        log.error(ex.getMessage(), ex);

        return new ResponseEntity<>(erroResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}