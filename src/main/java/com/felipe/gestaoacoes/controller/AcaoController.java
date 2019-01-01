package com.felipe.gestaoacoes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.gestaoacoes.entidade.Acao;
import com.felipe.gestaoacoes.entidade.Empresa;
import com.felipe.gestaoacoes.entidade.FilaEmail;
import com.felipe.gestaoacoes.enums.StatusFilaEmail;
import com.felipe.gestaoacoes.repo.AcaoRepository;
import com.felipe.gestaoacoes.repo.EmpresaRepository;
import com.felipe.gestaoacoes.repo.FilaEmailRepository;
import com.felipe.gestaoacoes.repo.InvestidorRepository;

@RestController
public class AcaoController {
	
	@Autowired
	private AcaoRepository acaoRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private InvestidorRepository investidorRepository;
	
	@Autowired
	private FilaEmailRepository filaEmailRepository;
	
	@RequestMapping("/v1/acao")
	public List<Acao> getAll() {
		return acaoRepository.findByInvestidorAtualIsNull();
	}
	
	@RequestMapping(value = "/v1/acao", method = RequestMethod.POST, consumes="application/json", produces="application/json")
    public ResponseEntity<String> criarAcao(@RequestBody Acao acao) {
        System.out.println("Criando a acao" + acao.getId());
        Optional<Empresa> empresa = empresaRepository.findById(acao.getEmpresa().getId());
        if (empresaRepository.findById(acao.getEmpresa().getId()) == null) {
            System.out.println("Não existe uma empresa com este ID");
            return new ResponseEntity<String>("Não existe uma empresa com este ID", HttpStatus.FAILED_DEPENDENCY);
        }
        acao.setEmpresa(empresa.get());
        
        acaoRepository.saveAndFlush(acao);
 
        return new ResponseEntity<String>("{\"mensagem\": \"Ação registrada com sucesso.\"}",HttpStatus.CREATED);
    }
	
	//Compra de ações
	@RequestMapping(value = "/v1/acao", method = RequestMethod.PUT, consumes="application/json", produces="application/json")
    public ResponseEntity<String> comprarAcao(@RequestBody Acao acao) {
		
		if (acao.getId() == null) {
			System.out.println("Favor informar o ID da ação");
			return new ResponseEntity<String>("Favor informar o ID da ação", HttpStatus.FAILED_DEPENDENCY);
		}
		
		Integer idNovoInvestidor = acao.getInvestidorAtual().getId();
		
		//Busca do banco
		
		
        System.out.println("Comprando a ação" + acao.getId());
        Optional<Empresa> empresa = empresaRepository.findById(acao.getEmpresa().getId());
        
        if (empresaRepository.findById(acao.getEmpresa().getId()) == null) {
            System.out.println("Não existe uma empresa com este ID");
            return new ResponseEntity<String>("Não existe uma empresa com este ID", HttpStatus.FAILED_DEPENDENCY);
        }
        
        if (investidorRepository.findById(acao.getInvestidorAtual().getId()) == null) {
            System.out.println("Não existe um investidor com este ID");
            return new ResponseEntity<String>("Não existe um investidor com este ID", HttpStatus.FAILED_DEPENDENCY);
        }

        //Atualizar Dados
        Optional<Acao> acaoDoBanco = acaoRepository.findById(acao.getId());
        acao = acaoDoBanco.get();
        
        acao.setEmpresa(empresa.get());
        acao.setInvestidorAtual(investidorRepository.findById(idNovoInvestidor).get());
        Acao acaoSalva = acaoRepository.saveAndFlush(acao);
        
        //enviar emails para comprador e empresa
        enviarEmails(acaoSalva);
 
        return new ResponseEntity<String>("{\"mensagem\": \"Compra efetuada com sucesso.\"}",HttpStatus.CREATED);
    }

	private void enviarEmails(Acao acao) {
		
		String mensagem = "Olá. A ação " + acao.getId() + ", da empresa" + acao.getEmpresa().getNome() + " foi comprada por " + acao.getInvestidorAtual().getNome() + ". O valor da operacao foi de R$" + acao.getValorAtual();
		
		//Email para investidor (comprador)
		FilaEmail filaEmailComprador = new FilaEmail();
		filaEmailComprador.setDestinatario(acao.getInvestidorAtual().getEmail());
		filaEmailComprador.setMensagem(mensagem);
		filaEmailComprador.setStatus(StatusFilaEmail.PENDENTE);
		filaEmailRepository.saveAndFlush(filaEmailComprador);
		
		//Email para empresa (vendedor)
		FilaEmail filaEmailVendedor = new FilaEmail();
		filaEmailVendedor.setDestinatario(acao.getEmpresa().getEmail());
		filaEmailVendedor.setMensagem(mensagem);
		filaEmailVendedor.setStatus(StatusFilaEmail.PENDENTE);
		filaEmailRepository.saveAndFlush(filaEmailVendedor);
	}

}
