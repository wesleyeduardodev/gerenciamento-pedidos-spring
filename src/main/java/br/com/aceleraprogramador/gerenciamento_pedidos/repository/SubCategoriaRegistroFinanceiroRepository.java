package br.com.aceleraprogramador.gerenciamento_pedidos.repository;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.CategoriaRegistroFinanceiro;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.SubCategoriaRegistroFinanceiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubCategoriaRegistroFinanceiroRepository extends JpaRepository<SubCategoriaRegistroFinanceiro, Long> {
    List<SubCategoriaRegistroFinanceiro> findAllByUsuarioId(Long usuarioId);
    List<SubCategoriaRegistroFinanceiro> findByCategoriaAndUsuarioId(CategoriaRegistroFinanceiro categoria, Long idUsuario);
    boolean existsByIdAndUsuarioId(Long id, Long usuarioId);
    Optional<SubCategoriaRegistroFinanceiro> findByIdAndUsuarioId(Long id, Long usuarioId);
}