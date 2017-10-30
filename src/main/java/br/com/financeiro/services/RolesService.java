/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financeiro.services;

import br.com.financeiro.models.Role;
import java.util.List;

/**
 *
 * @author eduardo
 */
public interface RolesService {

    public List<Role> all();

    public void delete(Role role);

    public void save(Role role);

    public Role find(Integer id);
    
    
    
}
