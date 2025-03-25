package Controller;

import Dao.VersioneDAO;
import Modello.Versione;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

public class VersioneController {

    @FXML private TableView<Versione> tableViewVersioni;
    @FXML private TableColumn<Versione, Integer> colIdVersione;
    @FXML private TableColumn<Versione, String> colTesto;
    @FXML private TableColumn<Versione, Integer> colNumero;
    @FXML private TableColumn<Versione, String> colData;
    @FXML private TableColumn<Versione, Integer> colIdPagina;
    @FXML private TableColumn<Versione, Integer> colIdAutore;
    @FXML private Label errorLabel;

    private VersioneDAO versioneDAO = new VersioneDAO();

    @FXML
    public void initialize() {
        colIdVersione.setCellValueFactory(new PropertyValueFactory<>("idVersione"));
        colTesto.setCellValueFactory(new PropertyValueFactory<>("testo"));
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colIdPagina.setCellValueFactory(new PropertyValueFactory<>("idPagina"));
        colIdAutore.setCellValueFactory(new PropertyValueFactory<>("idAutore"));

        // serve per limitare le scritte nella colonna ad una lunghezza di massimo 50
        colTesto.setCellFactory(column -> {
            TableCell<Versione, String> cell = new TableCell<>() {
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

        // apertura della finestra con doppio click
        tableViewVersioni.setRowFactory(tv -> {
            TableRow<Versione> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Versione versione = row.getItem();
                    showFullText(versione);
                }
            });
            return row;
        });

        loadVersioni();
    }

    private void loadVersioni() {
        List<Versione> lista = versioneDAO.getAllVersioni();
        ObservableList<Versione> obsList = FXCollections.observableArrayList(lista);
        tableViewVersioni.setItems(obsList);
    }

    // apertura finestra per testo completo
    private void showFullText(Versione versione) {
        Stage stage = new Stage();
        stage.setTitle("Dettaglio Versione - ID: " + versione.getIdVersione());
        TextArea textArea = new TextArea(versione.getTesto());
        textArea.setWrapText(true);
        textArea.setEditable(false);
        VBox vbox = new VBox(textArea);
        Scene scene = new Scene(vbox, 600, 400);
        stage.setScene(scene);
        stage.show();
    }
}





