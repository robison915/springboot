/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.springboot.services;

import com.curso.springboot.DAO.ClienteDAO;
import com.curso.springboot.domain.Cliente;
import com.curso.springboot.services.exception.ObjectNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Robison
 */
@Service
public class ClienteService {

    @Autowired
    private ClienteDAO clienteDAO;

    public Cliente findById(Integer id) {
        Optional<Cliente> optional = clienteDAO.findById(id);
        return optional.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! id: " + id + ", Tipo: " + Cliente.class.getName())
        );
    }
}
