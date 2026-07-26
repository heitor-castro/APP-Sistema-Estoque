/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appsistemaestoque.model;

public class TipoProduto {
    
    private int id;
    private String descricao;

    // Construtor vazio
    public TipoProduto() {
    }

    // Construtor com parâmetros
    public TipoProduto(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    //End
    
    // Esse método ajuda a mostrar apenas o nome do tipo de produto nas telas (ex: ComboBox)
    @Override
    public String toString() {
        return this.descricao;
    }
}