package com.chill.table.football.application.matches;

import com.chill.table.football.application.matches.dto.in.AcceptMatchRequestDTO;
import com.chill.table.football.application.matches.dto.in.CreateMatchWithPlayersRequestDTO;
import com.chill.table.football.application.matches.dto.in.EndMatchRequestDTO;
import com.chill.table.football.application.matches.dto.out.AcceptMatchResponseDTO;
import com.chill.table.football.application.matches.dto.out.CreateMatchWithPlayersResponseDTO;
import com.chill.table.football.application.matches.dto.out.EndMatchResponseDTO;
import com.chill.table.football.application.matches.exception.MatchExistsWithTooCloseDateTime;
import com.chill.table.football.application.matches.exception.TeamNameAlreadyExistException;
import com.chill.table.football.application.query.matches.MatchesFinder;
import com.chill.table.football.application.query.user.UserFinder;
import com.chill.table.football.application.user.ports.outgoing.UserDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Transactional
public class MatchesService {

    private MatchesRepository matchesRepository;
    private MatchesRepository.TeamRepository teamRepository;
    private MatchesRepository.PlayerRepository playerRepository;
    private MatchesRepository.AcceptationRepository acceptationRepository;

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
                    throw new MatchExistsWithTooCloseDateTime(m.getDateTime()); });

        Match match = new Match(createMatchWithPlayersRequestDTO.getDateTime(), firstTeam, secondTeam);
        match = matchesRepository.save(match);

        return match.toCreateMatchResponseDTO();
    }

    private Team getOrCreateTeam(CreateMatchWithPlayersRequestDTO.Team teamDTO) {
        Long firstPlayer = teamDTO.getFirstPlayer();
        Long secondPlayer = teamDTO.getSecondPlayer();
        return teamRepository.findByFirstPlayerIdAndSecondPlayerId(firstPlayer, secondPlayer)
                .orElse(teamRepository.findByFirstPlayerIdAndSecondPlayerId(secondPlayer, firstPlayer)
                    .orElse(createTeamFromDTO(teamDTO)));
    }

    private Team createTeamFromDTO(CreateMatchWithPlayersRequestDTO.Team teamDTO) {
        teamRepository.findByName(teamDTO.getName()).ifPresent(p -> { throw new TeamNameAlreadyExistException(teamDTO.getName()); });
        Player firstPlayer = getOrCreatePlayer(teamDTO.getFirstPlayer());
        Player secondPlayer = getOrCreatePlayer(teamDTO.getSecondPlayer());
        Team team = new Team(teamDTO.getName(), firstPlayer, secondPlayer);
        firstPlayer.appendTeam(team);
        secondPlayer.appendTeam(team);
        return team;
    }

    // TODO odkomentować
    private Player getOrCreatePlayer(Long playerId) {
//        UserDTO user = userFinder.getUser(playerId);
        return playerRepository.findById(playerId)
//                .orElse(new Player(user.getId()));
                    .orElse(new Player(playerId));
    }

    public EndMatchResponseDTO endMatch(EndMatchRequestDTO endMatchRequestDTO) {
        Objects.requireNonNull(endMatchRequestDTO);

        Match match = matchesRepository.findByIdOrThrow(endMatchRequestDTO.getMatchId());
        Team team = teamRepository.findByIdOrThrow(endMatchRequestDTO.getTeamId());

        match.end(team);

        return EndMatchResponseDTO.builder().build();
    }

    public AcceptMatchResponseDTO acceptMatch(AcceptMatchRequestDTO acceptMatchRequestDTO) {
        Match match = matchesRepository.findByIdOrThrow(acceptMatchRequestDTO.getMatchId());
        Acceptation acceptation = acceptationRepository.findByIdOrThrow(acceptMatchRequestDTO.getAcceptationId());
//        match.accept(acceptation);
        return new AcceptMatchResponseDTO();
    }
}
