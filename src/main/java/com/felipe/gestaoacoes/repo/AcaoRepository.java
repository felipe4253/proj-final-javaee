package com.felipe.gestaoacoes.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipe.gestaoacoes.entidade.Acao;
import com.felipe.gestaoacoes.entidade.Empresa;

@Repository
public interface AcaoRepository extends JpaRepository<Acao, Integer> {
	
	//Ofertas disponiveis por empresa
	List<Acao> findByEmpresaAndInvestidorAtualIsNull(Empresa empresa);
	
	//Ofertas disponiveis
	List<Acao> findByInvestidorAtualIsNull();

}
