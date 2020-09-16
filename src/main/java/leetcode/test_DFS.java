package leetcode;

import lombok.NoArgsConstructor;

import javax.sound.midi.Soundbank;

/**
 * @Author laijinhan
 * @date 2020/9/13 11:17 下午
 */


public class test_DFS {
    static
    class Solution {
        public boolean exist(char[][] board, String word) {
            int n=board.length, m=board[0].length;
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    boolean flag=check(board,word,i,j,0);
                    if(flag) return true;
                }
            }
            return false;

        }

        public boolean check(char[][] board,String word,int i,int j,int k){
            if (k>=word.length()) return true;
            if(i<0||j<0|| i>=board.length || j>=board[0].length || board[i][j]!=word.charAt(k)) return false;
            board[i][j]+=256;
            boolean result=check(board,word,i-1,j,k+1)||check(board,word,i+1,j,k+1)||
                    check(board,word,i,j-1,k+1)||check(board,word,i,j+1,k+1);
            board[i][j]-=256;
            return result;

        }
        public boolean exist1(char[][] board, String word) {
            int n=board.length,m=board[0].length;
            boolean[][] visited=new boolean[n][m];
            boolean ans=false;
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    if(board[i][j]==word.charAt(0)){
                        ans=search(board,visited,word,i,j,0);
                        if(ans) return true;
                    }
                }
            }
            return false;
        }
        public boolean search(char[][] board,boolean[][] visited ,String word,int i,int j,int k){
            if(k>=word.length()) return true;
            if(i<0 || j<0 || i>=board.length || j>=board[0].length ||  board[i][j]!=word.charAt(k) || visited[i][j]) return false;
            visited[i][j]=true;
            boolean ans=search(board,visited,word,i+1,j,k+1) ||search(board,visited,word,i-1,j,k+1) ||
                    search(board,visited,word,i,j+1,k+1)|| search(board,visited,word,i,j-1,k+1);
            visited[i][j]=false;
            return ans;

        }
    }

    public static void main(String[] args) {
        char[][] input=new char[][]{{'a','a'}};
        boolean see = new Solution().exist1(input, "aaa");
        System.out.println(see);
    }
}
