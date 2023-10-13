package ru.eventflow.sample.ui.presenter;

import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TitledPane;
import ru.eventflow.sample.eventbus.EventBus;
import ru.eventflow.sample.ui.event.MouseClickedEvent;
import ru.eventflow.sample.ui.event.MouseClickedEventHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class SelectPresenter implements Presenter, MouseClickedEventHandler {

    private final EventBus eventBus;

    @FXML
    private TitledPane titledPane;

    @Inject
    public SelectPresenter(EventBus eventBus) {
        try (InputStream in = getClass().getResourceAsStream("/fxml/SelectView.fxml")) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setController(this);
            fxmlLoader.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.eventBus = eventBus;
        this.eventBus.addHandler(MouseClickedEvent.TYPE, this);

        init();
    }

    private void init() {
        ListView<CheckBox> listView = new ListView<>();
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        Arrays.asList("A", "B", "C").forEach(s -> listView.getItems().add(new CheckBox(s)));
        titledPane.setContent(listView);
    }

    @Override
    public Parent getRootElement() {
        return titledPane;
    }

    @Override
    public void onEvent(MouseClickedEvent e) {
        if (!isDescendant(e.getSource(), titledPane)) {
            titledPane.setExpanded(false);
        }
    }

    private boolean isDescendant(Node node, Node potentialDescendant) {
        if (potentialDescendant == null) {
            return true;
        }
        while (node != null) {
            if (node == potentialDescendant) {
                return true;
            }
            node = node.getParent();
        }
        return false;
    }

}
