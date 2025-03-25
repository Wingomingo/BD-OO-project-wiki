package Modello;

public class Utente {
    private int id;
    private String nome;
    private String username;
    private String email;
    private String password;
    private String ruolo;

    public Utente(int id, String nome, String username, String email, String password, String ruolo) {
        this.id = id;
        this.nome = nome;
        this.username = username;
        this.email = email;
        this.password = password;
        this.ruolo = ruolo;
    }

    // Getter e Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRuolo() { return ruolo; }
    public void setRuolo(String ruolo) { this.ruolo = ruolo; }
}