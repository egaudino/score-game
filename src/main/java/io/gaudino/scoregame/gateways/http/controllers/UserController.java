package io.gaudino.scoregame.gateways.http.controllers;

import io.gaudino.scoregame.domains.User;
import io.gaudino.scoregame.gateways.data.UserMemoryDataGateway;
import io.gaudino.scoregame.gateways.http.dtos.UserDTO;
import io.gaudino.scoregame.gateways.http.dtos.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/score")
public class UserController {

    private final UserMemoryDataGateway userMemoryGateway;

    @PostMapping
    public void addScore(@RequestBody @Valid UserDTO userDTO) {
        userMemoryGateway.add(userDTO.toDomain());
    }

    @GetMapping("/{id}/position")
    public User getUser(@PathVariable Long id) {
        return userMemoryGateway.getAllRanking()
                .stream()
                .filter(user -> id.equals(user.getUserId()))
                .findAny()
                .orElse(null);
    }

    @GetMapping("/highscorelist")
    public UserResponse getHighScoreList() {
        return new UserResponse(userMemoryGateway.getAllRanking());
    }
}
