package com.cryptography.cryptography;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CriptoFX extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Создает экземпляр класса MainPanelCrypto для вывода на экран
        MyPanelCr MyPanelCr = new MyPanelCr();
        Scene scene = new Scene(MyPanelCr.border);
        stage.setScene(scene);
        stage.setTitle("Criptograph");
        stage.show(); // Тут он выводит
    }

    public static void main(String[] args) {
        launch();
    }
}