package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @Author laijinhan
 * @date 2020/11/26 9:06 上午
 */


public class BaseSortAlgorithm {
	/**
	 *
	 * @param nums 待排序的数
	 */
	public void LsdSort(int[] nums){
		int n=nums.length;
		long exp = 1;
		int[] buf = new int[n];// 存放数据
		int maxVal = Arrays.stream(nums).max().getAsInt(); // 最大值
		while (maxVal >= exp) {
			int[] cnt = new int[10]; // 桶，用来存放个数
			for (int i = 0; i < n; i++) {
				int digit = (nums[i] / (int) exp) % 10;
				cnt[digit]++;
			}
			// 这一步的目的，是记录当前桶的数据，在buffer里面可以存放的位置
			for (int i = 1; i < 10; i++) {
				cnt[i] += cnt[i - 1];
			}
			for (int i = n - 1; i >= 0; i--) {
				int digit = (nums[i] / (int) exp) % 10;
				buf[cnt[digit] - 1] = nums[i];
				cnt[digit]--;
			}
			System.arraycopy(buf, 0, nums, 0, n);
			exp *= 10;
		}
	}

	/**
	 * 定义一个数组，长度为最大值加1，然后把原数组数据放上去，在返回
	 * @param nums
	 */
	public void CountingSort(int[] nums){
		int maxValue = Arrays.stream(nums).max().getAsInt();

		int bucketLen = maxValue + 1;
		int[] bucket = new int[bucketLen];
		for (int value : nums) {
			bucket[value]++;
		}

		int sortedIndex = 0;
		for (int j = 0; j < bucketLen; j++) {
			while (bucket[j] > 0) {
				nums[sortedIndex++] = j;
				bucket[j]--;
			}
		}
	}

	/**
	 * 桶排序
	 * @param nums
	 */
	public void bucketSort(int[] nums){
		int n=nums.length;
		int maxValue = Arrays.stream(nums).max().getAsInt();
		int minValue = Arrays.stream(nums).min().getAsInt();

		int bucketNum = (maxValue - minValue) /n + 1;

		ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketNum);

		for(int i = 0; i < bucketNum; i++){
			bucketArr.add(new ArrayList<>());
		}

		// 将每个元素放入桶
		for(int i = 0; i < n; i++){
			int num = (nums[i] - minValue) / n; //判断被分到那一个桶
			bucketArr.get(num).add(nums[i]);
		}

		// 对每个桶进行排序
		for(int i = 0; i < bucketArr.size(); i++){
			Collections.sort(bucketArr.get(i));
		}
		// 将桶中的元素赋值到原序列
		int index = 0;
		for(int i = 0; i < bucketArr.size(); i++){
			for(int j = 0; j < bucketArr.get(i).size(); j++){
				nums[index++] = bucketArr.get(i).get(j);
			}
		}
	}

	public static void main(String[] args) {
		int[]data = {73, 22, 93, 43, 55, 14, 28, 65, 39, 81, 33, 100};
		// new BaseSortAlgorithm().LsdSort(data);
		// new BaseSortAlgorithm().CountingSort(data);
		new BaseSortAlgorithm().bucketSort(data);

		for (int datum : data) {
			System.out.println(datum);
		}
	}
}


