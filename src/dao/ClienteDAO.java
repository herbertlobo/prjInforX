package dao;

import modelo.Cliente;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements intFDAO<Cliente> {

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    public ClienteDAO(){
        this.conn = null;
        this.stmt = null;
        this.rs = null;
    }

    /**
     * Metodo salvar (Insert) no banco de dados
     * @param cli modelo  cliente
     * @return falso se não de erro, verdadiro contrario
     */
    @Override
    public boolean salvar(Cliente cli) {
        final String ADD = "INSERT INTO public.tbclientes (nomecli, endcli, fonecli, emailcli) VALUES( ?, ?, ?, ?);";
        try{
            conn = ModuloConecxao2.conector();
            assert conn != null;
            stmt = conn.prepareStatement(ADD);
            stmt.setString(1,cli.getCliente());
            stmt.setString(2, cli.getEndereco());
            stmt.setString(3,cli.getTelefone());
            stmt.setString(4,cli.getEmail());
            return !stmt.execute();
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Erro ao Salvar\n"+ex);
        } finally {
            ModuloConecxao2.close(conn,stmt);
        }
        return false;
    }

    /**
     * Metodo alterar (Update) no banco de dados
     * @param cli cliente
     * @return falso se não de erro, verdadiro contrario
     */
    @Override
    public boolean alterar(Cliente cli) {
        final String UPD = "UPDATE public.tbclientes SET nomecli = ?, endcli = ?, fonecli = ?, emailcli = ? WHERE idcli = ? ;";
        try{
            conn = ModuloConecxao2.conector();
            assert conn != null;
            stmt = conn.prepareStatement(UPD);
            stmt.setString(1,cli.getCliente());
            stmt.setString(2, cli.getEndereco());
            stmt.setString(3,cli.getTelefone());
            stmt.setString(4,cli.getEmail());
            stmt.setInt(5,cli.getIdcli());
            return stmt.execute();
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Erro ao Alterar\n"+ex);
        } finally {
            ModuloConecxao2.close(conn,stmt);
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
        final String DEL = "DELETE FROM public.tbclientes WHERE idcli=?;";
        try{
            conn = ModuloConecxao2.conector();
            assert conn != null;
            stmt = conn.prepareStatement(DEL);
            stmt.setInt(1,id);
            return !stmt.execute();
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Erro ao Excluir\n"+ex);
        } finally {
            ModuloConecxao2.close(conn,stmt);
        }
        return false;
    }

    /**
     * Metodo lista (select) no banco de dados
     * @return todos os resgistros da tabela
     */
    @Override
    public List<Cliente> lista() {
        List<Cliente> valores = new ArrayList<>();
        final String SLC = "SELECT idcli, nomecli, endcli, fonecli, emailcli FROM public.tbclientes ORDER BY nomecli;";
        try{
            conn = ModuloConecxao2.conector();
            assert conn != null;
            stmt = conn.prepareStatement(SLC);
            rs = stmt.executeQuery();
            if(rs != null){
                while (rs.next()){
                    Cliente cli = new Cliente();
                    cli.setIdcli(rs.getInt("idcli"));
                    cli.setCliente(rs.getString("nomecli"));
                    cli.setEndereco(rs.getString("endcli"));
                    cli.setTelefone(rs.getString("fonecli"));
                    cli.setEmail(rs.getString("emailcli"));
                    valores.add(cli);
                }
            }
            return valores;
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Erro ao retorna uma lista\n"+ex);
        } finally {
            ModuloConecxao2.close(conn,stmt,rs);
        }
        return null;
    }

    /**
     * Metodo consultPorId (select c/ parametro) no banco de dados
     * @param id chave primeria do registro
     * @return usuario
     */
    public Cliente buscarPorId(int id) {
        final String SLC = "SELECT idcli, nomecli, endcli, fonecli, emailcli FROM public.tbclientes WHERE idcli=?";
        try{
            conn = ModuloConecxao2.conector();
            assert conn != null;
            stmt = conn.prepareStatement(SLC);
            stmt.setInt(1,id);
            rs = stmt.executeQuery();
            if(rs != null) while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setIdcli(rs.getInt("idcli"));
                cli.setCliente(rs.getString("nomecli"));
                cli.setEndereco(rs.getString("endcli"));
                cli.setTelefone(rs.getString("fonecli"));
                cli.setEmail(rs.getString("emailcli"));
                return cli;
            }

        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Erro ao retorna uma lista\n"+ex);
        } finally {
            ModuloConecxao2.close(conn,stmt,rs);
        }
        return null;
    }
}
