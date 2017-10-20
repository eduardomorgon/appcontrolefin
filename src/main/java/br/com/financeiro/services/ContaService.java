package br.com.financeiro.services;

import java.util.Calendar;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import br.com.financeiro.models.Conta;
import br.com.financeiro.repositories.ContasRepository;

@Service
@Transactional
@Scope(value=WebApplicationContext.SCOPE_REQUEST)	
public class ContaService {
	
	private ContasRepository repository;

	@Autowired
	public ContaService(ContasRepository repository) {
		this.repository = repository;
	}
	
	public void save(Conta conta) {
		
		repository.save(conta);
	}
	
	public void delete(Conta conta) {
		
		repository.delete(conta.getId());
	}
	
	public Conta find(Integer id) {
		
		return repository.findOne(id);
	}
	
    public Page<Conta> findAllByPage(Pageable pageable) {
    	
    	return repository.findByDataPagamentoIsNull(pageable);
    }
    
    public Page<Conta> findAllByPayPage(Pageable pageable) {
    	
    	return repository.findByDataPagamentoIsNotNull(pageable);
    }
    
    public Conta pay(Integer id) {
    	
    	Conta conta = repository.findOne(id);
    	conta.doPayment();
    	repository.save(conta);
    	return conta;
    }
    
    public Long countNextSevenDays() {
    	
    	Calendar today = Calendar.getInstance();
    	Calendar nextWeek = Calendar.getInstance();
    	nextWeek.add(Calendar.DATE, 7);
    	return repository.countByDataVencimentoBetweenAndDataPagamentoIsNull(today, nextWeek);
    }
	

}
