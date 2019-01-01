package com.felipe.gestaoacoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.gestaoacoes.entidade.Acao;
import com.felipe.gestaoacoes.entidade.Empresa;
import com.felipe.gestaoacoes.repo.AcaoRepository;

@RestController ()
public class EmpresaController {
	
	@Autowired
	private AcaoRepository acaoRepository;
	
	@RequestMapping("/v1/empresa/{id}/acao")
	public List<Acao> getAll(@PathVariable(value="id") String id) {
		return acaoRepository.findByEmpresaAndInvestidorAtualIsNull(
				new Empresa(Integer.parseInt(id))
			);
	}

}
