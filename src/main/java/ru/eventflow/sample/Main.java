package ru.eventflow.sample;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.eventflow.sample.ui.presenter.MainPresenter;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Injector injector = Guice.createInjector(new GUIModule());
        MainPresenter mainPresenter = injector.getInstance(MainPresenter.class);

        Parent parent = mainPresenter.getParent();
        primaryStage.setScene(new Scene(parent));
        primaryStage.setTitle("JavaFX 8 Dependency injection");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
