package proxy.test;

/**
 * Created by author on 2021/10/17 16:52
 */
public class UserServiceImpl implements UserService {
    @Override
    public String getUserName(String name) {
        return String.format("Hello, %s!", name);
    }

    @Override
    public Integer getAge(Integer age) {
        return age;
    }
}
