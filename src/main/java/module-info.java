module com.example.wiki {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires java.prefs;

    opens com.example.wiki to javafx.graphics, javafx.fxml;
    opens Controller to javafx.fxml;
    opens Dao to javafx.fxml;
    opens Modello to javafx.fxml;
    opens Utilities to javafx.fxml;

    exports com.example.wiki;
    exports Controller;
    exports Dao;
    exports Modello;
    exports Utilities;
}

