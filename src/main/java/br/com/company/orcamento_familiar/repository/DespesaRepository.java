package br.com.company.orcamento_familiar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.company.orcamento_familiar.model.Despesas;

public interface DespesaRepository extends JpaRepository<Despesas,Long>{
    
}
