package com.mycompany.appsistemaestoque.dao;

import com.mycompany.appsistemaestoque.conexao.Conexao;
import com.mycompany.appsistemaestoque.model.ItemNotaEntrada;
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
 * @author cadu0
 */
public class ItemNotaEntradaDAO {

    // Variável que vai segurar a conexão com o banco
    private Connection conexao;

    // Construtor: Assim que o DAO é chamado, ele abre a conexão
    public ItemNotaEntradaDAO() {
        conexao = Conexao.getConexao();
    }

    // Salva um item (produto + quantidade) vinculado a uma nota de entrada
    public void cadastrar(int notaEntradaId, int produtoId, int quantidade, double valorTotal) {
        String sql = "INSERT INTO itens_nota_entrada (nota_entrada_id, produto_id, quantidade, valor_total) VALUES (?, ?, ?, ?)";
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

    // Lista todos os itens que pertencem a uma nota específica
    public List<ItemNotaEntrada> listarPorNota(int notaEntradaId) {
        List<ItemNotaEntrada> lista = new ArrayList<>();
        String sql = "SELECT * FROM itens_nota_entrada WHERE nota_entrada_id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, notaEntradaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ItemNotaEntrada item = new ItemNotaEntrada();
                item.setId(rs.getInt("id"));
                item.setNotaEntradaId(rs.getInt("nota_entrada_id"));
                item.setProdutoId(rs.getInt("produto_id"));
                item.setQuantidade(rs.getInt("quantidade"));
                item.setValorTotal(rs.getDouble("valor_total"));
                lista.add(item);
            }
            rs.close();
            stmt.close();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar itens da nota: " + erro);
        }
        return lista;
    }

    // Altera a quantidade de um item já existente. Recalcula o valor total
    // desse item (valor unitário do produto x nova quantidade) e depois
    // atualiza o valor total da nota inteira, já que ele é a soma dos itens.
    public void alterar(ItemNotaEntrada item) {
        // 1. Busca o valor unitário do produto pra recalcular o total do item
        Produto produto = buscarProdutoPorId(item.getProdutoId());
        if (produto == null) {
            JOptionPane.showMessageDialog(null, "Produto do item não encontrado!");
            return;
        }
        double novoValorTotal = produto.getValorUnitario() * item.getQuantidade();

        String sql = "UPDATE itens_nota_entrada SET quantidade = ?, valor_total = ? WHERE id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, item.getQuantidade());
            stmt.setDouble(2, novoValorTotal);
            stmt.setInt(3, item.getId());
            stmt.execute();
            stmt.close();

            // mantém o valor total da nota sincronizado com a soma dos itens
            recalcularValorTotalNota(item.getNotaEntradaId());
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar item: " + erro);
        }
    }

    // Exclui um item da nota e recalcula o valor total da nota
    public void excluir(ItemNotaEntrada item) {
        String sql = "DELETE FROM itens_nota_entrada WHERE id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, item.getId());
            stmt.execute();
            stmt.close();

            recalcularValorTotalNota(item.getNotaEntradaId());
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir item: " + erro);
        }
    }

    // Auxiliar: busca um produto pelo ID (pra pegar o valor unitário)
    private Produto buscarProdutoPorId(int produtoId) {
        for (Produto p : new ProdutoDAO().listar()) {
            if (p.getId() == produtoId) {
                return p;
            }
        }
        return null;
    }

    // Soma o valor_total de todos os itens da nota e atualiza
    // nota_entrada.valor_total_nota, pra manter os dois sincronizados
    // sempre que um item for alterado ou excluído
    private void recalcularValorTotalNota(int notaEntradaId) {
        String sqlSoma = "SELECT SUM(valor_total) AS total FROM itens_nota_entrada WHERE nota_entrada_id = ?";
        try {
            PreparedStatement stmtSoma = conexao.prepareStatement(sqlSoma);
            stmtSoma.setInt(1, notaEntradaId);
            ResultSet rs = stmtSoma.executeQuery();

            double novoTotal = 0.0;
            if (rs.next()) {
                novoTotal = rs.getDouble("total");
            }
            rs.close();
            stmtSoma.close();

            String sqlUpdate = "UPDATE nota_entrada SET valor_total_nota = ? WHERE id = ?";
            PreparedStatement stmtUpdate = conexao.prepareStatement(sqlUpdate);
            stmtUpdate.setDouble(1, novoTotal);
            stmtUpdate.setInt(2, notaEntradaId);
            stmtUpdate.execute();
            stmtUpdate.close();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao recalcular total da nota: " + erro);
        }
    }
}