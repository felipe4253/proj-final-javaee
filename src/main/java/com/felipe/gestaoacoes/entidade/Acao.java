package com.felipe.gestaoacoes.entidade;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Acao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(targetEntity=Empresa.class, fetch=FetchType.EAGER)
	private Empresa empresa;
	
	@ManyToOne(targetEntity=Investidor.class, fetch = FetchType.EAGER)
	private Investidor investidorAtual;
	
	private Double valorInicial;
	
	private Double valorAtual;
	
	private Date dataCompra;
	
}
