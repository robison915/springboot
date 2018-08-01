/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.springboot.services;

import com.curso.springboot.dao.CategoriaDAO;
import com.curso.springboot.dao.ProdutoDAO;
import com.curso.springboot.domain.Categoria;
import com.curso.springboot.domain.Produto;
import com.curso.springboot.services.exception.ObjectNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author Robison
 */
@Service
public class ProdutoService {

    @Autowired
    private ProdutoDAO produtoDAO;
    @Autowired
    private CategoriaDAO categoriaDAO;

    public Produto findById(Integer id) {
        Optional<Produto> optional = produtoDAO.findById(id);
        return optional.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! id: " + id + ", Tipo: " + Produto.class.getName())
        );
    }
    
    public Page<Produto> search(String nome, List<Integer> ids,Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage,Sort.Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaDAO.findAllById(ids);
        //return produtoDAO.search(nome, categorias, pageRequest);
        return produtoDAO.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
        
    }
}
