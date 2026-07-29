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
//CLASSE QUE FAZ A INTERAÇÃO COM O BANCO DE DADOS
public class ProdutoDAO {
    //variável que vai segurar a conexão com o banco
    private Connection conexao;
    
    //CONSTRUTORES
    //assim que o DAO é chamado, ele abre a conexão
    public ProdutoDAO(){
        conexao = Conexao.getConexao();
    }
    //método para salvar no banco
    public void cadastrarProduto(Produto obj){//objeto da classe Produto
        //comando SQL para inserir dados
        String sql = "INSERT INTO produto (descricao,valor_unit,quantidade,tipo_id) VALUES (?, ?, ?, ?)";
        
        try{
            //prepara o comando SQL para ser executado
            PreparedStatement stmt = conexao.prepareStatement(sql);
            //troca os "?" do SQL pelos dados do objeto
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getValorUnitario());
            stmt.setInt(3, obj.getQuantidade());
            stmt.setInt(4, obj.getTipoId());
            //executa o comando no banco de dados
            stmt.execute();
            //fecha o comando e comunica sucesso
            stmt.close();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        }catch (SQLException erro) {
            // se der erro, mostra na tela
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + erro);
        }
    }
    //método para alterar um Produto já cadastrado
    public void alterarProduto(Produto obj){
        
        //comando SQL para sobrescrever dados
        String sql = "UPDATE produto SET descricao = ?, valor_unit = ?, quantidade = ?, tipo_id = ? WHERE id = ?";
        try{
            //prepara o comando SQL para ser executado
            PreparedStatement stmt = conexao.prepareStatement(sql);
            //adiciona os dados do objeto nos parâmetros do SQL 
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getValorUnitario());
            stmt.setInt(3, obj.getQuantidade());
            stmt.setInt(4, obj.getTipoId());
            stmt.setInt(5, obj.getId());
            //executa o comando no banco de dados
            stmt.execute();
            //fecha o comando e comunica sucesso
            stmt.close();
            JOptionPane.showMessageDialog(null, "Informações alteradas com sucesso!");
        }catch (SQLException erro) {
            //se der erro, mostra na tela
            JOptionPane.showMessageDialog(null, "Erro ao alterar informações: " + erro);
        }
        }
    // método para excluir um produto pelo ID
    public void excluirProduto(Produto obj) {
        //comando SQL para excluir dados
        String sql = "DELETE FROM produto WHERE id = ?";
        try {
            //prepara o comando SQL para ser executado
            PreparedStatement stmt = conexao.prepareStatement(sql);
            //define o ID do produto a ser excluído
            stmt.setInt(1, obj.getId());
            //executa o comando no banco de dados
            stmt.execute();
            //fecha o comando e comunica sucesso
            stmt.close();
            JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");
        } catch (SQLException erro) {
            // Verifica se o erro é o 1451 (produto travado na nota de entrada)
            if (erro.getErrorCode() == 1451) {
                JOptionPane.showMessageDialog(null, 
                    "Não é possível excluir: Este produto já está sendo usado em uma Nota de Entrada.", 
                    "Aviso de Segurança", 
                    JOptionPane.WARNING_MESSAGE);
            } else {
                // Se for outro erro, exibe a mensagem padrão
                JOptionPane.showMessageDialog(null, "Erro ao excluir: " + erro);
            }
        }
    }
    //método para listar todos os Produtos (ex: preencher uma tabela na tela)
    public List<Produto> listar() {
        //lista que vai guardar os produtos retornados
        List<Produto> lista = new ArrayList<>();
        //comando SQL para buscar todos os produtos
        String sql = "SELECT * FROM Produto";
        try {
            //prepara e executa a consulta
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            //percorre cada linha do resultado e monta um objeto Produto
            while (rs.next()) {
                Produto obj = new Produto();
                obj.setId(rs.getInt("id"));
                obj.setDescricao(rs.getString("descricao"));
                obj.setValorUnitario(rs.getDouble("valor_unit"));
                obj.setQuantidade(rs.getInt("quantidade"));
                obj.setTipoId(rs.getInt("tipo_id"));
                lista.add(obj);
            }
            //fecha recursos e retorna a lista
            rs.close();
            stmt.close();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar: " + erro);
        }
        return lista;//retorna a lista
    }
    //método para listar produtos filtrando por tipo (usado no combo de filtro da tela de consulta)
    public List<Produto> listarPorTipo(int tipoId) {
        //lista que vai guardar os produtos retornados
        List<Produto> lista = new ArrayList<>();
        //comando SQL para buscar só os produtos do tipo informado
        String sql = "SELECT * FROM Produto WHERE tipo_id = ?";
        try {
            //prepara a consulta e define o parâmetro
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, tipoId);
            ResultSet rs = stmt.executeQuery();
            //percorre cada linha do resultado e monta um objeto Produto
            while (rs.next()) {
                Produto obj = new Produto();
                obj.setId(rs.getInt("id"));
                obj.setDescricao(rs.getString("descricao"));
                obj.setValorUnitario(rs.getDouble("valor_unit"));
                obj.setQuantidade(rs.getInt("quantidade"));
                obj.setTipoId(rs.getInt("tipo_id"));
                lista.add(obj);
            }
            //fecha recursos e retorna a lista
            rs.close();
            stmt.close();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar por tipo: " + erro);
        }
        return lista;
    }
    
}


