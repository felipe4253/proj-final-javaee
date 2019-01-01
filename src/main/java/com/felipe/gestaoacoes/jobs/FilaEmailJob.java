package com.felipe.gestaoacoes.jobs;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.felipe.gestaoacoes.entidade.FilaEmail;
import com.felipe.gestaoacoes.enums.StatusFilaEmail;
import com.felipe.gestaoacoes.repo.FilaEmailRepository;
import com.felipe.gestaoacoes.service.EmailServiceImpl;



@Component
public class FilaEmailJob {

	@Autowired
	private FilaEmailRepository filaEmailRepository;
	
	@Autowired
	private EmailServiceImpl emailService;
	
	private static final Logger log = LoggerFactory.getLogger(FilaEmailJob.class);

	@Scheduled(fixedRate = 5000)
	public void reportCurrentTime() {
		log.info("Buscando emails pendentes de envio");
		List<FilaEmail> emailsPendentes = filaEmailRepository.findByStatus(StatusFilaEmail.PENDENTE);
		
		for (FilaEmail email : emailsPendentes) {
			try {
				emailService.sendSimpleMessage(email.getDestinatario(), "Atualização de ações", email.getMensagem());
				email.setStatus(StatusFilaEmail.ENVIADO);
				filaEmailRepository.saveAndFlush(email);
			} catch (Exception e) {
				email.setStatus(StatusFilaEmail.ERRO);
				filaEmailRepository.saveAndFlush(email);
			}
		}
	}
}
