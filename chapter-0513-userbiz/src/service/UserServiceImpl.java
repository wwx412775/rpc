package service;

public class UserServiceImpl implements UserService{

	@Override
	public User getUserInfo(int id) {
		User user = new User();
		user.setId(id);
		user.setName("David"+ id);
		return user;
	}

}
