package com.chill.table.football.application.user.ports.outgoing;

import lombok.Getter;
import lombok.Value;

@Getter
@Value
public class UserDTO {

    private final Long id;
    private final String userName;

}
