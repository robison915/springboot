/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.springboot.resources.exceptions;

import java.io.Serializable;

/**
 *
 * @author Robison
 */
public class FieldMessageException implements Serializable{
    private static final long serialVersionUID = -2048278309839116391L;
    
    private String fieldName;
    private String message;

    public FieldMessageException() {
    }

    public FieldMessageException(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
}
