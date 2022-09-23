package modelo;

import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable {

    private int iduser;
    private String usuario;
    private String fone;
    private String login;
    private String senha;
    private String perfil;

    public Usuario() {
    }

    public Usuario(int iduser, String usuario, String fone, String login, String senha, String perfil) {
        this.iduser = iduser;
        this.usuario = usuario;
        this.fone = fone;
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
    }

    public Usuario(String usuario, String fone, String login, String senha, String perfil) {
        this.usuario = usuario;
        this.fone = fone;
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "iduser=" + iduser +
                ", usuario='" + usuario + '\'' +
                ", fone='" + fone + '\'' +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", perfil='" + perfil + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return iduser == usuario.iduser;
    }

    @Override
    public int hashCode() {
        return Objects.hash(iduser);
    }
}
