package com.chill.table.football.infrastructure.util;

import com.chill.table.football.application.user.User;
import com.chill.table.football.application.user.ports.outgoing.UserDTO;
import com.chill.table.football.application.util.EntityMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntityMapperDefaultTest {

    @Autowired
    private EntityMapper entityMapper;

    @Mock
    private User user;

    @Before
    public void setUp() {
        Mockito.when(user.getId()).thenReturn(1L);
        Mockito.when(user.getUserName()).thenReturn("admin");
    }

    @Test
    public void map() throws Exception {
        final UserDTO userDTO = entityMapper.map(user, UserDTO.class);
        Assert.assertEquals(user.getId(), userDTO.getId());
        Assert.assertEquals(user.getUserName(), userDTO.getUserName());
    }

    @Test
    public void mapCollection() throws Exception {
        final List<User> users = new ArrayList<>();
        users.add(user);
        final Set<UserDTO> userDTOS = entityMapper.mapCollection(users, UserDTO.class, new TypeToken<HashSet<UserDTO>>() {}.getType());
        final UserDTO userDTO = userDTOS.iterator().next();
        Assert.assertEquals(user.getId(), userDTO.getId());
        Assert.assertEquals(user.getUserName(), userDTO.getUserName());
    }

}