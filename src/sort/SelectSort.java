package sort;

/**
 * Created by author on 2021/10/22 11:00
 */
public class SelectSort {

    /**
     * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置。
     * 再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
     * 重复第二步，直到所有元素均排序完毕。
     * @param args
     */

    public static void main(String[] args) {
        int[] nums = new int[(int) (Math.random() * 10)];
        for(int i=0; i<nums.length; i++) {
            nums[i] = (int) (Math.random() * 100);
            System.out.print(nums[i] + " ");
        }
        System.out.println();
        selectSort(nums);
        for(int i=0; i<nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    public static void selectSort(int[] nums) {
        int len = nums.length;
        for(int i=0; i<len; i++) {
            for(int j=i+1; j<len; j++) {
                if(nums[j] < nums[i]) {
                    int tmp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = tmp;
                }
            }
        }
    }
}
