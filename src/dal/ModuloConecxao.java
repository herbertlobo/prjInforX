package dal;

import javax.swing.*;
import java.sql.*;

public class ModuloConecxao {

    public static Connection conector(){

        try{
            Class.forName("org.mariadb.jdbc.Driver");

            return DriverManager.getConnection("jdbc:mariadb://localhost:3306/dbinforx","root","");
        } catch (ClassNotFoundException cfe){
            JOptionPane.showMessageDialog(null,"Erro no Driver de Conecxão\n"+cfe,"Erro",JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Erro de Conecxão\n"+ex,"Erro",JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public static void close(Connection conn){
        try{
            if(conn != null){
                conn.close();
            }
        } catch (SQLException ex){
            System.out.println("Erro ao Fechar conecxão: "+ex.getMessage());
        }
    }

    public static void close(Connection conn, PreparedStatement stmt){
        try{
            if(stmt != null){
                stmt.close();
            }
        } catch (SQLException ex){
            System.out.println("Erro ao Fechar conecxão: "+ex.getMessage());
        } finally {
            close(conn);
        }
    }

    public static void close(Connection conn, PreparedStatement stmt, ResultSet rs){
        try{
            if( rs != null){
                rs.close();
            }
        } catch (SQLException ex){
            System.out.println("Erro ao Fechar conecxão: "+ex.getMessage());
        } finally {
            close(conn,stmt);
        }
    }
}
