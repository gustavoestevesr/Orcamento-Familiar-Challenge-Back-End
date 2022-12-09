package br.com.company.orcamento_familiar.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.company.orcamento_familiar.dto.DespesasDto;
import br.com.company.orcamento_familiar.model.Categoria;
import br.com.company.orcamento_familiar.model.Despesas;
import br.com.company.orcamento_familiar.repository.DespesaRepository;

@Service
public class DespesasService {
    
    @Autowired
    private DespesaRepository repository;
    private ModelMapper mapper = new ModelMapper();

    public DespesasDto salvar(DespesasDto despesasDto) {
        // boolean despesaJaCadastradaNoMes = repository.despesaJaCadastrada(despesasDto.getDescricao(), despesasDto.getData().getYear(), despesasDto.getData().getMonthValue());
        // if (despesaJaCadastradaNoMes) {
        //     throw new ValidationException("Despesa já cadastrada no mês!");
        // }

        if (despesasDto.getCategoria() == null) {
            despesasDto.setCategoria(Categoria.OUTRAS);
        }

        Despesas despesa = mapper.map(despesasDto, Despesas.class);
        despesa = repository.save(despesa);
        return mapper.map(despesa, DespesasDto.class);
    }

    public List<DespesasDto> listarTodos(){
        List<Despesas> despesas = repository.findAll();
        return despesas.stream()
        .map(d -> mapper.map(d, DespesasDto.class))
        .collect(Collectors.toList());
    }

    public Optional<DespesasDto> obterPorId(@NotNull Long id){
        Optional<Despesas> despesa = repository.findById(id);
        if (despesa.isPresent()) {
            return Optional.of(mapper.map(despesa.get(), DespesasDto.class));
        }
        return Optional.empty();
    }

    public void exclusao(@NotNull Long id){
        repository.deleteById(id);
    }

    public DespesasDto atualizar(Long id, DespesasDto despesasDto ){
        Despesas receita = mapper.map(despesasDto, Despesas.class);
        receita.setId(id);
        receita = repository.save(receita);
        return mapper.map(receita, DespesasDto.class);
    }

}
