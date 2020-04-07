package io.gaudino.scoregame.domains;

import lombok.Data;

@Data
public class Scoreboard {

    private Long userId;
    private Integer maxScore;
    private Integer position;

    public Scoreboard(Long userId, Integer score) {
        this.userId = userId;
        this.maxScore = score;
    }

}
