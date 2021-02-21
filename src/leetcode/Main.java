package leetcode;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;


public class Main {

    public static void main(String[] args) {
        int[] a = {2,3,5};
        System.out.println(getSumAbsoluteDifferences(a));
    }

    public static int[] getSumAbsoluteDifferences(int[] nums) {
        int len = nums.length;
        int[] result = new int[len];
        int[] sumL = new int[len];
        int[] sumR = new int[len];
        for(int i=0; i<len; i++) {
            if(i == 0) {
                sumL[0] = nums[i];
            } else {
                sumL[i] = sumL[i-1] + nums[i];
            }
        }
        for(int i=len-1; i>=0; i--) {
            if(i == len-1) {
                sumR[len - i -1] = nums[i];
            } else {
                sumR[len - i -1] = sumR[len - i -2] + nums[i];
            }
        }
        for(int i=0; i<len; i++) {
            if(i == 0) {
                result[i] = sumR[len-i-2] - (len - i -1) * nums[i];
            } else if(i == len -1) {
                result[i] = nums[i] * i -sumL[i-1];
            } else {
                result[i] = nums[i] * i -sumL[i-1] + sumR[len-i-2] - (len - i -1) * nums[i];
            }
        }
        return result;
    }

    public static int longestSubarray(int[] nums, int limit) {
        int result = 0;
        for(int i=0; i<nums.length; i++) {
            int flag = 0;
            int max = 0;
            int min = Integer.MAX_VALUE;
            if(i>0 && nums[i] == nums[i-1]) {
                continue;
            }
            for(int j=i; j<nums.length; j++) {
                max = Math.max(nums[j], max);
                min = Math.min(nums[j], min);
                if(Math.abs(nums[i] - nums[j]) <= limit && Math.abs(max - min) <= limit) {
                    flag++;
                } else {
                    break;
                }
            }
            result = Math.max(result, flag);
        }
        return result;
    }

    /**
     * 最长有效括号
     * @param s
     * @return
     */
    public static int longestValidParentheses(String s) {
        if(s.length() <= 1) {
            return 0;
        }
        Stack<String> stack = new Stack<>();
        int[] nums = new int[s.length()];
        for(int i = 0; i < s.length(); i++) {
            if(i == 0) {
                stack.push(i + ":" + s.charAt(i));
            } else {
                if(stack.size() != 0 && stack.peek().split(":")[1].equals("(") && s.charAt(i) == ')') {
                    nums[Integer.valueOf(stack.pop().split(":")[0])] = 1;
                    nums[i] = 1;
                } else {
                    stack.push(i + ":" + s.charAt(i));
                }
            }
        }
        int flag = 0;
        int result = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                flag = 0;
            } else {
                flag++;
            }
            result = Math.max(flag, result);
        }
        return result;
    }



    /**
     * 斐波那契 递归
     * @param n
     * @return
     */
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


    /**
     * 最长回文子串
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        if(s.length() == 1) {
            return s;
        }
        if(s.length() == 2) {
            if(s.charAt(0) == s.charAt(1)) {
                return s;
            } else {
                return s.charAt(0) + "";
            }
        }
        char[] chars = s.toCharArray();
        int[] range = new int[2];
        for(int i=0; i<chars.length; i++) {
            i = findLobgest(chars, i, range);
        }
        return s.substring(range[0], range[1] + 1);
    }
    public static int findLobgest(char[] chars, int low, int[] range) {
        int high = low;
        // 找到回文子串中间值
        while(high < chars.length - 1 && chars[low] == chars[high+1]) {
            high++;
        }
        int ans = high;
        // 中间往两边蔓延
        while(low > 0 && high < chars.length - 1 && chars[low-1] == chars[high+1]) {
            low--;
            high++;
        }
        if(high - low > range[1] - range[0]) {
            range[0] = low;
            range[1] = high;
        }
        return ans;
    }

    /**
     * 1004. 最大连续1的个数 III
     * 给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
     * 返回仅包含 1 的最长（连续）子数组的长度。
     * @param A
     * @param K
     * @return
     */
    public static int longestOnes(int[] A, int K) {
        if(K == 0) {
            int result = 0;
            int max = 0;
            for(int i=0; i<A.length; i++) {
                if(A[i] == 1) {
                    result++;
                    max = Math.max(result, max);
                } else {
                    result = 0;
                }
            }
            return max;
        }
        int num0 = 0;
        for(int i=0; i<A.length; i++) {
            if(A[i] == 0) {
                num0++;
            }
        }
        if(num0 <= K) {
            return A.length;
        }
        int[] nums = new int[num0];
        int j = 0;
        for(int i=0; i<A.length; i++) {
            if(A[i] == 0) {
                nums[j] = i;
                j++;
            }
        }
        int result = 0;
        for(int i=0; i<=num0-K; i++) {
            if(i == 0) {
                result = Math.max(result, nums[i+K] - nums[i]);
            } else if(i == num0 - K) {
                result = Math.max(result, A.length - nums[i-1] - 1);
            } else {
                result = Math.max(result, nums[i+K] - nums[i-1] -1);
            }
        }
        return result;
    }

    /**
     *
     * @param nums
     * @return
     */
    public static int findShortestSubArray(int[] nums) {
        int result = Integer.MAX_VALUE;
        int max = 0;
        Map<Integer, String> map = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
            if(map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + "," + i);
            } else {
                map.put(nums[i], i+"");
            }
        }
        for(Integer key : map.keySet()) {
            max = Math.max(max, map.get(key).split(",").length);
        }
        for(Integer key : map.keySet()) {
            if(map.get(key).split(",").length == max) {
                result = Math.min(result, Integer.valueOf(map.get(key).split(",")[max-1]) - Integer.valueOf(map.get(key).split(",")[0]) + 1);
            }
        }
        return result;
    }
}
