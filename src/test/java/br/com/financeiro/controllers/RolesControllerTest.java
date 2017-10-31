/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financeiro.controllers;

import br.com.financeiro.models.Role;
import br.com.financeiro.services.RolesService;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author eduardo
 */
public class RolesControllerTest {
    
    @InjectMocks
    private RolesController rolesController;
    
    @Mock(name = "rolesService")
    private RolesService rolesService;
    @Mock
    private BindingResult bindingResult;
    
    private Role role1, role2;
    
    public RolesControllerTest() {
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        role1 = new Role(1, "ADMIN");
        role2 = new Role(2, "USERS");
    }
    
    @Test
    public void testSaveRoleWithoutErrorValidation() {
        
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        String save = rolesController.save(role1, bindingResult);
        
        Assert.assertEquals("redirect:/roles/all", save);
    }
    
    @Test
    public void testSaveRoleWithErrorValidation() {
        
        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        String save = rolesController.save(role1, bindingResult);
        
        Assert.assertEquals("/roles/form", save);
    }
    
    @Test
    public void testDeleteRoleWithoutErrorValidation() {
        
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        String save = rolesController.delete(role1, bindingResult);
        
        Assert.assertEquals("redirect:/roles/all", save);
    }
    
    @Test
    public void testDeleteRoleWithErrorValidation() {
        
        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        String save = rolesController.delete(role1, bindingResult);
        
        Assert.assertEquals("/roles/form", save);
    }
    
    @Test
    public void testFindOneRole() {
        
        Mockito.when(rolesService.find(1)).thenReturn(role1);
        ModelAndView modelAndView = rolesController.find(1);
        Role findRole = (Role) modelAndView.getModel().get("role");
        
        Assert.assertEquals("roles/form", modelAndView.getViewName());
        Assert.assertEquals(role1.getId(), findRole.getId());
        Assert.assertEquals(role1.getName(), findRole.getName());
        
    }
    
    @Test
    public void testFindAllRoles() {
        
        Mockito.when(rolesService.all()).thenReturn(Arrays.asList(role1, role2));
        ModelAndView modelAndView = rolesController.all();
        List roles = (List) modelAndView.getModel().get("roles");
        
        Assert.assertEquals("roles/list", modelAndView.getViewName());
        Assert.assertEquals(2, roles.size());
        
    }
    
}
