package DAO;

import ConexaoBD.ConexaoBD;
import Entity.Operario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperarioDAO {
    private Connection connection;

    public OperarioDAO ()throws SQLException{
        this.connection = ConexaoBD.getInstancia().getConnection();
    }

    public void inserirOperario(Operario operario)throws SQLException{
        String sql = "INSERT INTO Operario (Nome_Operario, Funcao) VALUES (?, ?)";
        try(PreparedStatement stmt = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS)){
            stmt.setString(1,operario.getNomeOperario());
            stmt.setString(2, operario.getFuncao());
            stmt.executeUpdate();
            try(ResultSet set = stmt.getGeneratedKeys()) {
                if(set.next()){
                    operario.setIdOperario(1);
                }
            }
        }

    }

    public void atualizarOperario(Operario operario) throws SQLException {
        String sql = "UPDATE Operario SET Nome_Operario = ?, Funcao = ? WHERE ID_Operario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, operario.getNomeOperario());
            stmt.setString(2, operario.getFuncao());
            stmt.setInt(3, operario.getIdOperario());
            stmt.executeUpdate();
        }
    }

    public void excluirOperario(int idOperario) throws SQLException {
        String sql = "DELETE FROM Operario WHERE ID_Operario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idOperario);
            stmt.executeUpdate();
        }
    }

    public List<Operario>listarOperarios()throws SQLException{
        List<Operario>operarios = new ArrayList<>();
        String sql = "SELECT * FROM Operario";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Operario operario = new Operario();
                operario.setIdOperario(rs.getInt("ID_Operario"));
                operario.setNomeOperario(rs.getString("Nome_Operario"));
                operario.setFuncao(rs.getString("Funcao"));
                operarios.add(operario);
            }
        }
        return operarios;
    }
}
