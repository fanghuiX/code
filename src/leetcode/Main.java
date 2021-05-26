package leetcode;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Main {

    Set<Integer> set = new TreeSet<Integer>((o1, o2) -> o2.compareTo(o1));

    private static boolean res = false;
    static int[][] forward = {{0, 1}, {1, 0}, {0 ,-1}, {-1, 0}};

    public static void main(String[] args) {
        int[] arr = {100,-23,-23,404,100,23,23,23,3,404};
        System.out.println(minJumps(arr));
    }


    static int ress = Integer.MAX_VALUE;
    public static int minJumps(int[] arr) {
        if(arr.length == 1) {
            return 0;
        }
        List<Integer> list = new ArrayList<>();
        list.add(arr[0]);
        for(int i=1; i<arr.length; i++) {
            if(arr[i] != arr[i-1]) {
                list.add(arr[i]);
            }
        }
        Queue<List<Integer>> queue = new LinkedList<>();
        // 第一个值，下标，第二个值，跳的次数
        queue.offer(new ArrayList<Integer>(){{add(0); add(0);}});
        int terminus = list.size() - 1;
        int[] visited = new int[terminus+1];
        visited[0] = 1;
        while(!queue.isEmpty()) {
            List<Integer> point = queue.poll();
            int num = point.get(0);
            int flag = point.get(1);
            if(num == terminus) {
                ress = Math.min(flag, ress);
            } else {
                if(num != 0 && visited[num - 1] == 0) {
                    queue.offer(new ArrayList<Integer>(){{add(num-1); add(flag+1);}});
                    visited[num - 1] = 1;
                }
                if(num + 1 <= terminus && visited[num + 1] == 0) {
                    queue.offer(new ArrayList<Integer>(){{add(num+1); add(flag+1);}});
                    visited[num + 1] = 1;
                }
                for(int i=0; i<terminus+1; i++) {
                    if(list.get(i) == list.get(num) && i > num && i != num + 1 && visited[i] == 0) {
                        int p = i;
                        queue.offer(new ArrayList<Integer>(){{add(p); add(flag+1);}});
                        visited[i] = 1;
                    }
                }
            }
        }
        return ress;
    }


    public static boolean exist(char[][] board, String word) {
        char first = word.charAt(0);
        int m = board.length;
        int n = board[0].length;
        List<List<Integer>> list = new ArrayList<>();
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(board[i][j] == first) {
                    List<Integer> l = new ArrayList<>();
                    l.add(i);
                    l.add(j);
                    list.add(l);
                }
            }
        }
        for(int i=0; i<list.size(); i++) {
            int[][] nums = new int[m][n];
            dfs(nums, board, word, list.get(i).get(0), list.get(i).get(1), 1);
        }
        return res;
    }

    private static void dfs(int[][] nums, char[][] board, String word, int m, int n, int flag) {
        if(m < 0 || m >= board.length || n < 0 || n >= board[0].length || flag > word.length()) {
            return;
        }
        if(board[m][n] != word.charAt(flag-1) || nums[m][n] == 1) {
            nums[m][n] = 0;
            return;
        } else {
            if(flag == word.length()) {
                res = true;
            }
            nums[m][n] = 1;
        }
        for(int i=0; i<forward.length; i++) {
            dfs(nums, board, word, m + forward[i][0], n + forward[i][1], flag + 1);
        }
    }



    public static List<String> topKFrequent(String[] words, int k) {
        List<String> result = new ArrayList<>();
        Map<String, Integer> map = new TreeMap<>();
        for(String s : words) {
            if(map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }
        //这里将map.entrySet()转换成list
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list, (Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) -> {
            if(o1.getValue().equals(o2.getValue())) {
                return o1.getKey().compareTo(o2.getKey());
            } else {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        int flag = 0;
        for(Map.Entry<String, Integer> m : list) {
            if(flag >= k) {
                break;
            }
            result.add(m.getKey());
            flag++;
        }
        return result;
    }

    static List<Integer> result = new ArrayList<>();
    public static List<Integer> spiralOrder(int[][] matrix) {
        order(matrix, 0, 0, matrix.length - 1, matrix[0].length - 1);
        return result;
    }

    private static void order(int[][] matrix, int x1, int y1, int x2, int y2) {
        if(x1 >= x2 || y1 >= y2) {
            if(x1 == x2) {
                for(int i=y1; i<=y2; i++) {
                    result.add(matrix[x1][i]);
                }
            } else {
                for(int i=x1; i<=x2; i++) {
                    result.add(matrix[i][y1]);
                }
            }
            return;
        }
        int m = x1;
        int n = y1 + 1;
        result.add(matrix[x1][y1]);
        while(!(m == x1 && n == y1)) {
            result.add(matrix[m][n]);
            if(m == x1 && n < y2) {
                n++;
            } else if(n == y2 && m < x2) {
                m++;
            } else if(m == x2 && n > y1) {
                n--;
            } else if(n == y1 && m > x1) {
                m--;
            }
        }
        order(matrix, x1 + 1, y1 + 1, x2 - 1, y2 - 1);
    }


    public static int leastBricks(List<List<Integer>> wall) {
        Set<Integer> set = new HashSet<>();
        int maxWidth = 0;
        for(int i=0; i<wall.size(); i++) {
            int width = 0;
            for(int j=0; j<wall.get(i).size(); j++) {
                if(j == 0) {
                    wall.get(i).set(j, wall.get(i).get(j));
                } else {
                    wall.get(i).set(j, wall.get(i).get(j) + wall.get(i).get(j-1));
                }
                set.add(wall.get(i).get(j));
                width++;
            }
            maxWidth = Math.max(maxWidth, width);
        }
        int max = 0;
        for(int o : set) {
            int flag = 0;
            if(o == wall.get(0).get(wall.get(0).size()-1)) {
                continue;
            }
            for(int i=0; i<wall.size(); i++) {
                if(wall.get(i).contains(o)) {
                    flag++;
                }
            }
            max = Math.max(max, flag);
        }
        return wall.size() - max;
    }

    // 878
    public static int nthMagicalNumber(int n, int a, int b) {
        int MOD = 1000000007;
        if(a > b) {
            a = a + b;
            b = a - b;
            a = a - b;
        }
        if(b % a == 0) {
            return  Integer.valueOf(String.valueOf((long)a * n % MOD));
        }
        long[] flag = {a, b};
        for(int i=1; i<n; i++) {
            if(flag[0] <= flag[1]) {
                flag[0] += a;
            } else {
                flag[1] += b;
            }
            if(flag[0] == flag[1]) {
                flag[0] += 1;
            }
        }
        return Integer.valueOf(String.valueOf(Math.min(flag[0], flag[1]) % MOD));

//        int x = 1, y = 1;
//        Long result = Math.min((long)a, (long)b);
//        for(int i=1; i<n; i++) {
//            if(result == a * x) {
//                x++;
//            }
//            if(result == b * y) {
//                y++;
//            }
//            result = Math.min((long)a * x, (long)b * y);
//        }
//        return Integer.valueOf(String.valueOf(result % 1000000007));
    }


    public static int removeDuplicates(int[] nums) {
        int len = nums.length;
        if(len <= 2) {
            return len;
        }
        int left = 2;
        for(int i=2; i<len; i++) {
            nums[left] = nums[i];
            if(!(nums[left] == nums[left-1] && nums[left] == nums[left-2])) {
                left++;
            }
        }
        return left;
    }


    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int flag = nums1.length - 1;
        if(m == 0) {
            nums1 = nums2.clone();
        }
        while(m > 0 && n > 0) {
            if(nums2[n-1] > nums1[m-1]) {
                nums1[flag] = nums2[n-1];
                n--;
            } else {
                nums1[flag] = nums1[m-1];
                m--;
            }
            flag--;
        }
    }


    public static int longestCommonSubsequence(String text1, String text2) {
        int text1Length = text1.length();
        int text2Length = text2.length();
        if(text1Length == 0 || text2Length == 0) {
            return 0;
        }
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        int result = 0;
        int[][] flag = new int[text2Length][text1Length];
        for(int i=text1Length-1; i>=0; i--) {
            for(int j=text2Length-1; j>=0; j--) {
                int cnt = 0;
                if(chars1[i] == chars2[j]) {
                    cnt = 1;
                } else {
                    cnt = 0;
                }
                if(i == text1Length-1 && j == text2Length-1) {
                    flag[j][i] = cnt;
                } else if(i != text1Length-1 && j != text2Length-1) {
                    flag[j][i] = cnt + Math.max(flag[j+1][i], flag[j][i+1]);
                } else if(i == text1Length - 1) {
                    flag[j][i] = cnt == 0 ? flag[j+1][i] : cnt;
                } else if(j == text2Length - 1) {
                    flag[j][i] = cnt == 0 ? flag[j][i+1] : cnt;
                }
                result = Math.max(result, flag[j][i]);
            }
        }
        return result;
    }




    public static int calculate(String s) {
        if(s.length() == 1) {
            return Integer.valueOf(s);
        }
        s = s.replaceAll("\\+", " + ");
        s = s.replaceAll("\\-", " - ");
        s = s.replaceAll("\\*", " * ");
        s = s.replaceAll("\\/", " / ");
        // 转为字符串数组
        String[] strs = s.split(" ");
        List<String> list = new ArrayList<>();
        for(String str : strs) {
            if(!str.equals("")) {
                list.add(str);
            }
        }
        // 栈
        Stack<String> stack = new Stack<>();
        for(int i=0; i<list.size(); i++) {
            if(list.get(i).equals("+") || list.get(i).equals("-")) {
                stack.push(list.get(i));
            } else if(list.get(i).equals("*") || list.get(i).equals("/")) {
                int preNum = Integer.valueOf(stack.pop());
                int lastNum = Integer.valueOf(list.get(i+1));
                if(list.get(i).equals("*")) {
                    stack.push(preNum * lastNum + "");
                } else {
                    stack.push(preNum / lastNum + "");
                }
                i++;
            } else {
                stack.push(list.get(i));
            }
        }
        list = new ArrayList<>();
        while(stack.size() != 0) {
            list.add(stack.pop());
        }
        Collections.reverse(list);
        int result = 0;
        for(int i=0; i<list.size(); i++) {
            if(list.get(i).equals("+")) {
                stack.push(Integer.valueOf(stack.pop()) + Integer.valueOf(list.get(i+1)) + "");
                i++;
            } else if(list.get(i).equals("-")) {
                stack.push(Integer.valueOf(stack.pop()) - Integer.valueOf(list.get(i+1)) + "");
                i++;
            } else {
                stack.push(list.get(i));
            }
        }
        return Integer.valueOf(stack.pop());
    }



    public boolean validPalindrome(String s) {
        if(s == null) {
            return false;
        }
        if(s.length() <= 2) {
            return true;
        }
        List<Integer> list = isPalindrome(s);
        if(list.size() == 0) {
            return true;
        } else {
            String removeLeft = s.substring(0, list.get(0)) + s.substring(list.get(0) + 1);
            String removeRight = s.substring(0, list.get(1)) + s.substring(list.get(1) + 1);
            if(isPalindrome(removeLeft).size() == 0 || isPalindrome(removeRight).size() == 0) {
                return true;
            }
        }
        return false;
    }

    // 记录移动时不匹配的字符的位置
    private List<Integer> isPalindrome(String s) {
        List<Integer> list = new ArrayList<>();
        int i = 0;
        int j = s.length() - 1;
        while(i < j) {
            if(s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                list.add(i);
                list.add(j);
                return list;
            }
        }
        return list;
    }

    public int findSecondMinimumValue(TreeNode root) {
        DFS(root);
        if(set.size() >= 2) {
            int flag = 1;
            for(Integer i : set) {
                if(flag == 2) {
                    return i;
                }
                flag++;
            }
        }
        return -1;
    }

    public void DFS(TreeNode node) {
        if(node.left == null) {
            return;
        } else {
            set.add(node.left.val);
            DFS(node.left);
            set.add(node.right.val);
            DFS(node.right);
        }
    }

    public static int lengthOfLIS(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        int result = 0;
        Stack<Integer> s = null;
        for(int i=0; i<nums.length; i++) {
            s = new Stack<>();
            s.push(nums[i]);
            for(int j=i+1; j<nums.length; j++) {
                if(nums[i] == nums[j]) {
                    continue;
                }
                if(nums[j] > s.peek()) {
                    s.push(nums[j]);
                } else if(nums[j] < s.peek()) {
                    int flag = s.pop();
                    if(!s.empty() && nums[j] > s.peek()) {
                        s.push(nums[j]);
                    } else {
                        s.push(flag);
                    }
                }
            }
            result = Math.max(s.size(), result);
        }
        return result;
    }

    public static boolean isToeplitzMatrix(int[][] matrix) {
        for(int i=0; i<matrix[0].length; i++) {
            int row = 0;
            int col = i;
            while(row < matrix.length-1 && col < matrix[0].length-1) {
                row++;
                col++;
                if(matrix[row][col] != matrix[0][i]) {
                    return false;
                }
            }
        }
        for(int i=0; i<matrix.length; i++) {
            int row = i;
            int col = 0;
            while(row < matrix.length-1 && col < matrix[0].length-1) {
                row++;
                col++;
                if(matrix[row][col] != matrix[i][0]) {
                    return false;
                }
            }
        }
        return true;
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
