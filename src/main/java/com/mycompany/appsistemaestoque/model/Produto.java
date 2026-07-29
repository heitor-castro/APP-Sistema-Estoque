/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appsistemaestoque.model;


/**
 *
 * @author Heitor
 */
public class Produto {
    //ATRIBUTOS
    private Integer id, tipoId, quantidade;
    private String descricao;
    private Double valorUnitario;
    
    //CONSTRUTORES
    public Produto(){
        id = 0;
        descricao = "";
        valorUnitario = 0.0;
        tipoId = 0;
        quantidade = 0;
    }
    public Produto(Integer id, String descricao, Double valorUnitario, Integer tipoId, Integer quantidade) {
        this.id = id;
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
        this.tipoId = tipoId;
        this.quantidade = quantidade;
    }
    //GETTERS AND SETTERS
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Double getValorUnitario() {
        return valorUnitario;
    }
    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
    public Integer getTipoId() {
        return tipoId;
    }
    public void setTipoId(Integer tipoId) {
        this.tipoId = tipoId;
    }
    public Integer getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    
    @Override
    public String toString() {
        return this.id + " - " + this.descricao;
    }
}