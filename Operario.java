package Entity;

import java.util.ArrayList;
import java.util.List;

public class Operario {
    private int idOperario;
    private String nomeOperario;
    private String funcao;

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }

    private List<Projeto> projetos = new ArrayList<>();

    public int getIdOperario() {
        return idOperario;
    }

    public void setIdOperario(int idOperario) {
        this.idOperario = idOperario;
    }

    public String getNomeOperario() {
        return nomeOperario;
    }

    public void setNomeOperario(String nomeOperario) {
        this.nomeOperario = nomeOperario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public String toString() {
        return "Operario{" +
                "idOperario=" + idOperario +
                ", nomeOperario='" + nomeOperario + '\'' +
                ", funcao='" + funcao + '\'' +
                '}';
    }
}
