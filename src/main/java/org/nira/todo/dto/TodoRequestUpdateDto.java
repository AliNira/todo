package org.nira.todo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        title = "Todo Update Request"
)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TodoRequestUpdateDto {
    @Schema(
            description = "Todo title"
    )
    private String title;
    @Schema(
            description = "Todo description"
    )
    private String description;
    @Schema(
            description = "Todo completion"
    )
    private Boolean done;
}
