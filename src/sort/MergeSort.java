package sort;

/**
 * 归并排序
 * Created by author on 2021/10/11 10:40
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] nums = new int[(int) (Math.random() * 10)];
        // int[] nums = {23, 18, 739, 26, 93, 170, 9, 3479, 72, 93, 40, 82, 379};
        for(int i=0; i<nums.length; i++) {
            nums[i] = (int) (Math.random() * 100);
            System.out.print(nums[i] + " ");
        }
        System.out.println();
        for(int i=0; i<nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }


}
