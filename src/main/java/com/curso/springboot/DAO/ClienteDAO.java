/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.springboot.DAO;

import com.curso.springboot.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



/**
 *
 * @author Robison
 */
@Repository
public interface ClienteDAO extends JpaRepository<Cliente, Integer> {

}