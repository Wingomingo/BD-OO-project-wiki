package Modello;

import java.sql.Timestamp;

public class Notifica {
    private int idNotifica;
    private Timestamp data;
    private boolean letta;
    private int idModificatore;
    private int idAutore;
    private int idCreatorePagina;

    public Notifica(int idNotifica, Timestamp data, boolean letta, int idModificatore, int idAutore, int idCreatorePagina) {
        this.idNotifica = idNotifica;
        this.data = data;
        this.letta = letta;
        this.idModificatore = idModificatore;
        this.idAutore = idAutore;
        this.idCreatorePagina = idCreatorePagina;
    }

    // Getters e Setters

    public int getIdNotifica() {
        return idNotifica;
    }

    public void setIdNotifica(int idNotifica) {
        this.idNotifica = idNotifica;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public boolean isLetta() {
        return letta;
    }

    public void setLetta(boolean letta) {
        this.letta = letta;
    }

    public int getIdModificatore() {
        return idModificatore;
    }

    public void setIdModificatore(int idModificatore) {
        this.idModificatore = idModificatore;
    }

    public int getIdAutore() {
        return idAutore;
    }

    public void setIdAutore(int idAutore) {
        this.idAutore = idAutore;
    }

    public int getIdCreatorePagina() {
        return idCreatorePagina;
    }

    public void setIdCreatorePagina(int idCreatorePagina) {
        this.idCreatorePagina = idCreatorePagina;
    }
}


