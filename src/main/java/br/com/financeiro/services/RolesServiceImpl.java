/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financeiro.services;

import br.com.financeiro.models.Role;
import br.com.financeiro.repositories.RoleRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author eduardo
 */
@Service
@Transactional
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class RolesServiceImpl implements RolesService {

    private final RoleRepository roleRepository;

    @Autowired
    public RolesServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> all() {
        return roleRepository.findAll();
    }

    @Override
    public void delete(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role find(Integer id) {
        return roleRepository.findOne(id);
    }
    
    
}
