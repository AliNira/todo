package org.nira.todo.dto.todo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoSearchCriteria {
    private String title;
    private String description;
    private Boolean done;
    private Boolean hasImage;
    private String search;
}