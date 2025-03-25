package Modello;

import java.sql.Timestamp;

public class Versione {

    private int idVersione;
    private String testo;
    private Timestamp data;
    private int numero;
    private int idPagina;
    private int idAutore;
    private int idUtenteModifica;


    public Versione(int idVersione, String testo, Timestamp data, int numero, int idPagina, int idAutore, int idUtenteModifica) {
        this.idVersione = idVersione;
        this.testo = testo;
        this.data = data;
        this.numero = numero;
        this.idPagina = idPagina;
        this.idAutore = idAutore;
        this.idUtenteModifica = idUtenteModifica;
    }



    public int getIdVersione() {
        return idVersione;
    }

    public void setIdVersione(int idVersione) {
        this.idVersione = idVersione;
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

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getIdPagina() {
        return idPagina;
    }

    public void setIdPagina(int idPagina) {
        this.idPagina = idPagina;
    }

    public int getIdAutore() {
        return idAutore;
    }

    public void setIdAutore(int idAutore) {
        this.idAutore = idAutore;
    }

    public int getIdUtenteModifica() {
        return idUtenteModifica;
    }

    public void setIdUtenteModifica(int idUtenteModifica) {
        this.idUtenteModifica = idUtenteModifica;
    }
}



