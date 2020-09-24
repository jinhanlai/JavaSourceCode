package leetcode.tree;

import java.util.LinkedList;

/**
 * @Author laijinhan
 * @date 2020/9/24 11:47 上午
 */

/**
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 * https://leetcode-cn.com/problems/find-mode-in-binary-search-tree/
 */
class findMode_ {
    private int count=0; //表示重复的个数
    private int max=0; // 表示最大的个数
    private LinkedList<Integer> ans;
    private TreeNode pre=null;//表示上一个结点

    public int[] findMode(TreeNode root) {
        // //常规的dfs中序遍历
        // ans =new LinkedList<>();
        // if(root==null) return new int[0];
        // dfs(root);
        // return ans.stream().mapToInt(Integer::valueOf).toArray();

        //利用前驱结点来实现中序遍历
        ans =new LinkedList<>();
        if(root==null) return new int[0];
        while(root!=null){
            if(root.left==null){
                update(root);
                root=root.right;
                continue;
            }else{
                TreeNode prenode =root.left; // 寻找前驱结点
                while(prenode.right!=null && prenode.right!=root){
                    prenode=prenode.right;
                }
                if(prenode.right==null){
                    prenode.right=root;
                    root=root.left;
                }else{
                    //pre.right = null;
                    update(root);

                    root = root.right;
                }
            }
        }
        return ans.stream().mapToInt(Integer::valueOf).toArray();

    }

    public void update(TreeNode root){
        if(pre!=null && pre.val==root.val){
            count++;
        }else{
            count=1;
        }
        if(count==max){
            ans.add(root.val);
        }else if(count>max){
            ans.clear();
            max=count;
            ans.add(root.val);
        }
        pre=root;
    }
    public void dfs(TreeNode root){
        if(root==null) return ;
        dfs(root.left);
        update(root);
        pre=root;
        dfs(root.right);
    }
}
