package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Optional;
import Modello.Utente;
import Utilities.SessionManager;

public class MenuController {

    @FXML private Label lblUserInfo;

    private void apriFinestra(String fxmlFile, String titolo) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Fxml/" + fxmlFile));
            Stage stage = new Stage();
            stage.setTitle(titolo);
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // per accedere a gestione utenti serve una password amministrativa in modo da non far entrare chiunque in quella sezione delicata e non mostrare dati sensibili
    @FXML
    public void apriUtenti() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Accesso Amministratore");
        dialog.setHeaderText("Inserisci password amministratore");
        dialog.setContentText("Password:");
        Optional<String> result = dialog.showAndWait();
        if(result.isPresent() && result.get().equals("admin1")){
            apriFinestra("Utente.fxml", "Gestione Utenti");
        } else {
            System.out.println("Password amministrativa errata o annullata.");
        }
    }

    @FXML
    public void apriPagine() {
        apriFinestra("Pagina.fxml", "Gestione Pagine");
    }

    @FXML
    public void apriModifiche() {
        apriFinestra("Modifica.fxml", "Gestione Modifiche");
    }

    @FXML
    public void apriVersioni() {
        apriFinestra("Versione.fxml", "Gestione Versioni");
    }

    @FXML
    public void apriNotifiche() {
        apriFinestra("Notifica.fxml", "Gestione Notifiche");
    }

    @FXML
    public void initialize() {
        Utente utente = SessionManager.getInstance().getUtenteLoggato();
        if (utente != null) {
            lblUserInfo.setText("Bentornato " + utente.getUsername() + " (ID: " + utente.getId() + ")");
        } else {
            lblUserInfo.setText("Nessun utente loggato");
        }
    }
}





