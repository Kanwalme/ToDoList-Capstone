package com.devmountain.ToDoList.controllers;

import com.devmountain.ToDoList.dtos.ToDoDto;
import com.devmountain.ToDoList.services.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class ToDoController {
@Autowired
    private ToDoService toDoService;

@GetMapping("/user/{userId}")
    public List<ToDoDto> getToDolistByUser(@PathVariable Long userId) {
    return toDoService.getAllToDoItemsByUserId(userId);
}
@GetMapping("/{toDoId}")
    public Optional<ToDoDto> getToDoItemById(@PathVariable Long toDoId) {
    return toDoService.getToDoItemById(toDoId);
}
@PostMapping("/user/{userId}")
    public void addToDoItem(@RequestBody ToDoDto toDoDto, @PathVariable Long userId){
    toDoService.addToDoItem(toDoDto, userId);
}
@DeleteMapping("/{toDoId}")
    public void deleteToDoItemById(@PathVariable Long toDoId){
    toDoService.deleteToDoItemById(toDoId);
}
@PutMapping
    public void updateToDoItem(@RequestBody ToDoDto toDoDto) {
    toDoService.updateToDoItemById(toDoDto);
}
}
