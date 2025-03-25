package Dao;

import Modello.Modifica;
import Utilities.DBconnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModificaDAO {
    private Connection conn;

    public ModificaDAO() {
        try {
            conn = DBconnection.getConnection();
        } catch (SQLException e) {
            System.out.println("Errore durante la connessione al database in ModificaDAO: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // modifiche ordinate in ordine decrescente
    public List<Modifica> getAllModifiche() {
        List<Modifica> modifiche = new ArrayList<>();
        String query = "SELECT * FROM modifiche ORDER BY Data DESC";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Modifica modifica = new Modifica(
                        rs.getInt("ID_modifica"),
                        rs.getString("Testo"),
                        rs.getString("Stato"),
                        rs.getInt("ID_utente"),
                        rs.getInt("ID_pagina"),
                        rs.getTimestamp("Data")
                );
                modifiche.add(modifica);
            }
            System.out.println("Trovate " + modifiche.size() + " modifiche totali.");
        } catch (SQLException e) {
            System.out.println("Errore nel recupero delle modifiche: " + e.getMessage());
            e.printStackTrace();
        }
        return modifiche;
    }

    public boolean insertModifica(Modifica modifica) {
        String query = "INSERT INTO modifiche (Testo, Stato, ID_utente, ID_pagina) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, modifica.getTesto());
            stmt.setString(2, modifica.getStato());
            stmt.setInt(3, modifica.getIdUtente());
            stmt.setInt(4, modifica.getIdPagina());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Modifica inserita con successo!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Errore durante l'inserimento della modifica: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateModificaState(int idModifica, String newState) {
        String query = "UPDATE modifiche SET Stato = ? WHERE ID_modifica = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newState);
            stmt.setInt(2, idModifica);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Stato della modifica aggiornato con successo!");
                return true;
            } else {
                System.out.println("Nessuna modifica trovata con l'ID specificato.");
            }
        } catch (SQLException e) {
            System.out.println("Errore durante l'aggiornamento dello stato della modifica: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}




