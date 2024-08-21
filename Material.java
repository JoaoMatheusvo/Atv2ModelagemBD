package Entity;

import java.util.ArrayList;
import java.util.List;

public class Material {
    private int idMaterial;
    private String nomeMaterial;
    private int quantidade;

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }

    private List<Projeto> projetos = new ArrayList<>();

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getNomeMaterial() {
        return nomeMaterial;
    }

    public void setNomeMaterial(String nomeMaterial) {
        this.nomeMaterial = nomeMaterial;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Material{" +
                "idMaterial=" + idMaterial +
                ", nomeMaterial='" + nomeMaterial + '\'' +
                ", quantidade=" + quantidade +
                '}';
    }
}
