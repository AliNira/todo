package org.nira.todo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.nira.todo.dto.TodoRequestDto;
import org.nira.todo.dto.TodoResponseDto;
import org.nira.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> getTodoById(@PathVariable("id") Long id) {
        TodoResponseDto foundTodo = todoService.getTodoById(id);
        return new ResponseEntity<>(foundTodo, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> getTodos() {
        List<TodoResponseDto> todos = todoService.getTodos();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }
}
