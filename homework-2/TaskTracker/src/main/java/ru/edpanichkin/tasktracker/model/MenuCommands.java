package ru.edpanichkin.tasktracker.model;

import lombok.extern.slf4j.Slf4j;
import ru.edpanichkin.tasktracker.repo.MemRepo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public enum MenuCommands {
  EXIT("exit") {
    @Override
    public String doCommand() {
      System.exit(0);
      return "Shutdown the System";
    }
  },
  VIEW_ALL("view_all") {
    @Override
    public String doCommand() {
      return MemRepo.usersMap.values().toString();
    }
  },
  FREE_MEMORY("free_memory") {
    @Override
    public String doCommand() {
      MemRepo.cleanMemoryData();
      return "Memory db was cleaned";
    }
  },
  LOAD_DATA("load_data") {
    @Override
    public String doCommand() {
      MemRepo.loadDataToProgram();
      return "DATA was loaded to Memory db";
    }
  },
  EDIT("EDIT") {
    // 0     1  id  status
    //edit task 1     1
    @Override
    public String throwCommand(String[] command) {
      log.error(Arrays.toString(command));
      switch (command[1].toLowerCase()) {
        case "task": {
          int taskId = Integer.parseInt(command[2]);
          TaskStatus taskStatus = TaskStatus.values()[Integer.parseInt(command[3])];
          MemRepo.tasksMap.get(taskId).setTaskStatus(taskStatus);
          return "TASK STATUS " + taskId + " changed to " + taskStatus;
        }
        case "user": {
          int userId = Integer.parseInt(command[2]);
          MemRepo.usersMap.get(userId).setUserName(command[3]);
          return "USERID " + userId + " name changed to " + command[3];
        }
      }
      return null;
  }
},
        DELETE("DELETE"){
@Override
public String throwCommand(String[]command){
        log.error(Arrays.toString(command));
        switch(command[1].toLowerCase()){
        case"task":{
        int taskId=Integer.parseInt(command[2]);
        int userId=MemRepo.tasksMap.get(taskId).getUserId();
        MemRepo.usersMap.get(userId).getTasksMapInUser().remove(taskId);
        MemRepo.tasksMap.remove(taskId);
        return"DELETED TASK "+taskId+" and in userId "+userId;
        }
        case"user":
        return MemRepo.usersMap.remove(Integer.parseInt(command[2])).toString();
        }
        return null;
        }
        },
        ADD("ADD"){
@Override
public String throwCommand(String[]command){
        log.error(Arrays.toString(command));
        switch(command[1].toLowerCase()){
        case"task":{
        int id=0;
        Optional<Integer> maxId=MemRepo.tasksMap.keySet().stream().max(Integer::compare);
        if(maxId.isPresent()){
        id=maxId.get()+1;
        log.error("MAXID "+maxId.get());
        }
        // 0    1     2         3       4     5     6
        //add task taskName taskInfo userId date taskStatus
        //add task Spring Внесена_из_Веба 3 29.07.2022
        Task task=new Task(id,
        command[2],
        command[3],
        Integer.parseInt(command[4]),
        LocalDate.parse(command[5],DateTimeFormatter.ofPattern("dd.MM.yyyy")),
        TaskStatus.NEW);
        MemRepo.tasksMap.put(id,task);
        return"Task added / id: "+id;
        }
        case"user":{
        int id=0;
        Optional<Integer> maxId=MemRepo.usersMap.keySet().stream().max(Integer::compare);
        if(maxId.isPresent()){
        id=maxId.get()+1;
        log.error("MAXID "+maxId.get());
        }
        // 0    1     2
        //add user userName
        //add user Spring
        User user=new User(id,command[2]);
        MemRepo.usersMap.put(id,user);
        return"User added / id: "+id;
        }
        }
        return null;
        }
        },
        VIEW("VIEW"){
@Override
public String throwCommand(String[]command){
        log.error(Arrays.toString(command));
        switch(command[1].toLowerCase()){
        case"task":
        return MemRepo.tasksMap.get(Integer.parseInt(command[2])).toString();
        case"user":
        return MemRepo.usersMap.get(Integer.parseInt(command[2]))
        .getTasksMapInUser()
        .values()
        .stream().sorted(Comparator.comparing(t->t.getTaskStatus().ordinal()))
        .collect(Collectors.toList()).toString();
        }
        return null;
        }
        },
        SAVE("save"){
@Override
public String doCommand(){
        MemRepo.saveState();
        return"Данные сохранены";
        }
        };
private final String status;


        MenuCommands(String status){
        this.status=status;
        }

public String getStatus(){
        return status;
        }

public String doCommand(){
        return"??";
        }

public String throwCommand(String[]command){
        return Arrays.toString(command);
        }

@Override
public String toString(){
        return status;
        }


        }
