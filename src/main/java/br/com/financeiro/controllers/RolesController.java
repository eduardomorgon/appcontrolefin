/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financeiro.controllers;

import br.com.financeiro.models.Role;
import br.com.financeiro.services.RolesService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author eduardo
 */
@Controller
@RequestMapping("/roles")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class RolesController {
    
    private RolesService rolesService;
   
    @Autowired
    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }
    
    @GetMapping("/new")
    public ModelAndView form(Role role) {
        ModelAndView modelAndView = new ModelAndView("roles/form");
        modelAndView.addObject("role", role);
        return modelAndView;
    }
    
    @GetMapping("/all") 
    public ModelAndView all() {
        ModelAndView modelAndView = new ModelAndView("/roles/list");
        List<Role> roles = rolesService.all();
        modelAndView.addObject("roles", roles);
        return modelAndView;
    }
    
    @GetMapping("/{id}")
    public ModelAndView find(@PathVariable Integer id) {
        Role role = rolesService.find(id);
//        ModelAndView modelAndView = new ModelAndView("roles/form");
//        modelAndView.addObject("role", role);
        return form(role);
    }
    
    @DeleteMapping
    public String delete(@Valid Role role, BindingResult bindingResult) {
        
        if (bindingResult.hasErrors()) {
            return "/roles/form";
        }
        
        rolesService.delete(role);
        return "redirect:/roles/all";
    }
    
    @PostMapping
    public String save(@Valid Role role, BindingResult bindingResult) {
        
        if (bindingResult.hasErrors()) {
            return "/roles/form";
        }
        rolesService.save(role);
        return "redirect:/roles/all";
    }
    
}
