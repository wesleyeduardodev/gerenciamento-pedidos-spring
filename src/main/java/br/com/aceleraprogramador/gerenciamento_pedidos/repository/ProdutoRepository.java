package br.com.aceleraprogramador.gerenciamento_pedidos.repository;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("""
        SELECT p FROM Produto p 
        WHERE (:id IS NULL OR p.id = :id)
          AND (:nome IS NULL OR LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%')))
          AND (:descricao IS NULL OR LOWER(p.descricao) LIKE LOWER(CONCAT('%', :descricao, '%')))
          AND (:preco IS NULL OR p.preco = :preco)
          AND (:fornecedorId IS NULL OR p.fornecedor.id = :fornecedorId)
    """)
    Page<Produto> findByFilters(
            @Param("id") Long id,
            @Param("nome") String nome,
            @Param("descricao") String descricao,
            @Param("preco") BigDecimal preco,
            @Param("fornecedorId") Long fornecedorId,
            Pageable pageable
    );
}