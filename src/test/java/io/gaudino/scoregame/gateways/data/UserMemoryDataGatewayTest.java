package io.gaudino.scoregame.gateways.data;

import io.gaudino.scoregame.domains.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserMemoryDataGatewayTest {

    @Spy
    @InjectMocks
    UserMemoryDataGateway userMemoryDataGateway;

    static Map<Long, User> userMap;

    @Spy
    List<User> userList;

    User user;

    @Before
    public void init() {
        userMap = new ConcurrentHashMap<>();
        user = new User();
    }

    @Test
    public void shouldAddUser() {
        user.setUserId(1L);
        user.setScore(20);

        when(userMemoryDataGateway.getAllRanking()).thenReturn(Collections.singletonList(user));

        userMemoryDataGateway.add(user);
        List<User> userList = userMemoryDataGateway.getAllRanking();

        assertEquals(userList.stream().findFirst().get().getUserId(), user.getUserId());
    }

    @Test
    public void shoudGetRankedList() {
        User user1 = new User();
        user1.setUserId(1L);
        user1.setScore(20);

        User user2 = new User();
        user2.setUserId(2L);
        user2.setScore(40);

        User user3 = new User();
        user3.setUserId(3L);
        user3.setScore(60);

        userMemoryDataGateway.add(user1);
        userMemoryDataGateway.add(user2);
        userMemoryDataGateway.add(user3);

        userList = userMemoryDataGateway.getAllRanking();

        assertEquals(userList.get(0).getPosition(), Integer.valueOf(1));
        assertEquals(userList.get(1).getPosition(), Integer.valueOf(2));
        assertEquals(userList.get(2).getPosition(), Integer.valueOf(3));
    }

}