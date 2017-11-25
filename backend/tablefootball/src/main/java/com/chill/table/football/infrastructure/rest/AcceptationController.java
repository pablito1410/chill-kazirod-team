package com.chill.table.football.infrastructure.rest;

import com.chill.table.football.application.query.acceptation.AcceptationFinder;
import com.chill.table.football.application.query.acceptation.AcceptationProjection;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/acceptation")
public class AcceptationController {

    private AcceptationFinder acceptationFinder;

    public AcceptationController(AcceptationFinder acceptationFinder) {
        this.acceptationFinder = Objects.requireNonNull(acceptationFinder);
    }

    @GetMapping("/player/{playerId}")
    List<AcceptationProjection> getForUser(@PathVariable Long playerId) {
        return acceptationFinder.findById(playerId);
    }
}
