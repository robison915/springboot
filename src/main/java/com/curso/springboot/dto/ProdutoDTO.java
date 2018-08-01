/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.springboot.dto;

import com.curso.springboot.domain.Produto;
import java.io.Serializable;

/**
 *
 * @author Robison
 */
public class ProdutoDTO implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String nome;
    private Double preco;

    public ProdutoDTO() {
    }
    
    public ProdutoDTO(Produto produto){
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
    
    
}
