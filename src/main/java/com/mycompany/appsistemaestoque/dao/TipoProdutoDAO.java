package com.mycompany.appsistemaestoque.dao;

import com.mycompany.appsistemaestoque.model.TipoProduto;
import com.mycompany.appsistemaestoque.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet; // Import novo para ler os dados do banco
import java.sql.SQLException;
import java.util.ArrayList;  // Import novo para criar a lista
import java.util.List;       // Import novo para usar a lista
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
}