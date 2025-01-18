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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubCategoriaRegistroFinanceiroService {

    private final CategoriaRegistroFinanceiroService categoriaRegistroFinanceiroService;
    private final SubCategoriaRegistroFinanceiroRepository subCategoriaRegistroFinanceiroRepository;

    public SubCategoriaRegistroFinanceiroResponse create(Long idUsuario, SubCategoriaRegistroFinanceiroRequest request) {

        CategoriaRegistroFinanceiro categoriaRegistroFinanceiro = categoriaRegistroFinanceiroService.findByIdAndReturnCategoriaRegistroFinanceiro(request.getIdCategoria(), idUsuario);

        SubCategoriaRegistroFinanceiro subCategoriaRegistroFinanceiro = new SubCategoriaRegistroFinanceiro();
        subCategoriaRegistroFinanceiro.setNome(request.getNome());
        subCategoriaRegistroFinanceiro.setDescricao(request.getDescricao());
        subCategoriaRegistroFinanceiro.setCategoria(categoriaRegistroFinanceiro);
        subCategoriaRegistroFinanceiro.setUsuario(Usuario.builder().id(idUsuario).build());

        SubCategoriaRegistroFinanceiro savedSubCategoria = subCategoriaRegistroFinanceiroRepository.save(subCategoriaRegistroFinanceiro);

        return new SubCategoriaRegistroFinanceiroResponse(savedSubCategoria.getId(),
                savedSubCategoria.getNome(),
                savedSubCategoria.getDescricao(),
                savedSubCategoria.getCategoria().getId(),
                savedSubCategoria.getCategoria().getDescricao()
        );
    }

    public SubCategoriaRegistroFinanceiroResponse update(Long id, Long idUsuario, SubCategoriaRegistroFinanceiroRequest request) {

        CategoriaRegistroFinanceiro categoriaRegistroFinanceiro = categoriaRegistroFinanceiroService.findByIdAndReturnCategoriaRegistroFinanceiro(request.getIdCategoria(), idUsuario);

        SubCategoriaRegistroFinanceiro subCategoriaRegistroFinanceiro = subCategoriaRegistroFinanceiroRepository.findByIdAndUsuarioId(id, idUsuario)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + id));

        subCategoriaRegistroFinanceiro.setNome(request.getNome());
        subCategoriaRegistroFinanceiro.setDescricao(request.getDescricao());
        subCategoriaRegistroFinanceiro.setCategoria(categoriaRegistroFinanceiro);
        subCategoriaRegistroFinanceiro.setUsuario(Usuario.builder().id(idUsuario).build());

        SubCategoriaRegistroFinanceiro updatedSubCategoria = subCategoriaRegistroFinanceiroRepository.save(subCategoriaRegistroFinanceiro);

        return new SubCategoriaRegistroFinanceiroResponse(updatedSubCategoria.getId(),
                updatedSubCategoria.getNome(),
                updatedSubCategoria.getDescricao(),
                updatedSubCategoria.getCategoria().getId(),
                updatedSubCategoria.getCategoria().getDescricao());
    }

    public List<SubCategoriaRegistroFinanceiroResponse> findAll(Long idUsuario) {

        List<SubCategoriaRegistroFinanceiro> categorias = subCategoriaRegistroFinanceiroRepository.findAllByUsuarioId(idUsuario);

        return categorias.stream()
                .map(categoria -> new SubCategoriaRegistroFinanceiroResponse(categoria.getId(),
                        categoria.getNome(),
                        categoria.getDescricao(),
                        categoria.getCategoria().getId(),
                        categoria.getCategoria().getNome()))
                .collect(Collectors.toList());
    }

    public SubCategoriaRegistroFinanceiroResponse findById(Long id, Long idUsuario) {

        SubCategoriaRegistroFinanceiro subCategoriaRegistroFinanceiro = subCategoriaRegistroFinanceiroRepository.findByIdAndUsuarioId(id, idUsuario)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + id));

        return new SubCategoriaRegistroFinanceiroResponse(subCategoriaRegistroFinanceiro.getId(),
                subCategoriaRegistroFinanceiro.getNome(),
                subCategoriaRegistroFinanceiro.getDescricao(),
                subCategoriaRegistroFinanceiro.getCategoria().getId(),
                subCategoriaRegistroFinanceiro.getDescricao());
    }

    public SubCategoriaRegistroFinanceiro findByIdUsuario(Long id, Long idUsuario) {
        return subCategoriaRegistroFinanceiroRepository.findByIdAndUsuarioId(id, idUsuario)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + id));
    }


    public List<SubCategoriaRegistroFinanceiroResponse> findByIdCategoria(Long idCategoria, Long idUsuario) {

        CategoriaRegistroFinanceiro categoriaRegistroFinanceiro = categoriaRegistroFinanceiroService.findByIdAndReturnCategoriaRegistroFinanceiro(idCategoria, idUsuario);

        List<SubCategoriaRegistroFinanceiro> subCategoriaRegistroFinanceiros = subCategoriaRegistroFinanceiroRepository.findByCategoriaAndUsuarioId(categoriaRegistroFinanceiro, idUsuario);

        List<SubCategoriaRegistroFinanceiroResponse> subCategorias = new ArrayList<>();
        subCategoriaRegistroFinanceiros.forEach(categoria -> {
            SubCategoriaRegistroFinanceiroResponse subCategoriaRegistroFinanceiroResponse = new SubCategoriaRegistroFinanceiroResponse(categoria.getId(),
                    categoria.getNome(),
                    categoria.getDescricao(),
                    categoria.getCategoria().getId(),
                    categoria.getDescricao());
            subCategorias.add(subCategoriaRegistroFinanceiroResponse);
        });
        return subCategorias;
    }

    public void delete(Long id, Long idUsuario) {
        if (!subCategoriaRegistroFinanceiroRepository.existsByIdAndUsuarioId(id, idUsuario)) {
            throw new RuntimeException("SubCategoria não encontrada com o ID: " + id);
        }
        subCategoriaRegistroFinanceiroRepository.deleteById(id);
    }
}