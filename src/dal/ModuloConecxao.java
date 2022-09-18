package dal;

import javax.swing.*;
import java.sql.*;

public class ModuloConecxao {


    /**
     * Metodo Conection (conecxão)
     * @return retorna um String de conecxão
     */
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

    /**
     * Metodo para fechar a conecxão
     * @param conn Connection
     */
    public static void close(Connection conn){
        try{
            if(conn != null){
                conn.close();
            }
        } catch (SQLException ex){
            System.out.println("Erro ao Fechar conecxão: "+ex.getMessage());
        }
    }

    /**
     * Metodo para fechar uma conexão e Um objeto que representa uma instrução SQL pré-compilada.
     * @param conn  Connection
     * @param stmt  PreparedStatemant
     */
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

    /**
     * Metodo para fechar uma conexão, Um objeto que representa uma instrução SQL pré-compilada e
     * retorno de um conjunto de resultados de banco de dados
     * @param conn Connection
     * @param stmt PreparedStatemant
     * @param rs   ResultSet
     */
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
