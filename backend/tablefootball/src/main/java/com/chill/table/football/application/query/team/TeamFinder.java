package com.chill.table.football.application.query.team;

import com.chill.table.football.application.matches.exception.TeamNotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
                .orElseThrow(() -> new TeamNotFoundException(teamId, null));
    }

    public List<TeamProjection> findAll() {
        return teamFinderRepository.findBy().stream()
                .map(TeamProjectionImpl::new)
                .collect(Collectors.toList());
    }

    public TeamProjection findByName(String name) {
        return teamFinderRepository.findByName(name)
                .map(TeamProjectionImpl::new)
                .orElseThrow(() -> new TeamNotFoundException(null, name));
    }
}
