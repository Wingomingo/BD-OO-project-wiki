package Dao;

import Modello.Collegamento;
import Utilities.DBconnection;
import Utilities.DatabaseException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CollegamentoDAO {
    public List<Collegamento> getCollegamentiByFrase(int idFrase) {
        List<Collegamento> collegamenti = new ArrayList<>();
        String query = "SELECT * FROM collegamenti WHERE ID_frase_collegamento = ?";

        try (Connection conn = DBconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idFrase);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Collegamento c = new Collegamento(
                        rs.getInt("ID_collegamento"),
                        rs.getInt("ID_frase_collegamento"),
                        rs.getInt("ID_pagina_destinazione")
                );
                collegamenti.add(c);
            }
        } catch (SQLException e) {
            DatabaseException.handleSQLException(e);
        }
        return collegamenti;
    }
}

