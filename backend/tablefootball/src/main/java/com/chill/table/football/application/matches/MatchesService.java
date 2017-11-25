package com.chill.table.football.application.matches;

import com.chill.table.football.application.matches.dto.in.AcceptMatchRequestDTO;
import com.chill.table.football.application.matches.dto.in.CreateMatchWithPlayersRequestDTO;
import com.chill.table.football.application.matches.dto.in.EndMatchRequestDTO;
import com.chill.table.football.application.matches.dto.in.RejectMatchRequestDTO;
import com.chill.table.football.application.matches.dto.out.AcceptMatchResponseDTO;
import com.chill.table.football.application.matches.dto.out.CreateMatchWithPlayersResponseDTO;
import com.chill.table.football.application.matches.dto.out.EndMatchResponseDTO;
import com.chill.table.football.application.matches.dto.out.RejectMatchResponseDTO;
import com.chill.table.football.application.matches.exception.MatchExistsWithTooCloseDateTime;
import com.chill.table.football.application.matches.exception.TeamNameAlreadyExistException;
import com.chill.table.football.application.query.matches.MatchesFinder;
import com.chill.table.football.application.user.Player;
import com.chill.table.football.application.user.PlayerRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Transactional
public class MatchesService {

    private MatchesRepository matchesRepository;
    private MatchesRepository.TeamRepository teamRepository;
    private MatchesRepository.AcceptationRepository acceptationRepository;

    private MatchesFinder matchesFinder;
    private PlayerRepository playerRepository;

    public MatchesService(MatchesRepository matchesRepository, MatchesRepository.TeamRepository teamRepository,
                          MatchesRepository.AcceptationRepository acceptationRepository,
                          MatchesFinder matchesFinder, PlayerRepository playerRepository) {
        this.matchesRepository = Objects.requireNonNull(matchesRepository);
        this.teamRepository = Objects.requireNonNull(teamRepository);
        this.acceptationRepository = Objects.requireNonNull(acceptationRepository);

        this.matchesFinder = Objects.requireNonNull(matchesFinder);
        this.playerRepository = Objects.requireNonNull(playerRepository);
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
                .orElseGet(() ->  teamRepository.findByFirstPlayerIdAndSecondPlayerId(secondPlayer, firstPlayer)
                        .orElse(createTeamFromDTO(teamDTO)));
    }

    private Team createTeamFromDTO(CreateMatchWithPlayersRequestDTO.Team teamDTO) {
        teamRepository.findByName(teamDTO.getName()).ifPresent(p -> { throw new TeamNameAlreadyExistException(teamDTO.getName()); });
        Player firstPlayer = playerRepository.findByIdOrThrow(teamDTO.getFirstPlayer());
        Player secondPlayer = playerRepository.findByIdOrThrow(teamDTO.getSecondPlayer());
        return new Team(teamDTO.getName(), firstPlayer, secondPlayer);
    }

    public EndMatchResponseDTO endMatch(EndMatchRequestDTO endMatchRequestDTO) {
        Objects.requireNonNull(endMatchRequestDTO);

        Match match = matchesRepository.findByIdOrThrow(endMatchRequestDTO.getMatchId());
        Team team = teamRepository.findByIdOrThrow(endMatchRequestDTO.getTeamId());

        match.end(team, endMatchRequestDTO.getFirstScore(), endMatchRequestDTO.getSecondScore(), endMatchRequestDTO.getEndDateTime());

        return EndMatchResponseDTO.builder().build();
    }

    public AcceptMatchResponseDTO acceptMatch(AcceptMatchRequestDTO acceptMatchRequestDTO) {
        Match match = matchesRepository.findByIdOrThrow(acceptMatchRequestDTO.getMatchId());
        Acceptation acceptation = acceptationRepository.findByIdOrThrow(acceptMatchRequestDTO.getAcceptationId());
        match.accept(acceptation);
        return new AcceptMatchResponseDTO();
    }

    public RejectMatchResponseDTO rejectMatch(RejectMatchRequestDTO acceptMatchRequestDTO) {
        Match match = matchesRepository.findByIdOrThrow(acceptMatchRequestDTO.getMatchId());
        Acceptation acceptation = acceptationRepository.findByIdOrThrow(acceptMatchRequestDTO.getAcceptationId());
        match.reject(acceptation);
        return new RejectMatchResponseDTO();
    }
}
