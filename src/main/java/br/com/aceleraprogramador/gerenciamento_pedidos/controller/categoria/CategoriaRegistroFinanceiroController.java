package br.com.aceleraprogramador.gerenciamento_pedidos.controller.categoria;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.CategoriaRegistroFinanceiroRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.CategoriaRegistroFinanceiroResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Usuario;
import br.com.aceleraprogramador.gerenciamento_pedidos.security.UsuarioSecurityConfig;
import br.com.aceleraprogramador.gerenciamento_pedidos.service.CategoriaRegistroFinanceiroService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Gerenciamento de Categorias de Registros Financeiros")
@RestController
@RequestMapping("/api/categorias-registro-financeiros")
@RequiredArgsConstructor
public class CategoriaRegistroFinanceiroController {

    private final CategoriaRegistroFinanceiroService categoriaRegistroFinanceiroService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public ResponseEntity<CategoriaRegistroFinanceiroResponse> create(@Valid @RequestBody CategoriaRegistroFinanceiroRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsuarioSecurityConfig usuarioSecurity = (UsuarioSecurityConfig) authentication.getPrincipal();
        Usuario usuario = usuarioSecurity.getUsuario();
        CategoriaRegistroFinanceiroResponse response = categoriaRegistroFinanceiroService.create(usuario.getId(), request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaRegistroFinanceiroResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody CategoriaRegistroFinanceiroRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsuarioSecurityConfig usuarioSecurity = (UsuarioSecurityConfig) authentication.getPrincipal();
        Usuario usuario = usuarioSecurity.getUsuario();
        CategoriaRegistroFinanceiroResponse response = categoriaRegistroFinanceiroService.update(id, usuario.getId(), request);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    @GetMapping
    public ResponseEntity<List<CategoriaRegistroFinanceiroResponse>> findAll() {
        List<CategoriaRegistroFinanceiroResponse> categorias = categoriaRegistroFinanceiroService.findAll();
        return ResponseEntity.ok(categorias);
    }

    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaRegistroFinanceiroResponse> findById(@PathVariable Long id) {
        CategoriaRegistroFinanceiroResponse response = categoriaRegistroFinanceiroService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoriaRegistroFinanceiroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}