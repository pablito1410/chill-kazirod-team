package com.chill.table.football.infrastructure.authentication;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LoginRequestBody {

    private String userName;
    private String password;

}
