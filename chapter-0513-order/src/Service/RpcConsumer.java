package Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class RpcConsumer {
	
	public static Object remove(Class clazz) {
		
		return Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, 
				new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {				
				Socket socket = new Socket("localhost", 8888);
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				oos.writeUTF(clazz.getName());
				oos.writeUTF(method.getName());
				oos.writeObject(method.getParameterTypes());
				oos.writeObject(args);
				oos.flush();
		        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		        Object result= ois.readObject();
				oos.close();
				ois.close();
				socket.close();
				return result;
			}
		});		
	}

}
