package org.nira.todo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.nira.todo.dto.TodoRequestDto;
import org.nira.todo.dto.TodoResponseDto;
import org.nira.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponseDto> addTodo(@RequestBody @Valid TodoRequestDto todoRequestDto) {
        TodoResponseDto createdTodo = todoService.addTodo(todoRequestDto);
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }
}
