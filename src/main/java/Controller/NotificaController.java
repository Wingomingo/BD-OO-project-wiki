package Controller;

import Dao.NotificaDAO;
import Modello.Notifica;
import Modello.Utente;
import Utilities.SessionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;
import java.util.stream.Collectors;

public class NotificaController {

    @FXML
    private TableView<Notifica> tableViewNotifiche;
    @FXML
    private TableColumn<Notifica, Integer> colIdNotifica;
    @FXML
    private TableColumn<Notifica, String> colData;
    @FXML
    private TableColumn<Notifica, Boolean> colLetta;
    @FXML
    private TableColumn<Notifica, Integer> colIdModificatore;
    @FXML
    private TableColumn<Notifica, Integer> colIdAutore;
    @FXML
    private TableColumn<Notifica, Integer> colIdCreatore;

    private NotificaDAO notificaDAO = new NotificaDAO();

    @FXML
    public void initialize() {
        colIdNotifica.setCellValueFactory(new PropertyValueFactory<>("idNotifica"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colLetta.setCellValueFactory(new PropertyValueFactory<>("letta"));
        colIdModificatore.setCellValueFactory(new PropertyValueFactory<>("idModificatore"));
        colIdAutore.setCellValueFactory(new PropertyValueFactory<>("idAutore"));
        colIdCreatore.setCellValueFactory(new PropertyValueFactory<>("idCreatorePagina"));

        List<Notifica> allNotifiche = notificaDAO.getAllNotifiche();

        //filtra le notifiche a seconda dell utente loggato
        Utente logged = SessionManager.getInstance().getUtenteLoggato();
        if (logged != null) {
            allNotifiche = allNotifiche.stream()
                    .filter(n -> n.getIdCreatorePagina() == logged.getId())
                    .collect(Collectors.toList());
        }

        ObservableList<Notifica> notificaList = FXCollections.observableArrayList(allNotifiche);
        tableViewNotifiche.setItems(notificaList);
    }
}




