package br.com.company.orcamento_familiar.service;

import javax.validation.ValidationException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.company.orcamento_familiar.dto.ReceitaDto;
import br.com.company.orcamento_familiar.model.Receita;
import br.com.company.orcamento_familiar.repository.ReceitaRepository;

@Service
public class ReceitaService {
    
    private ReceitaRepository repository;
    private ModelMapper mapper;
    
    public ReceitaDto cadastrarReceita(ReceitaDto receitaDto) {

        boolean receitaJaCadastradaNoMes = repository.isReceitaJaCadastrada(receitaDto.getDescricao(), receitaDto.getData().getYear(), receitaDto.getData().getMonthValue());
        if (receitaJaCadastradaNoMes) {
            throw new ValidationException("Receita já cadastrada no mês!");
        }

        Receita receita = mapper.map(receitaDto, Receita.class);
        receita = repository.save(receita);
        return mapper.map(receita, ReceitaDto.class);
    }

}
