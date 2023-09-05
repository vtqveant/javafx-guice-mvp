package ru.eventflow.sample.ui.event;

import ru.eventflow.sample.eventbus.EventHandler;

public interface MouseClickedEventHandler extends EventHandler {
    void onEvent(final MouseClickedEvent e);
}

