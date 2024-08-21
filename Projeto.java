package Entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Projeto{
    private int idProjeto;
    private String nomeProjeto;
    private String local;
    private java.sql.Date dataIncio;
    private java.sql.Date dataTerminio;
    private List<Engenheiro>engenheiros = new ArrayList<>();
    private List<Operario> operarios = new ArrayList<>();
    private List<Equipamento>equipamentos = new ArrayList<>();
    private List<Material>materiais = new ArrayList<>();


    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Date getDataIncio() {
        return dataIncio;
    }

    public void setDataIncio(Date dataIncio) {
        this.dataIncio = dataIncio;
    }

    public Date getDataTerminio() {
        return dataTerminio;
    }

    public void setDataTerminio(Date dataTerminio) {
        this.dataTerminio = dataTerminio;
    }

    public List<Engenheiro> getEngenheiros() {
        return engenheiros;
    }

    public void setEngenheiros(List<Engenheiro> engenheiros) {
        this.engenheiros = engenheiros;
    }

    public List<Operario> getOperarios() {
        return operarios;
    }

    public void setOperarios(List<Operario> operarios) {
        this.operarios = operarios;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    public List<Material> getMateriais() {
        return materiais;
    }

    public void setMateriais(List<Material> materiais) {
        this.materiais = materiais;
    }

    @Override
    public String toString() {
        return "Projeto{" +
                "idProjeto=" + idProjeto +
                ", nomeProjeto='" + nomeProjeto + '\'' +
                ", local='" + local + '\'' +
                ", dataIncio=" + dataIncio +
                ", dataTerminio=" + dataTerminio +
                '}';
    }
}
