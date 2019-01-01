package com.felipe.gestaoacoes.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipe.gestaoacoes.entidade.Investidor;

@Repository
public interface InvestidorRepository extends JpaRepository<Investidor, Integer> {

}
