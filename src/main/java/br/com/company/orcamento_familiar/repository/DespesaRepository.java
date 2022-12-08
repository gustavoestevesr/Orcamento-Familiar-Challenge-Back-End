package br.com.company.orcamento_familiar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.company.orcamento_familiar.model.Despesas;

public interface DespesaRepository extends JpaRepository<Despesas,Long>{

    @Query("select case when count(r) > 0 then true else false end from Despesa r where r.descricao = :descricao and YEAR(r.data) = :ano and MONTH(r.data) = :mes")
    boolean isDespesaJaCadastrada(String descricao, int ano, int mes);
    
}
