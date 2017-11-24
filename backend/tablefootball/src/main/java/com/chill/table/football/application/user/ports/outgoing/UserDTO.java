package com.chill.table.football.application.user.ports.outgoing;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
public class UserDTO {

    private Long id;
    private String userName;

    public UserDTO() { }

}
