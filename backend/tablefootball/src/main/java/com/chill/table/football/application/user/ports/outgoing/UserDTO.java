package com.chill.table.football.application.user.ports.outgoing;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private Long id;
    private String userName;
}
