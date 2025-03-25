package Controller;

import Dao.UtenteDAO;
import Modello.Utente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;

public class UtenteController {
    @FXML private TableView<Utente> tableViewUtenti;
    @FXML private TableColumn<Utente, Integer> colId;
    @FXML private TableColumn<Utente, String> colNome;
    @FXML private TableColumn<Utente, String> colUsername;
    @FXML private TableColumn<Utente, String> colEmail;
    @FXML private TableColumn<Utente, String> colPassword;
    @FXML private TableColumn<Utente, String> colRuolo;

    @FXML private TextField txtNome;
    @FXML private TextField txtUsername;
    @FXML private TextField txtEmail;
    @FXML private TextField txtPassword;
    @FXML private ComboBox<String> cmbRuolo;
    @FXML private Button btnAggiorna;
    @FXML private Button btnElimina;
    @FXML private Label errorLabel;

    private UtenteDAO utenteDAO = new UtenteDAO();
    private int selectedUserId = -1;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colRuolo.setCellValueFactory(new PropertyValueFactory<>("ruolo"));

        // ruoli disponibili
        cmbRuolo.getItems().addAll("Scrittore", "Visitatore");

        caricaUtenti();

        btnAggiorna.setDisable(true);
        btnElimina.setDisable(true);
    }

    private void caricaUtenti() {
        List<Utente> utenti = utenteDAO.getAllUsers();
        ObservableList<Utente> obsList = FXCollections.observableArrayList(utenti);
        tableViewUtenti.setItems(obsList);
    }

    @FXML
    public void selezionaUtente() {
        Utente u = tableViewUtenti.getSelectionModel().getSelectedItem();
        if (u != null) {
            selectedUserId = u.getId();
            txtNome.setText(u.getNome());
            txtUsername.setText(u.getUsername());
            txtEmail.setText(u.getEmail());
            txtPassword.setText(u.getPassword());
            cmbRuolo.setValue(u.getRuolo());
            btnAggiorna.setDisable(false);
            btnElimina.setDisable(false);
            errorLabel.setText("");
        }
    }

    @FXML
    public void aggiornaUtente() {
        if (selectedUserId == -1) {
            errorLabel.setText("Seleziona un utente da aggiornare!");
            return;
        }
        String nome = txtNome.getText().trim();
        String username = txtUsername.getText().trim();
        String email = txtEmail.getText().trim();
        String password = txtPassword.getText().trim();
        String ruolo = cmbRuolo.getValue();
        if (nome.isEmpty() || username.isEmpty() || ruolo == null) {
            errorLabel.setText("Campi obbligatori mancanti!");
            return;
        }
        Utente u = new Utente(selectedUserId, nome, username, email, password, ruolo);
        utenteDAO.updateUtente(u);
        errorLabel.setText("Utente aggiornato con successo!");
        caricaUtenti();
        clearFields();
    }

    @FXML
    public void eliminaUtente() {
        if (selectedUserId == -1) {
            errorLabel.setText("Seleziona un utente da eliminare!");
            return;
        }
        utenteDAO.deleteUtente(selectedUserId);
        errorLabel.setText("Utente eliminato con successo!");
        caricaUtenti();
        clearFields();
    }

    private void clearFields() {
        txtNome.clear();
        txtUsername.clear();
        txtEmail.clear();
        txtPassword.clear();
        cmbRuolo.setValue(null);
        selectedUserId = -1;
        btnAggiorna.setDisable(true);
        btnElimina.setDisable(true);
    }
}





