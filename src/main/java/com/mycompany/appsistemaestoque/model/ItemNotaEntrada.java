/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appsistemaestoque.model;

/**
 *
 * @author Henrique
 */
public class ItemNotaEntrada {
     private Integer id;
    private Integer notaEntradaId; // FK -> nota_entrada.id
    private Integer produtoId;     // FK -> produto.id
    private Integer quantidade;
    private Double valorTotal;
 
    public ItemNotaEntrada() {
        this.id = 0;
        this.notaEntradaId = 0;
        this.produtoId = 0;
        this.quantidade = 0;
        this.valorTotal = 0.0;
    }
 
    public ItemNotaEntrada(Integer id, Integer notaEntradaId, Integer produtoId, Integer quantidade, Double valorTotal) {
        this.id = id;
        this.notaEntradaId = notaEntradaId;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
    }
 
    public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }
 
    public Integer getNotaEntradaId() {
        return notaEntradaId;
    }
 
    public void setNotaEntradaId(Integer notaEntradaId) {
        this.notaEntradaId = notaEntradaId;
    }
 
    public Integer getProdutoId() {
        return produtoId;
    }
 
    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }
 
    public Integer getQuantidade() {
        return quantidade;
    }
 
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
 
    public Double getValorTotal() {
        return valorTotal;
    }
 
    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    
    
}
