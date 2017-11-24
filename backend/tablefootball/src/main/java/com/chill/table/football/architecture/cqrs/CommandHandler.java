package com.chill.table.football.architecture.cqrs;

@FunctionalInterface
public interface CommandHandler<R, C extends Command<R>> {
    R handle(C command);
}
