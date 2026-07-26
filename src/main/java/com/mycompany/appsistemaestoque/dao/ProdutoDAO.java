/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appsistemaestoque.dao;
import com.mycompany.appsistemaestoque.conexao.Conexao;
import com.mycompany.appsistemaestoque.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;



/**
 *
 * @author Heitor
 */
public class ProdutoDAO {
    //Variável que vai segurar a conexão com o banco
    private Connection conexao;
    
    //CONSTRUTORES
    //Assim que o DAO é chamado, ele abre a conexão
    public ProdutoDAO(){
        conexao = Conexao.getConexao();
    }
    //Método para salvar no banco
    public void cadastrarProduto(Produto obj){
        //1. Comando SQL para inserir dados
        String sql = "INSERT INTO produto (id,descricao,valor_unit,quantidade,tipo_id) VALUES (?, ?, ?, ?, ?)";
        
        try{
            //2. Prepara o comando SQL para ser executado
            PreparedStatement stmt = conexao.prepareStatement(sql);
            // 3. Troca os "?" do SQL pelos dados do objeto
            stmt.setInt(1, obj.getId());
            stmt.setString(2, obj.getnome());
            stmt.setDouble(3, obj.getValorUnitario());
            stmt.setInt(4, obj.getQuantidade());
            stmt.setInt(5, obj.getTipoId());
            // 4. Executa o comando no banco de dados
            stmt.execute();
            //5. Fecha o comando e comunica sucesso
            stmt.close();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        }catch (SQLException erro) {
            // Se der erro, mostra na tela
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + erro);
        }
    }
    // Método para alterar um Produto já cadastrado
    public void alterarProduto(Produto obj){
        
        //1. Comando SQL para sobrescrever dados
        String sql = "UPDATE produto SET id = ?, descricao = ?, valor_unit = ?, quantidade = ?, tipo_id = ? WHERE id = ?";
        try{
            //2. Prepara o comando SQL para ser executado
            PreparedStatement stmt = conexao.prepareStatement(sql);
            // 3. Troca os "?" do SQL pelos dados do objeto
            stmt.setInt(1, obj.getId());
            stmt.setString(2, obj.getnome());
            stmt.setDouble(3, obj.getValorUnitario());
            stmt.setInt(4, obj.getQuantidade());
            stmt.setInt(5, obj.getTipoId());
            stmt.setInt(6, obj.getId());
            // 4. Executa o comando no banco de dados
            stmt.execute();
            //5. Fecha o comando e comunica sucesso
            stmt.close();
            JOptionPane.showMessageDialog(null, "Informações alteradas com sucesso!");
        }catch (SQLException erro) {
            // Se der erro, mostra na tela
            JOptionPane.showMessageDialog(null, "Erro ao alterar informações: " + erro);
        }
        }
    // Método para excluir um produto pelo ID
    public void excluirProduto(Produto obj) {
        //1. Comando SQL para excluir dados
        String sql = "DELETE FROM produto WHERE id = ?";
        try {
            //2. Prepara o comando SQL para ser executado
            PreparedStatement stmt = conexao.prepareStatement(sql);
            //3. Define o ID do produto a ser excluído
            stmt.setInt(1, obj.getId());
            //4. Executa o comando no banco de dados
            stmt.execute();
            //5. Fecha o comando e comunica sucesso
            stmt.close();
            JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + erro);
        }
    }
    // Método para listar todos os Produtos (ex: preencher uma tabela na tela)
    public List<Produto> listar() {
        //1. Lista que vai guardar os produtos retornados
        List<Produto> lista = new ArrayList<>();
        //2. Comando SQL para buscar todos os produtos
        String sql = "SELECT * FROM Produto";
        try {
            //3. Prepara e executa a consulta
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            //4. Percorre cada linha do resultado e monta um objeto Produto
            while (rs.next()) {
                Produto obj = new Produto();
                obj.setId(rs.getInt("id"));
                obj.setnome(rs.getString("descricao"));
                obj.setValorUnitario(rs.getDouble("valor_unit"));
                obj.setQuantidade(rs.getInt("quantidade"));
                obj.setTipoId(rs.getInt("tipo_id"));
                lista.add(obj);
            }
            //5. Fecha recursos e retorna a lista
            rs.close();
            stmt.close();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar: " + erro);
        }
        return lista;
    }
}


