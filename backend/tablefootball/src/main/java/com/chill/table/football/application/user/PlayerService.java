package com.chill.table.football.application.user;

import com.chill.table.football.application.matches.dto.in.CreateUserRequestDTO;
import com.chill.table.football.application.matches.dto.out.CreateUserResponseDTO;
import com.chill.table.football.application.matches.exception.PlayerAlreadyExistException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


@Transactional
public class PlayerService {

    private PlayerRepository playerRepository;
    private PasswordEncoder passwordEncoder;

    public PlayerService(PlayerRepository playerRepository, PasswordEncoder passwordEncoder) {
        this.playerRepository = Objects.requireNonNull(playerRepository);
        this.passwordEncoder = Objects.requireNonNull(passwordEncoder);
    }

    public CreateUserResponseDTO handle(CreateUserRequestDTO createUserRequestDTO) {
        if (playerRepository.findByUserName(createUserRequestDTO.getUserName()).isPresent()) {
            throw new PlayerAlreadyExistException(createUserRequestDTO.getUserName());
        }

        String rawPassword = createUserRequestDTO.getPassword();
        String hash = passwordEncoder.encode(rawPassword);
        Player player = new Player(createUserRequestDTO.getUserName(), hash,
                createUserRequestDTO.getFirstName(), createUserRequestDTO.getLastName());
        player = playerRepository.save(player);
        return CreateUserResponseDTO.builder()
                .id(player.getId())
                .build();
    }

}
