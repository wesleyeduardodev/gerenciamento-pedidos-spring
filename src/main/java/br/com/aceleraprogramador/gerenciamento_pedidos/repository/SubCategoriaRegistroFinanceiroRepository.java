package br.com.aceleraprogramador.gerenciamento_pedidos.repository;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.CategoriaRegistroFinanceiro;
import br.com.aceleraprogramador.gerenciamento_pedidos.model.SubCategoriaRegistroFinanceiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoriaRegistroFinanceiroRepository extends JpaRepository<SubCategoriaRegistroFinanceiro, Long> {

    List<SubCategoriaRegistroFinanceiro> findByCategoria(CategoriaRegistroFinanceiro categoria);

}