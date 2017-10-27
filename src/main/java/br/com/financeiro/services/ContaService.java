/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financeiro.services;

import br.com.financeiro.models.Conta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author eduardo
 */
public interface ContaService {

    Long countNextSevenDays();

    void delete(Conta conta);

    Conta find(Integer id);

    Page<Conta> findAllByPage(Pageable pageable);

    Page<Conta> findAllByPayPage(Pageable pageable);

    Conta pay(Integer id);

    void save(Conta conta);
    
}
