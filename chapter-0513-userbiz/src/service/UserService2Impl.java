package service;

import java.util.ArrayList;
import java.util.List;

public class UserService2Impl implements UserService2 {

	@Override
	public List<User> getUser() {	
		User user1 = new User();
		user1.setName("David");
		User user2 = new User();
		user2.setName("james");
		List<User> users = new ArrayList<User>();
		users.add(user1);
		users.add(user2);
		return users;
	}

}
