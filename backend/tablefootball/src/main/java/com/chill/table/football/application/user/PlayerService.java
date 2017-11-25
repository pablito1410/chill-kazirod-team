package com.chill.table.football.application.user;

import com.chill.table.football.application.matches.dto.in.CreateUserRequestDTO;
import com.chill.table.football.application.matches.dto.out.CreateUserResponseDTO;


public class PlayerService {

    private PlayerRepository playerRepository;
    private PasswordEncoder passwordEncoder;

    public PlayerService(PlayerRepository playerRepository, PasswordEncoder passwordEncoder) {
        this.playerRepository = playerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public CreateUserResponseDTO handle(CreateUserRequestDTO createUserRequestDTO) {
        playerRepository.findByUserNameOrThrow(createUserRequestDTO.getUserName());

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
