package Controller;

import Dao.PaginaDAO;
import Modello.Pagina;
import Modello.Utente;
import Utilities.SessionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;

public class PaginaController {

    @FXML private TableView<Pagina> tableViewPagine;
    @FXML private TableColumn<Pagina, Integer> colIdPagina;
    @FXML private TableColumn<Pagina, String> colTitolo;
    @FXML private TableColumn<Pagina, String> colTesto;
    @FXML private TableColumn<Pagina, String> colUsernameAutore;
    @FXML private TableColumn<Pagina, String> colTitoloCollegata;

    @FXML private TextField txtTitolo;
    @FXML private TextArea txtTesto;
    @FXML private Label errorLabel;

    @FXML private Button btnAggiungi;
    @FXML private Button btnModifica;
    @FXML private Button btnElimina;

    private PaginaDAO paginaDAO = new PaginaDAO();
    private int selectedPaginaId = -1;

    @FXML
    public void initialize() {
        colIdPagina.setCellValueFactory(new PropertyValueFactory<>("idPagina"));
        colTitolo.setCellValueFactory(new PropertyValueFactory<>("titolo"));
        colTesto.setCellValueFactory(new PropertyValueFactory<>("testo"));
        colUsernameAutore.setCellValueFactory(new PropertyValueFactory<>("usernameAutore"));
        colTitoloCollegata.setCellValueFactory(new PropertyValueFactory<>("titoloCollegata"));

        loadPagine();

        tableViewPagine.setOnMouseClicked(e -> selezionaPagina());
    }

    private void loadPagine() {
        List<Pagina> lista = paginaDAO.getAllPagine();
        ObservableList<Pagina> obsList = FXCollections.observableArrayList(lista);
        tableViewPagine.setItems(obsList);
    }

    @FXML
    public void selezionaPagina() {
        Pagina p = tableViewPagine.getSelectionModel().getSelectedItem();
        if (p != null) {
            selectedPaginaId = p.getIdPagina();
            txtTitolo.setText(p.getTitolo());
            txtTesto.setText(p.getTesto());
            errorLabel.setText("");
        }
    }

    @FXML
    public void aggiungiPagina() {
        Utente utenteLoggato = SessionManager.getInstance().getUtenteLoggato();
        if (utenteLoggato == null) {
            errorLabel.setText("Nessun utente loggato.");
            return;
        }
        if (!utenteLoggato.getRuolo().equalsIgnoreCase("Scrittore")) {
            errorLabel.setText("Solo gli scrittori possono aggiungere pagine.");
            return;
        }
        String titolo = txtTitolo.getText().trim();
        String testo = txtTesto.getText().trim();
        if (titolo.isEmpty() || testo.isEmpty()) {
            errorLabel.setText("Titolo e testo sono obbligatori!");
            return;
        }
        Pagina nuovaPagina = new Pagina(0, titolo, testo, utenteLoggato.getId());
        boolean success = paginaDAO.insertPagina(nuovaPagina);
        if (success) {
            errorLabel.setText("Pagina aggiunta con successo!");
            clearFields();
            loadPagine();
        } else {
            errorLabel.setText("Errore durante l'inserimento della pagina.");
        }
    }

    @FXML
    public void modificaPagina() {
        if (selectedPaginaId == -1) {
            errorLabel.setText("Seleziona una pagina da modificare.");
            return;
        }
        Utente utenteLoggato = SessionManager.getInstance().getUtenteLoggato();
        Pagina p = paginaDAO.getPaginaById(selectedPaginaId);
        if (p == null) {
            errorLabel.setText("Pagina non trovata.");
            return;
        }
        if (utenteLoggato == null || p.getIdAutore() != utenteLoggato.getId()) {
            errorLabel.setText("Non sei il proprietario di questa pagina. Non puoi modificarla.");
            return;
        }
        String nuovoTitolo = txtTitolo.getText().trim();
        String nuovoTesto = txtTesto.getText().trim();
        if (nuovoTitolo.isEmpty() || nuovoTesto.isEmpty()) {
            errorLabel.setText("Titolo e testo non possono essere vuoti.");
            return;
        }
        Pagina paginaAggiornata = new Pagina(selectedPaginaId, nuovoTitolo, nuovoTesto, p.getIdAutore());
        boolean success = paginaDAO.updatePagina(paginaAggiornata);
        if (success) {
            errorLabel.setText("Pagina modificata con successo.");
            clearFields();
            loadPagine();
        } else {
            errorLabel.setText("Errore durante l'aggiornamento della pagina.");
        }
    }

    @FXML
    public void eliminaPagina() {
        if (selectedPaginaId == -1) {
            errorLabel.setText("Seleziona una pagina da eliminare.");
            return;
        }
        Utente utenteLoggato = SessionManager.getInstance().getUtenteLoggato();
        Pagina p = paginaDAO.getPaginaById(selectedPaginaId);
        if (p == null) {
            errorLabel.setText("Pagina non trovata.");
            return;
        }
        if (utenteLoggato == null || p.getIdAutore() != utenteLoggato.getId()) {
            errorLabel.setText("Non sei il proprietario di questa pagina. Non puoi eliminarla.");
            return;
        }
        boolean success = paginaDAO.deletePagina(selectedPaginaId, utenteLoggato.getId());
        if (success) {
            errorLabel.setText("Pagina eliminata con successo.");
            clearFields();
            loadPagine();
        } else {
            errorLabel.setText("Errore durante l'eliminazione della pagina.");
        }
    }

    private void clearFields() {
        txtTitolo.clear();
        txtTesto.clear();
        selectedPaginaId = -1;
    }
}










