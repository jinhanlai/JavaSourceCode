package leetcode.tree;

import java.util.HashMap;
import java.util.Stack;

/**
 * @Author laijinhan
 * @date 2020/9/21 3:23 下午
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
 * 根据先序遍历和中序遍历确定一个二叉树
 */
public class buildTree {
    private HashMap<Integer, Integer> map;

    /**
     * 递归版本
     * @param preorder 先序结果
     * @param inorder 中序结果
     * @return 二叉树
     */
    public TreeNode buildTree_(int[] preorder, int[] inorder) {
        int n = preorder.length;
        map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }
        return build2Tree(preorder, 0, n - 1, inorder, 0, n - 1);

    }

    public TreeNode build2Tree(int[] preorder, int p_start, int p_end, int[] inorder, int i_start, int i_end) {
        if (p_start > p_end) return null;
        TreeNode root = new TreeNode(preorder[p_start]);
        int i_index = map.get(root.val);
        root.left = build2Tree(preorder, p_start + 1, p_start + i_index - i_start, inorder, i_start, i_index - 1);
        root.right = build2Tree(preorder, p_start + i_index - i_start + 1, p_end, inorder, i_index + 1, i_end);
        return root;

    }

    /**
     * 迭代版本
     * 前序遍历 preorder = [3,9,20,15,7]
     * 中序遍历 inorder = [9,3,15,20,7]
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
        return root;
    }
}
// https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solution/cong-qian-xu-yu-zhong-xu-bian-li-xu-lie-gou-zao-9/
