package leetcode.dp;

/**
 * @Author laijinhan
 * @date 2020/9/18 10:50 上午
 */


public class PatternMatch {
    public boolean isMatch(String s, String p) {
        //动态规划 f[i][j] 表示s的前i个字符是否跟，p的前j个字符相等。

        // 分当前匹配串是否是*号，
        int m=s.length(),n=p.length();
        boolean[][] f=new boolean[m+1][n+1];
        f[0][0]=true;
        for(int i=0;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(p.charAt(j-1)=='*'){
                    f[i][j]=f[i][j-2];  // * 代表出现0次的时候
                    if(match(s,p,i,j-1)){ // 表示* 出现一次或多次的情况  j-1是因为来判断*前一个字符是否跟s的相同
                        f[i][j]=f[i][j] || f[i-1][j] ;
                    }
                }else{
                    if(match(s,p,i,j)){
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];

    }
    public boolean match(String s, String p,int i,int j){
        if(i==0) return false;
        if(p.charAt(j-1)=='.') return true;
        return s.charAt(i-1) == p.charAt(j-1);
    }

    public static void main(String[] args) {
        System.out.println(new PatternMatch().isMatch("aa","a*"));
    }
}
