package br.com.aceleraprogramador.gerenciamento_pedidos.service;
import br.com.aceleraprogramador.gerenciamento_pedidos.adapter.ItemPedidoAdapter;
import br.com.aceleraprogramador.gerenciamento_pedidos.adapter.PagamentoAdapter;
import br.com.aceleraprogramador.gerenciamento_pedidos.adapter.PedidoAdapter;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.PedidoRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.PagamentoResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.PageResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.PedidoResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.enuns.StatusPedido;
import br.com.aceleraprogramador.gerenciamento_pedidos.exceptions.RecursoNaoEncontradoException;
import br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.dto.request.ClienteIntegracaoRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.dto.request.PagamentoIntegracaoRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.dto.response.ClienteIntegracaoResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.dto.response.PagamentoIntegracaoResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.service.IntegracaoClienteService;
import br.com.aceleraprogramador.gerenciamento_pedidos.integracao.multtdigital.cobranca.service.IntegracaoPagamentoService;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.ItemPedido;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Pedido;
import br.com.aceleraprogramador.gerenciamento_pedidos.repository.ItemPedidoRepository;
import br.com.aceleraprogramador.gerenciamento_pedidos.repository.PedidoRepository;
import br.com.aceleraprogramador.gerenciamento_pedidos.utils.ObjectMapperUtilsConfig;
import br.com.aceleraprogramador.gerenciamento_pedidos.utils.PaginacaoUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final IntegracaoClienteService integracaoClienteService;
    private final IntegracaoPagamentoService integracaoPagamentoService;

    public PedidoResponse criarPedido(PedidoRequest request) {

        log.info("Criando um novo Pedido...");
        log.info("JSON: {}", ObjectMapperUtilsConfig.pojoParaJson(request));

        Pedido pedido = PedidoAdapter.toEntity(request);
        List<ItemPedido> itens = ItemPedidoAdapter.toEntities(pedido, request.getItens());
        pedido.setItens(itens);

        pedidoRepository.save(pedido);

        PedidoResponse pedidoResponse = PedidoAdapter.toResponse(pedido);

        List<ItemPedido> itensSalvos = itemPedidoRepository.findByPedidoId(pedido.getId());
        pedidoResponse.setItens(ItemPedidoAdapter.toResponseList(itensSalvos));

        log.info("Pedido criado com sucesso...");
        return pedidoResponse;
    }

    public PageResponse<PedidoResponse> buscarTodosOsPedidosPorParametros(Long id,
                                                                          Long idCliente,
                                                                          String status,
                                                                          String dataPedido,
                                                                          Integer pageNumber,
                                                                          Integer pageSize,
                                                                          String sortBy,
                                                                          String sortDirection) {
        log.info("Buscando todos os Pedidos de forma parametrizada...");

        Pageable pageable = PaginacaoUtils.criarPageable(pageNumber, pageSize, sortBy, sortDirection);

        Page<Pedido> pedidos = pedidoRepository.findByFilters(id, status, idCliente, pageable);

        Page<PedidoResponse> responsePage = pedidos.map(PedidoAdapter::toResponse);

        PageResponse<PedidoResponse> pageResponse = PageResponse.
                <PedidoResponse>builder()
                .content(responsePage.getContent())
                .currentPage(responsePage.getNumber())
                .pageSize(responsePage.getSize())
                .totalElements(responsePage.getTotalElements())
                .totalPages(responsePage.getTotalPages())
                .build();

        log.info("Pedidos retornados com sucesso.");

        return pageResponse;
    }

    public PedidoResponse buscarPedidoPorId(Long id) {
        log.info("Buscando pedido com ID:{}", id);
        Pedido pedido = buscarEntidadePedidoPorId(id);
        PedidoResponse pedidoResponse = PedidoAdapter.toResponse(pedido);
        List<ItemPedido> itensSalvos = itemPedidoRepository.findByPedidoId(pedido.getId());
        pedidoResponse.setItens(ItemPedidoAdapter.toResponseList(itensSalvos));
        log.info("Pedido retornado com sucesso.");
        return pedidoResponse;
    }

    public void atualizarStatusDoPedido(Long id, String status) {
        log.info("Atualizando status do pedido com ID : {} para Status = {}", id, status);
        Pedido pedido = buscarEntidadePedidoPorId(id);
        pedido.setStatus(StatusPedido.valueOf(status));
        pedido.setDataAtualizacao(LocalDateTime.now());
        pedidoRepository.save(pedido);
        log.info("Status do pedido atualizado com sucesso.");
    }

    public void removerPedido(Long id) {
        log.info("Removendo do pedido com ID: {}", id);
        Pedido pedido = buscarEntidadePedidoPorId(id);
        pedidoRepository.delete(pedido);
        log.info("Pedido removido com sucesso.");
    }

    private Pedido buscarEntidadePedidoPorId(Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if (pedido.isEmpty()) {
            String erro = "Pedido não encontrado com o ID: " + id;
            throw new RecursoNaoEncontradoException(erro);
        } else {
            return pedido.get();
        }
    }

    public PagamentoResponse registrarPagamentoPedido(Long id) {

        log.info("Registrando pagamento do pedido com ID: {}", id);

        Pedido pedido = pedidoRepository.getReferenceById(id);
        PedidoResponse pedidoResponse = PedidoAdapter.toResponse(pedido);

        ClienteIntegracaoRequest clienteIntegracaoRequest = PagamentoAdapter.preencherClienteRequest(pedido);
        ClienteIntegracaoResponse clienteIntegracaoResponse = integracaoClienteService.criarCliente(clienteIntegracaoRequest);

        PagamentoIntegracaoRequest pagamentoIntegracaoRequest = PagamentoAdapter.preencherPagamentoRequest(clienteIntegracaoResponse, pedidoResponse);
        PagamentoIntegracaoResponse pagamentoIntegracaoResponse = integracaoPagamentoService.criarPagamento(pagamentoIntegracaoRequest);

        PagamentoResponse pagamentoResponse = PagamentoAdapter.preencherRespostaRegistroPagamento(pagamentoIntegracaoResponse);

        pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);
        pedido.setDataAtualizacao(LocalDateTime.now());
        pedidoRepository.save(pedido);

        log.info("Pagamento do pedido registrado com sucesso");

        return pagamentoResponse;
    }
}
