package ru.eventflow.sample;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import ru.eventflow.sample.eventbus.EventBus;
import ru.eventflow.sample.ui.presenter.MainPresenter;
import ru.eventflow.sample.ui.presenter.SelectPresenter;
import ru.eventflow.sample.ui.presenter.TopPanePresenter;

import java.io.IOException;
import java.util.Properties;

public class GUIModule extends AbstractModule {

    @Override
    protected void configure() {
        Names.bindProperties(binder(), getProperties());
        bind(EventBus.class).in(Singleton.class);
        bind(MainPresenter.class).in(Singleton.class);
        bind(SelectPresenter.class).in(Singleton.class);
        bind(TopPanePresenter.class).in(Singleton.class);
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
