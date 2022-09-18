package telas;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.util.Objects;

public class TelaSobre extends JInternalFrame {

    public TelaSobre() {
        iniComponents();
    }

    private void iniComponents() {
        ClassLoader loader = getClass().getClassLoader();
        JLabel lblGnu = new JLabel(new ImageIcon(Objects.requireNonNull(loader.getResource("icones/gnu-logo.png"))));
        JLabel lbllink = new JLabel("https://www.youtube.com/c/RoboticapraticaBr");
        lbllink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try{
                       URI link = new URI("https://www.youtube.com/watch?v=eA4WjjkzK3c&list=PLbEOwbQR9lqxsTusvu8wfkUECrmcV81MU");
                    Desktop.getDesktop().browse(link);
                } catch (Exception erro){
                    System.out.println("Erro:"+erro);
                }
            }
        } );
        JPanel pnSobre = new JPanel(new MigLayout("insets 20"));
        pnSobre.add(lblGnu, "dock east, wrap");
        pnSobre.add(new JLabel("Sistema para controle de Ordem de Serviço"),"dock center, wrap");
        pnSobre.add(new JLabel("Desenvolvido po Jose de Assis"),"dock center, wrap");
        pnSobre.add(lbllink,"dock center, wrap");
        pnSobre.add(new JLabel("Sob a licença GPL"),"dock center, wrap");

        setContentPane(pnSobre);
        setSize(480,200);
        setClosable(true);
        setResizable(false);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
    }
}
