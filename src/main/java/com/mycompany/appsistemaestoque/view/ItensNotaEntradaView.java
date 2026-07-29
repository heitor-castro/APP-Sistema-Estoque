/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.mycompany.appsistemaestoque.view;

import com.mycompany.appsistemaestoque.dao.ItemNotaEntradaDAO;
import com.mycompany.appsistemaestoque.dao.NotaEntradaDAO;
import com.mycompany.appsistemaestoque.model.ItemNotaEntrada;
import com.mycompany.appsistemaestoque.model.NotaEntrada;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Henrique
 */
public class ItensNotaEntradaView extends javax.swing.JInternalFrame {

    /**
     * Creates new form EditarItensNotaEntrada
     */
    // Menu que aparece ao clicar com o botão direito numa linha da tabela
   
    // Menu que aparece ao clicar com o botão direito numa linha da tabela
    private final JPopupMenu menuContexto = new JPopupMenu();
 
    /**
     * Creates new form EditarItensNotaEntrada
     */
    public ItensNotaEntradaView() {
        initComponents();
        carregarComboNotas();
        configurarMenuContexto();
    }
 
    // Preenche o combo com todas as notas de entrada já cadastradas
    private void carregarComboNotas() {
        cbBuscaNota.removeAllItems();
        List<NotaEntrada> notas = new NotaEntradaDAO().listar();
        for (NotaEntrada n : notas) {
            cbBuscaNota.addItem(n);
        }
    }
 
    public void atualizarTabela() {
        NotaEntrada notaSelecionada = (NotaEntrada) cbBuscaNota.getSelectedItem();
        if (notaSelecionada == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma nota de entrada primeiro!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
 
        int notaId = notaSelecionada.getId();
 
        // Chama o DAO
        ItemNotaEntradaDAO dao = new ItemNotaEntradaDAO();
        List<ItemNotaEntrada> lista = dao.listarPorNota(notaId);
 
        // Monta a tabela
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0); // Limpa as linhas antigas
 
        for (ItemNotaEntrada item : lista) {
            modelo.addRow(new Object[]{
                item.getId(),
                item.getNotaEntradaId(),
                item.getProdutoId(),
                item.getQuantidade()
            });
        }
    }
 
    // Pega os dados da linha selecionada na tabela e monta um ItemNotaEntrada
    private ItemNotaEntrada pegarItemSelecionado() {
        int linha = jTable1.getSelectedRow();
 
        if (linha == -1) {
            JOptionPane.showMessageDialog(this,
                "Selecione um item na tabela primeiro!",
                "Nenhum item selecionado",
                JOptionPane.WARNING_MESSAGE);
            return null;
        }
 
        ItemNotaEntrada item = new ItemNotaEntrada();
        item.setId((Integer) jTable1.getValueAt(linha, 0));
        item.setNotaEntradaId((Integer) jTable1.getValueAt(linha, 1));
        item.setProdutoId((Integer) jTable1.getValueAt(linha, 2));
        item.setQuantidade((Integer) jTable1.getValueAt(linha, 3));
        return item;
    }
 
    // Monta o menu de contexto (botão direito) com as opções Alterar/Excluir
    private void configurarMenuContexto() {
        JMenuItem itemAlterar = new JMenuItem("Alterar");
        itemAlterar.addActionListener(evt -> alterarItemSelecionado());
 
        JMenuItem itemExcluir = new JMenuItem("Excluir");
        itemExcluir.addActionListener(evt -> excluirItemSelecionado());
 
        menuContexto.add(itemAlterar);
        menuContexto.add(itemExcluir);
 
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mostrarMenuSeForCliqueDireito(evt);
            }
 
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                mostrarMenuSeForCliqueDireito(evt);
            }
        });
    }
 
    // Verifica se o clique foi o botão direito (varia entre Windows/Linux/Mac)
    // e, se for, seleciona a linha clicada e mostra o menu ali
    private void mostrarMenuSeForCliqueDireito(java.awt.event.MouseEvent evt) {
        if (!evt.isPopupTrigger()) {
            return;
        }
 
        int linha = jTable1.rowAtPoint(evt.getPoint());
        if (linha < 0) {
            return; // clicou fora de qualquer linha, não mostra o menu
        }
 
        jTable1.setRowSelectionInterval(linha, linha);
        menuContexto.show(jTable1, evt.getX(), evt.getY());
    }
 
    // Abre a tela de edição já preenchida com os dados do item selecionado
    private void alterarItemSelecionado() {
        ItemNotaEntrada selecionado = pegarItemSelecionado();
        if (selecionado == null) {
            return;
        }
 
        EditarItensNotaEntrada telaEditar = new EditarItensNotaEntrada(selecionado, this);
        getDesktopPane().add(telaEditar);
        telaEditar.setVisible(true);
    }
 
    // Exclui o item selecionado, com confirmação antes
    private void excluirItemSelecionado() {
        ItemNotaEntrada selecionado = pegarItemSelecionado();
        if (selecionado == null) {
            return;
        }
 
        int confirmacao = JOptionPane.showConfirmDialog(this,
            "Tem certeza que deseja excluir este item da nota?",
            "Confirmar exclusão",
            JOptionPane.YES_NO_OPTION);
 
        if (confirmacao != JOptionPane.YES_OPTION) {
            return;
        }
 
        new ItemNotaEntradaDAO().excluir(selecionado);
        atualizarTabela();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        cbBuscaNota = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Itens Nota de Entrada");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "ID Nota Entrada", "ID Produto", "Quantidade"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("ID Da Nota: ");

        jButton1.setText("Buscar");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        cbBuscaNota.addActionListener(this::cbBuscaNotaActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbBuscaNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1)
                    .addComponent(cbBuscaNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbBuscaNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBuscaNotaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbBuscaNotaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        atualizarTabela();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<NotaEntrada> cbBuscaNota;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
