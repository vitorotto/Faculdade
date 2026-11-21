package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenuApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/MainMenu.fxml")
        );
        Scene scene = new Scene(loader.load());

        stage.setTitle("Sistema de Cadastro de Contas");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
