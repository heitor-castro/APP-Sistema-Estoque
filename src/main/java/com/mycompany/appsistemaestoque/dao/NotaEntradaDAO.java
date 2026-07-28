/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appsistemaestoque.dao;

import com.mycompany.appsistemaestoque.model.NotaEntrada;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Caio
 */
public class NotaEntradaDAO {
    // Variável que vai segurar a conexão com o banco
    private Connection conexao;
    
    // Construtor: Assim que o DAO é chamado, ele abre a conexão
    public NotaEntradaDAO() {
        try {
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_estoque", "root", "mysql");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco: " + erro);
        }
    }
    
    // Método para salvar no banco
    public void cadastrarNotaEntrada(NotaEntrada obj) {
        // 1. Comando SQL SEM O ID (Auto_Increment resolve)
        String sql = "INSERT INTO nota_entrada (data_ent, valor_total_nota, fornecedor_id) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            // 2. Troca os "?" pelos dados do objeto (note que os números mudaram de 1 a 3)
            stmt.setObject(1, obj.getDataEntrada());
            stmt.setDouble(2, obj.getValorTotal());
            stmt.setInt(3, obj.getIdFornecedor());
            
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Nota de Entrada cadastrada com sucesso!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + erro);
        }
    }
    
    // Método para alterar uma Nota de Entrada já cadastrada
    public void alterarNotaEntrada(NotaEntrada obj) {
        // 1. Removido o "id = ?" do SET
        String sql = "UPDATE nota_entrada SET data_ent = ?, valor_total_nota = ?, fornecedor_id = ? WHERE id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setObject(1, obj.getDataEntrada());
            stmt.setDouble(2, obj.getValorTotal());
            stmt.setInt(3, obj.getIdFornecedor());
            stmt.setInt(4, obj.getId()); // O ID entra apenas na condição do WHERE
            
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Informações alteradas com sucesso!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar informações: " + erro);
        }
    }
    
    // Método para excluir e Listar permanecem iguais aos seus originais
    public void excluirNotaEntrada(NotaEntrada obj) {
        String sql = "DELETE FROM nota_entrada WHERE id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Nota de Entrada excluída com sucesso!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + erro);
        }
    }
    
    public List<NotaEntrada> listar() {
        List<NotaEntrada> lista = new ArrayList<>();
        String sql = "SELECT * FROM nota_entrada";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                NotaEntrada obj = new NotaEntrada();
                obj.setId(rs.getInt("id"));
                obj.setDataEntrada(rs.getDate("data_ent").toLocalDate());
                obj.setValorTotal(rs.getDouble("valor_total_nota"));
                obj.setIdFornecedor(rs.getInt("fornecedor_id"));
                lista.add(obj);
            }
            rs.close();
            stmt.close();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar: " + erro);
        }
        return lista;
    }
}