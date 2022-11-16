package br.com.company.orcamento_familiar.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.company.orcamento_familiar.dto.ReceitaDto;
import br.com.company.orcamento_familiar.service.ReceitaService;

@RestController
@RequestMapping(path = "/receitas")
public class ReceitaController {

    private ReceitaService service;
    
    @PostMapping()
    public ResponseEntity<ReceitaDto> cadastrarReceita(@RequestBody @Valid ReceitaDto receitaDto){
        ReceitaDto receita = service.cadastrarReceita(receitaDto);
        return new ResponseEntity<>(receita, HttpStatus.CREATED);
    }

}
