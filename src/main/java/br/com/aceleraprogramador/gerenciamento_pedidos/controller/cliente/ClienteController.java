package br.com.aceleraprogramador.gerenciamento_pedidos.controller.cliente;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.CreateClienteRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.UpdateClienteRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.ClienteResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.PageResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.service.ClienteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Gerenciamento de Clientes")
@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController implements ClienteAPI {

    private final ClienteService clienteService;

    @Override
    @PostMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public ClienteResponse criarCliente(@Valid @RequestBody CreateClienteRequest request) {
        return clienteService.criarCliente(request);
    }

    @Override
    @GetMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public PageResponse<ClienteResponse> buscarTodosOsClientes(@RequestParam(required = false) Integer pageNumber,
                                                               @RequestParam(required = false) Integer pageSize,
                                                               @RequestParam(required = false) String sortBy,
                                                               @RequestParam(required = false) String sortDirection) {
        return clienteService.buscarTodosOsClientes(pageNumber, pageSize, sortBy, sortDirection);
    }

    @Override
    @GetMapping(value = "/v1/buscarTodosOsClientesPorParametros", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public PageResponse<ClienteResponse> buscarTodosOsClientesPorParametros(@RequestParam(required = false) Long id,
                                                                            @RequestParam(required = false) String nome,
                                                                            @RequestParam(required = false) String email,
                                                                            @RequestParam(required = false) String telefone,
                                                                            @RequestParam(required = false) String endereco,
                                                                            @RequestParam(required = false) String profissao,
                                                                            @RequestParam(required = false) Integer pageNumber,
                                                                            @RequestParam(required = false) Integer pageSize,
                                                                            @RequestParam(required = false) String sortBy,
                                                                            @RequestParam(required = false) String sortDirection) {
        return clienteService.buscarTodosOsClientesPorParametros(id, nome, email, telefone, endereco, profissao, pageNumber, pageSize, sortBy, sortDirection);
    }

    @Override
    @GetMapping(value = "/v1/{idCliente}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public ClienteResponse buscarClientePorId(@PathVariable Long idCliente) {
        return clienteService.buscarClientePorId(idCliente);
    }

    @Override
    @GetMapping(value = "/v1/buscarPorNome", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public List<ClienteResponse> buscarClientesPorNome(
            @RequestParam() String nome) {
        return clienteService.buscarClientePorNome(nome);
    }

    @Override
    @GetMapping(value = "/v1/buscarPorEmail", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public List<ClienteResponse> buscarClientesPorEmail(
            @RequestParam() String email) {
        return clienteService.buscarClientePorEmail(email);
    }

    @Override
    @GetMapping(value = "/v1/buscarPorProfissao", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public List<ClienteResponse> buscarClientesPorProfissao(
            @RequestParam() String profissao) {
        return clienteService.buscarClientePorProfissao(profissao);
    }

    @Override
    @GetMapping(value = "/v1/buscarPorNomeEmailProfissao", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public List<ClienteResponse> buscarClientePorNomeEmailProfissao(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String profissao) {
        return clienteService.buscarClientePorNomeEmailProfissao(nome, email, profissao);
    }

    @Override
    @PutMapping(value = "/v1/{idCliente}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public void atualizarTodosOsDadosDoCliente(@PathVariable Long idCliente, @Valid @RequestBody UpdateClienteRequest request) {
        clienteService.atualizarTodosOsDadosDoCliente(idCliente, request);
    }

    @Override
    @PatchMapping(value = "/v1/{idCliente}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public ClienteResponse atualizarParcialmenteOsDadosDoCliente(@PathVariable Long idCliente, @Valid @RequestBody UpdateClienteRequest request) {
        return clienteService.atualizarParcialmenteOsDadosDoCliente(idCliente, request);
    }

    @Override
    @DeleteMapping(value = "/v1/{idCliente}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('ROLE_GERENTE','ROLE_ADMINISTRADOR','ROLE_USUARIO')")
    public void removerCliente(@PathVariable Long idCliente) {
        clienteService.removerCliente(idCliente);
    }
}
