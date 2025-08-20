package org.nira.todo.dto;

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
            description = "Todo ID"
    )
    private Long id;
    @Schema(
            description = "Todo Title"
    )
    private String title;
    @Schema(
            description = "Todo Description"
    )
    private String description;
    @Schema(
            description = "Todo Completion"
    )
    private Boolean done;
    @Schema(
            description = "Todo Image URL"
    )
    private String imageUrl;
}
