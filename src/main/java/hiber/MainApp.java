package hiber;
import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.sql.SQLException;
public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      Car car1 = new Car("Lada", "444");
      Car car2 = new Car("Doudge", "445");

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");

      user1.setCar(car1);
      user2.setCar(car2);

      UserService userService = context.getBean(UserService.class);
      userService.add(user1);
      userService.add(user2);

      User retrievedUser1 = userService.findUserByCarModelAndSeries("Lada", Integer.parseInt("444"));
      User retrievedUser2 = userService.findUserByCarModelAndSeries("Doudge", Integer.parseInt("445"));

      System.out.println("Retrieved User 1: " + retrievedUser1);
      System.out.println("Retrieved User 2: " + retrievedUser2);
      context.close();
   }
}