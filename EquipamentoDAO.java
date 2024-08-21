package DAO;

import ConexaoBD.ConexaoBD;
import Entity.Equipamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipamentoDAO {
    private Connection connection;

    public EquipamentoDAO()throws SQLException{
        this.connection = ConexaoBD.getInstancia().getConnection();
    }

    public void inserirEquipamento(Equipamento equipamento)throws SQLException{
        String sql = "INSERT INTO Equipamento (Nome_Equipamento, Tipo) VALUES (?, ?) ";
        try(PreparedStatement stmt = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, equipamento.getNomeEquipamento());
            stmt.setString(2,equipamento.getTipo());
            stmt.executeUpdate();
            try(ResultSet set = stmt.getGeneratedKeys()) {
                if(set.next()){
                    equipamento.setIdEquipamento(1);
                }
            }
        }
    }

    public void atualizarEquipamento(Equipamento equipamento) throws SQLException {
        String sql = "UPDATE Equipamento SET Nome_Equipamento = ?, Tipo = ? WHERE ID_Equipamento = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, equipamento.getNomeEquipamento());
            stmt.setString(2, equipamento.getTipo());
            stmt.setInt(3, equipamento.getIdEquipamento());
            stmt.executeUpdate();
        }
    }

    public void excluirEquipamento(int idEquipamento) throws SQLException {
        String sql = "DELETE FROM Equipamento WHERE ID_Equipamento = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idEquipamento);
            stmt.executeUpdate();
        }
    }

    public List<Equipamento>listarEquipamentos()throws SQLException{
        List<Equipamento>equipamentos = new ArrayList<>();
        String sql = "SELECT * FROM Equipamento";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Equipamento equipamento = new Equipamento();
                equipamento.setIdEquipamento(rs.getInt("ID_Equipamento"));
                equipamento.setNomeEquipamento(rs.getString("Nome_Equipamento"));
                equipamento.setTipo(rs.getString("Tipo"));
                equipamentos.add(equipamento);
            }
        }
        return equipamentos;
    }
}
