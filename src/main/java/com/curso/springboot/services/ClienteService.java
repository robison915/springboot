/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.springboot.services;

import com.curso.springboot.dao.ClienteDAO;
import com.curso.springboot.domain.Cliente;
import com.curso.springboot.domain.Cliente;
import com.curso.springboot.domain.enums.TipoCliente;
import com.curso.springboot.dto.ClienteDTO;
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
public class ClienteService {

    @Autowired
    private ClienteDAO clienteDAO;

    public Cliente findById(Integer id) {
        Optional<Cliente> optional = clienteDAO.findById(id);
        return optional.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! id: " + id + ", Tipo: " + Cliente.class.getName())
        );
    }
    
    
    public Cliente insert(Cliente cliente) {
        cliente.setId(null);
        return clienteDAO.save(cliente);
    }
    
    public Cliente update(Cliente cliente) {
        Cliente newCliente = this.findById(cliente.getId());
        this.updateData(newCliente, cliente);
        return clienteDAO.save(newCliente);
    }
    
    public void deleteById(Integer id) throws DataIntegrityException{
        this.findById(id);
        try{
            clienteDAO.deleteById(id);
        }catch(DataIntegrityViolationException ex){
            throw new DataIntegrityException("Não é possível excluir um cliente porque há entidades relacionadas");
        }
        
    }

    public List<Cliente> findAll() {
        return clienteDAO.findAll();
    }
    
    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage,Sort.Direction.valueOf(direction), orderBy);
        return clienteDAO.findAll(pageRequest);
    }
    
    public Cliente fromDTO(ClienteDTO clienteDTO){
        return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
    }
    
    private void updateData(Cliente newCliente, Cliente cliente){
        newCliente.setNome(cliente.getNome());
        newCliente.setEmail(cliente.getEmail());
    }
}
