package sort;

/**
 * Created by author on 2021/10/22 10:49
 */
public class BubbleSort {

    /**
     * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。
     * 这步做完后，最后的元素会是最大的数。
     * 针对所有的元素重复以上的步骤，除了最后一个。
     * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
     * @param args
     */

    public static void main(String[] args) {
        int[] nums = new int[(int) (Math.random() * 10)];
        for(int i=0; i<nums.length; i++) {
            nums[i] = (int) (Math.random() * 100);
            System.out.print(nums[i] + " ");
        }
        System.out.println();
        bubbleSort(nums);
        for(int i=0; i<nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    public static void bubbleSort(int[] nums) {
        int len = nums.length;
        for(int i=len - 1; i>0; i--) {
            for(int j=1; j<=i; j++) {
                if(nums[j] < nums[j - 1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j -1];
                    nums[j - 1] = tmp;
                }
            }
        }
    }
}
