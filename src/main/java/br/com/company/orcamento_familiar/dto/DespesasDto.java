package br.com.company.orcamento_familiar.dto;

import br.com.company.orcamento_familiar.model.Categoria;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DespesasDto extends RecursoDto {

    private Categoria categoria;
    
}
