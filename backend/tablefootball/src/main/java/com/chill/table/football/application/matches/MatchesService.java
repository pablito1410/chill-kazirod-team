package com.chill.table.football.application.matches;

import com.chill.table.football.application.matches.dto.in.CreateMatchWithPlayersRequestDTO;
import com.chill.table.football.application.matches.dto.in.EndMatchRequestDTO;
import com.chill.table.football.application.matches.dto.out.CreateMatchWithPlayersResponseDTO;
import com.chill.table.football.application.matches.dto.out.EndMatchResponseDTO;
import com.chill.table.football.application.matches.exception.MatchExistsWithTooCloseDateTime;
import com.chill.table.football.application.matches.exception.MatchNotFoundException;
import com.chill.table.football.application.matches.exception.TeamNotFoundException;
import com.chill.table.football.application.query.matches.MatchesFinder;
import com.chill.table.football.application.user.UserFinder;
import com.chill.table.football.application.user.ports.outgoing.UserDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
public class MatchesService {

    private MatchesRepository matchesRepository;
    private MatchesRepository.TeamRepository teamRepository;
    private MatchesRepository.PlayerRepository playerRepository;

    private MatchesFinder matchesFinder;
    private UserFinder userFinder;

    public MatchesService(MatchesRepository matchesRepository, MatchesRepository.TeamRepository teamRepository,
                          MatchesRepository.PlayerRepository playerRepository, MatchesFinder matchesFinder,
                          UserFinder userFinder) {
        this.matchesRepository = Objects.requireNonNull(matchesRepository);
        this.teamRepository = Objects.requireNonNull(teamRepository);
        this.playerRepository = Objects.requireNonNull(playerRepository);

        this.matchesFinder = Objects.requireNonNull(matchesFinder);
        this.userFinder = Objects.requireNonNull(userFinder);
    }

    public CreateMatchWithPlayersResponseDTO createMatchWithPlayers(CreateMatchWithPlayersRequestDTO createMatchWithPlayersRequestDTO) {
        Objects.requireNonNull(createMatchWithPlayersRequestDTO);
        CreateMatchWithPlayersRequestDTO.Team firstTeamDTO = createMatchWithPlayersRequestDTO.getFirstTeam();
        CreateMatchWithPlayersRequestDTO.Team secondTeam1DTO = createMatchWithPlayersRequestDTO.getSecondTeam();

        Team firstTeam = getOrCreateTeam(firstTeamDTO);
        Team secondTeam = getOrCreateTeam(secondTeam1DTO);

        matchesFinder.findMatchIn30MinutesBefore(createMatchWithPlayersRequestDTO.getDateTime())
                .ifPresent(m -> {
                    throw new MatchExistsWithTooCloseDateTime("Match with to close date time found = " + m.getDateTime()); });

        Match match = new Match(createMatchWithPlayersRequestDTO.getDateTime(), firstTeam, secondTeam);
        match = matchesRepository.save(match);

        return match.toCreateMatchResponseDTO();
    }

    private Team getOrCreateTeam(CreateMatchWithPlayersRequestDTO.Team teamDTO) {
        Optional<Team> team = teamRepository.findTop1ByIdIn(teamDTO.getPlayers());
        return team.orElse(createTeamFromDTO(teamDTO));
    }

    private Team createTeamFromDTO(CreateMatchWithPlayersRequestDTO.Team teamDTO) {
        Team team = new Team(teamDTO.getName());
        Set<Player> players = teamDTO.getPlayers()
                .stream()
                .map(this::getOrCreatePlayer)
                .collect(Collectors.toSet());
        players.forEach(team::appendPlayer);
        players.forEach(p -> p.appendTeam(team));
        return team;
    }

    private Player getOrCreatePlayer(Long playerId) {
        UserDTO user = userFinder.getUser(playerId);
        Optional<Player> player = playerRepository.findById(playerId);
        return player.orElse(new Player(user.getId()));
    }

    public EndMatchResponseDTO endMatch(EndMatchRequestDTO endMatchRequestDTO) {
        Objects.requireNonNull(endMatchRequestDTO);

        Match match = matchesRepository.findById(endMatchRequestDTO.getMatchId())
                .orElseThrow(() -> new MatchNotFoundException("Match with id = " + endMatchRequestDTO.getMatchId() + " not found."));
        Team team = teamRepository.findById(endMatchRequestDTO.getTeamId())
                .orElseThrow(() -> new TeamNotFoundException("Team with id = " + endMatchRequestDTO.getTeamId() + " not found."));
        match.end(team);

        return EndMatchResponseDTO.builder().build();
    }
}
