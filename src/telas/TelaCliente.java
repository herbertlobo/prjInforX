package telas;

import dao.ClienteDAO;
import dao.ClienteMTable;
import dao.ModuloConecxao2;
import modelo.Cliente;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class TelaCliente extends JInternalFrame {

    private JTable table;
    private JTextField txtNome;
    private JTextField txtEndereco;
    private JTextField txtEmail;
    private JTextField txtPesquisa;
    private JFormattedTextField txtTelefone;
    private Cliente cli;
    private ClienteDAO dao;

    public TelaCliente() {
        initComponets();
    }

    private void initComponets() {

        ClassLoader loader = getClass().getClassLoader();

        // PANEL PESQUISA
        JPanel pnPesquisa = new JPanel(new MigLayout("center", "[][]40px[]", "[]"));
        pnPesquisa.setBorder(BorderFactory.createTitledBorder("Pesquisa"));
        txtPesquisa = new JTextField(30);
        txtPesquisa.addKeyListener(new ActionPesquisa());
        JLabel lblLogoPs = new JLabel(new ImageIcon(Objects.requireNonNull(loader.getResource("icones/pesquisar.png"))));
        pnPesquisa.add(txtPesquisa);
        pnPesquisa.add(lblLogoPs);
        pnPesquisa.add(new JLabel("* Campos obrigatórios"), "align right");

        // PAINEL TABELA
        table = new JTable();
        atualizarTabela();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cli = new Cliente();
                cli = ClienteMTable.getCliente(table.getSelectedRow());
                txtNome.setText(cli.getCliente());
                txtEndereco.setText(cli.getEndereco());
                txtTelefone.setText(cli.getTelefone());
                txtEmail.setText(cli.getEmail());
            }
        });
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Tabela Clientes"));

        // PAINEL DE ENTRADA
        txtNome = new JTextField();
        txtEndereco = new JTextField();
        txtEmail = new JTextField();
        try {
            MaskFormatter mask = new MaskFormatter("(##) #####-####");
            txtTelefone = new JFormattedTextField(mask);
            txtTelefone.setColumns(15);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        JPanel pnEntrada = new JPanel(new MigLayout(("insets 0 40 0 40")));
        pnEntrada.setBorder(BorderFactory.createTitledBorder("Entreda de Dados"));
        pnEntrada.add(new JLabel("* Nome:"));
        pnEntrada.add(txtNome, "growx, pushx, wrap");
        pnEntrada.add(new JLabel("Endereço:"));
        pnEntrada.add(txtEndereco, "grow, pushx, wrap");
        pnEntrada.add(new JLabel("* Telefone:"));
        pnEntrada.add(txtTelefone, "wrap");
        pnEntrada.add(new JLabel("E-mail:"));
        pnEntrada.add(txtEmail, "growx, pushx");

        // PAINEL DE CONTROLE
        JButton btnAdicionar = new JButton(new ImageIcon(requireNonNull(loader.getResource("icones/create.png"))));
        btnAdicionar.setToolTipText("Adicionar");
        btnAdicionar.addActionListener(new ActionSalvar());
        JButton btnAlterar = new JButton(new ImageIcon(requireNonNull(loader.getResource("icones/update.png"))));
        btnAlterar.setToolTipText("Alterar");
        btnAlterar.addActionListener(new ActionAlterar());
        JButton btnRemover = new JButton(new ImageIcon(requireNonNull(loader.getResource("icones/delete.png"))));
        btnRemover.setToolTipText("Remover");
        btnRemover.addActionListener(new ActionRmover());

        JPanel pnControle = new JPanel(new MigLayout("center", "[]20[]20[]", "[]"));
        pnControle.setBorder(BorderFactory.createTitledBorder("Controle"));
        pnControle.add(btnAdicionar);
        pnControle.add(btnAlterar);
        pnControle.add(btnRemover);

        // PAINEL PRINCIPAL
        JPanel pnMain = new JPanel(new MigLayout("insets 10"));
        pnMain.add(pnPesquisa, "growx,pushx, wrap");
        pnMain.add(scrollPane, "grow, push, wrap");
        pnMain.add(pnEntrada, "growx,pushx,wrap");
        pnMain.add(pnControle, "growx,pushx,align center");

        // CONFIGURAÇÃO DA JANELA
        setContentPane(pnMain);
        setSize(TelaPrincipal.desktopPane.getWidth(), TelaPrincipal.desktopPane.getHeight());
        setClosable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
    }

    private void atualizarTabela() {
        dao = new ClienteDAO();
        if (dao.lista() != null) {
            table.setModel(new ClienteMTable(dao.lista()));
        }
    }

    private class ActionSalvar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cli = new Cliente();
            dao = new ClienteDAO();

            if (cli.getIdcli() != null) {
                if (verificarCampos()) {
                    cli.setCliente(txtNome.getText());
                    cli.setEndereco(txtEndereco.getText());
                    cli.setTelefone(txtTelefone.getText());
                    cli.setEmail(txtEmail.getText());
                    if (!dao.salvar(cli)) {
                        JOptionPane.showMessageDialog(TelaCliente.this, "Salvo");
                        atualizarTabela();
                        limparCampos();
                    }
                } else{
                    JOptionPane.showMessageDialog(TelaCliente.this, "Preecha os campos obrigatórios...");
                }
            } else {
                limparCampos();
            }
        }
    }

    private void limparCampos() {
        cli.setIdcli(null);
        txtNome.setText(null);
        txtEndereco.setText(null);
        txtTelefone.setText(null);
        txtEmail.setText(null);
    }

    private boolean verificarCampos() {
        return !txtNome.getText().isEmpty() && !txtEndereco.getText().isEmpty() && !txtEmail.getText().isEmpty();
    }

    private class ActionAlterar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dao = new ClienteDAO();
            if (cli.getIdcli() != null) {
                if (verificarCampos()) {
                    cli.setCliente(txtNome.getText());
                    cli.setEndereco(txtEndereco.getText());
                    cli.setTelefone(txtTelefone.getText());
                    cli.setEmail(txtEmail.getText());
                    if (!dao.alterar(cli)) {
                        JOptionPane.showMessageDialog(TelaCliente.this, "registro alterado...");
                        atualizarTabela();
                        limparCampos();
                    }
                } else{
                    JOptionPane.showMessageDialog(TelaCliente.this, "Preecha os campos obrigatórios...");
                }
            } else {
                JOptionPane.showMessageDialog(TelaCliente.this, "Selecione um registro...");
            }
        }
    }

    private class ActionRmover implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dao = new ClienteDAO();
            if (cli.getIdcli() != null) {
                    if (!dao.excluir(cli.getIdcli())) {
                        JOptionPane.showMessageDialog(TelaCliente.this, "registro excluido...");
                        atualizarTabela();
                        limparCampos();
                    }
            } else {
                JOptionPane.showMessageDialog(TelaCliente.this, "Selecione um registro...");
            }
        }
    }

    private class ActionPesquisa extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            List<Cliente> valores = new ArrayList<>();
            String SQL = "SELECT idcli, nomecli, endcli, fonecli, emailcli FROM public.tbclientes WHERE namecli LIKE ? ORDER BY nomecli;";
            try{
                Connection conn = ModuloConecxao2.conector();
                PreparedStatement stmt = conn.prepareStatement(SQL);
                stmt.setString(1,"%'"+txtPesquisa+"'%");
                ResultSet rs = stmt.getResultSet();
                if(rs != null) {
                    while (rs.next()) {
                        cli = new Cliente();
                        cli.setIdcli(rs.getInt("idcli"));
                        cli.setCliente(rs.getString("nomecli"));
                        cli.setEndereco(rs.getString("endcli"));
                        cli.setTelefone(rs.getString("fonecli"));
                        cli.setEmail(rs.getString("emailcli"));
                        valores.add(cli);
                    }
                }
            } catch (SQLException ex){
                JOptionPane.showMessageDialog(TelaCliente.this,"Erro na pequisa..\n"+ex);
            }
        }
    }
}
