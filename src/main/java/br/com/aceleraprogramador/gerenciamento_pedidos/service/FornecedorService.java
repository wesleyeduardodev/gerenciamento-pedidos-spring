package br.com.aceleraprogramador.gerenciamento_pedidos.service;
import br.com.aceleraprogramador.gerenciamento_pedidos.adapter.FornecedorAdapter;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.FornecedorRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.FornecedorResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.PageResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.exceptions.RecursoNaoEncontradoException;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Fornecedor;
import br.com.aceleraprogramador.gerenciamento_pedidos.repository.FornecedorRepository;
import br.com.aceleraprogramador.gerenciamento_pedidos.utils.ObjectMapperUtilsConfig;
import br.com.aceleraprogramador.gerenciamento_pedidos.utils.PaginacaoUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    public FornecedorResponse criarFornecedor(FornecedorRequest request) {

        log.info("Criando um novo fornecedor...");
        log.info("JSON: {}", ObjectMapperUtilsConfig.pojoParaJson(request));

        Fornecedor fornecedor = FornecedorAdapter.toEntity(request);
        fornecedorRepository.save(fornecedor);
        FornecedorResponse fornecedorResponse = FornecedorAdapter.toResponse(fornecedor);

        log.info("Fornecedor criado com sucesso...");

        return fornecedorResponse;
    }

    public PageResponse<FornecedorResponse> buscarTodosOsFornecedoresPorParametros(Long id,
                                                                                   String nome,
                                                                                   String cnpj,
                                                                                   String contato,
                                                                                   String endereco,
                                                                                   Integer pageNumber,
                                                                                   Integer pageSize,
                                                                                   String sortBy,
                                                                                   String sortDirection) {
        log.info("Buscando todos os fornecedores de forma parametrizado...");

        Pageable pageable = PaginacaoUtils.criarPageable(pageNumber, pageSize, sortBy, sortDirection);

        Page<Fornecedor> fornecedores = fornecedorRepository.findByFilters(id, nome, cnpj, contato, endereco, pageable);

        Page<FornecedorResponse> responsePage = fornecedores.map(FornecedorAdapter::toResponse);
        PageResponse<FornecedorResponse> pageResponse = PageResponse.
                <FornecedorResponse>builder()
                .content(responsePage.getContent())
                .currentPage(responsePage.getNumber())
                .pageSize(responsePage.getSize())
                .totalElements(responsePage.getTotalElements())
                .totalPages(responsePage.getTotalPages())
                .build();

        log.info("Fornecedores retornados com sucesso.");

        return pageResponse;
    }

    public FornecedorResponse buscarFornecedorPorId(Long id) {
        log.info("Buscando fornecedor com ID:{}", id);
        Fornecedor fornecedor = buscarEntidadeFornecedorPorId(id);
        FornecedorResponse fornecedorResponse = FornecedorAdapter.toResponse(fornecedor);
        log.info("Fornecedore retornado com sucesso.");
        return fornecedorResponse;
    }

    public void atualizarTodosOsDadosDoFornecedor(Long id, FornecedorRequest request) {
        log.info("Atualizando todos os dados do fornecedor com ID: {}", id);
        log.info("JSON: {}", ObjectMapperUtilsConfig.pojoParaJson(request));
        Fornecedor fornecedor = buscarEntidadeFornecedorPorId(id);
        FornecedorAdapter.toUpdate(fornecedor, request);
        fornecedorRepository.save(fornecedor);
        log.info("Fornecedor totalmente atualizado com sucesso.");
    }

    public void removerFornecedor(Long id) {
        log.info("Removendo do fornecedor com ID: {}", id);
        Fornecedor fornecedor = buscarEntidadeFornecedorPorId(id);
        fornecedorRepository.delete(fornecedor);
        log.info("Fornecedor removido com sucesso.");
    }

    private Fornecedor buscarEntidadeFornecedorPorId(Long id) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);
        if (fornecedor.isEmpty()) {
            String erro = "Fornecedor não encontrado com o ID: " + id;
            throw new RecursoNaoEncontradoException(erro);
        } else {
            return fornecedor.get();
        }
    }
}
