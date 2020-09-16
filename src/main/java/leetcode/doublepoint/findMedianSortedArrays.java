package leetcode.doublepoint;

import java.util.HashSet;

/**
 * @Author laijinhan
 * @date 2020/9/14 3:35 下午
 */


public class findMedianSortedArrays {
    static
    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int n = nums1.length, m = nums2.length;
            int left = (n + m + 1) / 2, right = (n + m + 2) / 2;
            return (getKthElement(nums1, nums2, left) + getKthElement(nums1, nums2, right)) / 2;

        }

        /**
         * 双指针查找法
         * @param nums1
         * @param nums2
         * @param k
         * @return
         */
        public double search(int[] nums1, int[] nums2, int k) {
            int i = 0, j = 0;
            while (i < nums1.length && j < nums2.length) {
                if (i + j < k - 1) {
                    if (nums1[i] < nums2[j]) {
                        i++;
                    } else {
                        j++;
                    }
                } else {
                    return nums1[i] > nums2[j] ? nums2[j] : nums1[i];
                }
            }
            return i >= nums1.length ? nums2[k - i] : nums1[k - j];
        }

        /**
         * 二分查找法
         * @param nums1
         * @param nums2
         * @param k
         * @return
         */
        public double getKthElement(int[] nums1, int[] nums2, int k) {
            /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
             * 这里的 "/" 表示整除
             * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
             * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
             * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
             * 这样 pivot 本身最大也只能是第 k-1 小的元素
             * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
             * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
             * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
             */

            int length1 = nums1.length, length2 = nums2.length;
            int index1 = 0, index2 = 0;
            while (true) {
                // 边界情况
                if (index1 == length1) {
                    return nums2[index2 + k - 1];
                }
                if (index2 == length2) {
                    return nums1[index1 + k - 1];
                }
                if (k == 1) {
                    return Math.min(nums1[index1], nums2[index2]);
                }

                // 正常情况
                int half = k / 2;
                int newIndex1 = Math.min(index1 + half, length1) - 1;
                int newIndex2 = Math.min(index2 + half, length2) - 1;
                int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
                if (pivot1 <= pivot2) {
                    k -= (newIndex1 - index1 + 1);
                    index1 = newIndex1 + 1;
                } else {
                    k -= (newIndex2 - index2 + 1);
                    index2 = newIndex2 + 1;
                }
            }
        }
    }
    public static void main(String[] args) {
        int[] input1=new int[]{1,2};
        int[] input2=new int[]{3,4};
        System.out.println(new Solution().findMedianSortedArrays(input1,input2));
        System.out.println(new Solution().getKthElement(input1,input2,3));
        
    }
}
