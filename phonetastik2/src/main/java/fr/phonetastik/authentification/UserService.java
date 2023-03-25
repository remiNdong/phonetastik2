package fr.phonetastik.authentification;

import java.util.List;

public interface UserService {
	
	User createUser(User user) throws Exception;

	User findUserById(String id);

	List<User> findAllUsers();

}