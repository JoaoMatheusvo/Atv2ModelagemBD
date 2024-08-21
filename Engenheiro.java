package Entity;

import java.util.ArrayList;
import java.util.List;

public class Engenheiro {
    private int idEngenheiro;
    private String nomeEngenheiro;
    private String especialidade;

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }

    private List<Projeto>projetos = new ArrayList<>();



    public int getIdEngenheiro() {
        return idEngenheiro;
    }

    public void setIdEngenheiro(int idEngenheiro) {
        this.idEngenheiro = idEngenheiro;
    }

    public String getNomeEngenheiro() {
        return nomeEngenheiro;
    }

    public void setNomeEngenheiro(String nomeEngenheiro) {
        this.nomeEngenheiro = nomeEngenheiro;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public String toString() {
        return "Engenheiro{" +
                "idEngenheiro=" + idEngenheiro +
                ", nomeEngenheiro='" + nomeEngenheiro + '\'' +
                ", especialidade='" + especialidade + '\'' +
                '}';
    }
}
