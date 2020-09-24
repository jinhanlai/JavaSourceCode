package leetcode.tree;

/**
 * @Author laijinhan
 * @date 2020/9/24 11:52 上午
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

/**
 * 层次遍历构建二叉树
 */
public class treeUtils {
    public  TreeNode bulidBST(int[] A) {
        TreeNode root = new TreeNode(A[0]);
        for (int i=1; i<A.length; i++) {
            createBST(root, A[i]);
        }
        return root;
    }

    private  void createBST(TreeNode node, int val) {
        if (val<node.val) {
            if (node.left == null) {
                node.left = new TreeNode(val);
            } else
                createBST(node.left, val);
        } else {
            if (node.right == null) {
                node.right = new TreeNode(val);
            } else
                createBST(node.right, val);
        }
    }

    public static void main(String[] args) {
    }

}
