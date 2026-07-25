/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appsistemaestoque.dao;
import com.mycompany.appsistemaestoque.model.Fornecedor;
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
 * @author Henrique
 */
public class FornecedorDAO {
    // Variável que vai segurar a conexão com o banco
    private Connection conexao;
    // Construtor: Assim que o DAO é chamado, ele abre a conexão
    public FornecedorDAO() {
        try {
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_estoque", "root", "");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco: " + erro);
        }
    }
    // Método para salvar no banco
    public void cadastrar(Fornecedor obj) {

        // 1. O comando SQL para inserir dados (troque o nome da tabela se precisar)
        String sql = "INSERT INTO fornecedor (razao_social, cnpj, telefone) VALUES (?, ?, ?)";
        try {
            // 2. Prepara o comando SQL para ser executado
            PreparedStatement stmt = conexao.prepareStatement(sql);
            // 3. Troca os "?" do SQL pelos dados do objeto
            stmt.setString(1, obj.getRazaoSocial());
            stmt.setString(2, obj.getCNPJ());
            stmt.setString(3, obj.getTelefone());
            // 4. Executa o comando no banco de dados
            stmt.execute();

            // 5. Fecha o comando e avisa que deu certo
            stmt.close();
            JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso!");
        } catch (SQLException erro) {
            // Se der erro, mostra na tela
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + erro);
        }
    }
    // Método para alterar um fornecedor já cadastrado
    public void alterar(Fornecedor obj) {

        String sql = "UPDATE fornecedor SET razao_social = ?, cnpj = ?, telefone = ? WHERE id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, obj.getRazaoSocial());
            stmt.setString(2, obj.getCNPJ());
            stmt.setString(3, obj.getTelefone());
            stmt.setInt(4, obj.getID());
            stmt.execute();

            stmt.close();
            JOptionPane.showMessageDialog(null, "Fornecedor alterado com sucesso!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar: " + erro);
        }
    }
    // Método para excluir um fornecedor pelo ID
    public void excluir(Fornecedor obj) {

        String sql = "DELETE FROM fornecedor WHERE id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, obj.getID());
            stmt.execute();

            stmt.close();
            JOptionPane.showMessageDialog(null, "Fornecedor excluído com sucesso!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + erro);
        }
    }
    // Método para listar todos os fornecedores (ex: preencher uma tabela na tela)
    public List<Fornecedor> listar() {

        List<Fornecedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM fornecedor";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Fornecedor obj = new Fornecedor();
                obj.setID(rs.getInt("id"));
                obj.setRazaoSocial(rs.getString("razao_social"));
                obj.setCNPJ(rs.getString("cnpj"));
                obj.setTelefone(rs.getString("telefone"));
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