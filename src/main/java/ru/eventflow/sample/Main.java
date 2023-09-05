package ru.eventflow.sample;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ru.eventflow.sample.eventbus.EventBus;
import ru.eventflow.sample.ui.event.MouseClickedEvent;
import ru.eventflow.sample.ui.presenter.MainPresenter;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Injector injector = Guice.createInjector(new GUIModule());
        MainPresenter mainPresenter = injector.getInstance(MainPresenter.class);
        EventBus eventBus = injector.getInstance(EventBus.class);

        Parent root = mainPresenter.getParent();

        // Letting know globally which node was clicked, see https://stackoverflow.com/a/38139005
        root.addEventFilter(MouseEvent.MOUSE_CLICKED,
                e -> eventBus.fireEvent(new MouseClickedEvent(e.getPickResult().getIntersectedNode()))
        );

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("JavaFX 20 Dependency Injection");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
