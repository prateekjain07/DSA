class l002{
    //===========================================================
    //=================STRINGS DP================================
    //===========================================================
    public static void main(String[] args) {
        palindromicSubstring();
      }
    
      public static void print(boolean[] dp) {
        for (boolean a : dp) {
          System.out.print(a + ", ");
        }
        System.out.println();
      }
    
      public static void print2D(boolean[][] dp) {
        for (boolean[] arr : dp) {
          // for(int a: arr){
          //     System.out.print(a + ", ");
          // }
          // System.out.println();
          print(arr);
        }
        System.out.println();
      }

      //================================================================
      //Palindromic Substring
      public static void palindromicSubstring() {
          String s = "abcbddbcfg";
          int n = s.length();
          boolean[][] dp = new boolean[n][n];

          System.out.println(PD_Substring_DP_Fill(s,dp));
          print2D(dp);
      }
      public static int PD_Substring_DP_Fill(String str, boolean[][] dp) {
        //This function is basically used for filling DP Array to
        //be used with other questions. Like Longest PD Substring
        
        //This function also gives total number of PD Substrings
        //Applying Gap Strategy with reference to BaseLine ie. Main Diagonal
       
        int count = 0;
        int n = str.length();

        for(int gap = 0; gap<n; gap++){
            for(int i=0, j=gap; j<n; i++, j++){
                if(gap == 0)    dp[i][j] = true;

                else if(gap == 1)   dp[i][j] = str.charAt(i) == str.charAt(j);

                else dp[i][j] = str.charAt(i) == str.charAt(j) && dp[i+1][j-1];

                if(dp[i][j])    count++;
            }
        }
        
        return count;
      }
}