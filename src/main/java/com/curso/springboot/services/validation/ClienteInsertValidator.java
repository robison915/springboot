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
import com.curso.springboot.domain.enums.TipoCliente;
import com.curso.springboot.dto.ClienteNewDTO;
import com.curso.springboot.resources.exceptions.FieldMessageException;
import com.curso.springboot.services.validation.utils.BR;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

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
        
        // inclua os testes aqui, inserindo erros na lista
        for (FieldMessageException e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
