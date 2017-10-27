package br.com.financeiro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.WebApplicationContext;

import br.com.financeiro.services.ContaServiceImpl;

@Controller
@Scope(value=WebApplicationContext.SCOPE_REQUEST)	
public class HomeController {

    private ContaServiceImpl contaService;

    @Autowired
    public HomeController(ContaServiceImpl contaService) {
            this.contaService = contaService;
    }

    @GetMapping("/")
    public String index(Model model) {

        Long countNextSevenDays = contaService.countNextSevenDays();
        model.addAttribute("countNextSevenDays", countNextSevenDays);
        return "home/index";
   }
	
}
