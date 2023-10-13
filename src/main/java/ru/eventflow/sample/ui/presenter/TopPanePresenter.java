package ru.eventflow.sample.ui.presenter;

import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import ru.eventflow.sample.eventbus.EventBus;
import ru.eventflow.sample.ui.event.StatusUpdateEvent;

import java.io.IOException;
import java.io.InputStream;

public class TopPanePresenter implements Presenter {

    private EventBus eventBus;

    @FXML
    private Parent topPaneViewParent;

    @FXML
    private Button topPaneButton;

    @FXML
    private AnchorPane leftAnchorPane;

    private int counter = 0;

    @Inject
    public TopPanePresenter(EventBus eventBus, SelectPresenter selectPresenter, SlideShowPresenter slideShowPresenter) {
        try (InputStream in = getClass().getResourceAsStream("/fxml/TopPaneView.fxml")) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setController(this);
            fxmlLoader.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.eventBus = eventBus;

        this.topPaneButton.setVisible(false);
//        this.topPaneButton.setOnMouseClicked(mouseEvent -> {
//            counter++;
//            this.eventBus.fireEvent(new StatusUpdateEvent("Button was clicked " + counter + " times!"));
//        });

//        this.leftAnchorPane.getChildren().add(selectPresenter.getRootElement());
        this.leftAnchorPane.getChildren().add(slideShowPresenter.getRootElement());
    }

    @Override
    public Parent getRootElement() {
        return topPaneViewParent;
    }

    public Button getTopPaneButton() {
        return topPaneButton;
    }

}
