package proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by author on 2021/10/16 16:38
 */
public class ReflectMain {

    public static void main(String[] args) {
        Map<Integer, Integer> map = getMap("java.util.LinkedHashMap");
        map.put(2, 2);
        map.put(1, 1);
        map.put(3, 3);
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey());
        }
    }

    public static Map<Integer, Integer> getMap(String className) {
        try {
            Class clazz = Class.forName(className);
            Constructor constructor = clazz.getConstructor();
            return (Map<Integer, Integer>) constructor.newInstance();
        } catch (Exception e) {
            return new HashMap<>();
        }
    }
}
