package br.com.etecia.epictask.repository;

import br.com.etecia.epictask.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // Produtos com quantidade maior que 0 (disponíveis)
    List<Produto> findByQuantidadeGreaterThan(int quantidade);

    // Produtos com quantidade igual a 0 (em falta)
    List<Produto> findByQuantidadeEquals(int quantidade);

    // Produtos próximos do vencimento
    List<Produto> findByValidadeBefore(java.time.LocalDate data);

    // Filtrar por categoria
    List<Produto> findByCategoria(String categoria);
}
