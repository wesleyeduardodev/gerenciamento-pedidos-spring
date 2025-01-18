package br.com.aceleraprogramador.gerenciamento_pedidos.repository;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.CategoriaRegistroFinanceiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRegistroFinanceiroRepository extends JpaRepository<CategoriaRegistroFinanceiro, Long> {
    List<CategoriaRegistroFinanceiro> findAllByUsuarioId(Long usuarioId);
    boolean existsByIdAndUsuarioId(Long id, Long usuarioId);
    Optional<CategoriaRegistroFinanceiro> findByIdAndUsuarioId(Long id, Long usuarioId);
}