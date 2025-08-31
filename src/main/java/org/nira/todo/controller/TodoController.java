package org.nira.todo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.nira.todo.dto.todo.TodoRequestDto;
import org.nira.todo.dto.todo.TodoRequestUpdateDto;
import org.nira.todo.dto.todo.TodoResponseDto;
import org.nira.todo.service.minio.FileStorageService;
import org.nira.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(
        name = "Todo"
)
@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;
    private final FileStorageService fileStorageService;

    @Operation(
            summary = "Create Todo"
    )
    @ApiResponse(
            responseCode = "201"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TodoResponseDto> addTodo(@RequestBody @Valid TodoRequestDto todoRequestDto) {
        TodoResponseDto createdTodo = todoService.addTodo(todoRequestDto);
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Todo by Id"
    )
    @ApiResponse(
            responseCode = "200"
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> getTodoById(@PathVariable("id") Long id) {
        TodoResponseDto foundTodo = todoService.getTodoById(id);
        return ResponseEntity.ok(foundTodo);
    }

    @Operation(
            summary = "Get All Todos"
    )
    @ApiResponse(
            responseCode = "200"
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> getAllTodos() {
        List<TodoResponseDto> todos = todoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }

    @Operation(
            summary = "Update Todo"
    )
    @ApiResponse(
            responseCode = "200"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<TodoResponseDto> updateTodo(@RequestBody TodoRequestUpdateDto todoRequestUpdateDto,
                                                      @PathVariable("id") Long id){
        TodoResponseDto todoResponseDto = todoService.updateTodo(todoRequestUpdateDto, id);
        return new ResponseEntity<>(todoResponseDto, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete Todo"
    )
    @ApiResponse(
            responseCode = "204"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id){
        todoService.deleteTodoById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
    }

    @Operation(
            summary = "Complete Todo"
    )
    @ApiResponse(
            responseCode = "200"
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("/{id}/complete")
    public ResponseEntity<TodoResponseDto> completeTodo(@PathVariable("id") Long id){
        TodoResponseDto todoResponseDto = todoService.completeTodo(id);
        return new ResponseEntity<>(todoResponseDto, HttpStatus.OK);
    }

    @Operation(
            summary = "Incomplete Todo"
    )
    @ApiResponse(
            responseCode = "200"
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("/{id}/in-complete")
    public ResponseEntity<TodoResponseDto> incompleteTodo(@PathVariable("id") Long id){
        TodoResponseDto todoResponseDto = todoService.inCompleteTodo(id);
        return new ResponseEntity<>(todoResponseDto, HttpStatus.OK);
    }

    @Operation(
            summary = "Add Image to Todo"
    )
    @ApiResponse(
            responseCode = "200"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/upload-image")
    public ResponseEntity<TodoResponseDto> uploadImage(@PathVariable Long id,
                                                       @RequestParam("file") MultipartFile file) throws Exception {
        String fileName = fileStorageService.uploadFile(file);
        TodoResponseDto updated = todoService.attachImageToTodo(id, fileName);
        return ResponseEntity.ok(updated);
    }



}
