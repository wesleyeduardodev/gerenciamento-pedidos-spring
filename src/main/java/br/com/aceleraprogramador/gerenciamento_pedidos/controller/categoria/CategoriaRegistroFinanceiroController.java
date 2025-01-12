package br.com.aceleraprogramador.gerenciamento_pedidos.controller.categoria;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.CategoriaRegistroFinanceiroRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.CategoriaRegistroFinanceiroResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.service.CategoriaRegistroFinanceiroService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Gerenciamento de Categorias de Registros Financeiros")
@RestController
@RequestMapping("/api/categorias-registro-financeiros")
@RequiredArgsConstructor
public class CategoriaRegistroFinanceiroController {

    private final CategoriaRegistroFinanceiroService categoriaRegistroFinanceiroService;

    // Criar nova categoria
    @PostMapping
    public ResponseEntity<CategoriaRegistroFinanceiroResponse> create(@Valid @RequestBody CategoriaRegistroFinanceiroRequest request) {
        CategoriaRegistroFinanceiroResponse response = categoriaRegistroFinanceiroService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Atualizar categoria por ID
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaRegistroFinanceiroResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody CategoriaRegistroFinanceiroRequest request) {
        CategoriaRegistroFinanceiroResponse response = categoriaRegistroFinanceiroService.update(id, request);
        return ResponseEntity.ok(response);
    }

    // Buscar todas as categorias
    @GetMapping
    public ResponseEntity<List<CategoriaRegistroFinanceiroResponse>> findAll() {
        List<CategoriaRegistroFinanceiroResponse> categorias = categoriaRegistroFinanceiroService.findAll();
        return ResponseEntity.ok(categorias);
    }

    // Buscar categoria por ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaRegistroFinanceiroResponse> findById(@PathVariable Long id) {
        CategoriaRegistroFinanceiroResponse response = categoriaRegistroFinanceiroService.findById(id);
        return ResponseEntity.ok(response);
    }

    // Deletar categoria por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoriaRegistroFinanceiroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}