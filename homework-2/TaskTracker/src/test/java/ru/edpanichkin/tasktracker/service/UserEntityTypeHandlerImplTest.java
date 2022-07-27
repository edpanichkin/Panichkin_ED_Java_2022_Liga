//package ru.edpanichkin.tasktracker.service;
//
//import org.junit.jupiter.api.*;
//import org.springframework.boot.test.context.SpringBootTest;
//import ru.edpanichkin.tasktracker.model.EntityType;
//import ru.edpanichkin.tasktracker.model.User;
//import ru.edpanichkin.tasktracker.repo.MemRepo;
//import ru.edpanichkin.tasktracker.service.entity.EntityFactory;
//import ru.edpanichkin.tasktracker.service.entity.UserEntityTypeHandlerImpl;
//
//import java.util.Comparator;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class UserEntityTypeHandlerImplTest {
//  UserEntityTypeHandlerImpl userEntityHandler = new UserEntityTypeHandlerImpl();
//  Map<Integer, User> userMap = userEntityHandler.getMap();
//
//  @BeforeEach
//  void init() {
//    MemRepo.loadDataToProgram();
//  }
//
//  @Order(1)
//  @Test
//  void view_user_by_Id_String_true() {
//    String viewUser1 = CommandHandler.parseCommand("view user 1");
//    String viewUser2 = CommandHandler.parseCommand("view user 2");
//    String viewUser3 = CommandHandler.parseCommand("view user 3");
//    User user1 = userMap.get(1);
//    String stringUser1 = user1.getId() + " " + user1.getUserName() + " " + userMap.get(1).getTaskList()
//            .stream().sorted(Comparator.comparing(t -> t.getTaskStatus().ordinal()))
//            .collect(Collectors.toList());
//    User user2 = userMap.get(2);
//    String stringUser2 = user2.getId() + " " + user2.getUserName() + " " + userMap.get(2).getTaskList()
//            .stream().sorted(Comparator.comparing(t -> t.getTaskStatus().ordinal()))
//            .collect(Collectors.toList());
//    User user3 = userMap.get(3);
//    String stringUser3 = user3.getId() + " " + user3.getUserName() + " " + userMap.get(3).getTaskList()
//            .stream().sorted(Comparator.comparing(t -> t.getTaskStatus().ordinal()))
//            .collect(Collectors.toList());
//
//    Assertions.assertEquals(viewUser1, stringUser1);
//    Assertions.assertEquals(viewUser2, stringUser2);
//    Assertions.assertEquals(viewUser3, stringUser3);
//  }
//
//  @Order(2)
//  @Test
//  void add_new_user() {
//    int newUserId = MemRepo.getNextId(EntityType.USER);
//    CommandHandler.parseCommand("add user Egor");
//    Assertions.assertEquals(newUserId, userMap.get(newUserId).getId());
//    Assertions.assertEquals("Egor", userMap.get(newUserId).getUserName());
//  }
//
//  @Order(3)
//  @Test
//  void delete_user_return_true() {
//    int userMapSize = userMap.size();
//    System.out.println(userMap.toString());
//    CommandHandler.parseCommand("deLeTe   User   4");
//    Assertions.assertEquals(--userMapSize, userMap.size());
//    Assertions.assertNull(userMap.get(4));
//  }
//
//  @Test
//  @Order(4)
//  @DisplayName("edit user status / get true")
//  void edit_Name_return_True() {
//    CommandHandler.parseCommand("edit User 1 petya");
//    CommandHandler.parseCommand("EdIT   uSEr    2   Vasya");
//    Assertions.assertEquals("petya", userMap.get(1).getUserName());
//    Assertions.assertEquals("Vasya", userMap.get(2).getUserName());
//    CommandHandler.parseCommand("EdIT   user    2    abc");
//    Assertions.assertEquals("abc", userMap.get(2).getUserName());
//  }
//
//  @Test
//  @Order(5)
//  void getType_Return_True() {
//    Assertions.assertEquals(EntityType.USER, EntityFactory.getCommander(EntityType.USER).getType());
//  }
//}