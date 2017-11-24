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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
//        final List<User> users = new ArrayList<>();
//        final User user2 = new User("Asd", "Sdfsdf");
//        users.add(user2);
//        TypeToken.of(new HashSet<>().getClass())
//                entityMapper.mapCollection(users, klazz.getClass());
//        final UserDTO userDTO = userDTOS.iterator().next();
//        Assert.assertEquals(user2.getId(), userDTO.getId());
//        Assert.assertEquals(user2.getUserName(), userDTO.getUserName());
    }

}