package Modello;

public class Collegamento {
    private int id;
    private int idFraseCollegamento;
    private int idPaginaDestinazione;

    public Collegamento(int id, int idFraseCollegamento, int idPaginaDestinazione) {
        this.id = id;
        this.idFraseCollegamento = idFraseCollegamento;
        this.idPaginaDestinazione = idPaginaDestinazione;
    }

    // Getter e Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdFraseCollegamento() { return idFraseCollegamento; }
    public void setIdFraseCollegamento(int idFraseCollegamento) { this.idFraseCollegamento = idFraseCollegamento; }

    public int getIdPaginaDestinazione() { return idPaginaDestinazione; }
    public void setIdPaginaDestinazione(int idPaginaDestinazione) { this.idPaginaDestinazione = idPaginaDestinazione; }
}

