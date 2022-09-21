package dal;

import modelo.Usuario;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements intFDAO<Usuario> {

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    /**
     * Metodo salvar (Insert) no banco de dados
     * @param user  usuarios
     * @return falso se não de erro, verdadiro contrario
     */
    @Override
    public boolean salvar(Usuario user) {
        try {
            conn = ModuloConecxao2.conector();
            String ADD = "INSERT INTO tbusuarios(usuario, fone, login, senha, perfil) VALUES(?, ?, ?, ?, ?);";
            assert conn != null;
            stmt = conn.prepareStatement(ADD);
            stmt.setString(1, user.getUsuario());
            stmt.setString(2, user.getFone());
            stmt.setString(3, user.getLogin());
            stmt.setString(4, user.getSenha());
            stmt.setString(5, user.getPerfil());
            return stmt.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar\n" + ex);
        } finally {
            ModuloConecxao.close(conn, stmt);
        }
        return true;
    }

    /**
     * Metodo alterar (Update) no banco de dados
      * @param user usuario
     * @return falso se não de erro, verdadiro contrario
     */
    @Override
    public boolean alterar(Usuario user) {
        try {
            conn = ModuloConecxao2.conector();
            String UPD = "UPDATE tbusuarios SET usuario=?, fone=?,login=?, senha=?,perfil=? WHERE iduser=?";
            assert conn != null;
            stmt = conn.prepareStatement(UPD);
            stmt.setString(1, user.getUsuario());
            stmt.setString(2, user.getFone());
            stmt.setString(3, user.getLogin());
            stmt.setString(4, user.getSenha());
            stmt.setString(5, user.getPerfil());
            stmt.setInt(6, user.getIduser());
            return stmt.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Alterar\n" + ex);
        } finally {
            ModuloConecxao.close(conn, stmt);
        }
        return true;
    }

    /**
     * Metodo excluir (delet) no banco de dados
     * @param id chave primaria do regitro
     * @return falso se não de erro, verdadiro contrario
     */
    @Override
    public boolean excluir(int id) {
        try {
            conn = ModuloConecxao2.conector();
            String DEL = "DELETE FROM tbusuarios WHERE iduser=?;";
            assert conn != null;
            stmt = conn.prepareStatement(DEL);
            stmt.setInt(1, id);
            return stmt.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir\n" + ex);
        } finally {
            ModuloConecxao.close(conn, stmt);
        }
        return true;
    }

    /**
     * Metodo lista (select) no banco de dados
     * @return todos os resgistros da tabela
     */
    @Override
    public List<Usuario> lista() {
        List<Usuario> valores = new ArrayList<>();
        try {
            conn = ModuloConecxao2.conector();
            String SLQ = "SELECT iduser, usuario, fone, login, senha, perfil FROM tbusuarios;";
            assert conn != null;
            stmt = conn.prepareStatement(SLQ);
            rs = stmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Usuario user = new Usuario();
                    // usuario, fone, login, senha, perfil
                    user.setIduser(rs.getInt("iduser"));
                    user.setUsuario(rs.getString("usuario"));
                    user.setFone(rs.getString("fone"));
                    user.setLogin(rs.getString("login"));
                    user.setSenha(rs.getString("senha"));
                    user.setPerfil(rs.getString("perfil"));
                    valores.add(user);
                }
            }
            return valores;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na lista\n" + ex);
        } finally {
            ModuloConecxao2.close(conn, stmt);
        }
        return null;
    }

    /**
     * Metodo consultID (select c/ parametro) no banco de dados
     * @param id chave primeria do registro
     * @return usuario
     */
    public Usuario consultaID(int id){
        Usuario user = null;
        try {
            conn = ModuloConecxao2.conector();
            String SQL_ID = "SELECT * FROM dbinforx.tbusuarios WHERE iduser=?;";
            assert conn != null;
            stmt = conn.prepareStatement(SQL_ID);
            stmt.setInt(1,id);
            rs = stmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    user = new Usuario();
                    user.setIduser(rs.getInt(1));
                    user.setUsuario(rs.getString(2));
                    user.setFone(rs.getString(3));
                    user.setLogin(rs.getString(4));
                    user.setSenha(rs.getString(5));
                    user.setPerfil(rs.getString(6));
                }
            }
            return user;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na consultaID\n" + ex);
        } finally {
            ModuloConecxao2.close(conn, stmt);
        }
        return null;
    }
}
