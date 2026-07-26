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
    private String nome;//DESCRIÇÃO
    private Double valorUnitario;
    
    //CONSTRUTORES
    public Produto(){
        id = 0;
        nome = "";
        valorUnitario = 0.0;
        tipoId = 0;
        quantidade = 0;
    }
    public Produto(Integer id, String nome, Double valorUnitario, Integer tipoId, Integer quantidade) {
        this.id = id;
        this.nome = nome;
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
    public String getnome() {
        return nome;
    }
    public void setnome(String nome) {
        this.nome = nome;
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
    
}