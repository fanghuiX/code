package proxy.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by author on 2021/10/17 16:52
 */
public class Client {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        InvocationHandler handler = new UserHandler(userService);
        UserService proxyInstance = (UserService) Proxy.newProxyInstance(
                userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(),
                handler);
        System.out.println(proxyInstance.getUserName("方辉"));
        System.out.println(proxyInstance.getAge(24));
    }
}
