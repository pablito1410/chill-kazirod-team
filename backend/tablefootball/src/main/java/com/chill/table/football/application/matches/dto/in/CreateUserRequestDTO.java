package com.chill.table.football.application.matches.dto.in;

import com.chill.table.football.application.matches.dto.out.CreateUserResponseDTO;
import com.chill.table.football.architecture.cqrs.Command;
import lombok.Value;

@Value
public class CreateUserRequestDTO implements Command<CreateUserResponseDTO> {

    private final String userName;
    private final String password;
    private String firstName;
    private String lastName;

}
