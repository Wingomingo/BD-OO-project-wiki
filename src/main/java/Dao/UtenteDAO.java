package Dao;

import Modello.Utente;
import Utilities.DBconnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAO {
    private Connection conn;

    public UtenteDAO() {
        try {
            conn = DBconnection.getConnection();
        } catch (SQLException e) {
            System.out.println("Errore durante la connessione al database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Utente> getAllUsers() {
        List<Utente> utenti = new ArrayList<>();
        String query = "SELECT * FROM utente";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                utenti.add(new Utente(
                        rs.getInt("ID_utente"),
                        rs.getString("nome"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("ruolo")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Errore nel recupero degli utenti: " + e.getMessage());
            e.printStackTrace();
        }
        return utenti;
    }

    public Utente getUtenteByUsername(String username) {
        String query = "SELECT * FROM utente WHERE BINARY username = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username.trim());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Utente(
                        rs.getInt("ID_utente"),
                        rs.getString("nome"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("ruolo")
                );
            }
        } catch (SQLException e) {
            System.out.println("Errore nel recupero dell'utente: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public Utente getUtenteById(int id) {
        String query = "SELECT * FROM utente WHERE ID_utente = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Utente(
                        rs.getInt("ID_utente"),
                        rs.getString("nome"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("ruolo")
                );
            }
        } catch (SQLException e) {
            System.out.println("Errore nel recupero dell'utente per ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void insertUtente(Utente utente) {
        String query = "INSERT INTO utente (nome, username, email, password, ruolo) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, utente.getNome());
            stmt.setString(2, utente.getUsername());

            // nel caso in cui il ruolo è visitatore, si forzano password e email a null
            if (utente.getRuolo().equalsIgnoreCase("visitatore") || utente.getRuolo().equalsIgnoreCase("lettore")) {
                stmt.setNull(3, Types.VARCHAR);
                stmt.setNull(4, Types.VARCHAR);
            } else {
                // Altrimenti, se i campi sono vuoti, impostali a null
                String email = utente.getEmail();
                if (email == null || email.trim().isEmpty()) {
                    stmt.setNull(3, Types.VARCHAR);
                } else {
                    stmt.setString(3, email);
                }
                String password = utente.getPassword();
                if (password == null || password.trim().isEmpty()) {
                    stmt.setNull(4, Types.VARCHAR);
                } else {
                    stmt.setString(4, password);
                }
            }

            String ruolo = utente.getRuolo();
            if (ruolo.equalsIgnoreCase("scrittore")) {
                ruolo = "Scrittore";
            } else if (ruolo.equalsIgnoreCase("visitatore") || ruolo.equalsIgnoreCase("lettore")) {
                ruolo = "Visitatore";
            }
            stmt.setString(5, ruolo);

            stmt.executeUpdate();
            System.out.println("Utente inserito con successo!");
        } catch (SQLException e) {
            System.out.println("Errore durante l'inserimento dell'utente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteUtente(int id) {
        String query = "DELETE FROM utente WHERE ID_utente = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Utente eliminato con successo!");
        } catch (SQLException e) {
            System.out.println("Errore durante l'eliminazione dell'utente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateUtente(Utente utente) {
        String query = "UPDATE utente SET nome = ?, username = ?, email = ?, password = ?, ruolo = ? WHERE ID_utente = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, utente.getNome());
            stmt.setString(2, utente.getUsername());

            // Se il ruolo è Visitatore, imposta email e password a null
            if (utente.getRuolo().equalsIgnoreCase("visitatore") || utente.getRuolo().equalsIgnoreCase("lettore")) {
                stmt.setNull(3, Types.VARCHAR);
                stmt.setNull(4, Types.VARCHAR);
            } else {
                String email = utente.getEmail();
                if (email == null || email.trim().isEmpty()) {
                    stmt.setNull(3, Types.VARCHAR);
                } else {
                    stmt.setString(3, email);
                }
                String password = utente.getPassword();
                if (password == null || password.trim().isEmpty()) {
                    stmt.setNull(4, Types.VARCHAR);
                } else {
                    stmt.setString(4, password);
                }
            }

            String ruolo = utente.getRuolo();
            if (ruolo.equalsIgnoreCase("scrittore")) {
                ruolo = "Scrittore";
            } else if (ruolo.equalsIgnoreCase("visitatore") || ruolo.equalsIgnoreCase("lettore")) {
                ruolo = "Visitatore";
            }
            stmt.setString(5, ruolo);
            stmt.setInt(6, utente.getId());

            stmt.executeUpdate();
            System.out.println("Utente aggiornato con successo!");
        } catch (SQLException e) {
            System.out.println("Errore durante l'aggiornamento dell'utente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}




