package designPatterns;

/**
 * Created by author on 2021/10/22 10:34
 */
public class SingleTon {

    public static void main(String[] args) {
        SingleInstance instance = SingleInstance.getInstance();
        SingleInstance instance1 = SingleInstance.getInstance();
        LazySingleTon aaa = LazySingleTon.getInstance();
        LazySingleTon bbb = LazySingleTon.getInstance();
        DoubleCheckSingleTon ccc = DoubleCheckSingleTon.getInstance();
        DoubleCheckSingleTon ddd = DoubleCheckSingleTon.getInstance();
        return;
    }
}

// 饿汉模式
class SingleInstance {
    private static SingleInstance instance = new SingleInstance();

    private SingleInstance() {

    }

    public static SingleInstance getInstance() {
        return instance;
    }
}

// 懒汉模式
class LazySingleTon {
    private static LazySingleTon instance = null;

    private LazySingleTon() {

    }

    public static synchronized LazySingleTon getInstance() {
        if(instance == null) {
            instance = new LazySingleTon();
        }
        return instance;
    }
}

// 双重校验
class DoubleCheckSingleTon {
    private volatile static DoubleCheckSingleTon instance = null;

    private DoubleCheckSingleTon() {

    }

    public static DoubleCheckSingleTon getInstance() {
        if(instance == null) {
            synchronized (DoubleCheckSingleTon.class) {
                if(instance == null) {
                    instance = new DoubleCheckSingleTon();
                }
            }
        }
        return instance;
    }
}
