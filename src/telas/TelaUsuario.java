package telas;

import dal.UsuarioDAO;
import modelo.Usuario;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import static java.util.Objects.requireNonNull;

public class TelaUsuario extends JInternalFrame {

    public static final JTextField txtId = new JTextField(5);
    public static final JTextField txtNome = new JTextField();
    public static final JTextField txtLogin = new JTextField();
    public static final JPasswordField txtSenha = new JPasswordField();
    public static JFormattedTextField txtFone;
    public static JComboBox<String> cmbPerfil;
    private Usuario user;

    public TelaUsuario() {
        super("Usuário");
        initComponents();
    }

    private void initComponents() {
        txtId.setEnabled(false);
        try {
            MaskFormatter mask = new MaskFormatter("(##) #####-####");
            txtFone = new JFormattedTextField(mask);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String[] opPerfil = {"Admin", "User", "Vendedor", "Tecníco"};
        cmbPerfil = new JComboBox<>(opPerfil);

        ClassLoader loader = getClass().getClassLoader();
        JButton btnAdicionar = new JButton(new ImageIcon(requireNonNull(loader.getResource("icones/create.png"))));
        btnAdicionar.setToolTipText("Adicionar");
        JButton btnConsultar = new JButton(new ImageIcon(requireNonNull(loader.getResource("icones/read.png"))));
        btnConsultar.setToolTipText("Consultar");
        JButton btnAlterar = new JButton(new ImageIcon(requireNonNull(loader.getResource("icones/update.png"))));
        btnAlterar.setToolTipText("Alterar");
        JButton btnRemover = new JButton(new ImageIcon(requireNonNull(loader.getResource("icones/delete.png"))));
        btnRemover.setToolTipText("Remover");

        // PAINEL DE ENTRADA
        JPanel pnEntrada = new JPanel(new MigLayout(""));
        pnEntrada.setBorder(BorderFactory.createTitledBorder("Entrada de Dados"));
        pnEntrada.add(new JLabel("Codígo:"), "split 5");
        pnEntrada.add(txtId, "wrap");
        pnEntrada.add(new JLabel("Usuário:"), "split 5");
        pnEntrada.add(txtNome, "span 4,growx,pushx,wrap");
        pnEntrada.add(new JLabel("Telefone:"), "split 5");
        pnEntrada.add(txtFone, "growx,pushx");
        pnEntrada.add(new JLabel("Perfil:"));
        pnEntrada.add(cmbPerfil, "growx,pushx,wrap");
        JPanel pnSenha = new JPanel(new MigLayout("", "[50%][50%]", "[]"));
        pnSenha.setBorder(BorderFactory.createTitledBorder("Senha do Usuário"));
        pnSenha.add(new JLabel("Login"), "split 2");
        pnSenha.add(txtLogin, "growx,pushx");
        pnSenha.add(new JLabel("Senha:"), "split 2");
        pnSenha.add(txtSenha, "growx,pushx");
        pnEntrada.add(pnSenha, "growx, pushx,span 4");

        // PAINEL DOS BOTOES
        JPanel pnBtn = new JPanel(new MigLayout("center", "[]15px[]15px[]15px[]", "[]"));
        pnBtn.add(btnAdicionar);
        pnBtn.add(btnConsultar);
        pnBtn.add(btnAlterar);
        pnBtn.add(btnRemover);


        // PAINEL PRINCIPAL
        JPanel pnMain = new JPanel(new MigLayout("", "[]", "[][]"));
        pnMain.add(pnEntrada, "growx, pushx ,wrap");
        pnMain.add(pnBtn, "growx, pushx");

        // ADDACTIONLISTENER
        btnAdicionar.addActionListener(new ActionSavar());
        btnConsultar.addActionListener(new ActionConsulta());
        btnAlterar.addActionListener(new ActionAlterar());
        btnRemover.addActionListener(new ActionRemover());

        // CONFIGURAÇÃO DA JABELA
        setContentPane(pnMain);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(false);
        setResizable(false);
        setSize(500, 300);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
    }

    class ActionSavar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            user = new Usuario();
            user.setUsuario(txtNome.getText());
            user.setFone(txtFone.getText());
            user.setLogin(txtLogin.getText());
            user.setSenha(new String(txtSenha.getPassword()));
            user.setPerfil(requireNonNull(cmbPerfil.getSelectedItem()).toString());

            UsuarioDAO dao = new UsuarioDAO();
            if (!dao.salvar(user)) {
                JOptionPane.showMessageDialog(TelaUsuario.this, "Registro Salvo");
                limparCampos();
            }
        }
    }

    private static class ActionConsulta implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            TlConsulta tlConsulta = new TlConsulta();
            TelaPrincipal.desktopPane.add(tlConsulta);
            tlConsulta.setVisible(true);
        }
    }

    private class ActionAlterar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            user = new Usuario();
            // VERIFICAR SE O CAMPO ID NÃO ESTA VAZIO
            if (!txtId.getText().trim().isEmpty()) {
                user.setIduser(Integer.parseInt(txtId.getText()));
                user.setUsuario(txtNome.getText());
                user.setFone(txtFone.getText());
                user.setLogin(txtLogin.getText());
                user.setSenha(new String(txtSenha.getPassword()));
                user.setPerfil(requireNonNull(cmbPerfil.getSelectedItem()).toString());

                UsuarioDAO dao = new UsuarioDAO();
                // CONDIÇÃO PRA NÃO SER POSIVEL ALTERAR O REGISTRO ADMINISTRADOR
                if(user.getIduser() != 1){
                    if (!dao.alterar(user)) {
                        JOptionPane.showMessageDialog(TelaUsuario.this, "Registro Alterado");
                        limparCampos();
                    }
                }else{
                    JOptionPane.showMessageDialog(TelaUsuario.this, "Usuario Administrador não pode ser Alterado");
                }
            } else {
                JOptionPane.showMessageDialog(TelaUsuario.this, "Informe o Id do Registro");
            }
        }
    }

    private class ActionRemover implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            UsuarioDAO dao = new UsuarioDAO();
            if (!txtId.getText().trim().isEmpty()) {
                if (!dao.excluir(Integer.parseInt(txtId.getText()))) {
                    JOptionPane.showMessageDialog(TelaUsuario.this, "Registro Excluido");
                    limparCampos();
                }
            } else {
                JOptionPane.showMessageDialog(TelaUsuario.this, "Informe o Id do Registro");
            }
        }
    }

    private void limparCampos() {
        txtId.setText(null);
        txtNome.setText(null);
        txtLogin.setText(null);
        txtSenha.setText(null);
        txtFone.setText(null);
        cmbPerfil.setSelectedIndex(0);
    }
}
