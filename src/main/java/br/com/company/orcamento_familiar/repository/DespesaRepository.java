package br.com.company.orcamento_familiar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.company.orcamento_familiar.model.Despesas;

public interface DespesaRepository extends JpaRepository<Despesas,Long>{

    // @Query("select case when count(d) > 0 then true else false end from Despesa d where d.descricao = :descricao and YEAR(d.data) = :ano and MONTH(d.data) = :mes")
    // boolean despesaJaCadastrada(String descricao, int ano, int mes);
    
}
