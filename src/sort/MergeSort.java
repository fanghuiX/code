package sort;

/**
 * 归并排序
 * Created by author on 2021/10/11 10:40
 */
public class MergeSort {

    /**
     * 第一步：申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列
     * 第二步：设定两个指针，最初位置分别为两个已经排序序列的起始位置
     * 第三步：比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置
     * 重复步骤3直到某一指针超出序列尾
     * 将另一序列剩下的所有元素直接复制到合并序列尾
     * @param args
     */

    public static void main(String[] args) {
        int[] nums = new int[(int) (Math.random() * 10)];
        // int[] nums = {23, 18, 739, 26, 93, 170, 9, 3479, 72, 93, 40, 82, 379};
        for(int i=0; i<nums.length; i++) {
            nums[i] = (int) (Math.random() * 100);
            System.out.print(nums[i] + " ");
        }
        System.out.println();
        mergeSort(nums, new int[nums.length], 0, nums.length - 1);
        for(int i=0; i<nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    private static void mergeSort(int[] arr, int[] result, int start, int end) {
        if(start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        int start1 = start;
        int end1 = mid;
        int start2 = mid + 1;
        int end2 = end;
        mergeSort(arr, result, start1, end1);
        mergeSort(arr, result, start2, end2);
        int k = start;
        while(start1 <= end1 && start2 <= end2) {
            result[k++] = arr[start1] > arr[start2] ? arr[start2++] : arr[start1++];
        }
        while(start1 <= end1) {
            result[k++] = arr[start1++];
        }
        while(start2 <= end2) {
            result[k++] = arr[start2++];
        }
        for(int i=start; i<=end; i++) {
            arr[i] = result[i];
        }
    }

}
