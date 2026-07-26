/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appsistemaestoque.dao;

import com.mycompany.appsistemaestoque.model.NotaEntrada;
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
 * @author Caio
 */

public class NotaEntradaDAO {
    //Variável que vai segurar a conexão com o banco
    private Connection conexao;
    
    //Construtor: Assim que o DAO é chamado, ele abre a conexão
    public NotaEntradaDAO() {
        try {
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_estoque", "root", "");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco: " + erro);
        }
    }
    
    //Método para salvar no banco
    public void cadastrarNotaEntrada(NotaEntrada obj) {
        
        //1. Comando SQL para inserir dados
        //<<<<<<ATENCAO>>>>>> O ID é omitido pois é AUTO_INCREMENT no banco
        String sql = "INSERT INTO nota_entrada (data_ent, valor_total_nota, fornecedor_id) VALUES (?, ?, ?)";
        try {
            //2. Prepara o comando SQL para ser executado
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            //3. Troca os "?" do SQL pelos dados do objeto
            //stmt.setInt(1, obj.getId()); OBS: Linha omitida, não é necessário Setar o ID.
            stmt.setObject(1, obj.getDataEntrada());
            stmt.setDouble(2, obj.getValorTotal());
            stmt.setInt(3, obj.getIdFornecedor());
            
            //4. Executa o comando no banco de dados
            stmt.execute();
            
            //5. Fecha o comando e confirma cadastro
            stmt.close();
            JOptionPane.showMessageDialog(null, "Nota de Entrada cadastrada com sucesso!");
        } catch (SQLException erro) {
            // Se der erro, mostra na tela
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + erro);
        }
    }
    
    //Método para alterar uma Nota de Entrada já cadastrada
    public void alterarNotaEntrada(NotaEntrada obj) {
        
        //1. Comando SQL para sobrescrever dados
        //<<<<<<ATENCAO>>>>>> O ID não deve ser modificado no 'SET' mas mantido no 'WHERE'.
        String sql = "UPDATE nota_entrada SET data_ent = ?, valor_total_nota = ?, fornecedor_id = ? WHERE id = ?";
        try {
            //2. Prepara o comando SQL para ser executado
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            //3. Troca os "?" do SQL pelos dados do objeto
            //stmt.setInt(1, obj.getId()); OBS: Linha omitida, não é necessário alterar o ID.
            stmt.setObject(1, obj.getDataEntrada());
            stmt.setDouble(2, obj.getValorTotal());
            stmt.setInt(3, obj.getIdFornecedor());
            stmt.setInt(4, obj.getId());
            
            //4. Executa o comando no banco de dados
            stmt.execute();
            
            //5. Fecha o comando e confirma alterações
            stmt.close();
            JOptionPane.showMessageDialog(null, "Informações alteradas com sucesso!");
        } catch (SQLException erro) {
            // Se der erro, mostra na tela
            JOptionPane.showMessageDialog(null, "Erro ao alterar informações: " + erro);
        }
    }
    
    //Método para excluir uma Nota de Entrada pelo ID
    public void excluirNotaEntrada(NotaEntrada obj) {
        //1. Comando SQL para excluir dados
        String sql = "DELETE FROM nota_entrada WHERE id = ?";
        try {
            //2. Prepara o comando SQL para ser executado
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            //3. Define o ID da Nota de Entrada a ser excluída
            stmt.setInt(1, obj.getId());
            
            //4. Executa o comando no banco de dados
            stmt.execute();
            
            //5. Fecha o comando e confirma remoção
            stmt.close();
            JOptionPane.showMessageDialog(null, "Nota de Entrada excluída com sucesso!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + erro);
        }
    }
    
    //Método para listar todas as Notas de Entrada (ex: preencher uma tabela na tela)
    public List<NotaEntrada> listar() {
        //1. Lista que vai guardar as Notas de Entrada retornadas
        List<NotaEntrada> lista = new ArrayList<>();
        
        //2. Comando SQL para buscar todas as Notas
        String sql = "SELECT * FROM nota_entrada";
        try {
            //3. Prepara e executa a consulta
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            //4. Percorre cada linha do resultado e monta um objeto NotaEntrada
            while (rs.next()) {
                NotaEntrada obj = new NotaEntrada();
                obj.setId(rs.getInt("id"));
                
                //Conversão de SQL Date para LocalDate
                obj.setDataEntrada(rs.getDate("data_ent").toLocalDate());
                
                obj.setValorTotal(rs.getDouble("valor_total_nota"));
                obj.setIdFornecedor(rs.getInt("fornecedor_id"));
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
