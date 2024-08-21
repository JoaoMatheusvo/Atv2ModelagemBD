package Entity;

import java.util.ArrayList;
import java.util.List;

public class Equipamento {
    private int idEquipamento;
    private String nomeEquipamento;
    private String tipo;

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }

    private List<Projeto> projetos = new ArrayList<>();

    public int getIdEquipamento() {
        return idEquipamento;
    }

    public void setIdEquipamento(int idEquipamento) {
        this.idEquipamento = idEquipamento;
    }

    public String getNomeEquipamento() {
        return nomeEquipamento;
    }

    public void setNomeEquipamento(String nomeEquipamento) {
        this.nomeEquipamento = nomeEquipamento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Equipamento{" +
                "idEquipamento=" + idEquipamento +
                ", nomeEquipamento='" + nomeEquipamento + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
