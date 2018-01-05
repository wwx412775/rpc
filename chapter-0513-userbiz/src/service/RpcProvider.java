package service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class RpcProvider {

	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(8888);
			Object result = null;
			while (true) {
				Socket socket = serverSocket.accept();
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				String className = ois.readUTF();
				String methodName = ois.readUTF();
				Class[] parameterTypes = (Class[]) ois.readObject();
				Object[] argss = (Object[]) ois.readObject();
				
				Class<?> clazz = getClass(className);
				Method method = clazz.getMethod(methodName, parameterTypes);
				result = method.invoke(clazz.newInstance(), argss);

				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				oos.writeObject(result);
				oos.close();
				ois.close();
				socket.close();
//				serverSocket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	//注册接口服务
	private static Class getClass(String className) {
		Class clazz = null;
        if (className.equals(UserService.class.getName())) {
			clazz = UserServiceImpl.class;
		}
        if (className.equals(UserService2.class.getName())) {
			clazz = UserService2Impl.class;
		}
		return clazz;		
	}
}
