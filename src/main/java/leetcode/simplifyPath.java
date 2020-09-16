package leetcode;

import java.util.Stack;

/**
 * @Author laijinhan
 * @date 2020/9/16 11:37 上午
 */


public class simplifyPath {
    static class Solution {
        public String simplifyPath(String path) {
            String[] pathlist=path.split("/");
            Stack<String> stack=new Stack<>();
            for(String s:pathlist){
                if(s.equals("..")){
                    if(!stack.isEmpty()){
                        stack.pop();
                    }
                }else if(!s.equals("") && !s.equals(".")){
                    stack.push(s);
                }
            }
            if(stack.isEmpty()) return "/";
            StringBuilder ans=new StringBuilder();
            for(int i=0;i<stack.size();i++){
                ans.append("/"+stack.get(i));
            }
            return ans.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().simplifyPath("//home"));
    }
}
