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
 
    public void cadastrar(int notaEntradaId, int produtoId, int quantidade) {
        // Tabela alterada para 'itens_nota_entrada' (com 's')
        String sql = "INSERT INTO itens_nota_entrada (nota_entrada_id, produto_id, quantidade) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, notaEntradaId);
            stmt.setInt(2, produtoId);
            stmt.setInt(3, quantidade);
            stmt.execute();
 
            stmt.close();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar item da nota: " + erro);
        }
    }

    // Método para alterar a quantidade do item
    public void alterar(ItemNotaEntrada item) {
        String sql = "UPDATE itens_nota_entrada SET quantidade = ? WHERE id = ?";
        
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
        // Tabela alterada para 'itens_nota_entrada' (com 's')
        String sql = "SELECT * FROM itens_nota_entrada WHERE nota_entrada_id = ?";
        
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, notaId);
            java.sql.ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                ItemNotaEntrada item = new ItemNotaEntrada();
                item.setId(rs.getInt("id"));
                item.setNotaEntradaId(rs.getInt("nota_entrada_id")); 
                item.setProdutoId(rs.getInt("produto_id"));          
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