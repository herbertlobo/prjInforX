package telas;

import dao.ModuloConecxao;
import dao.ModuloConecxao2;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class TelaLogin extends JFrame {

    private final JTextField txtUsuario;
    private final JPasswordField txtSenha;
    private final JButton btnLogin;
    private JLabel lblDB;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public TelaLogin() {
        super("X System - Login");
        btnLogin = new JButton("Login");
        txtUsuario = new JTextField(10);
        txtUsuario.requestFocus();
        txtUsuario.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    txtSenha.requestFocus();
                }
            }
        });
        txtSenha = new JPasswordField(10);
        txtSenha.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    btnLogin.doClick();
                }
            }
        });

        ClassLoader loader = getClass().getClassLoader();

        try{
            conn = ModuloConecxao2.conector();
            if (conn != null) {
                lblDB = new JLabel(new ImageIcon(Objects.requireNonNull(loader.getResource("icones/dbok.png"))));
            } else {
                lblDB = new JLabel(new ImageIcon(Objects.requireNonNull(loader.getResource("icones/dberror.png"))));
            }
        } catch (Exception ex){
            System.out.println("Erro de Conecxão: \n"+ex.getMessage());
        }

        btnLogin.addActionListener(e -> logar());

        // PAINEL DE ENTRADA
        JPanel pnEntrada = new JPanel(new MigLayout("", "40[][]30", "20[]10[]"));
        pnEntrada.add(new JLabel("Usuário"));
        pnEntrada.add(txtUsuario, "growx,pushx, wrap");
        pnEntrada.add(new JLabel("Senha"));
        pnEntrada.add(txtSenha, "growx,pushx");

        JPanel pnDB = new JPanel(new MigLayout("", "[]", "[]"));
        pnDB.add(lblDB);

        // PAINEL DOS BOTÕES
        JPanel pnBtn = new JPanel(new MigLayout("", "[]", "[]"));
        pnBtn.add(btnLogin);

        // PAINEL PRINCPAL
        JPanel pnMain = new JPanel(new MigLayout("", "[][]", "[][]"));
        pnMain.add(pnEntrada, "span 2,grow, push, wrap");
        pnMain.add(pnDB, "align left");
        pnMain.add(pnBtn, "alignx right, aligny top");

        // CONFIGURAÇÃO DA JANELA
        setContentPane(pnMain);
        setSize(355, 165);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaLogin().setVisible(true));
    }

    // Metodo logar, verificar se exite o usuario e senha no banco de dados
    public void logar() {
        String sql = "SELECT * FROM tbusuarios WHERE login = ? AND senha = ?;";
        try {
            conn = ModuloConecxao2.conector();
            assert conn != null;
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, txtUsuario.getText());
            stmt.setString(2, new String(txtSenha.getPassword()));
            rs = stmt.executeQuery();
            if (rs.next()) {
                String nomeUser = rs.getString("usuario");
                String perfil = rs.getString("perfil");

                /* Instanciando TelaPrincipal
                 * @param nomeUser (String) nome do usu&aacute;rio.
                 * @param perfil  (String) perfil do usu&aacute;rio
                 */
                TelaPrincipal principal = new TelaPrincipal(nomeUser, perfil);
                principal.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(TelaLogin.this, "Usuário e/ou Senha inválido(s)");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(TelaLogin.this, "Tabela Usuário no banco de dados não existe\n" + ex);
        } finally {
            ModuloConecxao.close(conn, stmt, rs);
        }
    }
}
