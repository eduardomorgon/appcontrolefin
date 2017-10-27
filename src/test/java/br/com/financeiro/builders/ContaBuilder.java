/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financeiro.builders;

import br.com.financeiro.models.Conta;
import java.math.BigDecimal;
import java.util.Calendar;

/**
 *
 * @author eduardo
 */
public class ContaBuilder {
    
    private Conta conta;

    public ContaBuilder() {
        this.conta = new Conta();
    }
    
    public ContaBuilder withId(Integer id) {
        this.conta.setId(id);
        return this;
    }
    
    public ContaBuilder withNome(String nome) {
        this.conta.setNome(nome);
        return this;
    }
    
    public ContaBuilder withDescricao(String descricao) {
        this.conta.setDescricao(descricao);
        return this;
    }
    
    public ContaBuilder withDataVencimento(Calendar dataVencimento) {
        this.conta.setDataVencimento(dataVencimento);
        return this;
    }
    
    public ContaBuilder withValor(BigDecimal valor) {
        this.conta.setValor(valor);
        return this;
    }
    
    
    public ContaBuilder withDataPagamento() {
        this.conta.doPayment();
        return this;
    }
    
    public Conta create() {
        return this.conta;
    }
    
}
