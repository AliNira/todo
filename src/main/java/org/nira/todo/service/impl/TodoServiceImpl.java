package org.nira.todo.service.impl;

import lombok.RequiredArgsConstructor;
import org.nira.todo.dto.todo.TodoRequestDto;
import org.nira.todo.dto.todo.TodoRequestUpdateDto;
import org.nira.todo.dto.todo.TodoResponseDto;
import org.nira.todo.dto.todo.TodoSearchCriteria;
import org.nira.todo.entity.Todo;
import org.nira.todo.exception.ResourceNotFoundException;
import org.nira.todo.repository.TodoRepo;
import org.nira.todo.repository.specification.TodoSpecification;
import org.nira.todo.service.TodoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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
    public Page<TodoResponseDto> getAllTodos(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Todo> todos = todoRepo.findAll(pageable);
        return todos.map(MAPPER::mapToTodoResponseDto);
    }

    @Override
    public Page<TodoResponseDto> searchTodos(TodoSearchCriteria criteria, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Specification<Todo> specification = TodoSpecification.buildSpecification(criteria);
        Page<Todo> todos = todoRepo.findAll(specification, pageable);
        return todos.map(MAPPER::mapToTodoResponseDto);
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
        todoRepo.findById(id).
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

    @Override
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
