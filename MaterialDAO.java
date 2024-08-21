package DAO;

import ConexaoBD.ConexaoBD;
import Entity.Material;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAO {
    private Connection connection;

    public MaterialDAO()throws SQLException{
        this.connection = ConexaoBD.getInstancia().getConnection();
    }

    public void inserirMaterial (Material material)throws SQLException{
        String sql = "INSERT INTO material (Nome_Material, Quantidade) VALUES (?, ?)";
        try(PreparedStatement stmt = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, material.getNomeMaterial());
            stmt.setInt(2,material.getQuantidade());
            stmt.executeUpdate();
            try(ResultSet set = stmt.getGeneratedKeys()) {
                if(set.next()){
                    material.setIdMaterial(1);
                }
            }
        }
    }

    public void atualizarMaterial(Material material) throws SQLException {
        String sql = "UPDATE Material SET Nome_Material = ?, Quantidade = ? WHERE ID_Material = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, material.getNomeMaterial());
            stmt.setInt(2, material.getQuantidade());
            stmt.setInt(3, material.getIdMaterial());
            stmt.executeUpdate();
        }
    }

    public void excluirMaterial(int idMaterial) throws SQLException {
        String sql = "DELETE FROM Material WHERE ID_Material = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idMaterial);
            stmt.executeUpdate();
        }
    }

    public List<Material>listarMateriais()throws SQLException{
        List<Material>materiais = new ArrayList<>();
        String sql = "SELECT * FROM Material";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
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
