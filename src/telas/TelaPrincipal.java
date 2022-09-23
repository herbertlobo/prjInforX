package telas;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;

public class TelaPrincipal extends JFrame {

    private JLabel lblUsuario;
    public static JDesktopPane desktopPane;

    public TelaPrincipal(String user, String perfil) {
        super("X - Systema de cadastro de OS");
        initComponents(perfil);
        if(user.equalsIgnoreCase("Administrador")){
            lblUsuario.setForeground(Color.RED);
        }
        lblUsuario.setText(user);
        
    }

    private void initComponents(String perfil) {
        Font font = new Font(null, Font.BOLD, 18);
        lblUsuario = new JLabel("Usuário");
        lblUsuario.setFont(font);
        DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
        JLabel lblData = new JLabel(format.format(new Date()));
        lblData.setFont(font);
        ClassLoader loader = getClass().getClassLoader();
        JLabel lblLogoX = new JLabel(new ImageIcon(Objects.requireNonNull(loader.getResource("icones/x.png"))));
        desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(85, 107, 47));
        JPanel pnLateral = new JPanel(new MigLayout("", "20px[]20px", "40px[]20px[]40px[]"));
        pnLateral.add(lblUsuario, "align center, wrap");
        pnLateral.add(lblData, "align center, wrap");
        pnLateral.add(lblLogoX, "growx, pushx, align center");
        JPanel pnPrincipal = new JPanel(new MigLayout());
        pnPrincipal.add(desktopPane, "dock center");
        pnPrincipal.add(pnLateral, "dock east");
        setContentPane(pnPrincipal);
        setJMenuBar(new MenuPrincipal(perfil));
        setSize(917, 542);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
