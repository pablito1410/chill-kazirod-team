package com.chill.table.football.infrastructure.rest;

import com.chill.table.football.application.query.player.PlayerFinder;
import com.chill.table.football.application.query.player.PlayerProjection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/player")
public class PlayerController {
    private PlayerFinder playerFinder;

    PlayerController(PlayerFinder playerFinder) {
        this.playerFinder = Objects.requireNonNull(playerFinder);
    }

    @GetMapping("{playerId}")
    PlayerProjection getOne(@PathParam("playerId") Long playerId) {
        return playerFinder.findByIdOrThrow(playerId);
    }

    @GetMapping
    List<PlayerProjection> getAll() {
        return playerFinder.findAll();
    }
}
