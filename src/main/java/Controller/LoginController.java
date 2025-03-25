package Controller;

import Dao.UtenteDAO;
import Modello.Utente;
import Utilities.SessionManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.example.wiki.App;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    private boolean richiedePassword = false;
    private UtenteDAO utenteDAO = new UtenteDAO();
    private App app;

    // Setter per passare l'istanza dell'app
    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    public void checkUser() {
        String username = usernameField.getText().trim();
        if (!username.isEmpty()) {
            Utente utente = utenteDAO.getUtenteByUsername(username);
            if (utente != null) {
                if (utente.getRuolo().equalsIgnoreCase("scrittore")) {
                    richiedePassword = true;
                    passwordField.setDisable(false);
                } else {
                    richiedePassword = false;
                    passwordField.setDisable(true);
                }
                errorLabel.setText("");
            } else {
                errorLabel.setText("Utente non trovato!");
                passwordField.setDisable(true);
            }
        }
    }

    @FXML
    public void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty()) {
            errorLabel.setText("Inserisci il nome utente!");
            return;
        }

        Utente utente = utenteDAO.getUtenteByUsername(username);
        if (utente == null) {
            errorLabel.setText("Utente non esistente!");
            return;
        }

        if (richiedePassword) {
            if (password.isEmpty()) {
                errorLabel.setText("Inserisci la password!");
                return;
            }
            if (!utente.getPassword().equals(password)) {
                errorLabel.setText("Password errata!");
                return;
            }
        }

        // salva l'utente loggato
        SessionManager.getInstance().setUtenteLoggato(utente);

        // apre il menu principale
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.close();
        app.showMenu();
    }

    @FXML
    public void openRegisterPage() {
        app.showRegister();
    }
}

