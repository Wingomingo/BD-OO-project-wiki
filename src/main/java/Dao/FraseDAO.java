package Dao;

import Modello.Frase;
import Utilities.DBconnection;
import Utilities.DatabaseException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FraseDAO {
    public List<Frase> getFrasiByPagina(int idPagina) {
        List<Frase> frasi = new ArrayList<>();
        String query = "SELECT * FROM frasi WHERE ID_pagina = ?";

        try (Connection conn = DBconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idPagina);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Frase f = new Frase(
                        rs.getInt("ID_frase"),
                        rs.getString("Frase"),
                        rs.getBoolean("Collegamento"),
                        rs.getInt("ID_pagina"),
                        rs.getInt("ID_pagina_collegata")
                );
                frasi.add(f);
            }
        } catch (SQLException e) {
            DatabaseException.handleSQLException(e);
        }
        return frasi;
    }
}

