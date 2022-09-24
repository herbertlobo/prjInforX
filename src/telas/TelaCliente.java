package telas;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class TelaCliente extends JInternalFrame {


    public TelaCliente() {
        initComponets();
    }

    private void initComponets() {

        ClassLoader loader = getClass().getClassLoader();

        // PANEL PESQUISA
        JPanel pnPesquisa = new JPanel(new MigLayout("center","[][]40px[]","[]"));
        pnPesquisa.setBorder(BorderFactory.createTitledBorder("Pesquisa"));
        JTextField txtPesquisa = new JTextField(30);
        JLabel lblLogoPs = new JLabel(new ImageIcon(Objects.requireNonNull(loader.getResource("icones/pesquisar.png"))));
        pnPesquisa.add(txtPesquisa);
        pnPesquisa.add(lblLogoPs);
        pnPesquisa.add(new JLabel("* Campos obrigatórios"),"align right");

        // PAINEL TABELA
        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Tabela Clientes"));

        // PAINEL DE ENTRADA
        JTextField txtNome = new JTextField();
        JTextField txtEndereco = new JTextField();
        JFormattedTextField txtTelefone;
        JTextField txtEmail = new JTextField();
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
        pnEntrada.add(txtNome,"growx, pushx, wrap");
        pnEntrada.add(new JLabel("Endereço:"));
        pnEntrada.add(txtEndereco,"grow, pushx, wrap");
        pnEntrada.add(new JLabel("* Telefone:"));
        pnEntrada.add(txtTelefone,"wrap");
        pnEntrada.add(new JLabel("E-mail:"));
        pnEntrada.add(txtEmail,"growx, pushx");

        // PAINEL DE CONTROLE
        JButton btnAdicionar = new JButton(new ImageIcon(requireNonNull(loader.getResource("icones/create.png"))));
        btnAdicionar.setToolTipText("Adicionar");
        JButton btnAlterar = new JButton(new ImageIcon(requireNonNull(loader.getResource("icones/update.png"))));
        btnAlterar.setToolTipText("Alterar");
        JButton btnRemover = new JButton(new ImageIcon(requireNonNull(loader.getResource("icones/delete.png"))));
        btnRemover.setToolTipText("Remover");

        JPanel pnControle = new JPanel(new MigLayout("center","[]20[]20[]","[]"));
        pnControle.setBorder(BorderFactory.createTitledBorder("Controle"));
        pnControle.add(btnAdicionar);
        pnControle.add(btnAlterar);
        pnControle.add(btnRemover);

        // PAINEL PRINCIPAL
        JPanel pnMain = new JPanel(new MigLayout("insets 10"));
        pnMain.add(pnPesquisa,"growx,pushx, wrap");
        pnMain.add(scrollPane,"grow, push, wrap");
        pnMain.add(pnEntrada,"growx,pushx,wrap");
        pnMain.add(pnControle,"growx,pushx,align center");

        // CONFIGURAÇÃO DA JANELA
        setContentPane(pnMain);
        setSize(TelaPrincipal.desktopPane.getWidth(),TelaPrincipal.desktopPane.getHeight());
        setClosable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
    }
}
