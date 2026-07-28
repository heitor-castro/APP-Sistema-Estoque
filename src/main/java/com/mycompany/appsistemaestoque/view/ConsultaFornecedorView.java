/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.mycompany.appsistemaestoque.view;

import com.mycompany.appsistemaestoque.dao.FornecedorDAO;
import com.mycompany.appsistemaestoque.model.Fornecedor;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author cadu0
 */
public class ConsultaFornecedorView extends javax.swing.JInternalFrame {

    private final JPopupMenu menuContexto = new JPopupMenu();
    /**
     * Creates new form ConsultaFornecedor
     */
    public ConsultaFornecedorView() {
        initComponents();
        carregarTabela();
        configurarMenuContexto();
    }
 
    //monta o cabeçalho e preenche a tabela com os dados do banco
    void carregarTabela() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Razão Social");
        model.addColumn("CNPJ");
        model.addColumn("Telefone");
 
        List<Fornecedor> lista = new FornecedorDAO().listar();
 
        for (Fornecedor f : lista) {
            model.addRow(new Object[]{
                f.getID(),
                f.getRazaoSocial(),
                f.getCNPJ(),
                f.getTelefone()
            });
        }
 
        jTable1.setModel(model);
    }
 
    // Pega os dados da linha selecionada na tabela e monta um Fornecedor
    private Fornecedor pegarFornecedorSelecionado() {
        int linha = jTable1.getSelectedRow();
 
        if (linha == -1) {
            JOptionPane.showMessageDialog(this,
                "Selecione um fornecedor na tabela primeiro!",
                "Nenhum fornecedor selecionado",
                JOptionPane.WARNING_MESSAGE);
            return null;
        }
 
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setID((Integer) jTable1.getValueAt(linha, 0));
        fornecedor.setRazaoSocial((String) jTable1.getValueAt(linha, 1));
        fornecedor.setCNPJ((String) jTable1.getValueAt(linha, 2));
        fornecedor.setTelefone((String) jTable1.getValueAt(linha, 3));
        return fornecedor;
    }
    
    private void configurarMenuContexto() {
        JMenuItem itemAlterar = new JMenuItem("Alterar");
        itemAlterar.addActionListener(evt -> alterarFornecedorSelecionado());
 
        JMenuItem itemExcluir = new JMenuItem("Excluir");
        itemExcluir.addActionListener(evt -> excluirFornecedorSelecionado());
 
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
 
    // Abre a tela de edição (EditarFornecedor) já preenchida com os dados
    // da linha selecionada. Como é um JDialog modal, quando ele fecha a
    // tabela já foi atualizada por ele mesmo (chama telaConsulta.carregarTabela())
    private void alterarFornecedorSelecionado() {
        Fornecedor selecionado = pegarFornecedorSelecionado();
        if (selecionado == null) {
            return;
        }
 
        java.awt.Frame janelaPrincipal = (java.awt.Frame) javax.swing.SwingUtilities.getWindowAncestor(this);
        EditarFornecedor dialogo = new EditarFornecedor(janelaPrincipal, true, selecionado, this);
        dialogo.setVisible(true);
    }
    
    private void excluirFornecedorSelecionado() {
        Fornecedor selecionado = pegarFornecedorSelecionado();
        if (selecionado == null) {
            return;
        }
 
        FornecedorDAO dao = new FornecedorDAO();
        if (dao.temNotaVinculada(selecionado.getID())) {
            JOptionPane.showMessageDialog(this,
                "Não é possível excluir! Este fornecedor possui Notas de Entrada registradas.",
                "Bloqueio de Exclusão",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
 
        int confirmacao = JOptionPane.showConfirmDialog(this,
            "Tem certeza que deseja excluir \"" + selecionado.getRazaoSocial() + "\"?",
            "Confirmar exclusão",
            JOptionPane.YES_NO_OPTION);
 
        if (confirmacao != JOptionPane.YES_OPTION) {
            return;
        }
 
        dao.excluir(selecionado);
        carregarTabela();
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

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Consulta de Fornecedores");
        setToolTipText("");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Razão Social", "CNPJ", "Telefone"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
