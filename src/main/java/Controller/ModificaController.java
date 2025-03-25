package Controller;

import Dao.ModificaDAO;
import Dao.PaginaDAO;
import Modello.Modifica;
import Modello.Utente;
import Utilities.SessionManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;
import java.util.stream.Collectors;

public class ModificaController {

    @FXML private TableView<Modifica> tableViewModifiche;
    @FXML private TableColumn<Modifica, Integer> colIdModifica;
    @FXML private TableColumn<Modifica, String> colTesto;
    @FXML private TableColumn<Modifica, String> colStato;
    @FXML private TableColumn<Modifica, Integer> colIdUtente;
    @FXML private TableColumn<Modifica, Integer> colIdPagina;
    @FXML private TableColumn<Modifica, String> colData;
    @FXML private TableColumn<Modifica, String> colProposta; // Nuova colonna "Proposta"
    @FXML private Label errorLabel;

    @FXML private TextField txtTestoNuovo;
    @FXML private TextField txtIdPaginaNuovo;
    @FXML private Button btnProponi;

    private ModificaDAO modificaDAO = new ModificaDAO();
    private PaginaDAO paginaDAO = new PaginaDAO(); // Per verificare se l'utente loggato è il proprietario della pagina

    @FXML
    public void initialize() {
        colIdModifica.setCellValueFactory(new PropertyValueFactory<>("idModifica"));
        colTesto.setCellValueFactory(new PropertyValueFactory<>("testo"));
        colStato.setCellValueFactory(new PropertyValueFactory<>("stato"));
        colIdUtente.setCellValueFactory(new PropertyValueFactory<>("idUtente"));
        colIdPagina.setCellValueFactory(new PropertyValueFactory<>("idPagina"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));

        // configurazione della colonna proposta a "a me" se la modifica è destinata all'utente loggato altrimenti "da me" se l'utente loggato ha proposto la modifica
        colProposta.setCellValueFactory(cellData -> {
            Modifica mod = cellData.getValue();
            Utente logged = SessionManager.getInstance().getUtenteLoggato();
            String proposta = "";
            if (logged != null) {
                if (mod.getIdUtente() == logged.getId()) {
                    proposta = "da me";
                } else {
                    int idAutorePagina = paginaDAO.getAutoreByPaginaId(mod.getIdPagina());
                    proposta = (idAutorePagina == logged.getId()) ? "a me" : "";
                }
            }
            return new SimpleStringProperty(proposta);
        });

        colTesto.setCellFactory(column -> {
            TableCell<Modifica, String> cell = new TableCell<>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setTooltip(null);
                    } else {
                        String truncated = item.length() > 50 ? item.substring(0, 50) + "..." : item;
                        setText(truncated);
                        setTooltip(new Tooltip(item));
                    }
                }
            };
            return cell;
        });

        tableViewModifiche.setRowFactory(tv -> {
            TableRow<Modifica> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    Modifica mod = row.getItem();
                    showFullText(mod);
                }
            });
            return row;
        });

        loadModifiche();
    }

    private void loadModifiche() {
        List<Modifica> allModifiche = modificaDAO.getAllModifiche();
        Utente logged = SessionManager.getInstance().getUtenteLoggato();
        if (logged != null) {
            // Filtra per modifiche proposte da me o proposte a me (cioè, dove il creatore della pagina è l'utente loggato)
            allModifiche = allModifiche.stream()
                    .filter(mod -> mod.getIdUtente() == logged.getId() ||
                            paginaDAO.getAutoreByPaginaId(mod.getIdPagina()) == logged.getId())
                    .collect(Collectors.toList());
        }
        ObservableList<Modifica> obsList = FXCollections.observableArrayList(allModifiche);
        tableViewModifiche.setItems(obsList);
    }

    private void showFullText(Modifica mod) {
        Stage stage = new Stage();
        stage.setTitle("Dettaglio Modifica - ID: " + mod.getIdModifica());
        TextArea textArea = new TextArea(mod.getTesto());
        textArea.setWrapText(true);
        textArea.setEditable(false);
        VBox vbox = new VBox(textArea);
        Scene scene = new Scene(vbox, 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    // permette di proporre una modifica se l utente loggato è uno scrittore
    @FXML
    public void proponiModifica() {
        Utente utenteLoggato = SessionManager.getInstance().getUtenteLoggato();
        if (utenteLoggato == null) {
            errorLabel.setText("Nessun utente loggato.");
            return;
        }
        if (!utenteLoggato.getRuolo().equalsIgnoreCase("Scrittore")) {
            errorLabel.setText("Solo gli scrittori possono proporre modifiche.");
            return;
        }
        String testoNuovo = txtTestoNuovo.getText().trim();
        String idPaginaStr = txtIdPaginaNuovo.getText().trim();
        if (testoNuovo.isEmpty() || idPaginaStr.isEmpty()) {
            errorLabel.setText("Inserisci il testo e l'ID della pagina.");
            return;
        }
        int idPagina;
        try {
            idPagina = Integer.parseInt(idPaginaStr);
        } catch (NumberFormatException e) {
            errorLabel.setText("ID della pagina non valido.");
            return;
        }
        Modifica nuovaModifica = new Modifica(0, testoNuovo, "Proposta", utenteLoggato.getId(), idPagina, null);
        boolean success = modificaDAO.insertModifica(nuovaModifica);
        if (success) {
            errorLabel.setText("Modifica proposta con successo.");
            txtTestoNuovo.clear();
            txtIdPaginaNuovo.clear();
            loadModifiche();
        } else {
            errorLabel.setText("Errore durante la proposta della modifica.");
        }
    }

    // permette di accettare una notifica solo se l'utente loggato è il proprietario della pagina
    @FXML
    public void approvaModifica() {
        Modifica mod = tableViewModifiche.getSelectionModel().getSelectedItem();
        if (mod == null) {
            errorLabel.setText("Seleziona una modifica dalla tabella.");
            return;
        }
        Utente utenteLoggato = SessionManager.getInstance().getUtenteLoggato();
        if (utenteLoggato == null) {
            errorLabel.setText("Nessun utente loggato.");
            return;
        }
        int idAutorePagina = paginaDAO.getAutoreByPaginaId(mod.getIdPagina());
        if (idAutorePagina != utenteLoggato.getId()) {
            errorLabel.setText("Non sei il proprietario della pagina. Non puoi approvare questa modifica.");
            return;
        }
        boolean success = modificaDAO.updateModificaState(mod.getIdModifica(), "Approvata");
        if (success) {
            errorLabel.setText("Modifica approvata con successo.");
            loadModifiche();
        } else {
            errorLabel.setText("Errore nell'approvazione della modifica.");
        }
    }

    // permette di rifiutare una notifica solo se l'utente loggato è il proprietario della pagina
    @FXML
    public void rifiutaModifica() {
        Modifica mod = tableViewModifiche.getSelectionModel().getSelectedItem();
        if (mod == null) {
            errorLabel.setText("Seleziona una modifica dalla tabella.");
            return;
        }
        Utente utenteLoggato = SessionManager.getInstance().getUtenteLoggato();
        if (utenteLoggato == null) {
            errorLabel.setText("Nessun utente loggato.");
            return;
        }
        int idAutorePagina = paginaDAO.getAutoreByPaginaId(mod.getIdPagina());
        if (idAutorePagina != utenteLoggato.getId()) {
            errorLabel.setText("Non sei il proprietario della pagina. Non puoi rifiutare questa modifica.");
            return;
        }
        boolean success = modificaDAO.updateModificaState(mod.getIdModifica(), "Rifiutata");
        if (success) {
            errorLabel.setText("Modifica rifiutata con successo.");
            loadModifiche();
        } else {
            errorLabel.setText("Errore nel rifiuto della modifica.");
        }
    }
}




