package com.devmountain.ToDoList.entities;

import com.devmountain.ToDoList.dtos.ToDoDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ToDo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(columnDefinition = "text")
    private String item;

    @Column
    private String date;

    @Column
    private Boolean completed;


    @ManyToOne
    @JsonBackReference
    private User user;

    public ToDo(ToDoDto toDoDto){
        if (toDoDto.getItem() != null) {
            this.item = toDoDto.getItem();
        }
        if (toDoDto.getDate() != null) {
            this.date = toDoDto.getDate();
        }
        this.completed = toDoDto.getCompleted();

    }
}


