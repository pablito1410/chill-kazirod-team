package com.chill.table.football.infrastructure.rest;

import com.chill.table.football.application.query.user.UserFinder;
import com.chill.table.football.application.user.ports.incoming.CreateUserCommand;
import com.chill.table.football.application.user.ports.outgoing.UserDTO;
import com.chill.table.football.architecture.cqrs.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final CommandGateway commandGateway;
    private final UserFinder userFinder;

    @Autowired
    public UserController(final CommandGateway commandGateway,
                          final UserFinder userFinder) {
        this.commandGateway = commandGateway;
        this.userFinder = userFinder;
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ResponseEntity<Long> createUser(@RequestBody final CreateUserCommand command) {
        final Long id = commandGateway.dispatch(command);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> get(@PathVariable(name = "id") final long id) {
        final UserDTO user = userFinder.getUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public ResponseEntity<Collection<UserDTO>> getAll() {
        final Collection<UserDTO> allUsers = userFinder.getAll();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
}
