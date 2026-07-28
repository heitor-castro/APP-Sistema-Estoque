/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.mycompany.appsistemaestoque.view;
import com.mycompany.appsistemaestoque.dao.ProdutoDAO;
import com.mycompany.appsistemaestoque.dao.TipoProdutoDAO;
import com.mycompany.appsistemaestoque.model.Produto;
import com.mycompany.appsistemaestoque.model.TipoProduto;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Heitor
 */
public class ConsultaProdutosView extends javax.swing.JInternalFrame {

    /**
     * Creates new form ConsultaProdutos
     */
    public ConsultaProdutosView() {
        initComponents();//inicia o layout
        carregarTabela();//chamada da função da tabela
        configurarMenuContexto();//chamada da função do botão direito

    }
    //função para montar o cabeçalho e preencher a tabela com os dados do banco
    public void carregarTabela() {
        //define as colunas da tabela
        DefaultTableModel model = new DefaultTableModel();//classe vazia para tabelas
        //preenche o cabeçaçho e cria as colunas
        model.addColumn("ID");
        model.addColumn("Descrição");
        model.addColumn("Valor Unitário");
        model.addColumn("Tipo");
        model.addColumn("Quantidade");

        //busca os produtos no banco via DAO
        List<Produto> lista = new ProdutoDAO().listar();//
        List<TipoProduto> tipos = new TipoProdutoDAO().listarTipos();
        
        //adiciona cada produto como uma linha da tabela
        for (Produto p : lista) {
        String nomeTipo = buscarNomeTipo(p.getTipoId(), tipos);
        //preenche cada linha com seus respectivos atributos
        model.addRow(new Object[]{
            p.getId(),
            p.getDescricao(),
            p.getValorUnitario(), 
            nomeTipo,
            p.getQuantidade()
        });
        }

        jTable1.setModel(model);
}
    private String buscarNomeTipo(int tipoId, List<TipoProduto> tipos) {
        for (TipoProduto t : tipos) {
            if (t.getId() == tipoId) return t.getDescricao();
        }
        return "Desconhecido";
    }
    //metodo para abrir menu ao clicar com o botão direito do mouse
    private void configurarMenuContexto() {
        JPopupMenu popup = new JPopupMenu();
        JMenuItem itemAlterar = new JMenuItem("Alterar");
        JMenuItem itemExcluir = new JMenuItem("Excluir");
        popup.add(itemAlterar);
        popup.add(itemExcluir);
        
        //adiciona um listener de mouse na tabela pra detectar clique direito
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                //verifica se foi o botão direito (isPopupTrigger cobre variações entre SO)
                if (evt.isPopupTrigger() || javax.swing.SwingUtilities.isRightMouseButton(evt)) {
                    int linha = jTable1.rowAtPoint(evt.getPoint());//descobre em qual linha o clique caiu
                    if (linha >= 0) {
                        jTable1.setRowSelectionInterval(linha, linha); //seleciona a linha clicada
                        popup.show(jTable1, evt.getX(), evt.getY());//exibe o menu na posição do clique
                    }
                }
            }
        });
        //liga cada item do menu ao seu respectivo método
        itemExcluir.addActionListener(e -> excluirLinhaSelecionada());
        itemAlterar.addActionListener(e -> alterarLinhaSelecionada());
    }
    //método para excluir cadastro
    private void excluirLinhaSelecionada() {
        int linha = jTable1.getSelectedRow();
        if (linha < 0) return;//se nenhuma linha selecionada, ignora

        int id = (int) jTable1.getValueAt(linha, 0); //coluna 0 = ID

        int resposta = JOptionPane.showConfirmDialog(this,
            "Deseja realmente excluir o produto ID " + id + "?",
            "Confirmar exclusão", JOptionPane.YES_NO_OPTION);
    
        //pede confirmação antes de excluir
        if (resposta == JOptionPane.YES_OPTION) {
            Produto p = new Produto();
            p.setId(id);//só o ID é necessário pra excluir
            new ProdutoDAO().excluirProduto(p);
            carregarTabela(); //atualiza a lista após excluir
        }
    }
    //metodo para alterar cadastro
    private void alterarLinhaSelecionada() {
        int linha = jTable1.getSelectedRow();
        if (linha < 0) return;//se //nenhuma linha selecionada, ignora


        //monta um Produto com os dados da linha selecionada
        Produto p = new Produto();
        p.setId((int) jTable1.getValueAt(linha, 0));
        p.setDescricao((String) jTable1.getValueAt(linha, 1));
        p.setValorUnitario((Double) jTable1.getValueAt(linha, 2));
        p.setQuantidade((int) jTable1.getValueAt(linha, 4));

        // busca o tipoId real a partir do nome mostrado na tabela
        String nomeTipo = (String) jTable1.getValueAt(linha, 3);
        List<TipoProduto> tipos = new TipoProdutoDAO().listarTipos();//busca todos os tipos de novo
        for (TipoProduto t : tipos) {
            if (t.getDescricao().equals(nomeTipo)) {//se encontrou o tipo, pega o ID real
                p.setTipoId(t.getId());
                break;
            }
        }
        EditarProdutoView dialog = new EditarProdutoView(null, true, p, this);
        dialog.setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Consulta Produtos");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Descrição", "Valor Unitário", "Tipo", "Quantidade"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
