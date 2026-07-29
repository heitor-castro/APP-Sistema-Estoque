/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appsistemaestoque.model;

import java.time.LocalDate;

/**
 *
 * @author Caio
 */
public class NotaEntrada {
    //NOTA ENTRADA[id, dataEntrada, idFornecedor,valorTotal];
    
    //Atributos
    private Integer id;
    private LocalDate dataEntrada;
    private Integer idFornecedor;
    private Double valorTotal;
    
    //Construtores
    public NotaEntrada() {
        id = 0;
        dataEntrada = null;
        idFornecedor = 0;
        valorTotal = 0.0;
    }
    
    public NotaEntrada(Integer id, LocalDate dataEntrada, Integer idFornecedor, Double valorTotal) {
        this.id = id;
        this.dataEntrada = dataEntrada;
        this.idFornecedor = idFornecedor;
        this.valorTotal = valorTotal;
    }
    
    //Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(Integer idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}
