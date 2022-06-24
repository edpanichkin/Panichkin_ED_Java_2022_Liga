import static repository.MemoryRepository.*;
import static util.Menu.mainMenu;

public class Main {

  public static void main(String[] args) {
    loadDataToProgram();
    while (true) {
      mainMenu();
    }
  }
}