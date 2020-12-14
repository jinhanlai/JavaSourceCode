package leetcode.collection;

import java.util.Arrays;

/**
 * @Author laijinhan
 * @date 2020/10/28 9:54 上午
 */


public class smallerNumbersThanCurrent {
	static class Solution {
		public int[] smallerNumbersThanCurrent(int[] nums) {
			// 快排的思想
			int[] ans =new int[nums.length];
			for(int i=0;i<nums.length;i++){
				int j=partition(i, Arrays.copyOf(nums,nums.length));
				ans[i]=j;
			}
			return ans;
		}
		public int partition(int i,int[] arrays){
			int pivot=arrays[i];
			int right=arrays.length-1, left =0;
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
	}

	public static void main(String[] args) {

		new Solution().smallerNumbersThanCurrent(new int[]{8,1,2,2,3});
	}
}
