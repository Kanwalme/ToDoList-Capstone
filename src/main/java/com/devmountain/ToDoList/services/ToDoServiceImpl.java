package com.devmountain.ToDoList.services;

import com.devmountain.ToDoList.dtos.ToDoDto;
import com.devmountain.ToDoList.entities.ToDo;
import com.devmountain.ToDoList.entities.User;

import com.devmountain.ToDoList.repositories.ToDoRepository;
import com.devmountain.ToDoList.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ToDoServiceImpl implements ToDoService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ToDoRepository toDoRepository;

    @Override
    @Transactional
    public void addToDoItem(ToDoDto toDoDto, Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        ToDo toDo = new ToDo(toDoDto);
        userOptional.ifPresent(toDo::setUser);
        toDoRepository.saveAndFlush(toDo);
    }
    @Override
    @Transactional
    public void deleteToDoItemById(Long toDoId){
        Optional<ToDo> toDoOptional = toDoRepository.findById(toDoId);
        toDoOptional.ifPresent(toDo -> toDoRepository.delete(toDo));
    }
    @Override
    @Transactional
    public void updateToDoItemById(ToDoDto toDoDto){
        Optional<ToDo> toDoOptional = toDoRepository.findById(toDoDto.getId());
        toDoOptional.ifPresent(toDo -> {
            toDo.setItem(toDoDto.getItem());
            toDo.setDate(toDoDto.getDate());
            toDo.setCompleted(toDoDto.getCompleted());
            toDoRepository.saveAndFlush(toDo);
        });
}
    @Override
    public List<ToDoDto> getAllToDoItemsByUserId(Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            List<ToDo> toDoList = toDoRepository.findAllByUserEquals(userOptional.get());
            return toDoList.stream().map(toDo -> new ToDoDto(toDo)).collect(Collectors.toList());
        }
        return Collections.emptyList();
}
    @Override
    public Optional <ToDoDto> getToDoItemById(Long toDoId) {
        Optional <ToDo> toDoOptional = toDoRepository.findById(toDoId);
        if (toDoOptional.isPresent()) {
            return Optional.of(new ToDoDto(toDoOptional.get()));
        }
        return Optional.empty();
}

}
