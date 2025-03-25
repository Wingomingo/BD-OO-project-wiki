package Modello;

import java.sql.Timestamp;

public class Pagina {
    private int idPagina;
    private String titolo;
    private String testo;
    private Timestamp data;
    private int idAutore;
    private String usernameAutore;   // si ottiene dalla join con utente
    private String titoloCollegata;

  // costruttore con 7 parametri
    public Pagina(int idPagina, String titolo, String testo, Timestamp data, int idAutore, String usernameAutore, String titoloCollegata) {
        this.idPagina = idPagina;
        this.titolo = titolo;
        this.testo = testo;
        this.data = data;
        this.idAutore = idAutore;
        this.usernameAutore = usernameAutore;
        this.titoloCollegata = titoloCollegata;
    }

    // costruttore con 5 parametri
    public Pagina(int idPagina, String titolo, String testo, Timestamp data, int idAutore) {
        this(idPagina, titolo, testo, data, idAutore, null, null);
    }

    // costruttore con 4 parametri
    public Pagina(int idPagina, String titolo, String testo, int idAutore) {
        this(idPagina, titolo, testo, null, idAutore, null, null);
    }


    public int getIdPagina() {
        return idPagina;
    }

    public void setIdPagina(int idPagina) {
        this.idPagina = idPagina;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public int getIdAutore() {
        return idAutore;
    }

    public void setIdAutore(int idAutore) {
        this.idAutore = idAutore;
    }

    public String getUsernameAutore() {
        return usernameAutore;
    }

    public void setUsernameAutore(String usernameAutore) {
        this.usernameAutore = usernameAutore;
    }

    public String getTitoloCollegata() {
        return titoloCollegata;
    }

    public void setTitoloCollegata(String titoloCollegata) {
        this.titoloCollegata = titoloCollegata;
    }
}







