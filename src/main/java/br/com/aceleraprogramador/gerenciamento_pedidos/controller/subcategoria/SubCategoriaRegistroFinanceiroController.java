package br.com.aceleraprogramador.gerenciamento_pedidos.controller.subcategoria;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.SubCategoriaRegistroFinanceiroRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.SubCategoriaRegistroFinanceiroResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Usuario;
import br.com.aceleraprogramador.gerenciamento_pedidos.security.UsuarioSecurityConfig;
import br.com.aceleraprogramador.gerenciamento_pedidos.service.SubCategoriaRegistroFinanceiroService;
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

@Tag(name = "Gerenciamento de SubCategorias de Registros Financeiros")
@RestController
@RequestMapping("/api/subcategorias-registro-financeiros")
@RequiredArgsConstructor
public class SubCategoriaRegistroFinanceiroController {

    private final SubCategoriaRegistroFinanceiroService subCategoriaRegistroFinanceiroService;

    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    @PostMapping
    public ResponseEntity<SubCategoriaRegistroFinanceiroResponse> create(@Valid @RequestBody SubCategoriaRegistroFinanceiroRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsuarioSecurityConfig usuarioSecurity = (UsuarioSecurityConfig) authentication.getPrincipal();
        Usuario usuario = usuarioSecurity.getUsuario();
        SubCategoriaRegistroFinanceiroResponse response = subCategoriaRegistroFinanceiroService.create(usuario.getId(), request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    @PutMapping("/{id}")
    public ResponseEntity<SubCategoriaRegistroFinanceiroResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody SubCategoriaRegistroFinanceiroRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsuarioSecurityConfig usuarioSecurity = (UsuarioSecurityConfig) authentication.getPrincipal();
        Usuario usuario = usuarioSecurity.getUsuario();
        SubCategoriaRegistroFinanceiroResponse response = subCategoriaRegistroFinanceiroService.update(id, usuario.getId(), request);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    @GetMapping
    public ResponseEntity<List<SubCategoriaRegistroFinanceiroResponse>> findAll() {
        List<SubCategoriaRegistroFinanceiroResponse> categorias = subCategoriaRegistroFinanceiroService.findAll();
        return ResponseEntity.ok(categorias);
    }

    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    @GetMapping("/{id}")
    public ResponseEntity<SubCategoriaRegistroFinanceiroResponse> findById(@PathVariable Long id) {
        SubCategoriaRegistroFinanceiroResponse response = subCategoriaRegistroFinanceiroService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        subCategoriaRegistroFinanceiroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}