package bit;

/**
 * @Author laijinhan
 * @date 2020/12/14 下午10:15
 */

/**
 * 剪指offer56-1，数组中数字出现的次数
 */
public class singleNumbers {
	public int[] singleNumbers(int[] nums) {
		// 因为除了两个数以外，其他都只出现了两次，这出现了两次的数相与，一定为0
		int res=0;//所有数相与的结果,比如两个不相同的数为a，b 那么 res=a^b
		for(int num : nums){
			res^=num;
		}
		// 只需要在区分a，b即可
		int flag = res & (-res);

		int a = 0; // 用于记录A或B其中一者
		// 分组操作
		for (int val : nums) {
			// 根据二进制位上的那个“1”进行分组
			// 需要注意的是，分组的结果必然是相同的数在相同的组，且还有一个结果数
			// 因此每组的数再与res=0一路异或下去，最终会得到那个结果数A或B
			// 且由于异或运算具有自反性，因此只需得到其中一个数即可
			if ((flag & val) != 0) {
				a ^= val;
			}
		}
		return new int[] {a, res ^ a};

	}

	public static void main(String[] args) {
		new singleNumbers().singleNumbers(new int[]{1,2,10,4,1,4,3,3});
	}
}
