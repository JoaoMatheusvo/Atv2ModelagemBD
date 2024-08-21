package DAO;

import ConexaoBD.ConexaoBD;
import Entity.Engenheiro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EngenheiroDAO {
    private Connection connection;

    public EngenheiroDAO()throws SQLException{
        this.connection = ConexaoBD.getInstancia().getConnection();
    }

    public void inserirEngenheiro(Engenheiro engenheiro)throws SQLException{
        String sql = "INSERT INTO Engenheiro (Nome_Engenheiro, Especialidade) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, engenheiro.getNomeEngenheiro());
            stmt.setString(2, engenheiro.getEspecialidade());
            stmt.executeUpdate();

            try(ResultSet set = stmt.getGeneratedKeys()) {
                if(set.next()){
                    engenheiro.setIdEngenheiro(1);
                }
            }
        }
    }

    public void atualizarEngenheiro(Engenheiro engenheiro) throws SQLException {
        String sql = "UPDATE Engenheiro SET Nome_Engenheiro = ?, Especialidade = ? WHERE ID_Engenheiro = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, engenheiro.getNomeEngenheiro());
            stmt.setString(2, engenheiro.getEspecialidade());
            stmt.setInt(3, engenheiro.getIdEngenheiro());
            stmt.executeUpdate();
        }
    }

    public void excluirEngenheiro(int idEngenheiro) throws SQLException {
        String sql = "DELETE FROM Engenheiro WHERE ID_Engenheiro = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idEngenheiro);
            stmt.executeUpdate();
        }
    }

    public List<Engenheiro> listarEngenheiro()throws SQLException{
        List<Engenheiro> engenheiros = new ArrayList<>();
        String sql = "SELECT * FROM Engenheiro";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Engenheiro engenheiro = new Engenheiro();
                engenheiro.setIdEngenheiro(rs.getInt("ID_Engenheiro"));
                engenheiro.setNomeEngenheiro(rs.getString("Nome_Engenheiro"));
                engenheiro.setEspecialidade(rs.getString("Especialidade"));
                engenheiros.add(engenheiro);
            }
        }
        return engenheiros;
    }
}
