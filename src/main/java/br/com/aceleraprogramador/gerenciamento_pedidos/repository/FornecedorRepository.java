package br.com.aceleraprogramador.gerenciamento_pedidos.repository;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Fornecedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

    @Query("""
                SELECT f FROM Fornecedor f 
                WHERE (:id IS NULL OR f.id = :id)
                  AND (:nome IS NULL OR LOWER(f.nome) LIKE LOWER(CONCAT('%', :nome, '%')))
                     AND (:cnpj IS NULL OR LOWER(f.cnpj) LIKE LOWER(CONCAT('%', :cnpj, '%')))
                  AND (:contato IS NULL OR LOWER(f.contato) LIKE LOWER(CONCAT('%', :contato, '%')))
                  AND (:endereco IS NULL OR LOWER(f.endereco) LIKE LOWER(CONCAT('%', :endereco, '%')))
            """)
    Page<Fornecedor> findByFilters(
            @Param("id") Long id,
            @Param("nome") String nome,
            @Param("cnpj") String cnpj,
            @Param("contato") String contato,
            @Param("endereco") String endereco,
            Pageable pageable
    );
}