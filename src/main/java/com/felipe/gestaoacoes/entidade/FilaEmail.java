package com.felipe.gestaoacoes.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.felipe.gestaoacoes.enums.StatusFilaEmail;

import lombok.Data;

@Data
@Entity
public class FilaEmail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String destinatario;
	
	private String mensagem;
	
	private StatusFilaEmail status;
	
	
}
