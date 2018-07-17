/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.springboot.domain;

import com.curso.springboot.domain.enums.EstadoPagamento;
import java.util.Date;
import javax.persistence.Entity;

/**
 *
 * @author Robison
 */
@Entity
public class PagamentoBoleto extends Pagamento{

    private static final long serialVersionUID = 2691442321646831105L;
    private Date dataPagamento;
    private Date dataVencimento;

    public PagamentoBoleto() {
    }

    public PagamentoBoleto(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Date dataPagamento, Date dataVencimento) {
        super(id, estadoPagamento, pedido);
        this.dataPagamento = dataPagamento;
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
    
    
}
