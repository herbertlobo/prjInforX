package dal;

import modelo.Cliente;

import java.util.List;

public class ClienteDAO implements intFDAO<Cliente> {

    @Override
    public boolean salvar(Cliente cliente) {
        return false;
    }

    @Override
    public boolean alterar(Cliente cliente) {
        return false;
    }

    @Override
    public boolean excluir(int id) {
        return false;
    }

    @Override
    public List<Cliente> lista() {
        return null;
    }
}
