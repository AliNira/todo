package org.nira.todo.dto.todo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        title = "Todo Response"
)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TodoResponseDto {
    @Schema(
            description = "Todo id"
    )
    private Long id;
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
    @Schema(
            description = "Todo image URL"
    )
    private String imageUrl;
}
