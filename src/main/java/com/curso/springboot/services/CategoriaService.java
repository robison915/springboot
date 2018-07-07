/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.springboot.services;

import com.curso.springboot.DAO.CategoriaDAO;
import com.curso.springboot.domain.Categoria;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Robison
 */
@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaDAO categoriaDAO;
    
    public Categoria buscar(Integer id){
        Optional<Categoria> optional = categoriaDAO.findById(id);
        return (Categoria) optional.orElse(null); 
    }
}
