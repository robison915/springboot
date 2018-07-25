/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.springboot.resources;

import com.curso.springboot.domain.Categoria;
import com.curso.springboot.dto.CategoriaDTO;
import com.curso.springboot.services.CategoriaService;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author Robison
 */
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Categoria> findById(@PathVariable Integer id) {

        Categoria cat = categoriaService.findById(id);
        
        return ResponseEntity.ok().body(cat);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO categoriaDTO) {
        Categoria categoria = categoriaService.fromDTO(categoriaDTO);
        categoria = categoriaService.insert(categoria);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                 .path("/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO categoriaDTO, @PathVariable Integer id) {
        Categoria categoria = categoriaService.fromDTO(categoriaDTO);
        categoria.setId(id);
        categoria = categoriaService.update(categoria);
        return ResponseEntity.noContent().build();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoriaDTO>> findAll() {

        List<Categoria> listCategorias = categoriaService.findAll();
        List<CategoriaDTO> listCategoriaDTOs = listCategorias.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
        //List<CategoriaDTO> listCategoriaDTOs = listCategorias.map(obj -> new CategoriaDTO(obj));
        return ResponseEntity.ok().body(listCategoriaDTOs);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<CategoriaDTO>> findPage(@RequestParam(name = "page", defaultValue = "0") Integer page, 
                                                       @RequestParam(name = "linesperpage", defaultValue = "24") Integer linesPerPage, 
                                                       @RequestParam(name = "orderby", defaultValue = "nome") String orderBy, 
                                                       @RequestParam(name = "direction", defaultValue = "ASC") String direction){

        Page<Categoria> listCategorias = categoriaService.findPage(page,linesPerPage,orderBy,direction.toUpperCase());
        //Page<CategoriaDTO> listCategoriaDTOs = listCategorias.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
        Page<CategoriaDTO> listCategoriaDTOs = listCategorias.map(obj -> new CategoriaDTO(obj));
        
        return ResponseEntity.ok().body(listCategoriaDTOs);
    }
}
