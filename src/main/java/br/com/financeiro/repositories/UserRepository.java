/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financeiro.repositories;

import br.com.financeiro.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author eduardo
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
    User findByUsername(String username);
    
}
