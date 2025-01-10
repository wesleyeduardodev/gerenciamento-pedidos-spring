package br.com.aceleraprogramador.gerenciamento_pedidos.service;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.CategoriaRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.CategoriaResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Categoria;
import br.com.aceleraprogramador.gerenciamento_pedidos.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    // Criar nova categoria
    public CategoriaResponse create(CategoriaRequest request) {
        Categoria categoria = new Categoria();
        categoria.setNome(request.getNome());

        Categoria savedCategoria = categoriaRepository.save(categoria);

        return new CategoriaResponse(savedCategoria.getId(), savedCategoria.getNome());
    }

    // Atualizar uma categoria
    public CategoriaResponse update(Long id, CategoriaRequest request) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + id));

        categoria.setNome(request.getNome());
        Categoria updatedCategoria = categoriaRepository.save(categoria);

        return new CategoriaResponse(updatedCategoria.getId(), updatedCategoria.getNome());
    }

    // Buscar todas as categorias
    public List<CategoriaResponse> findAll() {
        List<Categoria> categorias = categoriaRepository.findAll();

        return categorias.stream()
                .map(categoria -> new CategoriaResponse(categoria.getId(), categoria.getNome()))
                .collect(Collectors.toList());
    }

    // Buscar uma categoria por ID
    public CategoriaResponse findById(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + id));

        return new CategoriaResponse(categoria.getId(), categoria.getNome());
    }

    // Remover uma categoria
    public void delete(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Categoria não encontrada com o ID: " + id);
        }

        categoriaRepository.deleteById(id);
    }
}