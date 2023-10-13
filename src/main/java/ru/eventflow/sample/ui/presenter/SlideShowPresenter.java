package ru.eventflow.sample.ui.presenter;

import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import ru.eventflow.sample.eventbus.EventBus;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SlideShowPresenter implements Presenter {

    private final EventBus eventBus;

    @FXML
    private Pane slideShowRootPane;

    @FXML
    private Button leftButton;

    @FXML
    private Button rightButton;

    @FXML
    private ImageView imageView;

    private int currentImageIndex;

    private List<Image> images;

    @Inject
    public SlideShowPresenter(EventBus eventBus) {
        try (InputStream in = getClass().getResourceAsStream("/fxml/SlideShow.fxml")) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setController(this);
            fxmlLoader.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.eventBus = eventBus;

        this.images = new ArrayList<>();
        try {
            images.addAll(Arrays.asList(
                    new Image(new FileInputStream(getClass().getResource("/img/no_img.png").getFile())),
                    new Image(new FileInputStream(getClass().getResource("/img/1.png").getFile())),
                    new Image(new FileInputStream(getClass().getResource("/img/2.png").getFile())),
                    new Image(new FileInputStream(getClass().getResource("/img/3.png").getFile()))
            ));
            this.currentImageIndex = 0;
            this.imageView.setImage(images.get(this.currentImageIndex));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.leftButton.setOnMouseClicked(event -> {
            currentImageIndex = (images.size() + currentImageIndex - 1) % images.size();
            this.imageView.setImage(images.get(currentImageIndex));
        });

        this.rightButton.setOnMouseClicked(event -> {
            currentImageIndex = (images.size() + currentImageIndex + 1) % images.size();
            this.imageView.setImage(images.get(currentImageIndex));
        });
    }

    @Override
    public Parent getRootElement() {
        return slideShowRootPane;
    }

}
