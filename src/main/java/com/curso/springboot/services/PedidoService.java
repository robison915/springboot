/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.springboot.services;

import com.curso.springboot.dao.PedidoDAO;
import com.curso.springboot.domain.Pedido;
import com.curso.springboot.services.exception.ObjectNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Robison
 */
@Service
public class PedidoService {

    @Autowired
    private PedidoDAO pedidoDAO;

    public Pedido findById(Integer id) {
        Optional<Pedido> optional = pedidoDAO.findById(id);
        return optional.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! id: " + id + ", Tipo: " + Pedido.class.getName())
        );
    }
}
