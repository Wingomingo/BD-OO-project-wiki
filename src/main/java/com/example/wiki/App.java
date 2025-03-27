package com.example.wiki;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;
import Controller.LoginController;
import Controller.RegisterController;

public class App extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showLogin();
    }

    public void showLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
            Parent root = loader.load();

            LoginController loginController = loader.getController();
            loginController.setApp(this);

            Scene scene = new Scene(root, 600, 400);
            primaryStage.setTitle("Login");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void showRegister() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Register.fxml"));
            Parent root = loader.load();

            RegisterController registerController = loader.getController();
            registerController.setApp(this);

            Scene scene = new Scene(root, 600, 400);
            Stage registerStage = new Stage();
            registerStage.setTitle("Registrazione");
            registerStage.setScene(scene);
            registerStage.initModality(Modality.APPLICATION_MODAL);
            registerStage.showAndWait();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void showMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Menu.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root, 600, 400);
            primaryStage.setTitle("Wiki1 - Gestione Database");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void logout() {
        showLogin();
    }

    public static void main(String[] args) {
        launch(args);
    }
}








