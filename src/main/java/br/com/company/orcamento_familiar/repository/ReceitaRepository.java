package br.com.company.orcamento_familiar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.company.orcamento_familiar.model.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    
    @Query("select case when count(r) > 0 then true else false end from Receita r where r.descricao = :descricao and YEAR(r.data) = :ano and MONTH(r.data) = :mes")
    boolean isReceitaJaCadastrada(String descricao, int ano, int mes);

}
