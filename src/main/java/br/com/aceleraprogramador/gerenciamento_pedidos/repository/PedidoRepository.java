package br.com.aceleraprogramador.gerenciamento_pedidos.repository;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("""
        SELECT p FROM Pedido p 
        WHERE (:id IS NULL OR p.id = :id)
          AND (:status IS NULL OR LOWER(p.status) LIKE LOWER(CONCAT('%', :status, '%')))
          AND (:dataPedido IS NULL OR p.dataPedido = :dataPedido)
          AND (:clienteId IS NULL OR p.cliente.id = :clienteId)
    """)
    Page<Pedido> findByFilters(
            @Param("id") Long id,
            @Param("status") String status,
            @Param("dataPedido") LocalDateTime dataPedido,
            @Param("clienteId") Long clienteId,
            Pageable pageable
    );
}