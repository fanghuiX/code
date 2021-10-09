package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by author on 2021/8/6 22:43
 */
public class GamePlayerIH implements InvocationHandler {

    // 被代理者
    Class cls = null;
    // 被代理的实例
    Object obj = null;

    // 构造函数
    public GamePlayerIH(Object _obj) {
        this.obj = _obj;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        if(method.getName().equalsIgnoreCase("login")) {
            System.out.println("有人在登录我的账号。");
        }
        return method.invoke(this.obj, args);
    }
}
