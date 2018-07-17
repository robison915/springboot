/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.springboot.domain.enums;

/**
 *
 * @author Robison
 */
public enum EstadoPagamento {
    PENDENTE(1,"Pendente"),
    QUITADO(2,"Quitado"),
    CANCELADO(3,"Cancelado");
    
    private int codigo;
    private String descricao;

    private EstadoPagamento(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
      
    public static EstadoPagamento toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        
        for(EstadoPagamento x : EstadoPagamento.values()){
            if(cod.equals(x.getCodigo())){
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido, codigo: " + cod);
    }
}
