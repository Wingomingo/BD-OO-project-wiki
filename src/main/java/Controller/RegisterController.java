package Controller;

import Dao.UtenteDAO;
import Modello.Utente;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.wiki.App;

public class RegisterController {

    @FXML private TextField txtNome;
    @FXML private TextField txtUsername;
    @FXML private TextField txtEmail;
    @FXML private PasswordField txtPassword;
    @FXML private ComboBox<String> cmbRuolo;

    private UtenteDAO utenteDAO = new UtenteDAO();
    private App app;

    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    public void handleRegister() {
        String nome = txtNome.getText().trim();
        String username = txtUsername.getText().trim();
        String email = txtEmail.getText().trim();
        String password = txtPassword.getText().trim();
        String ruolo = cmbRuolo.getValue();

        // obbliga nome, username e ruolo a non essere null
        if (nome.isEmpty() || username.isEmpty() || ruolo == null) {
            mostraMessaggio("Errore", "Nome, username e ruolo sono obbligatori!");
            return;
        }

        // da scrittore la password e la mail sono obbligatorie
        if (ruolo.equalsIgnoreCase("scrittore")) {
            if (email.isEmpty() || password.isEmpty()) {
                mostraMessaggio("Errore", "Per uno scrittore, email e password sono obbligatori!");
                return;
            }
        }

        Utente nuovoUtente = new Utente(0, nome, username, email, password, ruolo);
        utenteDAO.insertUtente(nuovoUtente);

        mostraMessaggio("Successo", "Registrazione completata con successo!");
        closeRegister();
    }

    @FXML
    public void closeRegister() {
        app.showLogin();
    }

    private void mostraMessaggio(String titolo, String messaggio) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titolo);
        alert.setHeaderText(null);
        alert.setContentText(messaggio);
        alert.showAndWait();
    }
}


