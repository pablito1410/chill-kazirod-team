package com.chill.table.football.infrastructure.rest;

import com.chill.table.football.application.query.team.TeamFinder;
import com.chill.table.football.application.query.team.TeamProjection;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/team")
public class TeamController {

    private TeamFinder teamFinder;

    TeamController(TeamFinder teamFinder) {
        this.teamFinder = Objects.requireNonNull(teamFinder);
    }

    @GetMapping("/{teamId}")
    TeamProjection getOne(@PathVariable("teamId") Long teamId) {
        return teamFinder.findOneOrThrow(teamId);
    }

    @GetMapping
    List<TeamProjection> getAll() {
        return teamFinder.findAll();
    }

    @GetMapping
    TeamProjection getByName(@RequestParam(value = "name") String name) {
        return teamFinder.findByName(name);
    }
}
