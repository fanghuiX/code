package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by author on 2021/8/6 22:47
 */
public class Client {
    public static void main(String[] args) throws Throwable {
        IGamePlayer player = new GamePlayer();
        // 定义一个handler
        InvocationHandler handler = new GamePlayerIH(player);
        // 动态产生一个代理者
        IGamePlayer proxy = (IGamePlayer) Proxy.newProxyInstance(player.getClass().getClassLoader(), new Class[] {IGamePlayer.class}, handler);

        proxy.login("方辉", "666");

        proxy.killBoss();

        proxy.upgrade();
    }
}
