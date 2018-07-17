/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.springboot.domain;

import com.curso.springboot.domain.enums.EstadoPagamento;
import javax.persistence.Entity;

/**
 *
 * @author Robison
 */
@Entity
public class PagamentoCartao extends Pagamento{

    private static final long serialVersionUID = -8300414065980392557L;
    private Integer numeroParcelas;

    public PagamentoCartao() {
    }

    public PagamentoCartao(Integer id, Integer numeroParcelas, EstadoPagamento estadoPagamento, Pedido pedido) {
        super(id, estadoPagamento, pedido);
        this.numeroParcelas = numeroParcelas;
    }

    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }
    
    
}
