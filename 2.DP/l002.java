import java.util.LinkedList;
class l002{
  //Start from 3.10 on the video for Leetcode 115
  
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
	  //==================================================================
	  //Longest Palindromic Subsequence - LC 516
    public static int longestPalindromeSubseq2(String s, int si, int ei, int[][] dp) {
      // System.out.println(si + "---" + ei);
  if(si==ei)	return dp[si][ei]=1;
  
      // if(si == ei-1)  return dp[si][ei] = (s.charAt(si) == s.charAt(ei)? 2:1);
      
  if(si>ei)	return dp[si][ei] = 0;

  if(dp[si][ei]!=0)	return dp[si][ei];

  int len = 0;
      if(s.charAt(si) == s.charAt(ei))
      len = Math.max(len, longestPalindromeSubseq2(s,si+1,ei-1,dp) + 2);
      else{
          len = Math.max(len, longestPalindromeSubseq2(s,si,ei-1,dp));
      len = Math.max(len, longestPalindromeSubseq2(s,si+1,ei,dp));
          
      }
  return dp[si][ei] = len ;

  }
  
  public static int longestPalindromeSubseq2DP(String s, int SI, int EI, int[][] dp) {
    int n = s.length();
    for(int gap = 0; gap<n; gap++){
      for(int si=0, ei=gap; ei<n; si++,ei++){
        if(si==ei){
          dp[si][ei]=1;
          continue;
        }

        int len = 0;
        if(s.charAt(si) == s.charAt(ei))
          len = dp[si+1][ei-1] + 2;
        else{
          len = Math.max(dp[si][ei-1],dp[si+1][ei]);    
        }
        dp[si][ei] = len;
      }
    }
      LinkedList<Character> LL1 = new LinkedList<>();
      LinkedList<Character> LL2 = new LinkedList<>();
      generateLPS_String(dp,s,SI,EI,LL1,LL2);
      
      System.out.println(ansArr);
      
    return dp[SI][EI];
  }

  static ArrayList<String> ansArr = new ArrayList<>();

  public static void generateLPS_String(int[][] dp, String s, int i, int j, LinkedList<Character> LL1, LinkedList<Character> LL2) {
    if(i == j){
      LL1.addLast(s.charAt(i));
       
      String ans = "";
        
      ans= LL1.toString() + LL2.toString();
       ansArr.add(ans);
      return;
    }
    if(i>j)   return;
    if(s.charAt(i) == s.charAt(j)){
      //Remove what you add so as to deal with multiple answers
      LL1.addLast(s.charAt(i));
      LL2.addFirst(s.charAt(j));
      generateLPS_String(dp,s,i+1,j-1,LL1,LL2);
        
      LL1.removeLast();
      LL2.removeFirst();
        
    }
    else{
      if(dp[i+1][j] >= dp[i][j-1]){
        generateLPS_String(dp,s,i+1,j,LL1,LL2);
      }
      else{
        generateLPS_String(dp,s,i,j-1,LL1,LL2);
      }
    }
  }
  
	  //==================================================================
      //Leetcode #5
      public static String longestPalindromicSubstring (String str) {

        //Here, DP array at every i,j index contains the length of longest
        //palindromic substring between i,j
        
        //This function also gives total number of PD Substrings
        //Applying Gap Strategy with reference to BaseLine ie. Main Diagonal
       
        int count = 0;
        int n = str.length();
        int[][] dp = new int[n][n];
        int si=0,ei=0,len = 0;
        for(int gap = 0; gap<n; gap++){
            for(int i=0, j=gap; j<n; i++, j++){
                if(gap == 0)    dp[i][j] = 1;

                else if(gap == 1 && str.charAt(i) == str.charAt(j))   dp[i][j] = 2;

                else if (str.charAt(i) == str.charAt(j) && dp[i+1][j-1]>0) dp[i][j] = dp[i+1][j-1] + 2;

                if(dp[i][j]>len){
                  si = i;
                  ei = j;
                  len = dp[i][j];
                }
            }
        }
        
        return str.substring(si,ei+1);
      }

      //================================================================
      //General DP Filling Code
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