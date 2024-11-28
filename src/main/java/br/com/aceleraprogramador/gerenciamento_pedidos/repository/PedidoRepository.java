package br.com.aceleraprogramador.gerenciamento_pedidos.repository;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("""
                SELECT p FROM Pedido p 
                WHERE (:id IS NULL OR p.id = :id)
                  AND (:status IS NULL OR LOWER(p.status) LIKE LOWER(CONCAT('%', :status, '%')))
                  AND (:dataPedido IS NULL OR FUNCTION('DATE', p.dataPedido) = :dataPedido)
                  AND (:idCliente IS NULL OR p.cliente.id = :idCliente)
            """)
    Page<Pedido> findByFilters(
            @Param("id") Long id,
            @Param("status") String status,
            @Param("dataPedido") LocalDate dataPedido,
            @Param("idCliente") Long idCliente,
            Pageable pageable
    );

    @Query("""
                SELECT p FROM Pedido p 
                WHERE (:id IS NULL OR p.id = :id)
                  AND (:status IS NULL OR LOWER(p.status) LIKE LOWER(CONCAT('%', :status, '%')))               
                  AND (:idCliente IS NULL OR p.cliente.id = :idCliente)
            """)
    Page<Pedido> findByFilters(
            @Param("id") Long id,
            @Param("status") String status,
            @Param("idCliente") Long idCliente,
            Pageable pageable
    );
}