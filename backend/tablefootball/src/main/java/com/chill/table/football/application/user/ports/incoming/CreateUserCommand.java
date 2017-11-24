package com.chill.table.football.application.user.ports.incoming;

import lombok.Value;

@Value
public class CreateUserCommand {

    private final String userName;
    private final String password;

}
