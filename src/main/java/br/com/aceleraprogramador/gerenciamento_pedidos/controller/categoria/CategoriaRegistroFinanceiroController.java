package br.com.aceleraprogramador.gerenciamento_pedidos.controller.categoria;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.CategoriaRegistroFinanceiroRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.CategoriaRegistroFinanceiroResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Usuario;
import br.com.aceleraprogramador.gerenciamento_pedidos.security.UsuarioSecurityConfig;
import br.com.aceleraprogramador.gerenciamento_pedidos.service.CategoriaRegistroFinanceiroService;
import br.com.aceleraprogramador.gerenciamento_pedidos.utils.ObjectMapperUtilsConfig;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CategoriaRegistroFinanceiroController {

    private final CategoriaRegistroFinanceiroService categoriaRegistroFinanceiroService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public ResponseEntity<CategoriaRegistroFinanceiroResponse> create(@Valid @RequestBody CategoriaRegistroFinanceiroRequest request) {
        log.info("Iniciando criação de categoria. JSON: {}", ObjectMapperUtilsConfig.pojoParaJson(request));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsuarioSecurityConfig usuarioSecurity = (UsuarioSecurityConfig) authentication.getPrincipal();
        Usuario usuario = usuarioSecurity.getUsuario();
        log.info("Usuário autenticado: {}", usuario.getId());
        CategoriaRegistroFinanceiroResponse response = categoriaRegistroFinanceiroService.create(usuario.getId(), request);
        log.info("Categoria criada com sucesso. JSON: {}", ObjectMapperUtilsConfig.pojoParaJson(response));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public ResponseEntity<CategoriaRegistroFinanceiroResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody CategoriaRegistroFinanceiroRequest request) {
        log.info("Iniciando atualização de categoria com ID: {}. JSON: {}", id, ObjectMapperUtilsConfig.pojoParaJson(request));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsuarioSecurityConfig usuarioSecurity = (UsuarioSecurityConfig) authentication.getPrincipal();
        Usuario usuario = usuarioSecurity.getUsuario();
        log.info("Usuário autenticado para atualização: {}", usuario.getId());
        CategoriaRegistroFinanceiroResponse response = categoriaRegistroFinanceiroService.update(id, usuario.getId(), request);
        log.info("Categoria atualizada com sucesso. JSON: {}", ObjectMapperUtilsConfig.pojoParaJson(response));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public ResponseEntity<List<CategoriaRegistroFinanceiroResponse>> findAll() {
        log.info("Buscando todas as categorias.");
        List<CategoriaRegistroFinanceiroResponse> categorias = categoriaRegistroFinanceiroService.findAll();
        log.info("Total de categorias encontradas: {}", categorias.size());
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public ResponseEntity<CategoriaRegistroFinanceiroResponse> findById(@PathVariable Long id) {
        log.info("Buscando categoria com ID: {}", id);
        CategoriaRegistroFinanceiroResponse response = categoriaRegistroFinanceiroService.findById(id);
        log.info("Categoria encontrada. JSON: {}", ObjectMapperUtilsConfig.pojoParaJson(response));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Iniciando exclusão de categoria com ID: {}", id);
        categoriaRegistroFinanceiroService.delete(id);
        log.info("Categoria com ID: {} excluída com sucesso.", id);
        return ResponseEntity.noContent().build();
    }
}
