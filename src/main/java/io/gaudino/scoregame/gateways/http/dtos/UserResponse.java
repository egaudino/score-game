package io.gaudino.scoregame.gateways.http.dtos;

import io.gaudino.scoregame.domains.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserResponse {

    private List<User> highscores;

    public UserResponse(List<User> userList) {
        this.highscores = userList;
    }
}
