package com.chill.table.football.infrastructure.rest;

import com.chill.table.football.application.matches.dto.in.CreateUserRequestDTO;
import com.chill.table.football.application.matches.dto.out.CreateUserResponseDTO;
import com.chill.table.football.application.query.player.PlayerFinder;
import com.chill.table.football.application.query.player.PlayerProjection;
import com.chill.table.football.architecture.cqrs.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
class PlayerController {

    private CommandGateway commandGateway;
    private PlayerFinder playerFinder;

    PlayerController(CommandGateway commandGateway,
                     PlayerFinder playerFinder) {
        this.commandGateway = commandGateway;
        this.playerFinder = playerFinder;
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    CreateUserResponseDTO createUser(@RequestBody final CreateUserRequestDTO requestDTO) {
        return commandGateway.dispatch(requestDTO);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    PlayerProjection get(@PathVariable(name = "id") final long id) {
        return playerFinder.findByIdOrThrow(id);
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    List<PlayerProjection> getAll() {
        return playerFinder.findAll();
    }
}
