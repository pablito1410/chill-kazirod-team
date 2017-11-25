package com.chill.table.football.infrastructure.rest;

import com.chill.table.football.application.query.team.TeamFinder;
import com.chill.table.football.application.query.team.TeamProjection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("team")
public class TeamController {

    private TeamFinder teamFinder;

    TeamController(TeamFinder teamFinder) {
        this.teamFinder = Objects.requireNonNull(teamFinder);
    }

    @GetMapping("/{teamId}")
    TeamProjection getOne(Long teamId) {
        return teamFinder.findOneOrThrow(teamId);
    }
}
