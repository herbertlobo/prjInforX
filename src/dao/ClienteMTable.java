package dao;

import modelo.Cliente;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ClienteMTable extends AbstractTableModel {

    private final String[] colunas = {"Cliente","Endereço","Contato","E-mail"};
    private static List<Cliente> valores;

    private ClienteMTable(){
        valores = new ArrayList<>();
    }

    public ClienteMTable(List<Cliente> valores) {
        this();
        ClienteMTable.valores = valores;
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

        switch (coluna){
            case 0 : return valores.get(linha).getCliente();
            case 1: return valores.get(linha).getEndereco();
            case 2: return valores.get(linha).getTelefone();
            case 3: return valores.get(linha).getEmail();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0: case 1: case 2: case 3: return String.class;
            default: return null;
        }
    }

    public static Cliente getCliente(int linha){
        return valores.get(linha);
    }
}
