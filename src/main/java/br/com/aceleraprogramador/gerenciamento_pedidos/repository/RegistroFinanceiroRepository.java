package br.com.aceleraprogramador.gerenciamento_pedidos.repository;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.RegistroFinanceiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RegistroFinanceiroRepository extends JpaRepository<RegistroFinanceiro, Long> {
    List<RegistroFinanceiro> findAllByUsuarioId(Long usuarioId);
    boolean existsByIdAndUsuarioId(Long id, Long usuarioId);
    Optional<RegistroFinanceiro> findByIdAndUsuarioId(Long id, Long usuarioId);
}