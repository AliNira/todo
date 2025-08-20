package org.nira.todo.service.impl;

import lombok.RequiredArgsConstructor;
import org.nira.todo.dto.TodoRequestDto;
import org.nira.todo.dto.TodoRequestUpdateDto;
import org.nira.todo.dto.TodoResponseDto;
import org.nira.todo.entity.Todo;
import org.nira.todo.exception.ResourceNotFoundException;
import org.nira.todo.repository.TodoRepo;
import org.nira.todo.service.TodoService;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.nira.todo.mapper.TodoMapper.MAPPER;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepo todoRepo;

    @Override
    public TodoResponseDto addTodo(TodoRequestDto todoRequestDto) {
        Todo todo = MAPPER.mapToTodo(todoRequestDto);
        Todo savedTodo = todoRepo.save(todo);
        return MAPPER.mapToTodoResponseDto(savedTodo);
    }

    @Override
    public TodoResponseDto getTodoById(Long id) {
        Todo todo = todoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo Not Found with id: " + id));
        return MAPPER.mapToTodoResponseDto(todo);
    }

    @Override
    public List<TodoResponseDto> getAllTodos() {
        List<Todo> todos = todoRepo.findAll();
        return todos.stream().map(MAPPER::mapToTodoResponseDto).toList();
    }

    @Override
    public TodoResponseDto updateTodo(TodoRequestUpdateDto todoRequestUpdateDto, Long id) {
        Todo todo = todoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo Not Found with id: " + id));
        applyNonNullFields(todoRequestUpdateDto, todo);
        Todo savedTodo = todoRepo.save(todo);
        return MAPPER.mapToTodoResponseDto(savedTodo);
    }

    @Override
    public void deleteTodoById(Long id) {
        Todo todo = todoRepo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Todo Not Found with id: " + id));
        todoRepo.deleteById(id);
    }

    @Override
    public TodoResponseDto completeTodo(Long id) {
        Todo todo = todoRepo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Todo Not Found with id: " + id));
        todo.setDone(Boolean.TRUE);
        Todo completeTodo = todoRepo.save(todo);
        return MAPPER.mapToTodoResponseDto(completeTodo);
    }

    @Override
    public TodoResponseDto inCompleteTodo(Long id) {
        Todo todo = todoRepo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Todo Not Found with id: " + id));
        todo.setDone(Boolean.FALSE);
        Todo completeTodo = todoRepo.save(todo);
        return MAPPER.mapToTodoResponseDto(completeTodo);
    }

    public TodoResponseDto attachImageToTodo(Long id, String fileName) {
        Todo todo = todoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
        todo.setImageUrl(fileName);
        todoRepo.save(todo);
        return MAPPER.mapToTodoResponseDto(todo);
    }

    private void applyNonNullFields(TodoRequestUpdateDto dto, Todo entity){
        if(dto.getTitle() != null) entity.setTitle(dto.getTitle());
        if(dto.getDescription() != null) entity.setDescription(dto.getDescription());
        if(dto.getDone() != null) entity.setDone(dto.getDone());
    }

}
