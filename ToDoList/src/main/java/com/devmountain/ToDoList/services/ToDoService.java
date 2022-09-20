package com.devmountain.ToDoList.services;

import com.devmountain.ToDoList.dtos.ToDoDto;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ToDoService {
    @Transactional
    void addToDoItem(ToDoDto toDoDto, Long userId);

    @Transactional
    void deleteToDoItemById(Long toDoId);

    @Transactional
    void updateToDoItemById(ToDoDto toDoDto);

    List<ToDoDto> getAllToDoItemsByUserId(Long userId);

    Optional<ToDoDto> getToDoItemById(Long toDoId);
}
