/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.springboot.resources;

import com.curso.springboot.domain.Cliente;
import com.curso.springboot.domain.Cliente;
import com.curso.springboot.dto.ClienteDTO;
import com.curso.springboot.services.ClienteService;
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

/**
 *
 * @author Robison
 */
@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> findById(@PathVariable Integer id) {

        Cliente cat = clienteService.findById(id);
        
        return ResponseEntity.ok().body(cat);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Integer id) {
        Cliente cliente = clienteService.fromDTO(clienteDTO);
        cliente.setId(id);
        cliente = clienteService.update(cliente);
        return ResponseEntity.noContent().build();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> findAll() {

        List<Cliente> listClientes = clienteService.findAll();
        List<ClienteDTO> listClienteDTOs = listClientes.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        //List<ClienteDTO> listClienteDTOs = listClientes.map(obj -> new ClienteDTO(obj));
        return ResponseEntity.ok().body(listClienteDTOs);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(name = "page", defaultValue = "0") Integer page, 
                                                       @RequestParam(name = "linesperpage", defaultValue = "24") Integer linesPerPage, 
                                                       @RequestParam(name = "orderby", defaultValue = "nome") String orderBy, 
                                                       @RequestParam(name = "direction", defaultValue = "ASC") String direction){

        Page<Cliente> listClientes = clienteService.findPage(page,linesPerPage,orderBy,direction.toUpperCase());
        //Page<ClienteDTO> listClienteDTOs = listClientes.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        Page<ClienteDTO> listClienteDTOs = listClientes.map(obj -> new ClienteDTO(obj));
        
        return ResponseEntity.ok().body(listClienteDTOs);
    }
}
