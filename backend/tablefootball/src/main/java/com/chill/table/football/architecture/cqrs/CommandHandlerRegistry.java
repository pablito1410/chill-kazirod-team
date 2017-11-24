package com.chill.table.football.architecture.cqrs;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CommandHandlerRegistry {

    private final Map<Class<? extends Command<?>>, CommandHandler<?, ? extends Command<?>>> handlers = new HashMap<>();

    public <R, C extends Command<R>> void register(final CommandHandler<R, C> handler, final Class<C> commandClass) {
        Objects.requireNonNull(handler);
        Objects.requireNonNull(commandClass);

        handlers.put(commandClass, handler);
    }

    @SuppressWarnings("unchecked")
    <R, C extends Command<R>> CommandHandler<R, C> get(final C command) {
        Objects.requireNonNull(command);

        final CommandHandler<R, C> handler = (CommandHandler<R, C>) handlers.get(command.getClass());

        if (handler == null) {
            throw new IllegalStateException("No handler registered for command: " + command.getClass().getSimpleName());
        } else {
            return handler;
        }
    }
}
