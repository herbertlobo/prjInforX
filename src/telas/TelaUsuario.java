package telas;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;

import static java.util.Objects.requireNonNull;

public class TelaUsuario extends JInternalFrame {

    public TelaUsuario() {
        super("Usuário");
        initComponents();
    }

    private void initComponents() {

        JTextField txtId = new JTextField(5);
        JTextField txtNome = new JTextField();
        JTextField txtLogin = new JTextField();
        JPasswordField txtSenha = new JPasswordField();
        JFormattedTextField txtFone;
        try {
            MaskFormatter mask = new MaskFormatter("(##) #####-####");
            txtFone = new JFormattedTextField(mask);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String[] opPerfil = {"Admin","User", "Vendedor", "Tecníco"};
        JComboBox<String> cmbPerfil = new JComboBox<>(opPerfil);

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
        pnEntrada.add(new JLabel("Codígo:"),"split 5");
        pnEntrada.add(txtId, "wrap");
        pnEntrada.add(new JLabel("Usuário:"),"split 5");
        pnEntrada.add(txtNome, "span 4,growx,pushx,wrap");
        pnEntrada.add(new JLabel("Telefone:"),"split 5");
        pnEntrada.add(txtFone,"growx,pushx");
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
        pnMain.add(pnEntrada,"growx, pushx ,wrap");
        pnMain.add(pnBtn,"growx, pushx");

        setContentPane(pnMain);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(false);
        setResizable(false);
        setSize(500, 300);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
    }
}
