package com.chill.table.football.application.matches;

import com.chill.table.football.application.matches.dto.in.CreateMatchRequestDTO;
import com.chill.table.football.application.matches.dto.in.SetWinnerRequestDTO;
import com.chill.table.football.application.matches.dto.out.CreateMatchResponseDTO;
import com.chill.table.football.application.matches.dto.out.SetWinnerResponseDTO;
import com.chill.table.football.application.user.User;
import com.chill.table.football.application.user.UserService;
import com.google.common.collect.ImmutableSet;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
public class MatchesService {

    private MatchesRepository matchesRepository;
    private UserService userService;

    public MatchesService(MatchesRepository matchesRepository, UserService userService) {
        this.matchesRepository = Objects.requireNonNull(matchesRepository);
        this.userService = Objects.requireNonNull(userService);
    }

    public CreateMatchResponseDTO createMatch(CreateMatchRequestDTO createMatchRequestDTO) {
        Objects.requireNonNull(createMatchRequestDTO);

        CreateMatchRequestDTO.Team firstTeamDto = createMatchRequestDTO.getFirstTeam();
        Team firstTeam = Team.fromDTO(firstTeamDto.getName(), loadUsers(firstTeamDto.getUsers()));

        CreateMatchRequestDTO.Team secondTeamDto = createMatchRequestDTO.getSecondTeam();
        Team secondTeam = Team.fromDTO(firstTeamDto.getName(), loadUsers(firstTeamDto.getUsers()));

        Match match = new Match(firstTeam, secondTeam);
        match = matchesRepository.save(match);

        return match.toCreateMatchResponseDTO();
    }

    private Set<User> loadUsers(Set<Long> userIds) {
        return userIds.stream()
                .map(userService::getUser)
                .collect(Collectors.toSet());
    }

    public SetWinnerResponseDTO setWinner(SetWinnerRequestDTO setWinnerRequestDTO) {
        Objects.requireNonNull(setWinnerRequestDTO);

        return SetWinnerResponseDTO.builder().build();
    }
}
