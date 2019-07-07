package in.anandm.msl.userservice.model;

public interface UserRepository {

	UserDetails findUserByEmail(String emailId);
	
	void save(UserDetails userDetails);
}
