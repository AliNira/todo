package org.nira.todo.service;

import org.nira.todo.dto.todo.TodoRequestDto;
import org.nira.todo.dto.todo.TodoRequestUpdateDto;
import org.nira.todo.dto.todo.TodoResponseDto;
import org.nira.todo.dto.todo.TodoSearchCriteria;
import org.springframework.data.domain.Page;

public interface TodoService {

    TodoResponseDto addTodo(TodoRequestDto todoRequestDto);

    TodoResponseDto getTodoById(Long id);

    Page<TodoResponseDto> getAllTodos(int page, int size);

    Page<TodoResponseDto> searchTodos(TodoSearchCriteria criteria, int page, int size);

    TodoResponseDto updateTodo(TodoRequestUpdateDto todoRequestUpdateDto, Long id);

    void deleteTodoById(Long id);

    TodoResponseDto completeTodo(Long id);

    TodoResponseDto inCompleteTodo(Long id);

    TodoResponseDto attachImageToTodo(Long id, String fileName);
}
