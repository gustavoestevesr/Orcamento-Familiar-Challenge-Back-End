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

import br.com.company.orcamento_familiar.dto.ReceitaDto;
import br.com.company.orcamento_familiar.service.ReceitaService;

@RestController
@RequestMapping(path = "/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaService service;

    @PostMapping()
    public ResponseEntity<ReceitaDto> salvar(@RequestBody @Valid ReceitaDto receitaDto) {
        ReceitaDto receita = service.salvar(receitaDto);
        return new ResponseEntity<>(receita, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ReceitaDto>> listarTodos() {
        List<ReceitaDto> receitas = service.listarTodos();
        return new ResponseEntity<>(receitas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceitaDto> obterPorId(@PathVariable @NotNull Long id) {
        Optional<ReceitaDto> receita = service.obterPorId(id);
        if (receita.isPresent()) {
            return new ResponseEntity(receita.get(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public ResponseEntity<Void> exclusao(@PathVariable @NotNull Long id) {
        Optional<ReceitaDto> receita = service.obterPorId(id);
        if (receita.isPresent()) {
            service.exclusao(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReceitaDto> atualizar(@PathVariable @NotNull Long id,
            @RequestBody @Valid ReceitaDto receitaDto) {
        Optional<ReceitaDto> receita = service.obterPorId(id);
        if (receita.isPresent()) {
            service.atualizar(id, receitaDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
