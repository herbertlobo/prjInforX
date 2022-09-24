package modelo;

import java.io.Serializable;
import java.util.Objects;

public class Cliente implements Serializable {

    private int idcli;
    private String clinete;
    private String endereco;
    private String telefone;
    private String email;

    public Cliente() {
    }

    public Cliente(String clinete, String endereco, String telefone, String email) {
        this.clinete = clinete;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }

    public int getIdcli() {
        return idcli;
    }

    public void setIdcli(int idcli) {
        this.idcli = idcli;
    }

    public String getClinete() {
        return clinete;
    }

    public void setClinete(String clinete) {
        this.clinete = clinete;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idcli=" + idcli +
                ", clinete='" + clinete + '\'' +
                ", endereco='" + endereco + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return idcli == cliente.idcli;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcli);
    }
}
