package Modello;

import java.sql.Timestamp;

public class Modifica {
    private int idModifica;
    private String testo;
    private String stato;
    private int idUtente;
    private int idPagina;
    private Timestamp data;

    public Modifica(int idModifica, String testo, String stato, int idUtente, int idPagina, Timestamp data) {
        this.idModifica = idModifica;
        this.testo = testo;
        this.stato = stato;
        this.idUtente = idUtente;
        this.idPagina = idPagina;
        this.data = data;
    }

    // Getters e Setters
    public int getIdModifica() {
        return idModifica;
    }

    public void setIdModifica(int idModifica) {
        this.idModifica = idModifica;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public int getIdPagina() {
        return idPagina;
    }

    public void setIdPagina(int idPagina) {
        this.idPagina = idPagina;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ID: " + idModifica + " | Stato: " + stato + " | Testo: " + testo;
    }
}


