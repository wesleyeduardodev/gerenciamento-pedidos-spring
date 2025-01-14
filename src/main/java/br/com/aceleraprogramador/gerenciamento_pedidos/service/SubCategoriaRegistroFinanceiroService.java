package br.com.aceleraprogramador.gerenciamento_pedidos.service;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.SubCategoriaRegistroFinanceiroRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.SubCategoriaRegistroFinanceiroResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.CategoriaRegistroFinanceiro;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.SubCategoriaRegistroFinanceiro;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Usuario;
import br.com.aceleraprogramador.gerenciamento_pedidos.repository.SubCategoriaRegistroFinanceiroRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubCategoriaRegistroFinanceiroService {

    private final SubCategoriaRegistroFinanceiroRepository subCategoriaRegistroFinanceiroRepository;

    public SubCategoriaRegistroFinanceiroResponse create(Long idUsuario, SubCategoriaRegistroFinanceiroRequest request) {

        SubCategoriaRegistroFinanceiro subCategoriaRegistroFinanceiro = new SubCategoriaRegistroFinanceiro();

        subCategoriaRegistroFinanceiro.setNome(request.getNome());
        subCategoriaRegistroFinanceiro.setDescricao(request.getDescricao());
        subCategoriaRegistroFinanceiro.setCategoria(CategoriaRegistroFinanceiro.builder().id(request.getIdCategoria()).build());
        subCategoriaRegistroFinanceiro.setUsuario(Usuario.builder().id(idUsuario).build());

        SubCategoriaRegistroFinanceiro savedSubCategoria = subCategoriaRegistroFinanceiroRepository.save(subCategoriaRegistroFinanceiro);

        return new SubCategoriaRegistroFinanceiroResponse(savedSubCategoria.getId(),
                savedSubCategoria.getNome(),
                savedSubCategoria.getDescricao(),
                savedSubCategoria.getCategoria().getId());
    }

    // Atualizar uma categoria
    public SubCategoriaRegistroFinanceiroResponse update(Long id, Long idUsuario, SubCategoriaRegistroFinanceiroRequest request) {
        SubCategoriaRegistroFinanceiro subCategoriaRegistroFinanceiro = subCategoriaRegistroFinanceiroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SubCategoria não encontrada com o ID: " + id));

        subCategoriaRegistroFinanceiro.setNome(request.getNome());
        subCategoriaRegistroFinanceiro.setDescricao(request.getDescricao());
        subCategoriaRegistroFinanceiro.setCategoria(CategoriaRegistroFinanceiro.builder().id(request.getIdCategoria()).build());
        subCategoriaRegistroFinanceiro.setUsuario(Usuario.builder().id(idUsuario).build());

        SubCategoriaRegistroFinanceiro updatedSubCategoria = subCategoriaRegistroFinanceiroRepository.save(subCategoriaRegistroFinanceiro);

        return new SubCategoriaRegistroFinanceiroResponse(updatedSubCategoria.getId(),
                updatedSubCategoria.getNome(),
                updatedSubCategoria.getDescricao(),
                updatedSubCategoria.getCategoria().getId());
    }

    // Buscar todas as categorias
    public List<SubCategoriaRegistroFinanceiroResponse> findAll() {
        List<SubCategoriaRegistroFinanceiro> categorias = subCategoriaRegistroFinanceiroRepository.findAll();

        return categorias.stream()
                .map(categoria -> new SubCategoriaRegistroFinanceiroResponse(categoria.getId(),
                        categoria.getNome(),
                        categoria.getDescricao(),
                        categoria.getCategoria().getId()))
                .collect(Collectors.toList());
    }

    // Buscar uma categoria por ID
    public SubCategoriaRegistroFinanceiroResponse findById(Long id) {
        SubCategoriaRegistroFinanceiro categoria = subCategoriaRegistroFinanceiroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SubCategoria não encontrada com o ID: " + id));

        return new SubCategoriaRegistroFinanceiroResponse(categoria.getId(),
                categoria.getNome(),
                categoria.getDescricao(),
                categoria.getCategoria().getId());
    }

    // Remover uma categoria
    public void delete(Long id) {
        if (!subCategoriaRegistroFinanceiroRepository.existsById(id)) {
            throw new RuntimeException("SubCategoria não encontrada com o ID: " + id);
        }

        subCategoriaRegistroFinanceiroRepository.deleteById(id);
    }
}