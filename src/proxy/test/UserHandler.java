package proxy.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by author on 2021/10/17 16:53
 */
public class UserHandler implements InvocationHandler {

    Object obj = null;

    Integer count = 0;

    public UserHandler(Object _ojb) {
        this.obj = _ojb;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        if(method.getName().equals("getAge")) {
            count++;
            System.out.println("getAge:" + count);
        }
        return method.invoke(obj, args);
    }
}
