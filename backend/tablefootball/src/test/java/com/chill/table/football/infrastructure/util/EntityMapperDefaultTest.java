//package com.chill.table.football.infrastructure.util;
//
//import com.chill.table.football.application.user.User;
//import com.chill.table.football.application.user.ports.outgoing.UserDTO;
//import com.chill.table.football.application.util.EntityMapper;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//@RunWith(SpringRunner.class)
//public class EntityMapperDefaultTest {
//
//    @Autowired
//    private EntityMapper entityMapper;
//
//    @Mock
//    private User user;
//
//    @Before
//    public void setUp() {
//        Mockito.when(user.getId()).thenReturn(1L);
//        Mockito.when(user.getUserName()).thenReturn("admin");
//    }
//
//    @Test
//    public void map() throws Exception {
//        final UserDTO userDTO = entityMapper.map(user, UserDTO.class);
//        Assert.assertEquals(userDTO.getId(), user.getId());
//        Assert.assertEquals(userDTO.getUserName(), user.getUserName());
//    }
//
//    @Test
//    public void mapCollection() throws Exception {
//        final Collection<User> users = new ArrayList<>();
//        users.add(user);
//        final Collection<UserDTO> userDTOS = entityMapper.mapCollection(users, UserDTO.class);
//        Assert.assertEquals(userDTOS.iterator().next().getId(), user.getId());
//        Assert.assertEquals(userDTOS.iterator().next().getUserName(), user.getUserName());
//    }
//
//}