package br.com.company.orcamento_familiar.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.company.orcamento_familiar.dto.ReceitaDto;
import br.com.company.orcamento_familiar.model.Receita;
import br.com.company.orcamento_familiar.repository.ReceitaRepository;

@Service
public class ReceitaService {
    
    @Autowired
    private ReceitaRepository repository;
    private ModelMapper mapper = new ModelMapper();
    
    public ReceitaDto salvar(ReceitaDto receitaDto) {
        boolean receitaJaCadastradaNoMes = repository.receitaJaCadastrada(receitaDto.getDescricao(), receitaDto.getData().getYear(), receitaDto.getData().getMonthValue());
        if (receitaJaCadastradaNoMes) {
            throw new ValidationException("Receita já cadastrada no mês!");
        }
        Receita receita = mapper.map(receitaDto, Receita.class);
        receita = repository.save(receita);
        return mapper.map(receita, ReceitaDto.class);
    }

    public Optional<ReceitaDto> obterPorId(@NotNull Long id) {
        Optional<Receita> receita = repository.findById(id);
        if (receita.isPresent()) {
            return Optional.of(mapper.map(receita.get(), ReceitaDto.class));
        }
        return Optional.empty();
    }

    public List<ReceitaDto> listarTodos() {
        List<Receita> receitas = repository.findAll();
        return receitas.stream()
            .map(r -> mapper.map(r, ReceitaDto.class))
            .collect(Collectors.toList());
    }

    public void exclusao(Long id){
        repository.deleteById(id);
    }

    public ReceitaDto atualizar(Long id, ReceitaDto receitaDto ){
        Receita receita = mapper.map(receitaDto, Receita.class);
        receita.setId(id);
        receita = repository.save(receita);
        return mapper.map(receita, ReceitaDto.class);
    }

}
