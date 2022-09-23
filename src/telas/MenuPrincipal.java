package telas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MenuPrincipal extends JMenuBar {

    public MenuPrincipal(String perfil) {
        // Menu Cadastro
        JMenu mnCadastro = new JMenu("Cadastros");
        JMenuItem mniClientes = new JMenuItem("Clientes");
        mniClientes.setMnemonic(KeyEvent.VK_C);
        JMenuItem mniOs = new JMenuItem("OS");
        mniOs.setMnemonic(KeyEvent.VK_O);
        JMenuItem mniUsuario = new JMenuItem("Usuários");
        mniUsuario.setMnemonic(KeyEvent.VK_U);
        mniUsuario.setEnabled(perfil.equalsIgnoreCase("admin"));
        mniUsuario.addActionListener((ActionEvent actionEvent) -> {
            TelaUsuario telaUsuario = new TelaUsuario();
            telaUsuario.setVisible(true);
            TelaPrincipal.desktopPane.add(telaUsuario);
        });
        mnCadastro.add(mniClientes);
        mnCadastro.add(mniOs);
        mnCadastro.add(mniUsuario);

        // Menu Relatório
        JMenu mnRelatorio = new JMenu("Relatório");
        JMenuItem mniServico = new JMenuItem("Serviços");
        mniServico.setEnabled(perfil.equalsIgnoreCase("admin"));
        mniServico.setMnemonic(KeyEvent.VK_S);
        mnRelatorio.add(mniServico);

        // Menu Ajuda
        JMenu mnAjuda = new JMenu("Ajuda");
        JMenuItem mniSobre = new JMenuItem("Sobre");
        mniSobre.setMnemonic(KeyEvent.VK_F1);
        mniSobre.addActionListener(actionEvent -> {
            TelaSobre telaSobre = new TelaSobre();
            telaSobre.setVisible(true);
            telaSobre.setLocation(TelaPrincipal.desktopPane.getHeight() / 2 - telaSobre.getHeight() / 2, TelaPrincipal.desktopPane.getHeight() / 2 - telaSobre.getHeight() / 2);
            TelaPrincipal.desktopPane.add(telaSobre);
        });
        mnAjuda.add(mniSobre);

        // Menu Opções
        JMenu mnOpcoes = new JMenu("Opções");
        JMenuItem mniSair = new JMenuItem("Sair");
        mniSair.setMnemonic(KeyEvent.VK_F4);
        mniSair.addActionListener(actionEvent -> {
            int opc = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (opc == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        mnOpcoes.add(mniSair);


        add(mnCadastro);
        add(mnRelatorio);
        add(mnAjuda);
        add(mnOpcoes);
    }


}
