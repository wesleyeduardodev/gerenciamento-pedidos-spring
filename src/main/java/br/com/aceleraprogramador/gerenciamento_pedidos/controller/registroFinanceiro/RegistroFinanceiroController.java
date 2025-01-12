package br.com.aceleraprogramador.gerenciamento_pedidos.controller.registroFinanceiro;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.RegistroFinanceiroRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.RegistroFinanceiroResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.service.RegistroFinanceiroService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Gerenciamento Registro Financeiros")
@RestController
@RequestMapping("/api/registros-financeiros")
@RequiredArgsConstructor
public class RegistroFinanceiroController {

    private final RegistroFinanceiroService registroFinanceiroService;

    /**
     * Cria um novo registro financeiro.
     */
    @PostMapping
    public ResponseEntity<RegistroFinanceiroResponse> createRegistro(
            @Valid @RequestBody RegistroFinanceiroRequest request) {
        RegistroFinanceiroResponse response = registroFinanceiroService.createRegistro(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Retorna todos os registros financeiros.
     */
    @GetMapping
    public ResponseEntity<List<RegistroFinanceiroResponse>> getAllRegistros() {
        List<RegistroFinanceiroResponse> registros = registroFinanceiroService.getAllRegistros();
        return ResponseEntity.ok(registros);
    }

    /**
     * Retorna um registro financeiro pelo ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RegistroFinanceiroResponse> getRegistroById(@PathVariable Long id) {
        RegistroFinanceiroResponse response = registroFinanceiroService.getRegistroById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Atualiza um registro financeiro existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<RegistroFinanceiroResponse> updateRegistro(
            @PathVariable Long id, @Valid @RequestBody RegistroFinanceiroRequest request) {
        RegistroFinanceiroResponse response = registroFinanceiroService.updateRegistro(id, request);
        return ResponseEntity.ok(response);
    }

    /**
     * Exclui um registro financeiro pelo ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegistro(@PathVariable Long id) {
        registroFinanceiroService.deleteRegistro(id);
        return ResponseEntity.noContent().build();
    }
}