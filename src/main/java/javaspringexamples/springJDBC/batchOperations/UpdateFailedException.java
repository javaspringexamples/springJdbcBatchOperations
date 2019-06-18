package javaspringexamples.springJDBC.batchOperations;

import org.springframework.dao.DataAccessException;

/**
 * 
 * @author mounir.sahrani@gmail.com
 *
 */
public class UpdateFailedException extends DataAccessException {
	public UpdateFailedException(String msg) {
		super(msg);
	}
}
