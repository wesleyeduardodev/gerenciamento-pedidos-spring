package br.com.aceleraprogramador.gerenciamento_pedidos.service;
import br.com.aceleraprogramador.gerenciamento_pedidos.adapter.ProdutoAdapter;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.ProdutoRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.PageResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.ProdutoResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.exceptions.RecursoNaoEncontradoException;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Produto;
import br.com.aceleraprogramador.gerenciamento_pedidos.repository.ProdutoRepository;
import br.com.aceleraprogramador.gerenciamento_pedidos.utils.ObjectMapperUtilsConfig;
import br.com.aceleraprogramador.gerenciamento_pedidos.utils.PaginacaoUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoResponse criarProduto(ProdutoRequest request) {

        log.info("Criando um novo Produto...");
        log.info("JSON: {}", ObjectMapperUtilsConfig.pojoParaJson(request));

        Produto produto = ProdutoAdapter.toEntity(request);
        produtoRepository.save(produto);
        ProdutoResponse produtoResponse = ProdutoAdapter.toResponse(produto);

        log.info("Produto criado com sucesso...");

        return produtoResponse;
    }

    public PageResponse<ProdutoResponse> buscarTodosOsProdutosPorParametros(Long id,
                                                                            String nome,
                                                                            String descricao,
                                                                            BigDecimal preco,
                                                                            Long idFornecedor,
                                                                            Integer pageNumber,
                                                                            Integer pageSize,
                                                                            String sortBy,
                                                                            String sortDirection) {
        log.info("Buscando todos os produtos de forma parametrizado...");

        Pageable pageable = PaginacaoUtils.criarPageable(pageNumber, pageSize, sortBy, sortDirection);

        Page<Produto> produtos = produtoRepository.findByFilters(id, nome, descricao, preco, idFornecedor, pageable);

        Page<ProdutoResponse> responsePage = produtos.map(ProdutoAdapter::toResponse);
        PageResponse<ProdutoResponse> pageResponse = PageResponse.
                <ProdutoResponse>builder()
                .content(responsePage.getContent())
                .currentPage(responsePage.getNumber())
                .pageSize(responsePage.getSize())
                .totalElements(responsePage.getTotalElements())
                .totalPages(responsePage.getTotalPages())
                .build();

        log.info("Produtos retornados com sucesso.");

        return pageResponse;
    }

    public ProdutoResponse buscarProdutoPorId(Long id) {
        log.info("Buscando produto com ID:{}", id);
        Produto produto = buscarEntidadeProdutosPorId(id);
        ProdutoResponse produtoResponse = ProdutoAdapter.toResponse(produto);
        log.info("Produto retornado com sucesso.");
        return produtoResponse;
    }

    public void atualizarTodosOsDadosDoProduto(Long id, ProdutoRequest request) {
        log.info("Atualizando todos os dados do prodduto com ID: {}", id);
        log.info("JSON: {}", ObjectMapperUtilsConfig.pojoParaJson(request));
        Produto produto = buscarEntidadeProdutosPorId(id);
        ProdutoAdapter.toUpdate(produto, request);
        produtoRepository.save(produto);
        log.info("Produto totalmente atualizado com sucesso.");
    }

    public void removerProduto(Long id) {
        log.info("Removendo do produto com ID: {}", id);
        Produto produto = buscarEntidadeProdutosPorId(id);
        produtoRepository.delete(produto);
        log.info("Produto removido com sucesso.");
    }

    private Produto buscarEntidadeProdutosPorId(Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isEmpty()) {
            String erro = "Produto não encontrado com o ID: " + id;
            throw new RecursoNaoEncontradoException(erro);
        } else {
            return produto.get();
        }
    }
}
