package br.com.aceleraprogramador.gerenciamento_pedidos.service;

import br.com.aceleraprogramador.gerenciamento_pedidos.dto.request.SubCategoriaRegistroFinanceiroRequest;
import br.com.aceleraprogramador.gerenciamento_pedidos.dto.response.SubCategoriaRegistroFinanceiroResponse;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.CategoriaRegistroFinanceiro;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.SubCategoriaRegistroFinanceiro;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Usuario;
import br.com.aceleraprogramador.gerenciamento_pedidos.repository.CategoriaRegistroFinanceiroRepository;
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

    private final CategoriaRegistroFinanceiroRepository categoriaRegistroFinanceiroRepository;
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
                savedSubCategoria.getCategoria().getId(),
                savedSubCategoria.getCategoria().getDescricao()
        );
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
                updatedSubCategoria.getCategoria().getId(),
                updatedSubCategoria.getCategoria().getDescricao());
    }

    // Buscar todas as categorias
    public List<SubCategoriaRegistroFinanceiroResponse> findAll() {
        List<SubCategoriaRegistroFinanceiro> categorias = subCategoriaRegistroFinanceiroRepository.findAll();

        return categorias.stream()
                .map(categoria -> new SubCategoriaRegistroFinanceiroResponse(categoria.getId(),
                        categoria.getNome(),
                        categoria.getDescricao(),
                        categoria.getCategoria().getId(),
                        categoria.getDescricao()))
                .collect(Collectors.toList());
    }

    // Buscar uma categoria por ID
    public SubCategoriaRegistroFinanceiroResponse findById(Long id) {
        SubCategoriaRegistroFinanceiro categoria = subCategoriaRegistroFinanceiroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SubCategoria não encontrada com o ID: " + id));

        return new SubCategoriaRegistroFinanceiroResponse(categoria.getId(),
                categoria.getNome(),
                categoria.getDescricao(),
                categoria.getCategoria().getId(),
                categoria.getDescricao());
    }

    public List<SubCategoriaRegistroFinanceiroResponse> findByIdCategoria(Long idCategoria) {
        CategoriaRegistroFinanceiro categoriaRegistroFinanceiro = categoriaRegistroFinanceiroRepository.findById(idCategoria).orElse(null);
        List<SubCategoriaRegistroFinanceiro> subCategoriaRegistroFinanceiros = subCategoriaRegistroFinanceiroRepository.findByCategoria(categoriaRegistroFinanceiro);
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

    // Remover uma categoria
    public void delete(Long id) {
        if (!subCategoriaRegistroFinanceiroRepository.existsById(id)) {
            throw new RuntimeException("SubCategoria não encontrada com o ID: " + id);
        }

        subCategoriaRegistroFinanceiroRepository.deleteById(id);
    }
}