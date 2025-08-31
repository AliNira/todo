package org.nira.todo.service;

import org.nira.todo.dto.todo.TodoRequestDto;
import org.nira.todo.dto.todo.TodoRequestUpdateDto;
import org.nira.todo.dto.todo.TodoResponseDto;

import java.util.List;

public interface TodoService {

    TodoResponseDto addTodo(TodoRequestDto todoRequestDto);

    TodoResponseDto getTodoById(Long id);

    List<TodoResponseDto> getAllTodos();

    TodoResponseDto updateTodo(TodoRequestUpdateDto todoRequestUpdateDto, Long id);

    void deleteTodoById(Long id);

    TodoResponseDto completeTodo(Long id);

    TodoResponseDto inCompleteTodo(Long id);

    TodoResponseDto attachImageToTodo(Long id, String fileName);
}
