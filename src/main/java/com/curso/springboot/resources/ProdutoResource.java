/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.springboot.resources;

import com.curso.springboot.domain.Produto;
import com.curso.springboot.dto.ProdutoDTO;
import com.curso.springboot.resources.utils.URL;
import com.curso.springboot.services.ProdutoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Robison
 */
@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Produto> findById(@PathVariable Integer id) {

        Produto cat = produtoService.findById(id);
        
        return ResponseEntity.ok().body(cat);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProdutoDTO>> findPage(@RequestParam(name = "nome", defaultValue = "") String nome,
                                                     @RequestParam(name = "categorias", defaultValue = "") String categorias,
                                                     @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                     @RequestParam(name = "linesperpage", defaultValue = "24") Integer linesPerPage, 
                                                     @RequestParam(name = "orderby", defaultValue = "nome") String orderBy, 
                                                     @RequestParam(name = "direction", defaultValue = "ASC") String direction){
        List<Integer> ids = URL.decodeIntList(categorias);
        String nomeDecoded = URL.decodeParam(nome);
        Page<Produto> listProdutos = produtoService.search(nomeDecoded, ids, page,linesPerPage,orderBy,direction.toUpperCase());
        //Page<ProdutoDTO> listProdutoDTOs = listProdutos.stream().map(obj -> new ProdutoDTO(obj)).collect(Collectors.toList());
        Page<ProdutoDTO> listProdutoDTOs = listProdutos.map(obj -> new ProdutoDTO(obj));
        
        return ResponseEntity.ok().body(listProdutoDTOs);
    }
}
