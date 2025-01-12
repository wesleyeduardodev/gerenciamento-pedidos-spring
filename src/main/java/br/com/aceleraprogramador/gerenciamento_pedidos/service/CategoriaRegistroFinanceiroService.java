package br.com.aceleraprogramador.gerenciamento_pedidos.service;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.CategoriaRegistroFinanceiroRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.CategoriaRegistroFinanceiroResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.CategoriaRegistroFinanceiro;
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

    // Criar nova categoria
    public CategoriaRegistroFinanceiroResponse create(CategoriaRegistroFinanceiroRequest request) {
        CategoriaRegistroFinanceiro categoria = new CategoriaRegistroFinanceiro();
        categoria.setNome(request.getNome());

        CategoriaRegistroFinanceiro savedCategoria = categoriaRegistroFinanceiroRepository.save(categoria);

        return new CategoriaRegistroFinanceiroResponse(savedCategoria.getId(), savedCategoria.getNome());
    }

    // Atualizar uma categoria
    public CategoriaRegistroFinanceiroResponse update(Long id, CategoriaRegistroFinanceiroRequest request) {
        CategoriaRegistroFinanceiro categoria = categoriaRegistroFinanceiroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + id));

        categoria.setNome(request.getNome());
        CategoriaRegistroFinanceiro updatedCategoria = categoriaRegistroFinanceiroRepository.save(categoria);

        return new CategoriaRegistroFinanceiroResponse(updatedCategoria.getId(), updatedCategoria.getNome());
    }

    // Buscar todas as categorias
    public List<CategoriaRegistroFinanceiroResponse> findAll() {
        List<CategoriaRegistroFinanceiro> categorias = categoriaRegistroFinanceiroRepository.findAll();

        return categorias.stream()
                .map(categoria -> new CategoriaRegistroFinanceiroResponse(categoria.getId(), categoria.getNome()))
                .collect(Collectors.toList());
    }

    // Buscar uma categoria por ID
    public CategoriaRegistroFinanceiroResponse findById(Long id) {
        CategoriaRegistroFinanceiro categoria = categoriaRegistroFinanceiroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + id));

        return new CategoriaRegistroFinanceiroResponse(categoria.getId(), categoria.getNome());
    }

    // Remover uma categoria
    public void delete(Long id) {
        if (!categoriaRegistroFinanceiroRepository.existsById(id)) {
            throw new RuntimeException("Categoria não encontrada com o ID: " + id);
        }

        categoriaRegistroFinanceiroRepository.deleteById(id);
    }
}