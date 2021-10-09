package proxy;

/**
 * Created by author on 2021/8/6 22:39
 */
public class GamePlayer implements IGamePlayer {

    String userName = null;
    Integer level = 1;

    public void login(String userName, String password) {
        this.userName = userName;
        System.out.println(userName + " 登录了游戏！！！");
    }

    public void killBoss() {
        System.out.println(this.userName + "杀死了一只BOSS！！！");
    }

    public void upgrade() {
        level++;
        System.out.println(this.userName + "达到了等级 " + level);
    }
}
