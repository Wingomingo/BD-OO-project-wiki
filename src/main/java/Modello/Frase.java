package Modello;

public class Frase {
    private int id;
    private String frase;
    private boolean collegamento;
    private int idPagina;
    private int idPaginaCollegata;

    public Frase(int id, String frase, boolean collegamento, int idPagina, int idPaginaCollegata) {
        this.id = id;
        this.frase = frase;
        this.collegamento = collegamento;
        this.idPagina = idPagina;
        this.idPaginaCollegata = idPaginaCollegata;
    }

    // Getter e Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFrase() { return frase; }
    public void setFrase(String frase) { this.frase = frase; }

    public boolean isCollegamento() { return collegamento; }
    public void setCollegamento(boolean collegamento) { this.collegamento = collegamento; }

    public int getIdPagina() { return idPagina; }
    public void setIdPagina(int idPagina) { this.idPagina = idPagina; }

    public int getIdPaginaCollegata() { return idPaginaCollegata; }
    public void setIdPaginaCollegata(int idPaginaCollegata) { this.idPaginaCollegata = idPaginaCollegata; }
}

