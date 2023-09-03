package ru.eventflow;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.eventflow.ui.presenter.MainController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Injector injector = Guice.createInjector(new GUIModule());
        MainController mainController = injector.getInstance(MainController.class);

        Parent parent = mainController.getRootPane();
        primaryStage.setScene(new Scene(parent));
        primaryStage.setTitle("JavaFX 8 Dependency injection");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
