package com.chill.table.football.infrastructure.rest;

import com.chill.table.football.application.user.UserService;
import com.chill.table.football.application.user.ports.incoming.CreateUserCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ResponseEntity<Long> createUser(final CreateUserCommand command) {
        final Long id = userService.handle(command);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

//    @RequestMapping(path = "/{id}" method = RequestMethod.GET)
//    public ResponseEntity<UserDTO> get(@PathVariable)
}
