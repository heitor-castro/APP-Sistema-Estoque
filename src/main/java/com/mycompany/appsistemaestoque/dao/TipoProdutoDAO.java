/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appsistemaestoque.dao;
import com.mycompany.appsistemaestoque.model.TipoProduto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Henrique
 */

public class TipoProdutoDAO {

    // Variável que vai segurar a conexão com o banco
    private Connection conexao;

    // Construtor: Assim que o DAO é chamado, ele abre a conexão
    public TipoProdutoDAO() {
        // this.conexao = new ConnectionFactory().getConnection();
    }

    // Método para salvar no banco
    public void cadastrar(TipoProduto obj) {
        
        // 1. O comando SQL para inserir dados (troque o nome da tabela se precisar)
        String sql = "INSERT INTO tipo_produto (descricao) VALUES (?)";

        try {
            // 2. Prepara o comando SQL para ser executado
            PreparedStatement stmt = conexao.prepareStatement(sql);

            // 3. Troca o "?" do SQL pela descrição do objeto
            stmt.setString(1, obj.getDescricao());

            // 4. Executa o comando no banco de dados
            stmt.execute();
            
            // 5. Fecha o comando e avisa que deu certo
            stmt.close();
            JOptionPane.showMessageDialog(null, "Tipo de Produto cadastrado com sucesso!");

        } catch (SQLException erro) {
            // Se der erro, mostra na tela
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + erro);
        }
    }
}
    
