package br.com.financeiro.repositories;

import java.util.Calendar;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.financeiro.models.Conta;

@Repository
public interface ContasRepository extends PagingAndSortingRepository<Conta, Integer> {
	
	Page<Conta> findByDataPagamentoIsNull(Pageable pageable);
	
	Page<Conta> findByDataPagamentoIsNotNull(Pageable pageable);
	
	Long countByDataVencimentoBetweenAndDataPagamentoIsNull(Calendar start, Calendar end);

}
