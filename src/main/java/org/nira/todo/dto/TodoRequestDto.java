package org.nira.todo.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TodoRequestDto {
    @NotEmpty(message = "Todo title can't be null or empty")
    private String title;
    @NotEmpty(message = "Todo description can't be null or empty")
    private String description;
    private Boolean done;
}
