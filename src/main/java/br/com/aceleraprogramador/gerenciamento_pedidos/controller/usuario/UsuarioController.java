package br.com.aceleraprogramador.gerenciamento_pedidos.controller.usuario;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.UsuarioRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.PageResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.UsuarioResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Gerenciamento de Usuários")
@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController implements UsuarioAPI {

    private final UsuarioService usuarioService;

    @Override
    @PostMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR')")
    public UsuarioResponse criarUsuario(@Valid @RequestBody UsuarioRequest request) {
        return usuarioService.criarUsuario(request);
    }

    @Override
    @GetMapping(value = "/v1/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR')")
    public UsuarioResponse buscarUsuarioPorId(@PathVariable Long id) {
        return usuarioService.buscarUsuarioPorId(id);
    }

    @Override
    @GetMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR')")
    public PageResponse<UsuarioResponse> buscarTodosOsUsuarios(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
        return usuarioService.buscarTodosOsUsuario(pageNumber, pageSize, sortBy, sortDirection);
    }

    @Override
    @PutMapping(value = "/v1/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR')")
    public void atualizarTodosOsDadosDoUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioRequest request) {
        usuarioService.atualizarTodosOsDadosDoUsuario(id, request);
    }

    @Override
    @DeleteMapping(value = "/v1/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR')")
    public void removerUsuario(@PathVariable Long id) {
        usuarioService.removerUsuario(id);
    }
}
