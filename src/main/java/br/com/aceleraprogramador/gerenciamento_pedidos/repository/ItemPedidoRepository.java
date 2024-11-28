package br.com.aceleraprogramador.gerenciamento_pedidos.repository;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.ItemPedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;


@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

    @Query("""
                SELECT i FROM ItemPedido i 
                WHERE (:id IS NULL OR i.id = :id)
                  AND (:pedidoId IS NULL OR i.pedido.id = :pedidoId)
                  AND (:produtoId IS NULL OR i.produto.id = :produtoId)
                  AND (:quantidade IS NULL OR i.quantidade = :quantidade)
                  AND (:precoUnitario IS NULL OR i.precoUnitario = :precoUnitario)
            """)
    Page<ItemPedido> findByFilters(
            @Param("id") Long id,
            @Param("pedidoId") Long pedidoId,
            @Param("produtoId") Long produtoId,
            @Param("quantidade") Integer quantidade,
            @Param("precoUnitario") BigDecimal precoUnitario,
            Pageable pageable
    );

    List<ItemPedido> findByPedidoId(Long idPedido);
}