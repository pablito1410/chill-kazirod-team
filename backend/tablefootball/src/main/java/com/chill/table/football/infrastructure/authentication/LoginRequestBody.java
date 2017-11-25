package com.chill.table.football.infrastructure.authentication;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

@Value
class LoginRequestBody {
    private String userName;
    private String password;

}
