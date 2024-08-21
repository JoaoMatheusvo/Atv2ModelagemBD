package DAO;

import ConexaoBD.ConexaoBD;
import Entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDAO {
    private Connection connection;

    public ProjetoDAO()throws SQLException{
        this.connection = ConexaoBD.getInstancia().getConnection();
    }

    public void inserirProjeto(Projeto projeto) {
        String sql = "INSERT INTO Projeto (Nome_Projeto, Local, Data_Inicio, Data_Termino) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, projeto.getNomeProjeto());
            stmt.setString(2, projeto.getLocal());
            stmt.setDate(3, projeto.getDataIncio());
            stmt.setDate(4, projeto.getDataTerminio());
            stmt.executeUpdate();

            try (ResultSet set = stmt.getGeneratedKeys()) {
                if (set.next()) {
                    projeto.setIdProjeto(set.getInt(1)); // Atualiza com o ID gerado
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarProjeto(Projeto projeto) throws SQLException {
        String sql = "UPDATE Projeto SET Nome_Projeto = ?, Local = ?, Data_Inicio = ?, Data_Termino = ? WHERE ID_Projeto = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, projeto.getNomeProjeto());
            stmt.setString(2, projeto.getLocal());
            stmt.setDate(3, projeto.getDataIncio());
            stmt.setDate(4, projeto.getDataTerminio());
            stmt.setInt(5, projeto.getIdProjeto());
            stmt.executeUpdate();
        }
    }

    public void excluirProjeto(int idProjeto) throws SQLException {
        String sql = "DELETE FROM Projeto WHERE ID_Projeto = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            stmt.executeUpdate();
        }
    }




    public List<Projeto>lisarProjetos() throws SQLException{
        List<Projeto>projetos = new ArrayList<>();
        String sql = "SELECT * FROM Projeto";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Projeto projeto = new Projeto();
                projeto.setIdProjeto(rs.getInt("ID_Projeto"));
                projeto.setNomeProjeto(rs.getString("Nome_Projeto"));
                projeto.setLocal(rs.getString("Local"));
                projeto.setDataIncio(rs.getDate("Data_Inicio"));
                projeto.setDataTerminio(rs.getDate("Data_Termino"));
                projetos.add(projeto);
            }

        }
        return projetos;


    }

    // Métodos para Alocacao_Engenheiro
    public void alocarEngenheiro(int idProjeto, int idEngenheiro) throws SQLException {
        String sql = "INSERT INTO Alocacao_Engenheiro (ID_Projeto, ID_Engenheiro) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            stmt.setInt(2, idEngenheiro);
            stmt.executeUpdate();
        }
    }

    public void desalocarEngenheiro(int idProjeto, int idEngenheiro) throws SQLException {
        String sql = "DELETE FROM Alocacao_Engenheiro WHERE ID_Projeto = ? AND ID_Engenheiro = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            stmt.setInt(2, idEngenheiro);
            stmt.executeUpdate();
        }
    }

    // Métodos para Alocacao_Operario
    public void alocarOperario(int idProjeto, int idOperario) throws SQLException {
        String sql = "INSERT INTO Alocacao_Operario (ID_Projeto, ID_Operario) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            stmt.setInt(2, idOperario);
            stmt.executeUpdate();
        }
    }

    public void desalocarOperario(int idProjeto, int idOperario) throws SQLException {
        String sql = "DELETE FROM Alocacao_Operario WHERE ID_Projeto = ? AND ID_Operario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            stmt.setInt(2, idOperario);
            stmt.executeUpdate();
        }
    }

    // Métodos para Uso_Equipamento
    public void usarEquipamento(int idProjeto, int idEquipamento) throws SQLException {
        String sql = "INSERT INTO Uso_Equipamento (ID_Projeto, ID_Equipamento) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            stmt.setInt(2, idEquipamento);
            stmt.executeUpdate();
        }
    }

    public void desusarEquipamento(int idProjeto, int idEquipamento) throws SQLException {
        String sql = "DELETE FROM Uso_Equipamento WHERE ID_Projeto = ? AND ID_Equipamento = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            stmt.setInt(2, idEquipamento);
            stmt.executeUpdate();
        }
    }

    // Métodos para Consumo_Material
    public void consumirMaterial(int idProjeto, int idMaterial) throws SQLException {
        String sql = "INSERT INTO Consumo_Material (ID_Projeto, ID_Material) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            stmt.setInt(2, idMaterial);
            stmt.executeUpdate();
        }
    }

    public void desconsumirMaterial(int idProjeto, int idMaterial) throws SQLException {
        String sql = "DELETE FROM Consumo_Material WHERE ID_Projeto = ? AND ID_Material = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            stmt.setInt(2, idMaterial);
            stmt.executeUpdate();
        }
    }

    // Métodos para listar engenheiros e operários alocados em um projeto
    public List<Engenheiro> listarEngenheirosPorProjeto(int idProjeto) throws SQLException {
        List<Engenheiro> engenheiros = new ArrayList<>();
        String sql = "SELECT e.* FROM Engenheiro e " +
                "INNER JOIN Alocacao_Engenheiro ae ON e.ID_Engenheiro = ae.ID_Engenheiro " +
                "WHERE ae.ID_Projeto = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Engenheiro engenheiro = new Engenheiro();
                engenheiro.setIdEngenheiro(rs.getInt("ID_Engenheiro"));
                engenheiro.setNomeEngenheiro(rs.getString("Nome_Engenheiro"));
                engenheiro.setEspecialidade(rs.getString("Especialidade"));
                engenheiros.add(engenheiro);
            }
        }
        return engenheiros;
    }

    public List<Operario> listarOperariosPorProjeto(int idProjeto) throws SQLException {
        List<Operario> operarios = new ArrayList<>();
        String sql = "SELECT o.* FROM Operario o " +
                "INNER JOIN Alocacao_Operario ao ON o.ID_Operario = ao.ID_Operario " +
                "WHERE ao.ID_Projeto = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Operario operario = new Operario();
                operario.setIdOperario(rs.getInt("ID_Operario"));
                operario.setNomeOperario(rs.getString("Nome_Operario"));
                operario.setFuncao(rs.getString("Funcao"));
                operarios.add(operario);
            }
        }
        return operarios;
    }

    // Métodos para listar equipamentos e materiais utilizados em um projeto
    public List<Equipamento> listarEquipamentosPorProjeto(int idProjeto) throws SQLException {
        List<Equipamento> equipamentos = new ArrayList<>();
        String sql = "SELECT eq.* FROM Equipamento eq " +
                "INNER JOIN Uso_Equipamento ue ON eq.ID_Equipamento = ue.ID_Equipamento " +
                "WHERE ue.ID_Projeto = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Equipamento equipamento = new Equipamento();
                equipamento.setIdEquipamento(rs.getInt("ID_Equipamento"));
                equipamento.setNomeEquipamento(rs.getString("Nome_Equipamento"));
                equipamento.setTipo(rs.getString("Tipo"));
                equipamentos.add(equipamento);
            }
        }
        return equipamentos;
    }

    public List<Material> listarMateriaisPorProjeto(int idProjeto) throws SQLException {
        List<Material> materiais = new ArrayList<>();
        String sql = "SELECT m.* FROM Material m " +
                "INNER JOIN Consumo_Material cm ON m.ID_Material = cm.ID_Material " +
                "WHERE cm.ID_Projeto = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Material material = new Material();
                material.setIdMaterial(rs.getInt("ID_Material"));
                material.setNomeMaterial(rs.getString("Nome_Material"));
                material.setQuantidade(rs.getInt("Quantidade"));
                materiais.add(material);
            }
        }
        return materiais;
    }
}
