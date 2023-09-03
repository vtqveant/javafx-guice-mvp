package ru.eventflow.ui.presenter;

import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import ru.eventflow.eventbus.EventBus;
import ru.eventflow.ui.event.StatusUpdateEvent;
import ru.eventflow.ui.event.StatusUpdateEventHandler;

import java.io.IOException;
import java.io.InputStream;

public class MainController implements StatusUpdateEventHandler {

    private EventBus eventBus;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private AnchorPane topPane;

    @FXML
    private AnchorPane bottomPane;

    @FXML
    private Text bottomText;

    @Inject
    public MainController(EventBus eventBus, TopPaneController topPaneController) {
        try (InputStream in = getClass().getResourceAsStream("/fxml/MainView.fxml")) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setController(this);
            fxmlLoader.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.eventBus = eventBus;
        this.eventBus.addHandler(StatusUpdateEvent.TYPE, this);

        topPane.getChildren().add(topPaneController.getParent());
    }

    @Override
    public void onEvent(StatusUpdateEvent e) {
        this.bottomText.setText(e.getMessage());
    }

    public AnchorPane getRootPane() {
        return rootPane;
    }

    public AnchorPane getTopPane() {
        return topPane;
    }

    public AnchorPane getBottomPane() {
        return bottomPane;
    }
}
