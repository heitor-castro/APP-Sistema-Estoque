package com.mycompany.appsistemaestoque.dao;

import com.mycompany.appsistemaestoque.model.TipoProduto;
import com.mycompany.appsistemaestoque.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.util.ArrayList;  
import java.util.List;       
import javax.swing.JOptionPane;

public class TipoProdutoDAO {

    private Connection conexao;

    // Construtor
    public TipoProdutoDAO() {
        this.conexao = Conexao.getConexao(); 
    }

    // 1. MÉTODO DE CADASTRAR
    public void cadastrar(TipoProduto obj) {
        String sql = "INSERT INTO tipo_prod (descricao) VALUES (?)";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Tipo de Produto cadastrado com sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + erro);
        }
    }

    // 2. MÉTODO DE CONSULTAR
    public List<TipoProduto> listarTipos() {
        List<TipoProduto> lista = new ArrayList<>();
        String sql = "SELECT * FROM tipo_prod";
        
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                TipoProduto obj = new TipoProduto();
                
                obj.setId(rs.getInt("id"));
                obj.setDescricao(rs.getString("descricao"));
                
                lista.add(obj);
            }
            return lista;
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar: " + erro);
            return null;
        }
    }
    // 3. MÉTODO DE EXCLUIR
    public boolean excluir(int idTipo) {
        try {
            // 1. Verifica se existem produtos vinculados
            String sqlVerifica = "SELECT COUNT(*) FROM produto WHERE tipo_id = ?";
            PreparedStatement stmtVerifica = conexao.prepareStatement(sqlVerifica);
            stmtVerifica.setInt(1, idTipo);
            ResultSet rs = stmtVerifica.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                javax.swing.JOptionPane.showMessageDialog(null, 
                    "Não é possível excluir este tipo, pois existem produtos cadastrados nele!", 
                    "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
                return false; 
            }

            // 2. Se não houver produtos, executa o DELETE
            String sqlDelete = "DELETE FROM tipo_prod WHERE id = ?";
            PreparedStatement stmtDelete = conexao.prepareStatement(sqlDelete);
            stmtDelete.setInt(1, idTipo);
            stmtDelete.execute();
            stmtDelete.close();

            return true; 

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Erro ao excluir o tipo de produto: " + e.getMessage());
            return false;
        }
    }
    
    // 5. MÉTODO DE BUSCAR POR ID (Necessário para preencher a tela ao alterar)
    public TipoProduto buscarPorId(int id) {
        TipoProduto obj = new TipoProduto();
        String sql = "SELECT * FROM tipo_prod WHERE id = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setDescricao(rs.getString("descricao"));
            }
            stmt.close();
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar por ID: " + erro);
        }
        
        return obj;
    }
    public void alterar(TipoProduto tp) {
        String sql = "UPDATE tipo_prod SET descricao = ? WHERE id = ?";
        
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, tp.getDescricao());
            stmt.setInt(2, tp.getId());
            
            stmt.execute();
            stmt.close();
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar: " + erro);
        }
    }
}