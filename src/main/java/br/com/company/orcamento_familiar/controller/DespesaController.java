package br.com.company.orcamento_familiar.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.company.orcamento_familiar.dto.DespesasDto;
import br.com.company.orcamento_familiar.service.DespesasService;

@RestController
@RequestMapping("/despesas")
public class DespesaController {

    @Autowired
    private DespesasService service;
    
    @PostMapping

    @GetMapping
    public ResponseEntity<List<DespesasDto>> listarTodos(){
        List<DespesasDto> despesas = service.listarTodos();
        return new ResponseEntity<>(despesas, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DespesasDto> obterPorId(@PathVariable @NotNull Long id){
        Optional<DespesasDto> receita = service.obterPorId(id);
        if (receita.isPresent()) {
            return new ResponseEntity<>(receita.get(),HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Void> exclusao(@PathVariable @NotNull Long id){
        Optional<DespesasDto> receita = service.obterPorId(id);
        if (receita.isPresent()) {
            service.exclusao(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DespesasDto> atualizar(@PathVariable @NotNull Long id, @RequestBody @Valid DespesasDto despesasDto) {
        Optional<DespesasDto> despesa = service.obterPorId(id);
        if (despesa.isPresent()) {
            service.atualizar(id, despesasDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
