package com.chill.table.football.application.user.ports.incoming;

import com.chill.table.football.architecture.cqrs.Command;
import lombok.Value;

@Value
public class CreateUserCommand implements Command<Long> {

    private final String userName;
    private final String password;

}
