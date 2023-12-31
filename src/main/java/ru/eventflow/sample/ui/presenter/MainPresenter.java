package ru.eventflow.sample.ui.presenter;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import ru.eventflow.sample.eventbus.EventBus;
import ru.eventflow.sample.ui.event.MouseClickedEvent;
import ru.eventflow.sample.ui.event.StatusUpdateEvent;
import ru.eventflow.sample.ui.event.StatusUpdateEventHandler;

import java.io.IOException;
import java.io.InputStream;

public class MainPresenter implements Presenter, StatusUpdateEventHandler {

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
    public MainPresenter(EventBus eventBus, TopPanePresenter topPanePresenter, @Named("initial_text") String key) {
        try (InputStream in = getClass().getResourceAsStream("/fxml/MainView.fxml")) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setController(this);
            fxmlLoader.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.eventBus = eventBus;
        this.eventBus.addHandler(StatusUpdateEvent.TYPE, this);

        // letting know globally which node was clicked, see https://stackoverflow.com/a/38139005
        rootPane.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            Node source = e.getPickResult().getIntersectedNode();
            eventBus.fireEvent(new MouseClickedEvent(source));
        });

        bottomText.setText(key);

        topPane.getChildren().add(topPanePresenter.getParent());
    }

    @Override
    public void onEvent(StatusUpdateEvent e) {
        bottomText.setText(e.getMessage());
    }

    @Override
    public AnchorPane getParent() {
        return rootPane;
    }

    public AnchorPane getTopPane() {
        return topPane;
    }

    public AnchorPane getBottomPane() {
        return bottomPane;
    }
}
