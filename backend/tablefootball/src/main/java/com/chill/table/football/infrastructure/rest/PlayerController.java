package com.chill.table.football.infrastructure.rest;

import com.chill.table.football.application.query.player.PlayerFinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("api/player")
public class PlayerController {
    private PlayerFinder playerFinder;

    PlayerController(PlayerFinder playerFinder) {
        this.playerFinder = Objects.requireNonNull(playerFinder);
    }
}
