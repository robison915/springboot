/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.springboot.dao;

import com.curso.springboot.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



/**
 *
 * @author Robison
 */
@Repository
public interface ClienteDAO extends JpaRepository<Cliente, Integer> {
    
    @Transactional(readOnly = true)
    Cliente findByEmail(String email);
}
