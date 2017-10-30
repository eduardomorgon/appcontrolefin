package br.com.financeiro.controllers;

import br.com.financeiro.builders.ContaBuilder;
import br.com.financeiro.models.Conta;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import br.com.financeiro.services.ContasService;

public class ContasControllerTest {
    
    @InjectMocks
    private ContasController contasController;
    
    @Mock(name = "service")
    private ContasService contaService;
    @Mock
    private BindingResult bindingResult;
    
    private Conta conta1, conta2;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        conta1 = new ContaBuilder()
                .withId(1)
                .withNome("Faculdade")
                .withDescricao("Conta Faculdade")
                .withDataVencimento(new GregorianCalendar(2017, Calendar.NOVEMBER, 10))
                .withValor(new BigDecimal("897.89"))
                .create();
        
        conta2 = new ContaBuilder()
                .withId(2)
                .withNome("Luz")
                .withDescricao("Conta de Luz")
                .withDataVencimento(new GregorianCalendar(2017, Calendar.OCTOBER, 30))
                .withValor(new BigDecimal("245.23"))
                .create();
        
        
    }

    @Test
    public void testSalvarContasSemErroDeValidacao() {

        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        String salvar = contasController.salvar(conta1, bindingResult);
        Assert.assertEquals("redirect:/contas/lancadas", salvar);
    }
    
    @Test
    public void testSalvarContasComErroDeValidacao() {

        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        String salvar = contasController.salvar(conta1, bindingResult);
        Assert.assertEquals("/contas/form", salvar);
    }
    
    @Test
    public void testBuscarContasPeloId() {
        Mockito.when(contaService.find(conta1.getId())).thenReturn(conta1);
        ModelAndView modelAndView = contasController.buscar(conta1.getId());
        
        Conta buscarConta = (Conta) modelAndView.getModelMap().get("conta");
        
        Assert.assertNotNull(buscarConta);
        Assert.assertEquals("contas/form", modelAndView.getViewName());
        Assert.assertEquals(conta1.getId(), buscarConta.getId());
        
    }
    
    @Test
    public void testExcluir() {
        
        String excluir = contasController.excluir(conta1);
        Assert.assertEquals("redirect:/contas/lancadas", excluir);
        
    }
    
    @Test
    public void testPagarContas() {
        
        Mockito.when(contaService.pay(conta1.getId())).thenReturn(conta1);
        conta1.doPayment();
        ModelAndView modelAndView = contasController.pagar(conta1.getId());
        Conta contaPaga = (Conta) modelAndView.getModelMap().get("conta");
        
        Assert.assertNotNull(contaPaga);
        Assert.assertNotNull(contaPaga.getDataPagamento());
        Assert.assertEquals("contas/form", modelAndView.getViewName());
        Assert.assertEquals(conta1.getDataPagamento().get(Calendar.DAY_OF_MONTH), contaPaga.getDataPagamento().get(Calendar.DAY_OF_MONTH));
        
    }
    
    @Test
    public void testListar() {
        
        List<Conta> contas = Arrays.asList(conta1, conta2);
        PageRequest pageable = new PageRequest(0, 6, Sort.Direction.ASC, "dataVencimento");
        
        Page<Conta> page = new PageImpl<>( contas, pageable, contas.size());
        
        Mockito.when(contasController.listar(pageable)).thenReturn(page);
        
        Page<Conta> pageLista = contasController.listar(pageable);
        
        Assert.assertEquals(2, pageLista.getContent().size());
        Assert.assertEquals(1, pageLista.getTotalPages());
        
    }
    
    
    @Test
    public void testListaPagas() {
        
        conta1.doPayment();
        conta2.doPayment();
        
        List<Conta> contas = Arrays.asList(conta1, conta2);
        PageRequest pageable = new PageRequest(0, 6, Sort.Direction.ASC, "dataVencimento");
        
        Page<Conta> page = new PageImpl<>(contas, pageable, contas.size());
        
        Mockito.when(contasController.listar(pageable)).thenReturn(page);
        
        Page<Conta> pageLista = contasController.listar(pageable);
        
        Assert.assertEquals(2, pageLista.getContent().size());
        Assert.assertEquals(1, pageLista.getTotalPages());
        Assert.assertNotNull(page.getContent().get(0).getDataPagamento());
        Assert.assertNotNull(page.getContent().get(1).getDataPagamento());
    }
    
    

}
