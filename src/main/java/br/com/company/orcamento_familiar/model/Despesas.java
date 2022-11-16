package br.com.company.orcamento_familiar.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "despesas")
@Getter
@Setter
public class Despesas extends Recurso {
    
}
