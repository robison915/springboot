/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.springboot.services;

import com.curso.springboot.dao.CategoriaDAO;
import com.curso.springboot.domain.Categoria;
import com.curso.springboot.services.exception.DataIntegrityException;
import com.curso.springboot.services.exception.ObjectNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author Robison
 */
@Service
public class CategoriaService {

    @Autowired
    private CategoriaDAO categoriaDAO;

    public Categoria findById(Integer id) {
        Optional<Categoria> optional = categoriaDAO.findById(id);
        return optional.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! id: " + id + ", Tipo: " + Categoria.class.getName())
        );
    }
    
    public Categoria insert(Categoria categoria) {
        categoria.setId(null);
        return categoriaDAO.save(categoria);
    }
    
    public Categoria update(Categoria categoria) {
        this.findById(categoria.getId());
        return categoriaDAO.save(categoria);
    }
    
    public void deleteById(Integer id) throws DataIntegrityException{
        this.findById(id);
        try{
            categoriaDAO.deleteById(id);
        }catch(DataIntegrityViolationException ex){
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
        }
        
    }

    public List<Categoria> findAll() {
        return categoriaDAO.findAll();
    }
    
    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage,Sort.Direction.valueOf(direction), orderBy);
        return categoriaDAO.findAll(pageRequest);
    }
}
