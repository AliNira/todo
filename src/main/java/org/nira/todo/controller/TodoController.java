package org.nira.todo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.nira.todo.dto.TodoRequestDto;
import org.nira.todo.dto.TodoRequestUpdateDto;
import org.nira.todo.dto.TodoResponseDto;
import org.nira.todo.service.FileStorageService;
import org.nira.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;
    private final FileStorageService fileStorageService;

    @PostMapping
    public ResponseEntity<TodoResponseDto> addTodo(@RequestBody @Valid TodoRequestDto todoRequestDto) {
        TodoResponseDto createdTodo = todoService.addTodo(todoRequestDto);
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> getTodoById(@PathVariable("id") Long id) {
        TodoResponseDto foundTodo = todoService.getTodoById(id);
        return ResponseEntity.ok(foundTodo);
    }

    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> getAllTodos() {
        List<TodoResponseDto> todos = todoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoResponseDto> updateTodo(@RequestBody @Valid TodoRequestUpdateDto todoRequestUpdateDto,
                                                      @PathVariable("id") Long id){
        TodoResponseDto todoResponseDto = todoService.updateTodo(todoRequestUpdateDto, id);
        return new ResponseEntity<>(todoResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable("id") Long id){
        todoService.deleteTodoById(id);
    }
    
    @PatchMapping("/{id}/complete")
    public ResponseEntity<TodoResponseDto> completeTodo(@PathVariable("id") Long id){
        TodoResponseDto todoResponseDto = todoService.completeTodo(id);
        return new ResponseEntity<>(todoResponseDto, HttpStatus.OK);
    }
    
    @PatchMapping("/{id}/in-complete")
    public ResponseEntity<TodoResponseDto> incompleteTodo(@PathVariable("id") Long id){
        TodoResponseDto todoResponseDto = todoService.inCompleteTodo(id);
        return new ResponseEntity<>(todoResponseDto, HttpStatus.OK);
    }

    @PostMapping("/{id}/upload-image")
    public ResponseEntity<TodoResponseDto> uploadImage(@PathVariable Long id,
                                                       @RequestParam("file") MultipartFile file) throws Exception {
        String fileName = fileStorageService.uploadFile(file);
        TodoResponseDto updated = todoService.attachImageToTodo(id, fileName);
        return ResponseEntity.ok(updated);
    }



}
