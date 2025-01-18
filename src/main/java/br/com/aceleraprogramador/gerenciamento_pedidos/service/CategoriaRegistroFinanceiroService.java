package br.com.aceleraprogramador.gerenciamento_pedidos.service;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.CategoriaRegistroFinanceiroRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.CategoriaRegistroFinanceiroResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.CategoriaRegistroFinanceiro;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Usuario;
import br.com.aceleraprogramador.gerenciamento_pedidos.repository.CategoriaRegistroFinanceiroRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoriaRegistroFinanceiroService {

    private final CategoriaRegistroFinanceiroRepository categoriaRegistroFinanceiroRepository;

    public CategoriaRegistroFinanceiroResponse create(Long idUsuario, CategoriaRegistroFinanceiroRequest request) {

        CategoriaRegistroFinanceiro categoria = new CategoriaRegistroFinanceiro();
        categoria.setNome(request.getNome());
        categoria.setDescricao(request.getDescricao());
        categoria.setUsuario(Usuario.builder().id(idUsuario).build());

        CategoriaRegistroFinanceiro savedCategoria = categoriaRegistroFinanceiroRepository.save(categoria);

        return new CategoriaRegistroFinanceiroResponse(savedCategoria.getId(), savedCategoria.getNome(), savedCategoria.getDescricao());
    }

    public CategoriaRegistroFinanceiroResponse update(Long id, Long idUsuario, CategoriaRegistroFinanceiroRequest request) {

        CategoriaRegistroFinanceiro categoria = categoriaRegistroFinanceiroRepository.findByIdAndUsuarioId(id, idUsuario)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + id));

        categoria.setNome(request.getNome());
        categoria.setDescricao(request.getDescricao());
        categoria.setUsuario(Usuario.builder().id(idUsuario).build());
        CategoriaRegistroFinanceiro updatedCategoria = categoriaRegistroFinanceiroRepository.save(categoria);

        return new CategoriaRegistroFinanceiroResponse(updatedCategoria.getId(), updatedCategoria.getNome(), updatedCategoria.getDescricao());
    }

    public List<CategoriaRegistroFinanceiroResponse> findAll(Long idUsuario) {

        List<CategoriaRegistroFinanceiro> categorias = categoriaRegistroFinanceiroRepository.findAllByUsuarioId(idUsuario);

        return categorias.stream()
                .map(categoria -> new CategoriaRegistroFinanceiroResponse(categoria.getId(), categoria.getNome(), categoria.getDescricao()))
                .collect(Collectors.toList());
    }

    public CategoriaRegistroFinanceiroResponse findById(Long id, Long idUsuario) {

        CategoriaRegistroFinanceiro categoria = categoriaRegistroFinanceiroRepository.findByIdAndUsuarioId(id, idUsuario)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + id));

        return new CategoriaRegistroFinanceiroResponse(categoria.getId(), categoria.getNome(), categoria.getDescricao());
    }

    public CategoriaRegistroFinanceiro findByIdAndReturnCategoriaRegistroFinanceiro(Long id, Long idUsuario) {
      return categoriaRegistroFinanceiroRepository.findByIdAndUsuarioId(id, idUsuario)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + id));
    }

    public void delete(Long id, Long idUsuario) {
        if (!categoriaRegistroFinanceiroRepository.existsByIdAndUsuarioId(id, idUsuario)) {
            throw new RuntimeException("Categoria não encontrada com o ID: " + id);
        }
        categoriaRegistroFinanceiroRepository.deleteById(id);
    }
}