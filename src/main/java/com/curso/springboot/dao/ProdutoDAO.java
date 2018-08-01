/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.springboot.dao;

import com.curso.springboot.domain.Categoria;
import com.curso.springboot.domain.Produto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



/**
 *
 * @author Robison
 */
@Repository
public interface ProdutoDAO extends JpaRepository<Produto, Integer> {
    
    /***************
    // Exemplo sem usar totalmente o spring
    @Query("select distinct p from Produto p inner join p.categorias c where p.nome like %:nome% and c in :categorias")
    Page<Produto> search(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);
    **************************/
    // O método abaixo faz o mesmo que o método acima, porém´são usados os padroes de nome do spring, onde são aplicados parametros de consulta conforme o nome do método
    @Transactional(readOnly = true)
    Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageRequest);
}
