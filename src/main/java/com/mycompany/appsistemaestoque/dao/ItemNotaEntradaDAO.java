/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appsistemaestoque.dao;
import com.mycompany.appsistemaestoque.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author cadu0
 */
public class ItemNotaEntradaDAO {
     private Connection conexao;
 
    // Construtor: Assim que o DAO é chamado, ele abre a conexão
    public ItemNotaEntradaDAO() {
        conexao = Conexao.getConexao();
    }
 
    // Salva um item (produto + quantidade) vinculado a uma nota de entrada
    public void cadastrar(int notaEntradaId, int produtoId, int quantidade, double valorTotal) {
        String sql = "INSERT INTO item_nota_entrada (nota_entrada_id, produto_id, quantidade, valor_total) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, notaEntradaId);
            stmt.setInt(2, produtoId);
            stmt.setInt(3, quantidade);
            stmt.setDouble(4, valorTotal);
            stmt.execute();
 
            stmt.close();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar item da nota: " + erro);
        }
    }
    
}
