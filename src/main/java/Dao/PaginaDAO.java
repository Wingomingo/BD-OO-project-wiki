package Dao;

import Modello.Pagina;
import Utilities.DBconnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaginaDAO {
    private Connection conn;

    public PaginaDAO() {
        try {
            conn = DBconnection.getConnection();
        } catch (SQLException e) {
            System.out.println("Errore nella connessione al database in PaginaDAO: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Recupera tutte le pagine unendole con utente per ottenere il Username dell’autore
    // e con collegamenti per aggregare i titoli delle pagine collegate.
    public List<Pagina> getAllPagine() {
        List<Pagina> pagine = new ArrayList<>();
        String query = "SELECT p.ID_pagina, p.Titolo, p.Testo, p.Data, p.ID_autore, " +
                "u.Username AS UsernameAutore, " +
                "GROUP_CONCAT(pc.Titolo SEPARATOR ', ') AS TitoloCollegata " +
                "FROM pagine p " +
                "JOIN utente u ON p.ID_autore = u.ID_utente " +
                "LEFT JOIN frasi f ON p.ID_pagina = f.ID_pagina " +
                "LEFT JOIN collegamenti c ON f.ID_frase = c.ID_frase_collegamento " +
                "LEFT JOIN pagine pc ON c.ID_pagina_destinazione = pc.ID_pagina " +
                "GROUP BY p.ID_pagina, p.Titolo, p.Testo, p.Data, p.ID_autore, u.Username " +
                "ORDER BY p.Data DESC";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Pagina pagina = new Pagina(
                        rs.getInt("ID_pagina"),
                        rs.getString("Titolo"),
                        rs.getString("Testo"),
                        rs.getTimestamp("Data"),
                        rs.getInt("ID_autore"),
                        rs.getString("UsernameAutore"),
                        rs.getString("TitoloCollegata")
                );
                pagine.add(pagina);
            }
            System.out.println("Trovate " + pagine.size() + " pagine totali.");
        } catch (SQLException e) {
            System.out.println("Errore nel recupero delle pagine: " + e.getMessage());
            e.printStackTrace();
        }
        return pagine;
    }

    // Inserisce una nuova pagina (disponibile solo se l'utente è uno scrittore)
    public boolean insertPagina(Pagina pagina) {
        String query = "INSERT INTO pagine (Titolo, Testo, ID_autore) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, pagina.getTitolo());
            stmt.setString(2, pagina.getTesto());
            stmt.setInt(3, pagina.getIdAutore());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Pagina inserita con successo!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Errore durante l'inserimento della pagina: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // Recupera una pagina dato il suo ID (senza il titolo collegato)
    public Pagina getPaginaById(int idPagina) {
        String query = "SELECT * FROM pagine WHERE ID_pagina = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idPagina);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Pagina(
                        rs.getInt("ID_pagina"),
                        rs.getString("Titolo"),
                        rs.getString("Testo"),
                        rs.getTimestamp("Data"),
                        rs.getInt("ID_autore")
                );
            }
        } catch (SQLException e) {
            System.out.println("Errore nel recupero della pagina: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // Recupera l'ID_autore della pagina dato il suo ID
    public int getAutoreByPaginaId(int idPagina) {
        String query = "SELECT ID_autore FROM pagine WHERE ID_pagina = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idPagina);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("ID_autore");
            }
        } catch (SQLException e) {
            System.out.println("Errore nel recupero dell'autore per la pagina: " + e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }

    // Metodo getIdPaginaById: restituisce l'ID della pagina se esiste, -1 altrimenti
    public int getIdPaginaById(int idPagina) {
        String query = "SELECT ID_pagina FROM pagine WHERE ID_pagina = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idPagina);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("ID_pagina");
            }
        } catch (SQLException e) {
            System.out.println("Errore nel recupero dell'ID della pagina: " + e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }

    // Aggiorna una pagina: modifica il Titolo e il Testo
    public boolean updatePagina(Pagina pagina) {
        String query = "UPDATE pagine SET Titolo = ?, Testo = ? WHERE ID_pagina = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, pagina.getTitolo());
            stmt.setString(2, pagina.getTesto());
            stmt.setInt(3, pagina.getIdPagina());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Pagina aggiornata con successo!");
                return true;
            } else {
                System.out.println("Nessuna pagina trovata con l'ID specificato.");
            }
        } catch (SQLException e) {
            System.out.println("Errore durante l'aggiornamento della pagina: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // Elimina una pagina solo se l'utente loggato (idAutore) è il proprietario della pagina
    public boolean deletePagina(int idPagina, int idAutore) {
        String query = "DELETE FROM pagine WHERE ID_pagina = ? AND ID_autore = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idPagina);
            stmt.setInt(2, idAutore);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Pagina eliminata con successo!");
                return true;
            } else {
                System.out.println("Eliminazione fallita: la pagina non esiste o non sei il proprietario.");
            }
        } catch (SQLException e) {
            System.out.println("Errore durante l'eliminazione della pagina: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}















