package io.gaudino.scoregame.gateways.http.dtos;

import io.gaudino.scoregame.domains.User;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDTO {

    @NotNull(message = "Id cannot be null")
    private Long id;
    @NotNull(message = "Score cannot be null")
    private Integer score;

    public User toDomain() {
        return User.builder()
                .userId(this.id)
                .score(this.score)
                .build();
    }

}
