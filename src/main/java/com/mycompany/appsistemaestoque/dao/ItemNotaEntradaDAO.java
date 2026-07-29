/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appsistemaestoque.dao;
import com.mycompany.appsistemaestoque.conexao.Conexao;
import com.mycompany.appsistemaestoque.model.ItemNotaEntrada;
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
    // Método para alterar a quantidade do item
    public void alterar(ItemNotaEntrada item) {
        // Atualiza a quantidade buscando pelo ID único do item
        String sql = "UPDATE item_nota_entrada SET quantidade = ? WHERE id = ?";
        
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, item.getQuantidade());
            stmt.setInt(2, item.getId()); 
            
            stmt.execute();
            stmt.close();
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar a quantidade do item: " + erro);
        }
    } 
    // Método para buscar os itens filtrando pelo ID da nota
    public java.util.List<ItemNotaEntrada> listarPorNota(int notaId) {
        java.util.List<ItemNotaEntrada> lista = new java.util.ArrayList<>();
        String sql = "SELECT * FROM item_nota_entrada WHERE nota_entrada_id = ?";
        
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, notaId);
            java.sql.ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                ItemNotaEntrada item = new ItemNotaEntrada();
                item.setId(rs.getInt("id"));
                item.setNotaEntradaId(rs.getInt("nota_entrada_id")); // Verifique se o nome do SET é esse no seu modelo
                item.setProdutoId(rs.getInt("produto_id"));          // Verifique se o nome do SET é esse no seu modelo
                item.setQuantidade(rs.getInt("quantidade"));
                
                lista.add(item);
            }
            stmt.close();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar itens: " + erro);
        }
        return lista;
    }
}
