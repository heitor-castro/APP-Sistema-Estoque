/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appsistemaestoque.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Henrique
 */
//CLASSE QUE FAZ A CONEXÃO COM O BANCO DE DADOS
public class Conexao {
    
    // Método que cria a conexão com o banco
    public static Connection getConexao() {
        String url = "jdbc:mysql://localhost:3306/sistema_estoque";
        String usuario = "root";
        String senha = "mysql"; 
        
        try {
            return DriverManager.getConnection(url, usuario, senha);//retorna a conexão com sucesso
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro na Conexão: " + erro.getMessage());
            return null;
        }
    }
    
}