package com.chill.table.football;

import com.chill.table.football.application.matches.dto.in.CreateMatchRequestDTO;
import com.chill.table.football.application.user.ports.incoming.CreateUserCommand;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@AutoConfigureJsonTesters
@SpringBootTest
public class JsonGenerator {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void generateCreateMatchJson() throws JsonProcessingException {
        CreateMatchRequestDTO createMatchRequestDTO = new CreateMatchRequestDTO(createTeam(1L, 2L), createTeam(3L, 4L), LocalDateTime.now().plusMinutes(30));
        String json = objectMapper.writeValueAsString(createMatchRequestDTO);
        System.out.println(json);
    }

    @Test
    public void generateCreateUserJson() throws JsonProcessingException {
        CreateUserCommand createUserCommand = new CreateUserCommand("admin", "admin1");
        String json = objectMapper.writeValueAsString(createUserCommand);
        System.out.println(json);
    }

    private CreateMatchRequestDTO.Team createTeam(Long firstId, Long secondId) {
        return new CreateMatchRequestDTO.Team(ImmutableSet.of(firstId, secondId));
    }
}
