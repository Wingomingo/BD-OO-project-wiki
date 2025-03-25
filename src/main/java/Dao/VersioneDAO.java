package Dao;

import Modello.Versione;
import Utilities.DBconnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VersioneDAO {
    private Connection conn;

    public VersioneDAO() {
        try {
            conn = DBconnection.getConnection();
        } catch (SQLException e) {
            System.out.println("Errore durante la connessione al database in VersioneDAO: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // versioni ordinate in ordine decrescente
    public List<Versione> getAllVersioni() {
        List<Versione> versioni = new ArrayList<>();
        String query = "SELECT * FROM versioni ORDER BY Data DESC";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Versione versione = new Versione(
                        rs.getInt("ID_versione"),
                        rs.getString("Testo"),
                        rs.getTimestamp("Data"),
                        rs.getInt("Numero"),
                        rs.getInt("ID_pagina"),
                        rs.getInt("ID_autore"),
                        rs.getInt("ID_utente_modifica")
                );
                versioni.add(versione);
            }
            System.out.println("Trovate " + versioni.size() + " versioni totali.");
        } catch (SQLException e) {
            System.out.println("Errore nel recupero delle versioni: " + e.getMessage());
            e.printStackTrace();
        }
        return versioni;
    }

    // Inserimento della nuova versione
    public boolean insertVersione(Versione versione) {
        String query = "INSERT INTO versioni (Testo, Data, Numero, ID_pagina, ID_autore, ID_utente_modifica) " +
                "VALUES (?, NOW(), ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, versione.getTesto());
            stmt.setInt(2, versione.getNumero());
            stmt.setInt(3, versione.getIdPagina());
            stmt.setInt(4, versione.getIdAutore());
            stmt.setInt(5, versione.getIdUtenteModifica());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Versione inserita con successo!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Errore durante l'inserimento della versione: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // Aggiorna una versione, ad esempio per aggiornare il testo e il numero della versione
    public boolean updateVersione(Versione versione) {
        String query = "UPDATE versioni SET Testo = ?, Numero = ? WHERE ID_versione = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, versione.getTesto());
            stmt.setInt(2, versione.getNumero());
            stmt.setInt(3, versione.getIdVersione());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Versione aggiornata con successo!");
                return true;
            } else {
                System.out.println("Nessuna versione trovata con l'ID specificato.");
            }
        } catch (SQLException e) {
            System.out.println("Errore durante l'aggiornamento della versione: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // Recupera una versione dato il suo ID
    public Versione getVersioneById(int idVersione) {
        String query = "SELECT * FROM versioni WHERE ID_versione = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idVersione);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Versione(
                        rs.getInt("ID_versione"),
                        rs.getString("Testo"),
                        rs.getTimestamp("Data"),
                        rs.getInt("Numero"),
                        rs.getInt("ID_pagina"),
                        rs.getInt("ID_autore"),
                        rs.getInt("ID_utente_modifica")
                );
            }
        } catch (SQLException e) {
            System.out.println("Errore nel recupero della versione per ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}







