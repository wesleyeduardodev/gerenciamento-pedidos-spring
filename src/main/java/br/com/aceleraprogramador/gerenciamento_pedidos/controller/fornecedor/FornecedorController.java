package br.com.aceleraprogramador.gerenciamento_pedidos.controller.fornecedor;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.FornecedorRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.FornecedorResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.PageResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.service.FornecedorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Gerenciamento de Fornecedores")
@RestController
@RequestMapping("/api/fornecedores")
@RequiredArgsConstructor
public class FornecedorController implements FornecedorAPI {

    private final FornecedorService fornecedorService;

    @Override
    @PostMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public FornecedorResponse criarFornecedor(@Valid @RequestBody FornecedorRequest request) {
        return fornecedorService.criarFornecedor(request);
    }

    @Override
    @GetMapping(value = "/v1/buscarTodosOsFornecedoresPorParametros", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public PageResponse<FornecedorResponse> buscarTodosOsFornecedoresPorParametros(@RequestParam(required = false) Long id,
                                                                                   @RequestParam(required = false) String nome,
                                                                                   @RequestParam(required = false) String cnpj,
                                                                                   @RequestParam(required = false) String contato,
                                                                                   @RequestParam(required = false) String endereco,
                                                                                   @RequestParam(required = false) Integer pageNumber,
                                                                                   @RequestParam(required = false) Integer pageSize,
                                                                                   @RequestParam(required = false) String sortBy,
                                                                                   @RequestParam(required = false) String sortDirection) {
        return fornecedorService.buscarTodosOsFornecedoresPorParametros(id, nome, cnpj, contato, endereco, pageNumber, pageSize, sortBy, sortDirection);
    }

    @Override
    @GetMapping(value = "/v1/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public FornecedorResponse buscarFornecedorPorId(@PathVariable Long id) {
        return fornecedorService.buscarFornecedorPorId(id);
    }

    @Override
    @PutMapping(value = "/v1/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_GERENTE')")
    public void atualizarTodosOsDadosDoFornecedor(@PathVariable Long id, @Valid @RequestBody FornecedorRequest request) {
        fornecedorService.atualizarTodosOsDadosDoFornecedor(id, request);
    }

    @DeleteMapping(value = "/v1/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    public void removerFornecedor(@PathVariable Long id) {
        fornecedorService.removerFornecedor(id);
    }
}
