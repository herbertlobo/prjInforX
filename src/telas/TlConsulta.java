package telas;

import dal.UsuarioDAO;
import dal.UsuarioMTable;
import modelo.Usuario;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TlConsulta extends JInternalFrame {

    public TlConsulta() {
        super("Selecione o Registro");

        JButton btnFechar = new JButton("Fachar");
        btnFechar.addActionListener(new ActionFechar());
        UsuarioDAO dao = new UsuarioDAO();
        JTable tabelaUser = new JTable();
        tabelaUser.setModel(new UsuarioMTable(dao.lista()));
        JScrollPane  scrollPane = new JScrollPane(tabelaUser);

        tabelaUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    Usuario user = UsuarioMTable.getUsuario(tabelaUser.getSelectedRow());
                    TelaUsuario.txtId.setText(String.valueOf(user.getIduser()));
                    TelaUsuario.txtNome.setText(user.getUsuario());
                    TelaUsuario.txtFone.setText(user.getFone());
                    TelaUsuario.txtLogin.setText(user.getLogin());
                    TelaUsuario.txtSenha.setText(user.getSenha());
                    TelaUsuario.cmbPerfil.setSelectedItem(user.getPerfil().toString());
                    dispose();
                }
            }
        });


        JPanel pnMain = new JPanel(new MigLayout("insets 10","[]","[][][]"));
        pnMain.add(new JLabel("* 2 clicks pra selecionar"),"pushx ,align right, wrap");
        pnMain.add(scrollPane,"grow, push,wrap");
        pnMain.add(btnFechar,"pushx, align right");

        setContentPane(pnMain);
        setSize(500,300);
        setResizable(false);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
    }

    private class ActionFechar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            dispose();
        }
    }
}
