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
import com.curso.springboot.dto.ClienteDTO;
import com.curso.springboot.dto.ClienteNewDTO;
import com.curso.springboot.resources.exceptions.FieldMessageException;
import com.curso.springboot.services.validation.utils.BR;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;
// https://www.4devs.com.br/gerador_de_cnpj
// https://www.4devs.com.br/gerador_de_cpf
public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
    
    @Autowired
    private ClienteDAO clienteDAO;
    @Autowired
    private HttpServletRequest httpServletRequest; // Para obter o parametro da URI
    
    @Override
    public void initialize(ClienteUpdate ann) {
    }

    @Override
    public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
        
        Map<String, String> map = (Map<String, String>) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));
        
        List<FieldMessageException> list = new ArrayList<>();
        
        Cliente clienteAux = clienteDAO.findByEmail(objDto.getEmail());
        if(clienteAux != null && !clienteAux.getId().equals(uriId)){
            list.add(new FieldMessageException("email", "Email já existe"));
        }
        
        // inclua os testes aqui, inserindo erros na lista
        list.stream().forEach((e) -> {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        });
        return list.isEmpty();
    }
}
