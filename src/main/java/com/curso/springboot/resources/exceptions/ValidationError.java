/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.springboot.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Robison
 */
public class ValidationError extends StandardError{
    private static final long serialVersionUID = 1L;
    
    private List<FieldMessageException> listFeldMessageExceptions = new ArrayList<>();
    
    public ValidationError(Integer status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);
    }

    public List<FieldMessageException> getErrors() {
        return listFeldMessageExceptions;
    }

    public void addError(String fieldName, String message){
        listFeldMessageExceptions.add(new FieldMessageException(fieldName, message));
    }
    
    
}
