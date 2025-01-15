package br.com.aceleraprogramador.gerenciamento_pedidos.controller.registroFinanceiro;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.RegistroFinanceiroRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.RegistroFinanceiroResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Usuario;
import br.com.aceleraprogramador.gerenciamento_pedidos.security.UsuarioSecurityConfig;
import br.com.aceleraprogramador.gerenciamento_pedidos.service.RegistroFinanceiroService;
import br.com.aceleraprogramador.gerenciamento_pedidos.utils.ObjectMapperUtilsConfig;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class RegistroFinanceiroController {

    private final RegistroFinanceiroService registroFinanceiroService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public ResponseEntity<RegistroFinanceiroResponse> createRegistro(@Valid @RequestBody RegistroFinanceiroRequest request) {
        log.info("Iniciando criação de registro financeiro. JSON: {}", ObjectMapperUtilsConfig.pojoParaJson(request));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsuarioSecurityConfig usuarioSecurity = (UsuarioSecurityConfig) authentication.getPrincipal();
        Usuario usuario = usuarioSecurity.getUsuario();
        log.info("Usuário autenticado: {}", usuario.getId());
        RegistroFinanceiroResponse response = registroFinanceiroService.createRegistro(usuario.getId(), request);
        log.info("Registro financeiro criado com sucesso. JSON: {}", ObjectMapperUtilsConfig.pojoParaJson(response));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public ResponseEntity<List<RegistroFinanceiroResponse>> getAllRegistros() {
        log.info("Buscando todos os registros financeiros.");
        List<RegistroFinanceiroResponse> registros = registroFinanceiroService.getAllRegistros();
        log.info("Total de registros encontrados: {}", registros.size());
        return ResponseEntity.ok(registros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroFinanceiroResponse> getRegistroById(@PathVariable Long id) {
        log.info("Buscando registro financeiro com ID: {}", id);
        RegistroFinanceiroResponse response = registroFinanceiroService.getRegistroById(id);
        log.info("Registro financeiro encontrado. JSON: {}", ObjectMapperUtilsConfig.pojoParaJson(response));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public ResponseEntity<RegistroFinanceiroResponse> updateRegistro(
            @PathVariable Long id, @Valid @RequestBody RegistroFinanceiroRequest request) {
        log.info("Iniciando atualização de registro financeiro com ID: {}. JSON: {}", id, ObjectMapperUtilsConfig.pojoParaJson(request));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsuarioSecurityConfig usuarioSecurity = (UsuarioSecurityConfig) authentication.getPrincipal();
        Usuario usuario = usuarioSecurity.getUsuario();
        log.debug("Usuário autenticado para atualização: {}", usuario.getId());
        RegistroFinanceiroResponse response = registroFinanceiroService.updateRegistro(id, usuario.getId(), request);
        log.info("Registro financeiro atualizado com sucesso. JSON: {}", ObjectMapperUtilsConfig.pojoParaJson(response));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public ResponseEntity<Void> deleteRegistro(@PathVariable Long id) {
        log.info("Iniciando exclusão de registro financeiro com ID: {}", id);
        registroFinanceiroService.deleteRegistro(id);
        log.info("Registro financeiro com ID: {} excluído com sucesso.", id);
        return ResponseEntity.noContent().build();
    }
}
