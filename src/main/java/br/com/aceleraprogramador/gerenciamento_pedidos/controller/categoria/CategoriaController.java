package br.com.aceleraprogramador.gerenciamento_pedidos.controller.categoria;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.CategoriaRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.CategoriaResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.service.CategoriaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Gerenciamento de Categorias")
@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    // Criar nova categoria
    @PostMapping
    public ResponseEntity<CategoriaResponse> create(@Valid @RequestBody CategoriaRequest request) {
        CategoriaResponse response = categoriaService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Atualizar categoria por ID
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody CategoriaRequest request) {
        CategoriaResponse response = categoriaService.update(id, request);
        return ResponseEntity.ok(response);
    }

    // Buscar todas as categorias
    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> findAll() {
        List<CategoriaResponse> categorias = categoriaService.findAll();
        return ResponseEntity.ok(categorias);
    }

    // Buscar categoria por ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> findById(@PathVariable Long id) {
        CategoriaResponse response = categoriaService.findById(id);
        return ResponseEntity.ok(response);
    }

    // Deletar categoria por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}