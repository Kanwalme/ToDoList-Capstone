package com.devmountain.ToDoList.repositories;

import com.devmountain.ToDoList.entities.ToDo;
import com.devmountain.ToDoList.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
    public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    List<ToDo> findAllByUserEquals(User user);
    }

