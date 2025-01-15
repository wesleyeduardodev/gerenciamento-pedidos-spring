package br.com.aceleraprogramador.gerenciamento_pedidos.controller.subcategoria;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.SubCategoriaRegistroFinanceiroRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.SubCategoriaRegistroFinanceiroResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Usuario;
import br.com.aceleraprogramador.gerenciamento_pedidos.security.UsuarioSecurityConfig;
import br.com.aceleraprogramador.gerenciamento_pedidos.service.SubCategoriaRegistroFinanceiroService;
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

@Tag(name = "Gerenciamento de SubCategorias de Registros Financeiros")
@RestController
@RequestMapping("/api/subcategorias-registro-financeiros")
@RequiredArgsConstructor
@Slf4j
public class SubCategoriaRegistroFinanceiroController {

    private final SubCategoriaRegistroFinanceiroService subCategoriaRegistroFinanceiroService;

    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    @PostMapping
    public ResponseEntity<SubCategoriaRegistroFinanceiroResponse> create(@Valid @RequestBody SubCategoriaRegistroFinanceiroRequest request) {
        log.info("Iniciando criação de subcategoria. JSON: {}", ObjectMapperUtilsConfig.pojoParaJson(request));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsuarioSecurityConfig usuarioSecurity = (UsuarioSecurityConfig) authentication.getPrincipal();
        Usuario usuario = usuarioSecurity.getUsuario();
        log.info("Usuário autenticado: {}", usuario.getId());
        SubCategoriaRegistroFinanceiroResponse response = subCategoriaRegistroFinanceiroService.create(usuario.getId(), request);
        log.info("Subcategoria criada com sucesso. JSON: {}", ObjectMapperUtilsConfig.pojoParaJson(response));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    @PutMapping("/{id}")
    public ResponseEntity<SubCategoriaRegistroFinanceiroResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody SubCategoriaRegistroFinanceiroRequest request) {
        log.info("Iniciando atualização de subcategoria com ID: {}. JSON: {}", id, ObjectMapperUtilsConfig.pojoParaJson(request));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsuarioSecurityConfig usuarioSecurity = (UsuarioSecurityConfig) authentication.getPrincipal();
        Usuario usuario = usuarioSecurity.getUsuario();
        log.info("Usuário autenticado para atualização: {}", usuario.getId());
        SubCategoriaRegistroFinanceiroResponse response = subCategoriaRegistroFinanceiroService.update(id, usuario.getId(), request);
        log.info("Subcategoria atualizada com sucesso. JSON: {}", ObjectMapperUtilsConfig.pojoParaJson(response));
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    @GetMapping
    public ResponseEntity<List<SubCategoriaRegistroFinanceiroResponse>> findAll() {
        log.info("Buscando todas as subcategorias.");
        List<SubCategoriaRegistroFinanceiroResponse> categorias = subCategoriaRegistroFinanceiroService.findAll();
        log.info("Total de subcategorias encontradas: {}", categorias.size());
        return ResponseEntity.ok(categorias);
    }

    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    @GetMapping("/{id}")
    public ResponseEntity<SubCategoriaRegistroFinanceiroResponse> findById(@PathVariable Long id) {
        log.info("Buscando subcategoria com ID: {}", id);
        SubCategoriaRegistroFinanceiroResponse response = subCategoriaRegistroFinanceiroService.findById(id);
        log.info("Subcategoria encontrada. JSON: {}", ObjectMapperUtilsConfig.pojoParaJson(response));
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    @GetMapping("/findByIdCategoria/{idCategoria}")
    public ResponseEntity<List<SubCategoriaRegistroFinanceiroResponse>> findByIdCategoria(@PathVariable Long idCategoria) {
        log.info("Buscando subcategorias da categoria com ID: {}", idCategoria);
        List<SubCategoriaRegistroFinanceiroResponse> responses = subCategoriaRegistroFinanceiroService.findByIdCategoria(idCategoria);
        log.info("Total de subcategorias encontradas para a categoria {}: {}", idCategoria, responses.size());
        return ResponseEntity.ok(responses);
    }

    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Iniciando exclusão de subcategoria com ID: {}", id);
        subCategoriaRegistroFinanceiroService.delete(id);
        log.info("Subcategoria com ID: {} excluída com sucesso.", id);
        return ResponseEntity.noContent().build();
    }
}
