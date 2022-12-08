package br.com.company.orcamento_familiar.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.company.orcamento_familiar.dto.DespesasDto;
import br.com.company.orcamento_familiar.model.Despesas;
import br.com.company.orcamento_familiar.repository.DespesaRepository;

@Service
public class DespesasService {
    
    @Autowired
    private DespesaRepository repository;
    private ModelMapper mapper = new ModelMapper();

    public List<DespesasDto> listarTodos(){
        List<Despesas> despesas = repository.findAll();
        return despesas.stream()
        .map(d -> mapper.map(despesas, DespesasDto.class))
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

}
