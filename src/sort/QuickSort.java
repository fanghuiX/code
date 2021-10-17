package sort;

import org.omg.CORBA.INTERNAL;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 快速排序
 * 分治思想
 * Created by author on 2021/10/10 9:32
 */
public class QuickSort {


    /**
     * 算法步骤
     * 从数列中挑出一个元素，称为 "基准"（pivot）;
     * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
     * 在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
     * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序；
     **/
    public static void main(String[] args) {
        int[] nums = new int[(int) (Math.random() * 10)];
        // int[] nums = {23, 18, 739, 26, 93, 170, 9, 3479, 72, 93, 40, 82, 379};
        for(int i=0; i<nums.length; i++) {
            nums[i] = (int) (Math.random() * 100);
            System.out.print(nums[i] + " ");
        }
        System.out.println();
        quickSort(nums, 0, nums.length - 1);
        for(int i=0; i<nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    public static void quickSort(int[] nums, int left, int right) {
        if(left >= right) {
            return;
        }
        int pivotNum = moveElement(nums, left, right);
        quickSort(nums, left, pivotNum - 1);
        quickSort(nums, pivotNum + 1, right);
    }

    public static int moveElement(int[] nums, int left, int right) {
        int pivotNum = nums[left];
        int l = left + 1;
        int r = right;
        while (l <= r) {
            if(nums[l] <= pivotNum) {
                l++;
            } else {
                if(nums[r] <= pivotNum) {
                    int tmp = nums[l];
                    nums[l] = nums[r];
                    nums[r] = tmp;
                    r--;
                    l++;
                } else {
                    r--;
                }
            }
        }
        int tmp = nums[l - 1];
        nums[l - 1] = nums[left];
        nums[left] = tmp;
        return l - 1;
    }
}
