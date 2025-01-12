package br.com.aceleraprogramador.gerenciamento_pedidos.controller.registroFinanceiro;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.RegistroFinanceiroRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.RegistroFinanceiroResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Usuario;
import br.com.aceleraprogramador.gerenciamento_pedidos.security.UsuarioSecurityConfig;
import br.com.aceleraprogramador.gerenciamento_pedidos.service.RegistroFinanceiroService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Tag(name = "Gerenciamento Registro Financeiros")
@RestController
@RequestMapping("/api/registros-financeiros")
@RequiredArgsConstructor
public class RegistroFinanceiroController {

    private final RegistroFinanceiroService registroFinanceiroService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public ResponseEntity<RegistroFinanceiroResponse> createRegistro(@Valid @RequestBody RegistroFinanceiroRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsuarioSecurityConfig usuarioSecurity = (UsuarioSecurityConfig) authentication.getPrincipal();
        Usuario usuario = usuarioSecurity.getUsuario();
        RegistroFinanceiroResponse response = registroFinanceiroService.createRegistro(usuario.getId(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public ResponseEntity<List<RegistroFinanceiroResponse>> getAllRegistros() {
        List<RegistroFinanceiroResponse> registros = registroFinanceiroService.getAllRegistros();
        return ResponseEntity.ok(registros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroFinanceiroResponse> getRegistroById(@PathVariable Long id) {
        RegistroFinanceiroResponse response = registroFinanceiroService.getRegistroById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public ResponseEntity<RegistroFinanceiroResponse> updateRegistro(
            @PathVariable Long id, @Valid @RequestBody RegistroFinanceiroRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsuarioSecurityConfig usuarioSecurity = (UsuarioSecurityConfig) authentication.getPrincipal();
        Usuario usuario = usuarioSecurity.getUsuario();
        RegistroFinanceiroResponse response = registroFinanceiroService.updateRegistro(id, usuario.getId(), request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public ResponseEntity<Void> deleteRegistro(@PathVariable Long id) {
        registroFinanceiroService.deleteRegistro(id);
        return ResponseEntity.noContent().build();
    }
}