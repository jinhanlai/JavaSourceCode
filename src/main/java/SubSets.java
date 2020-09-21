import java.util.ArrayList;
import java.util.List;

/**
 * @Author laijinhan
 * @date 2020/9/20 10:45 上午
 */


public class SubSets {
    public List<List<Integer>> subsets(int[] nums) {
        // 每个位置用0，1表示取不取该位置的数;所以一共有2^n个选择  eg：n=3 -> 001--111

        List<List<Integer>> ans =new ArrayList<>();
        List<Integer> item=new ArrayList<>();
        int n=nums.length;
        for(int mask=0;mask<(1<<n);mask++){
            item.clear();
            for(int i=0;i<n;i++){ //表示每个位置对应的取法
                if( (mask & (1<<i)) != 0){  // mask 相当于 101  001 这种的二进制数；在用每个位置去&
                    item.add(nums[i]);
                }

            }
            ans.add(item);
        }
        return ans;



    }

    public static void main(String[] args) {
        System.out.println(new SubSets().subsets(new int []{1,2,3}));
    }
}
