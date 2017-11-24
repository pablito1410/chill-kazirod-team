package com.chill.table.football.architecture.cqrs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class CommandGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandGateway.class);

    private final CommandHandlerRegistry registry;

    public CommandGateway(final CommandHandlerRegistry registry) {
        this.registry = Objects.requireNonNull(registry);
    }

    public <R> R dispatch(final Command<R> command) {
        Objects.requireNonNull(command);

        final CommandHandler<R, Command<R>> commandHandler = (CommandHandler<R, Command<R>>) registry.get(command);

        LOGGER.info("Dispatching command = {} by commandHandler = {}", command, commandHandler);

        final R result = commandHandler.handle(command);

        LOGGER.info("Dispatched command = {} by commandHandler = {} result = {}", command, commandHandler, result);

        return result;
    }

}
