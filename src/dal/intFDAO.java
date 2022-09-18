package dal;

import java.util.List;

public interface intFDAO <T>{


    boolean salvar(T t);
    boolean alterar(T t);
    boolean excluir(int id);
    List<T> lista();
}
