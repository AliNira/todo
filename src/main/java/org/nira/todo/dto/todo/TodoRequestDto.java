package org.nira.todo.dto.todo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        title = "Todo Request"
)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TodoRequestDto {
    @Schema(
            description = "Todo title"
    )
    @NotEmpty(message = "Todo title can't be null or empty")
    private String title;
    @Schema(
            description = "Todo description"
    )
    @NotEmpty(message = "Todo description can't be null or empty")
    private String description;
    @Schema(
            description = "Todo completion"
    )
    private Boolean done;

}
