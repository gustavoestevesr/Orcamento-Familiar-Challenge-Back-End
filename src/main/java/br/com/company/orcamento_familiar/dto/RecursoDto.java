package br.com.company.orcamento_familiar.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecursoDto {
    
    private Long id;

    @NotEmpty(message = "Necessário informar um valor para o campo descrição")
    private String descricao;

    @Positive(message = "Necessário informar um valor númerico positivo para o campo valor")
    private double valor;

    @NotNull(message = "Necessário informar o campo data")
    private LocalDate data;

}
