package test;


import dao.ClienteDAO;
import modelo.Cliente;
import org.junit.jupiter.api.Test;

public class ClienteDAOTest{

    @Test
    public void salvarTest() {
        Cliente cli = new Cliente();
        cli.setCliente("Laura Rebeca Assunção");
        cli.setEndereco("1ª Travessa Santa Maria Madalena, 742");
        cli.setTelefone("(82) 98889-6783");
        cli.setEmail("laura.rebeca.assuncao@grupoaguaviva.com.br");

        ClienteDAO dao = new ClienteDAO();
        if(dao.salvar(cli)){
            System.out.println("Salvo com sucesso...");
        }
    }

    @Test
    public void alterarTest() {
        ClienteDAO dao = new ClienteDAO();

        Cliente cli = dao.buscarPorId(10);
        //cli.setClinete("Laura Rebeca Assunção");
        cli.setEndereco("Rua José Franklin Sarmento Ferreira, 802");
        //cli.setTelefone("(82) 98889-6783");
        //cli.setEmail("laura.rebeca.assuncao@grupoaguaviva.com.br");


        if(!dao.alterar(cli)){
            System.out.println("Alterado com sucesso...");
        }
    }

    @Test
    public void excluirTest() {
        ClienteDAO dao = new ClienteDAO();

        if(dao.excluir(11)){
            System.out.println("Exluido com sucesso...");
        }
    }

    @Test
    public void lista() {
        ClienteDAO dao = new ClienteDAO();

        for (Cliente cli: dao.lista()) System.out.println(cli);
    }
}
