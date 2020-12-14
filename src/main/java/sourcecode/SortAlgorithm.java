package sourcecode;

import sun.applet.Main;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author laijinhan
 * @date 2020/9/22 3:09 下午
 */


public class SortAlgorithm {
    /**
     * 冒泡排序
     *
     * @param arrays 需要排序的数组
     * @return 排序后的结果
     * @description 1. 比较相邻的元素。如果第一个比第二个大，就交换它们两个。
     * 2. 每次比较过后，最大的元素就会在数组的末尾。
     */
    public int[] bubbleSort(int[] arrays) {
        int n = arrays.length;
        if (n == 0)
            return arrays;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arrays[j + 1] < arrays[j]) {
                    int temp = arrays[j + 1];
                    arrays[j + 1] = arrays[j];
                    arrays[j] = temp;
                }
            }
        }
        return arrays;
    }

    /**
     * 选择排序
     *
     * @param arrays
     * @return
     * @description 1. 依次从数组中选择最小的值 与 第一个值进行交换
     */
    public int[] selectSort(int[] arrays) {
        int n = arrays.length;
        if (n == 0)
            return arrays;
        for (int i = 0; i < n; i++) {
            int minindex = i;
            for (int j = i; j < n; j++) {
                if (arrays[j] < arrays[i]) {
                    minindex = j;
                }
            }
            int t = arrays[minindex];
            arrays[minindex] = arrays[i];
            arrays[i] = t;
        }
        return arrays;
    }

    /**
     * 归并排序
     * @param arrays 待排序的数组
     * @return
     */
    public int[] MergeSort(int[] arrays) {
        int n = arrays.length;
        if (n < 2) return arrays;
        merge(arrays, 0, n - 1,new int[n]);
        return arrays;
    }

    /**
     * 合并两个有序的数组
     * @param arr
     * @param left
     * @param right
     * @param tempt 用于合并两个有序数组的辅助数组
     */
    public void merge(int[] arr, int left, int right,int[] tempt) {
        if(left>=right) return;
        int mid=left+(right-left)/2;
        merge(arr,left,mid,tempt);
        merge(arr,mid+1,right,tempt);
        if(arr[mid]<=arr[mid+1]){ //本身有序不需要合并
            return;
        }
        // 合并两个数组
        int i = left, j = mid + 1;
        int index = 0; //辅助数组的索引
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                tempt[index++] = arr[i++];
            }
            else {
                tempt[index++] = arr[j++];
            }
        }
        while (i <= mid) tempt[index++] = arr[i++];
        while (j <= right) tempt[index++] = arr[j++];
        for (i = 0; i < right-left + 1; i++) {
            arr[i + left] = tempt[i];
        }

    }

    /**
     * 快速排序
     * @param arrays 待排序的数组
     * @return
     */
    public int[] quickSort(int[] arrays){
        int n=arrays.length;
        if(n<2) return arrays;
        quickSort_(arrays,0,n-1);

        return arrays;
    }

    /**
     * 依次确定每个数据所在的位置，再分治
     * @param arrays
     * @param left
     * @param right
     */
    public void quickSort_(int[] arrays,int left,int right){
        if(left>=right) return ;
        int j=partition(arrays,left,right);
        quickSort_(arrays,left,j-1);
        quickSort_(arrays,j+1,right);
    }

    /**
     * 确定left对应数据所在的位置
     * @param arrays
     * @param left
     * @param right
     * @return
     */
    public int partition(int[] arrays,int left,int right){
        int pivot=arrays[left];
        while (left<right){
            while (left<right && arrays[right]>=pivot) right--;
            if(left<right){
                arrays[left]=arrays[right];
            }
            while (left<right && arrays[left]<=pivot) left++;
            if(left<right){
                arrays[right]=arrays[left];
            }
        }
        arrays[left]=pivot;
        return left;
    }

    public static void main(String[] args) {
        SortAlgorithm sortAlgorithm = new SortAlgorithm();
        int[] ints = sortAlgorithm.quickSort(new int[]{1, 2, 5, 2, 90, 99, 7});
        Arrays.stream(ints).forEach(System.out::println);

    }
}
