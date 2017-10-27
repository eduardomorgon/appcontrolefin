package br.com.financeiro.controllers;

import br.com.financeiro.models.Conta;
import br.com.financeiro.services.ContaService;
import java.math.BigDecimal;
import java.util.Calendar;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BindingResult;

public class ContasControllerTest {
    
    @InjectMocks
    private ContasController contasController;
    
    @Mock(name = "service")
    private ContaService contaService;
    @Mock
    private BindingResult bindingResult;
    
    private Conta conta;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        conta = new Conta();
        conta.setId(1);
        conta.setNome("Luz");
        conta.setDescricao("Conta de Luz");
        Calendar dataVencimento = Calendar.getInstance();
        dataVencimento.set(2017, Calendar.OCTOBER, 30);
        conta.setDataVencimento(dataVencimento);
        conta.setValor(new BigDecimal("102.78"));
        
    }

    @Test
    public void testSalvarContasSemErroDeValidacao() {

        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        String salvar = contasController.salvar(conta, bindingResult);
        Assert.assertEquals("redirect:/contas", salvar);
    }
    
    @Test
    public void testSalvarContasComErroDeValidacao() {

        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        String salvar = contasController.salvar(conta, bindingResult);
        Assert.assertEquals("/contas/form", salvar);
    }
    
    

}
