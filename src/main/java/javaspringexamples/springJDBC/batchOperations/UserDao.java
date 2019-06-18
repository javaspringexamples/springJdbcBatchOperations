package javaspringexamples.springJDBC.batchOperations;

import java.util.List;

/**
 * 
 * @author mounir.sahrani@gmail.com
 *
 */
public interface UserDao {
	List<User> find(List<Long> userIds);
	void update(final List<User> users);
}