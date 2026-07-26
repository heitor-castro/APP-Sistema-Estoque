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
    public void excluir(TipoProduto obj) {
        String sql = "DELETE FROM tipo_prod WHERE id = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            
            stmt.execute();
            stmt.close();
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro no banco ao excluir: " + erro);
        }
    }

    // 4. MÉTODO DE ALTERAR
    public void alterar(TipoProduto obj) {
        String sql = "UPDATE tipo_prod SET descricao = ? WHERE id = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setInt(2, obj.getId());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Tipo de Produto alterado com sucesso!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro no banco ao alterar: " + erro);
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
}