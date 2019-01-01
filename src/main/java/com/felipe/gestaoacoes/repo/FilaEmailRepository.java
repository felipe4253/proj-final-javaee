package com.felipe.gestaoacoes.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipe.gestaoacoes.entidade.FilaEmail;
import com.felipe.gestaoacoes.enums.StatusFilaEmail;

@Repository
public interface FilaEmailRepository extends JpaRepository<FilaEmail, Integer> {
	
	public List<FilaEmail> findByStatus(StatusFilaEmail status);
	
}
