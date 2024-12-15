package br.com.aceleraprogramador.gerenciamento_pedidos.controller.pedido;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.PedidoRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.CobrancaResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.PageResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.PedidoResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.service.PedidoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Gerenciamento de Pedidos")
@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController implements PedidoAPI {

    private final PedidoService pedidoService;

    @Override
    @PostMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public PedidoResponse criarPedido(@Valid @RequestBody PedidoRequest request) {
        return pedidoService.criarPedido(request);
    }

    @Override
    @GetMapping(value = "/v1/buscarTodosOsPedidosPorParametros", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public PageResponse<PedidoResponse> buscarTodosOsPedidosPorParametros(@RequestParam(required = false) Long id,
                                                                          @RequestParam(required = false) Long idCliente,
                                                                          @RequestParam(required = false) String status,
                                                                          @RequestParam(required = false) String dataPedido,
                                                                          @RequestParam(required = false) Integer pageNumber,
                                                                          @RequestParam(required = false) Integer pageSize,
                                                                          @RequestParam(required = false) String sortBy,
                                                                          @RequestParam(required = false) String sortDirection) {
        return pedidoService.buscarTodosOsPedidosPorParametros(id, idCliente, status, dataPedido, pageNumber, pageSize, sortBy, sortDirection);
    }

    @Override
    @GetMapping(value = "/v1/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public PedidoResponse buscarPedidoPorId(@PathVariable Long id) {
        return pedidoService.buscarPedidoPorId(id);
    }

    @Override
    @PutMapping(value = "/v1/{id}/status/{status}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public void atualizarStatusDoPedido(@PathVariable Long id, @PathVariable String status) {
        pedidoService.atualizarStatusDoPedido(id, status);
    }

    @DeleteMapping(value = "/v1/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public void removerPedido(@PathVariable Long id) {
        pedidoService.removerPedido(id);
    }

    @Override
    @PutMapping(value = "/v1/registrarCobrancaPedido/pedido/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public CobrancaResponse registrarCobrancaPedido(@PathVariable Long id) {
        return pedidoService.registrarCobrancaPedido(id);
    }
}
