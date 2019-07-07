package in.anandm.msl.userservice.infrastructure.jdbc;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import in.anandm.msl.userservice.model.UserDetails;
import in.anandm.msl.userservice.model.UserRepository;

@Repository
public class JDBCUserRepository implements UserRepository {

	private QueryStore queryStore;
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	
	@Autowired
	public JDBCUserRepository(QueryStore queryStore, NamedParameterJdbcTemplate jdbcTemplate) {
		super();
		this.queryStore = queryStore;
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public UserDetails findUserByEmail(String emailId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("email", emailId);
		
		UserDetails found = jdbcTemplate.query(queryStore.getQuery("findUserByEmail"), paramMap,(rs) -> {

			UserDetails userDetails = null;
			while (rs.next()) {
				userDetails = new UserDetails();
				userDetails.setFirstName(rs.getString("first_name"));
				userDetails.setLastName(rs.getString("last_name"));
				userDetails.setEmail(rs.getString("email"));
				userDetails.setPassword(rs.getString("password"));
				userDetails.setEnabled(rs.getInt("enabled") == 1 ? Boolean.TRUE : Boolean.FALSE);
				userDetails.setPasswordExipresAt(rs.getDate("password_expires_at"));
			}

			return userDetails;

		});

		return found;
	}

	@Override
	public void save(UserDetails userDetails) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("firstName", userDetails.getFirstName());
		paramMap.put("lastName", userDetails.getLastName());
		paramMap.put("email", userDetails.getEmail());
		paramMap.put("password", userDetails.getPassword());
		
		jdbcTemplate.execute(queryStore.getQuery("insertUserDetails"), paramMap, (ps) -> {
			return ps.executeUpdate();
		});
	}

}
