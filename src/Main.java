import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        System.out.println(getFibonacciN(n));
    }

    // 斐波那契 递归
    private static BigInteger getFibonacciN(int n) {
        if(n == 1 || n == 2) {
            return BigInteger.ONE;
        }
        return cycle(BigInteger.ONE, BigInteger.ONE, n -3);
    }
    private static BigInteger cycle(BigInteger s, BigInteger e, int n) {
        if(n == 0) {
            return s.add(e);
        }
        return cycle(e, s.add(e), --n);
    }
}
