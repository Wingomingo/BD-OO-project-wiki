package Utilities;

import Modello.Utente;

public class SessionManager {
    private static SessionManager instance;
    private Utente utenteLoggato;

    private SessionManager() { }

    public static SessionManager getInstance() {
        if(instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void setUtenteLoggato(Utente utente) {
        this.utenteLoggato = utente;
    }

    public Utente getUtenteLoggato() {
        return utenteLoggato;
    }
}



