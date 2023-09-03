package ru.eventflow.ui.event;

import ru.eventflow.eventbus.EventHandler;

public interface StatusUpdateEventHandler extends EventHandler {
    void onEvent(final StatusUpdateEvent e);
}
