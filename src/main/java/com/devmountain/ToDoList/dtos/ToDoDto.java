package com.devmountain.ToDoList.dtos;

import com.devmountain.ToDoList.entities.ToDo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoDto implements Serializable {

    private Long id;
    private String item;
    private String date;
    private Boolean completed;

    private UserDto userDto;

    public ToDoDto(ToDo toDo) {
        if (toDo.getId() != null) {
            this.id = toDo.getId();
        }
        if (toDo.getItem() != null) {
            this.item = toDo.getItem();
        }
//        if (toDo.getDate() != null) {
//            this.date = toDo.getDate();
//        }
//       this.completed = toDo.getCompleted();
//        this.completed = false;
        }

    }

