package com.felipe.gestaoacoes.entidade;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Empresa {
	
	public Empresa(Integer id) {
		this.id = id;
	}
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String nome;
	
	private String email;
	
	@OneToMany(targetEntity=Acao.class, fetch = FetchType.LAZY)
	private List<Acao> acoes;

}
