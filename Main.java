import DAO.*;
import Entity.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;


public class Main {
    public static void main(String[] args) {

    try{
        // Criar instâncias dos DAOs
        ProjetoDAO projetoDAO = new ProjetoDAO();
        EngenheiroDAO engenheiroDAO = new EngenheiroDAO();
        OperarioDAO operarioDAO = new OperarioDAO();
        EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
        MaterialDAO materialDAO = new MaterialDAO();


        // Inserir projetos

        Projeto projeto1 = new Projeto();
        projeto1.setNomeProjeto("Construção do Edifício 1");
        projeto1.setLocal("Centro");
        projeto1.setDataIncio(Date.valueOf("2024-01-01"));
        projeto1.setDataTerminio(Date.valueOf("2024-12-31"));
        projetoDAO.inserirProjeto(projeto1);

        Projeto projeto2 = new Projeto();
        projeto2.setNomeProjeto("Reforma do Edifício 2");
        projeto2.setLocal("Zona Leste");
        projeto2.setDataIncio(Date.valueOf("2024-02-01"));
        projeto2.setDataTerminio(Date.valueOf("2024-11-30"));
        projetoDAO.inserirProjeto(projeto2);

        // Inserir engenheiros
        Engenheiro engenheiro1 = new Engenheiro();
        engenheiro1.setNomeEngenheiro("Jaime");
        engenheiro1.setEspecialidade("Estruturas");
        engenheiroDAO.inserirEngenheiro(engenheiro1);

        Engenheiro engenheiro2 = new Engenheiro();
        engenheiro2.setNomeEngenheiro("Daniel");
        engenheiro2.setEspecialidade("Elétrica");
        engenheiroDAO.inserirEngenheiro(engenheiro2);

        // Inserir operários
        Operario operario1 = new Operario();
        operario1.setNomeOperario("Nestor");
        operario1.setFuncao("Pedreiro");
        operarioDAO.inserirOperario(operario1);

        Operario operario2 = new Operario();
        operario2.setNomeOperario("Maria");
        operario2.setFuncao("Eletricista");
        operarioDAO.inserirOperario(operario2);

        // Inserir equipamentos
        Equipamento equipamento1 = new Equipamento();
        equipamento1.setNomeEquipamento("Betoneira");
        equipamento1.setTipo("Misturador");
        equipamentoDAO.inserirEquipamento(equipamento1);

        Equipamento equipamento2 = new Equipamento();
        equipamento2.setNomeEquipamento("Andaime");
        equipamento2.setTipo("Apoio");
        equipamentoDAO.inserirEquipamento(equipamento2);

        // Inserir materiais
        Material material1 = new Material();
        material1.setNomeMaterial("Cimento");
        material1.setQuantidade(500);
        materialDAO.inserirMaterial(material1);

        Material material2 = new Material();
        material2.setNomeMaterial("Areia");
        material2.setQuantidade(300);
        materialDAO.inserirMaterial(material2);


        // Associar engenheiros e operários aos projetos
        // Alocar engenheiros e operários em projetos



        projetoDAO.alocarEngenheiro(projeto1.getIdProjeto(), engenheiro1.getIdEngenheiro());
        projetoDAO.alocarOperario(projeto1.getIdProjeto(),operario1.getIdOperario());
        projetoDAO.consumirMaterial(projeto1.getIdProjeto(), material1.getIdMaterial());
        projetoDAO.usarEquipamento(projeto1.getIdProjeto(), equipamento1.getIdEquipamento());

        // Listar projetos
        List<Projeto> projetos = projetoDAO.lisarProjetos();
        System.out.println("Projetos:");
        for (Projeto p : projetos) {
            System.out.println(p.getIdProjeto() + ": " + p.getNomeProjeto() + " - Local: " + p.getLocal());
        }

        // Listar engenheiros
        List<Engenheiro> engenheiros = engenheiroDAO.listarEngenheiro();
        System.out.println("\nEngenheiros:");
        for (Engenheiro e : engenheiros) {
            System.out.println(e.getIdEngenheiro() + ": " + e.getNomeEngenheiro() + " - Especialidade: " + e.getEspecialidade());
        }

        // Listar operários
        List<Operario> operarios = operarioDAO.listarOperarios();
        System.out.println("\nOperários:");
        for (Operario o : operarios) {
            System.out.println(o.getIdOperario() + ": " + o.getNomeOperario() + " - Função: " + o.getFuncao());
        }

        // Listar materiais
        List<Material> materiais = materialDAO.listarMateriais();
        System.out.println("\nMateriais:");
        for (Material m : materiais) {
            System.out.println(m.getIdMaterial() + ": " + m.getNomeMaterial() + " - Quantidade: " + m.getQuantidade());
        }

        // Listar equipamentos
        List<Equipamento> equipamentos = equipamentoDAO.listarEquipamentos();
        System.out.println("\nEquipamentos:");
        for (Equipamento e : equipamentos) {
            System.out.println(e.getIdEquipamento() + ": " + e.getNomeEquipamento() + " - Tipo: " + e.getTipo());
        }

        // Listar engenheiros e operários alocados em um projeto
        List<Engenheiro> engenheirosProjeto1 = projetoDAO.listarEngenheirosPorProjeto(projeto1.getIdProjeto());
        System.out.println("\nEngenheiros alocados no Projeto : " + projeto1.getIdProjeto());
        for (Engenheiro e : engenheirosProjeto1) {
            System.out.println(e.getIdEngenheiro() + ": " + e.getNomeEngenheiro() + " - Especialidade: " + e.getEspecialidade());
        }

        List<Operario> operariosProjeto1 = projetoDAO.listarOperariosPorProjeto(projeto1.getIdProjeto());
        System.out.println("\nOperários alocados no Projeto : " + projeto1.getIdProjeto());
        for (Operario o : operariosProjeto1) {
            System.out.println(o.getIdOperario() + ": " + o.getNomeOperario() + " - Função: " + o.getFuncao());
        }

        // Listar equipamentos e materiais utilizados em um projeto
        List<Equipamento> equipamentosProjeto1 = projetoDAO.listarEquipamentosPorProjeto(projeto1.getIdProjeto());
        System.out.println("\nEquipamentos utilizados no Projeto : " + projeto1.getIdProjeto());
        for (Equipamento e : equipamentosProjeto1) {
            System.out.println(e.getIdEquipamento() + ": " + e.getNomeEquipamento() + " - Tipo: " + e.getTipo());
        }

        List<Material> materiaisProjeto1 = projetoDAO.listarMateriaisPorProjeto(projeto1.getIdProjeto());
        System.out.println("\nMateriais utilizados no Projeto : " + projeto1.getIdProjeto());
        for (Material m : materiaisProjeto1) {
            System.out.println(m.getIdMaterial() + ": " + m.getNomeMaterial() + " - Quantidade: " + m.getQuantidade());
        }



    } catch (SQLException e) {
        e.printStackTrace();
    }


    }
}