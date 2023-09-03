package ru.eventflow;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import ru.eventflow.eventbus.EventBus;
import ru.eventflow.ui.presenter.MainController;
import ru.eventflow.ui.presenter.TopPaneController;

import java.io.IOException;
import java.util.Properties;

public class GUIModule extends AbstractModule {

    @Override
    protected void configure() {
        Names.bindProperties(binder(), getProperties());
        bind(EventBus.class).in(Singleton.class);
        bind(MainController.class).in(Singleton.class);
        bind(TopPaneController.class).in(Singleton.class);
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream("/config.properties"));
        } catch (IOException e) {
            addError(e);
        }
        return properties;
    }

}
