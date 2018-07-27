/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.springboot.services.validation;

/**
 *
 * @author Robison
 */
import com.curso.springboot.dao.ClienteDAO;
import com.curso.springboot.domain.Cliente;
import com.curso.springboot.domain.enums.TipoCliente;
import com.curso.springboot.dto.ClienteNewDTO;
import com.curso.springboot.resources.exceptions.FieldMessageException;
import com.curso.springboot.services.validation.utils.BR;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
// https://www.4devs.com.br/gerador_de_cnpj
// https://www.4devs.com.br/gerador_de_cpf
public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
    
    @Autowired
    private ClienteDAO clienteDAO;
    
    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessageException> list = new ArrayList<>();
        
        if(objDto.getTipoCliente().equals(TipoCliente.PESSOA_FISICA.getCodigo()) && !BR.isValidCPF(objDto.getCpfCnpj())){
            list.add(new FieldMessageException("cpfCnpj", "CPF inválido"));
        }
        
        if(objDto.getTipoCliente().equals(TipoCliente.PESSOA_JURIDICA.getCodigo()) && !BR.isValidCNPJ(objDto.getCpfCnpj())){
            list.add(new FieldMessageException("cpfCnpj", "CNPJ inválido"));
        }
        
        Cliente clienteAux = clienteDAO.findByEmail(objDto.getEmail());
        if(clienteAux != null){
            list.add(new FieldMessageException("email", "Email já existe"));
        }
        
        // inclua os testes aqui, inserindo erros na lista
        for (FieldMessageException e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
