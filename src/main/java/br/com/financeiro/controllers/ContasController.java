package br.com.financeiro.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.financeiro.models.Conta;
import org.springframework.web.bind.annotation.RequestMethod;
import br.com.financeiro.services.ContasService;

/**
 *
 * @author eduardo
 */
@Controller
@RequestMapping("/contas")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class ContasController {

    private ContasService service;

    @Autowired
    public ContasController(ContasService service) {
        this.service = service;
    }

    @GetMapping("/novo")
    public ModelAndView form(Conta conta) {

        ModelAndView mv = new ModelAndView("contas/form");
        mv.addObject("conta", conta);
        return mv;
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public String salvar(@Valid Conta conta, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/contas/form";
        }

        service.save(conta);
        return "redirect:/contas/lancadas";
    }

    @GetMapping("/lancadas")
    public ModelAndView listar() {

        ModelAndView mv = new ModelAndView("contas/lista");
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView buscar(@PathVariable Integer id) {

        Conta contaParaEditar = service.find(id);
        ModelAndView mv = new ModelAndView("contas/form");
        mv.addObject("conta", contaParaEditar);
        return mv;
    }

    @GetMapping("/{id}/pagar")
    public ModelAndView pagar(@PathVariable Integer id) {

        Conta conta = service.pay(id);
        ModelAndView mv = new ModelAndView("contas/form");
        mv.addObject("conta", conta);
        return mv;
    }

    @DeleteMapping
    public String excluir(Conta conta) {

        service.delete(conta);
        return "redirect:/contas/lancadas";
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, headers = "Accept=application/json")
    @ResponseBody
    public Page<Conta> listar(Pageable pageable) {

        return service.findAllByPage(pageable);
    }

    @GetMapping("/pagas")
    public String pagas() {

        return "contas/pagas";
    }

    @GetMapping(value = "/finalizadas", produces = {MediaType.APPLICATION_JSON_VALUE}, headers = "Accept=application/json")
    @ResponseBody
    public Page<Conta> listaPagas(Pageable pageable) {

        return service.findAllByPayPage(pageable);
    }

}
