package dao;

import java.util.List;

public interface intFDAO <T>{

    /**
     *  Contrado dal (CRUD)
     */

    boolean salvar(T t);
    boolean alterar(T t);
    boolean excluir(int id);
    List<T> lista();
}
