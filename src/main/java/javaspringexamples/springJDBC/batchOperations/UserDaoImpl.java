package javaspringexamples.springJDBC.batchOperations;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

/**
 * 
 * @author mounir.sahrani@gmail.com
 *
 */
public class UserDaoImpl implements UserDao {

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private UserRowMapper userRowMapper = new UserRowMapper();

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
	}

	public List<User> find(List<Long> userIds) {
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource("userIds", userIds);
		return namedParameterJdbcTemplate.query("select * from user where id in (:userIds)", sqlParameterSource,
				userRowMapper);
	}

	public void update(final List<User> users) {
		int[] counts = jdbcTemplate.batchUpdate("update user set (name, user_name, locked) = (?,?,?) where id = ?",
				new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						User user = users.get(i);
						ps.setString(1, user.getName());
						ps.setString(2, user.getUserName());
						ps.setBoolean(3, user.isLocked());
						ps.setLong(4, user.getId());
					}

					public int getBatchSize() {
						return users.size();
					}
				});
		int i = 0;
		for (int count : counts) {
			if (count == 0)
				throw new UpdateFailedException("Row not updated :" + i);
			i++;
		}
	}
}
