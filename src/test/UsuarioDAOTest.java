package test;

import dao.UsuarioDAO;
import modelo.Usuario;
import org.junit.jupiter.api.Test;

class UsuarioDAOTest {

    @Test
    void insertTest(){
        Usuario user = new Usuario();
        user.setUsuario("Jose de Assis");
        user.setFone("(82) 98101-2587");
        user.setLogin("assis");
        user.setSenha("12345");
        user.setPerfil("user");

        UsuarioDAO dao = new UsuarioDAO();
        if(dao.salvar(user)){
            System.out.println("Usuário salvo com sucesso...");
        }
    }

    @Test
    void alterarTest(){
        Usuario user = new Usuario();
        user.setUsuario("Jose de Assis");
        user.setFone("(82) 98101-2587");
        user.setLogin("assis");
        user.setSenha("jose");
        user.setPerfil("user");
        user.setIduser(3);

        UsuarioDAO dao = new UsuarioDAO();
        if(dao.alterar(user)){
            System.out.println("Usuário Alterado com sucesso...");
        }
    }

    @Test
    void excluirTest(){
        UsuarioDAO dao = new UsuarioDAO();
        if(dao.excluir(3)){
            System.out.println("Usuário excluido com sucesso...");
        }
    }

    @Test
    void listarTodos(){
        UsuarioDAO dao = new UsuarioDAO();
        for (Usuario user: dao.lista()) {
            System.out.println(user);
        }
    }

    @Test
    void consultaPorID(){
        UsuarioDAO dao = new UsuarioDAO();

        Usuario use;
        if((use = dao.consultaID(2)) != null){
            System.out.println(use);
        }
    }
}