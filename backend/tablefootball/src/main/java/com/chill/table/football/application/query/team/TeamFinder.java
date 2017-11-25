package com.chill.table.football.application.query.team;

import com.chill.table.football.application.matches.exception.TeamNotFoundException;

import java.util.Objects;
import java.util.Optional;

public class TeamFinder {
    private TeamFinderRepository teamFinderRepository;

    public TeamFinder(TeamFinderRepository teamFinderRepository) {
        this.teamFinderRepository = Objects.requireNonNull(teamFinderRepository);
    }

    public Optional<TeamProjection> findOne(Long teamId) {
        return teamFinderRepository.findById(teamId)
                .map(TeamProjectionImpl::new);
    }

    public TeamProjection findOneOrThrow(Long teamId) {
        return findOne(teamId)
                .orElseThrow(() -> new TeamNotFoundException("Team with id " + teamId + " not found"));
    }
}
