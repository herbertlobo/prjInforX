package dao;


import modelo.Usuario;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class UsuarioMTable extends AbstractTableModel {


    private final String[] colunas = {"Usuário","Contato","Login","Perfil"};
    private static List<Usuario> valores;

    // CONSTRUTOR PRA INICIAR UMA LISTA VAZIA
    public UsuarioMTable(){
        valores = new ArrayList<>();
    }

    /**
     * Construto chamar o construtor anterior pra depois carregar uma lista
     * @param valores liste de Usuario
     */
    public UsuarioMTable(List<Usuario> valores) {
        this();
        UsuarioMTable.valores = valores;
    }

    @Override
    public int getRowCount() {
        return valores.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        if(valores != null){
            switch (coluna){
                case 0: return valores.get(linha).getUsuario();
                case 1: return valores.get(linha).getFone();
                case 2: return valores.get(linha).getLogin();
                case 3: return valores.get(linha).getPerfil();
                default:return null;
            }
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0: case 1: case 2: case 3: return String.class;
            default:return null;
        }
    }

    /**
     * Metodo pra resgata um Usuário da tabela
     * @param linha linha da pesquisa
     * @return usuario
     */
    public static Usuario getUsuario(int linha){
        return valores.get(linha);
    }
}
