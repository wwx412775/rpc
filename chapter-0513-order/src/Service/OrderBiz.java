package Service;

import java.util.List;

import service.User;
import service.UserService;
import service.UserService2;

public class OrderBiz {
	
	public void execute(int id) {
		UserService userService = (UserService) RpcConsumer.remove(UserService.class);
		User user = userService.getUserInfo(id);
		UserService2 userService2 = (UserService2) RpcConsumer.remove(UserService2.class);
		List<User> users =  userService2.getUser();
		for (User user2 : users) {
			System.out.println(user2.getName()+user2.getId());
		}
	}
	
	public static void main(String[] args) {
		OrderBiz orderBiz = new  OrderBiz();
		orderBiz.execute(10);
	}
}
