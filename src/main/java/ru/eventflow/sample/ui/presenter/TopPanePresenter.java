package ru.eventflow.sample.ui.presenter;

import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import ru.eventflow.sample.eventbus.EventBus;
import ru.eventflow.sample.ui.event.StatusUpdateEvent;

import java.io.IOException;
import java.io.InputStream;

public class TopPanePresenter {

    private EventBus eventBus;

    @FXML
    private Parent topPaneViewParent;

    @FXML
    private Button topPaneButton;

    private int counter = 0;

    @Inject
    public TopPanePresenter(EventBus eventBus) {
        try (InputStream in = getClass().getResourceAsStream("/fxml/TopPaneView.fxml")) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setController(this);
            fxmlLoader.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.eventBus = eventBus;

        this.topPaneButton.setOnMouseClicked(mouseEvent -> {
            this.eventBus.fireEvent(new StatusUpdateEvent("Button was clicked " + counter + " times!"));
            counter++;
        });
    }

    public Parent getParent() {
        return topPaneViewParent;
    }

    public Button getTopPaneButton() {
        return topPaneButton;
    }

}
