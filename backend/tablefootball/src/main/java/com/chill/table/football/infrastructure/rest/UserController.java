package com.chill.table.football.infrastructure.rest;

import com.chill.table.football.application.user.UserService;
import com.chill.table.football.application.user.ports.incoming.CreateUserCommand;
import com.chill.table.football.application.user.ports.outgoing.UserDTO;
import com.chill.table.football.architecture.cqrs.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final CommandGateway commandGateway;

    @Autowired
    public UserController(final UserService userService, final CommandGateway commandGateway) {
        this.userService = userService;
        this.commandGateway = commandGateway;
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ResponseEntity<Long> createUser(final CreateUserCommand command) {
        final Long id = commandGateway.dispatch(command);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> get(@PathVariable(name = "id") final long id) {
//        final UserDTO user = userService.getUser(id);
//        final UserDTO user = new UserDTO(0L, "kazirod");
//        return new ResponseEntity<>(user, HttpStatus.OK);
    return null;
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public ResponseEntity<Collection<UserDTO>> getAll() {
        final Collection<UserDTO> allUsers = userService.getAll();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
}
