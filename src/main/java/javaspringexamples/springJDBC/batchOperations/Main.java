package javaspringexamples.springJDBC.batchOperations;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 
 * @author mounir.sahrani@gmail.com
 *
 */
public class Main {
	public static void main(String[] args) throws SQLException {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Conf.class);

		List<Long> usersIds = new ArrayList<>();
		usersIds.add(65L);
		usersIds.add(256L);
		usersIds.add(999L);
		
		UserDao userDao = applicationContext.getBean(UserDao.class);
		List<User> users = userDao.find(usersIds);
		for (User user : users) {
			System.out.println(user.toString());
			user.setName("Waaaaaaw");
			user.setUserName("No no noooooooooo");
		}

		User u = new User();
		u.setId(36L);
		u.setName(":-|");
		users.add(u);
		
		userDao.update(users);

		users = userDao.find(usersIds);
		for (User user : users) {
			System.out.println(user.toString());
		}
	}
}