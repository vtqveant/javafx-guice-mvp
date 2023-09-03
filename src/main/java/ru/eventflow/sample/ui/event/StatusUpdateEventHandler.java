package ru.eventflow.sample.ui.event;

import ru.eventflow.sample.eventbus.EventHandler;

public interface StatusUpdateEventHandler extends EventHandler {
    void onEvent(final StatusUpdateEvent e);
}
