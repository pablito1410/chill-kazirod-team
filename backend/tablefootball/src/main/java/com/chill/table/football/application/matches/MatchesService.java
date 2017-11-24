package com.chill.table.football.application.matches;

import com.chill.table.football.application.matches.dto.in.CreateMatchRequestDTO;
import com.chill.table.football.application.matches.dto.in.EndMatchRequestDTO;
import com.chill.table.football.application.matches.dto.out.CreateMatchResponseDTO;
import com.chill.table.football.application.matches.dto.out.EndMatchResponseDTO;
import com.chill.table.football.application.matches.exception.MatchNotFoundException;
import com.chill.table.football.application.matches.exception.TeamNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Transactional
public class MatchesService {

    private MatchesRepository matchesRepository;
    private TeamRepository teamRepository;
    private PlayerRepository playerRepository;

    public MatchesService(MatchesRepository matchesRepository, TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.matchesRepository = Objects.requireNonNull(matchesRepository);
        this.teamRepository = Objects.requireNonNull(teamRepository);
        this.playerRepository = Objects.requireNonNull(playerRepository);
    }

    // TODO:
    // nie można dodać rezerwacji do 30 minut od tego co aktualnie jest w bazie
    public CreateMatchResponseDTO createMatch(CreateMatchRequestDTO createMatchRequestDTO) {
        Objects.requireNonNull(createMatchRequestDTO);
        CreateMatchRequestDTO.Team firstTeamDTO = createMatchRequestDTO.getFirstTeam();
        CreateMatchRequestDTO.Team secondTeam1DTO = createMatchRequestDTO.getSecondTeam();

        Team firstTeam = getOrCreateTeam(firstTeamDTO);
        Team secondTeam = getOrCreateTeam(secondTeam1DTO);

        Match match = new Match(createMatchRequestDTO.getDateTime(), firstTeam, secondTeam);
        match = matchesRepository.save(match);

        return match.toCreateMatchResponseDTO();
    }

    private Team getOrCreateTeam(CreateMatchRequestDTO.Team teamDTO) {
        Set<Long> playerIds = teamDTO.getPlayers();
        Optional<Team> team = teamRepository.findTop1ByIdIn(playerIds);
        return team.orElse(createTeamFromDTO(teamDTO));
    }

    private Team createTeamFromDTO(CreateMatchRequestDTO.Team teamDTO) {
        Team team = new Team();
        teamDTO.getPlayers().forEach(p -> team.appendPlayer(getOrCreatePlayer(p)));
        return team;
    }

    private Player getOrCreatePlayer(Long playerId) {
        Optional<Player> player = playerRepository.findById(playerId);
        return player.orElse(new Player());
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
