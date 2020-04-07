package io.gaudino.scoregame.gateways.data;

import io.gaudino.scoregame.domains.User;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.toList;

@Component
public class UserMemoryDataGateway {

    private static Map<Long, User> userMap = new ConcurrentHashMap<>();

    private static List<User> orderedList = new ArrayList<>();

    public void add(User user) {
        if (userMap.containsKey(user.getUserId())) {
            update(user.getUserId(), user.getScore());
        } else {
            userMap.putIfAbsent(user.getUserId(), user);
        }
    }

    private void update(Long id, Integer score) {
        userMap.compute(id, (key, value) -> {
            Integer sum = value.getScore() + score;
            value.setScore(sum);
            return value;
        });
    }

    public List<User> getAllRanking() {
        Integer position = 1;
        orderedList = userMap.values()
                .stream()
                .sorted(Comparator.comparingInt(User::getScore).reversed())
                .limit(20)
                .collect(toList());

        for (User user : orderedList) {
            user.setPosition(position);
            position++;
        }

        return orderedList;
    }


}
