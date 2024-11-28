package br.com.aceleraprogramador.gerenciamento_pedidos.controller.produto;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.ProdutoRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.PageResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.ProdutoResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.service.ProdutoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@Tag(name = "Gerenciamento de Produtos")
@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
public class ProdutoController implements ProdutoAPI {

    private final ProdutoService produtoService;

    @Override
    @PostMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public ProdutoResponse criarProduto(@Valid @RequestBody ProdutoRequest request) {
        return produtoService.criarProduto(request);
    }

    @Override
    @GetMapping(value = "/v1/buscarTodosOsProdutosPorParametros", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public PageResponse<ProdutoResponse> buscarTodosOsProdutosPorParametros(@RequestParam(required = false) Long id,
                                                                            @RequestParam(required = false) String nome,
                                                                            @RequestParam(required = false) String descricao,
                                                                            @RequestParam(required = false) BigDecimal preco,
                                                                            @RequestParam(required = false) Long idFornecedor,
                                                                            @RequestParam(required = false) Integer pageNumber,
                                                                            @RequestParam(required = false) Integer pageSize,
                                                                            @RequestParam(required = false) String sortBy,
                                                                            @RequestParam(required = false) String sortDirection) {
        return produtoService.buscarTodosOsProdutosPorParametros(id, nome, descricao, preco, idFornecedor, pageNumber, pageSize, sortBy, sortDirection);
    }

    @Override
    @GetMapping(value = "/v1/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public ProdutoResponse buscarProdutoPorId(@PathVariable Long id) {
        return produtoService.buscarProdutoPorId(id);
    }

    @Override
    @PutMapping(value = "/v1/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public void atualizarTodosOsDadosDoProduto(@PathVariable Long id, @Valid @RequestBody ProdutoRequest request) {
        produtoService.atualizarTodosOsDadosDoProduto(id, request);
    }

    @DeleteMapping(value = "/v1/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public void removerProduto(@PathVariable Long id) {
        produtoService.removerProduto(id);
    }
}
