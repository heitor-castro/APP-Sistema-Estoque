/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appsistemaestoque.model;

/**
 *
 * @author Henrique
 */
public class Fornecedor {
    private Integer ID;
    private String razaoSocial;
    private String CNPJ;
    private String telefone;
    
    public Fornecedor(){
        ID = 0;
        razaoSocial = "";
        CNPJ = "";
        telefone = "";
    }

    public Fornecedor(Integer ID, String razaoSocial, String CNPJ, String telefone) {
        this.ID = ID;
        this.razaoSocial = razaoSocial;
        this.CNPJ = CNPJ;
        this.telefone = telefone;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    
    
    
    
    

}
