/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appsistemaestoque.model;

import java.time.LocalDate;

/**
 *
 * @author Heitor
 */
public class Produto {

    //PRODUTO[id, descrição, valorUnitario, TipoID, dataEntrada];
    //ATRIBUTOS
    private Integer id;
    private String descricao;
    private Double valorUnitario;
    private Integer tipoId;
    private LocalDate dataEntrada;
    
    //CONSTRUTORES
    public Produto(){
        id = 0;
        descricao = "";
        valorUnitario = 0.0;
        tipoId = 0;
        dataEntrada = null;//
    }
    public Produto(Integer id, String descricao, Double valorUnitario, Integer tipoId, LocalDate dataEntrada) {
        this.id = id;
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
        this.tipoId = tipoId;
        this.dataEntrada = dataEntrada;
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
    public LocalDate getDataEntrada() {
        return dataEntrada;
    }
    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }
    
    
}