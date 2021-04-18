import java.util.LinkedList;
import java.util.Arrays;
import java.util.ArrayList;

class l002{
  
    //===========================================================
    //=================STRINGS DP================================
    //===========================================================
    public static void main(String[] args) {
        palindromicSubstring();
    }
    
      public static void print(int[] dp) {
        for (int a : dp) {
          System.out.print(a + ", ");
        }
        System.out.println();
      }
    
      public static void print2D(int[][] dp) {
        for (int[] arr : dp) {
          // for(int a: arr){
          //     System.out.print(a + ", ");
          // }
          // System.out.println();
          print(arr);
        }
        System.out.println();
      }
      //================================================================
      //LeetCode 44: WildCard Matching
      public boolean isMatch(String s, String p) {
        int[][] dp = new int[s.length()+1][p.length()+1];
        for(int i=0; i<s.length()+1; i++)   Arrays.fill(dp[i], -1);
        
        int res = matchWord(s,p,0,0,dp);
        return res == 0? false:true;
        
    }
    public static int matchWord(String s, String p, int i, int j, int[][] dp){
        //Must initialize DP with -1, then set 0 for false, 1 for true
        int n = s.length(), m = p.length();
        if(i == n && j == m)  return dp[i][j] = 1;
        
        if(n == i){
            int count=0;
            for(int idx = j; idx<m; idx++){
                if(p.charAt(idx) == '*')    count++;
                else break;
            }
            return dp[i][j] = (count == m - j)?1:0;
        }
        if(j == m)  return dp[i][j] = 0;

        if(dp[i][j]!=-1)  return dp[i][j];
        int res = -1;
        char iS = s.charAt(i), jP = p.charAt(j);
        if(jP != '*' && jP != '?'){
          if(iS == jP)  res = matchWord(s,p,i+1,j+1,dp);
            
            else return dp[i][j] = 0;
        }
        else if(jP == '?'){
          res = matchWord(s,p,i+1,j+1,dp);
        }
        else{
            int count=1;
            for(int idx = j+1; idx<m; idx++){
                if(p.charAt(idx) == '*')    count++;
                else break;
            }
            System.out.println(count);
          for(int len = 0; len<= n - i && res!=1; len++){
            res = matchWord(s,p,i+len,j+count,dp);
          }
        }
        
        return dp[i][j] = res;
      }
      public static int matchWord_DP(String s, String p, int I, int J, int[][] dp){
        int n = s.length(), m = p.length();
        for(int i=n; i>=0; i++){
          for(int j=m; j>=0; j++){
            if(i == n && j == m){
              dp[i][j] = 1;
              continue;
            }
            if(n == i){
                int count=0;
                for(int idx = j; idx<m; idx++){
                    if(p.charAt(idx) == '*')    count++;
                    else break;
                }
                dp[i][j] = (count == m - j)?1:0;
                continue;

            }
            if(j == m){
              dp[i][j] = 0;
              continue;
            }

            int res = -1;
        char iS = s.charAt(i), jP = p.charAt(j);
        if(jP != '*' && jP != '?'){
          if(iS == jP)  res = dp[i+1][j+1];
            
            else{
              return dp[i][j] = 0;
            }
        }
        else if(jP == '?'){
          res = dp[i+1][j+1];
        }
        else{
            int count=1;
            for(int idx = j+1; idx<m; idx++){
                if(p.charAt(idx) == '*')    count++;
                else break;
            }
            System.out.println(count);
          for(int len = 0; len<= n - i && res!=1; len++){
            res = dp[i+len][j+count];
          }
        }
        
        dp[i][j] = res;

          }
        }        
        return dp[I][J];
      }

      public static boolean isMatch_Class(String s, String p) {
        p = removeStar_Class(p);
        int n = s.length(), m = p.length();
        int[][] dp = new int[n+1][m+1];
        for(int i=0; i<=n; i++) Arrays.fill(dp[i],-1);
        return matchWord_Class(s,p,n,m,dp) == 1? true: false;
      }
      public static int matchWord_Class(String s, String p, int n, int m, int[][] dp) {

        char ch1 = s.charAt(n-1);
        char ch2 = p.charAt(m-1);
        
        if(n==0 || m == 0){
          if(n==0 && m==0){
            return dp[n][m] = 1;
          }
          else if(m==1 && ch2 == '*')   return dp[n][m] = 1;
          return dp[n][m] = 0;
        }

        if(dp[n][m]!=-1)  return dp[n][m];

        int val = 0;
        if(ch1 == ch2 || ch2 == '?')  val = matchWord_Class(s,p,n-1,m-1,dp);
        else if(ch2 == '*'){
          //When * is an empty string
          res = res || (matchWord_Class(s,p,n,m-1,dp) == 1) ;

          //When * maps multiple characters
          res = res || (matchWord_Class(s,p,n-1,m,dp) == 1) ;
          
          val = res? 1:0;
        }

        return dp[n][m] = val;
      }
      public static String removeStar_Class(String str) {
        if(str.length() == 0) return "";

        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0));
        int i=1;
        while(i<str.length()){
          while(i<str.length() && str.charAt(i-1) == '*' && str.charAt(i) == '*' ){
            i++;
          }
          if(i<str.length())  sb.append(str.charAt(i));
          i++;
        }

        return sb.toString();
      }
      //================================================================
      //Leetcode 72: Edit Distance
      public static int minDistance2(String word1, String word2, int n, int m, int[][] dp) {
        //Must FIll DP with -1
        if(n==0 || m==0){
          if(n==0 && m==0){
            return dp[n][m] = 0;
            //This if is Redundant. It's handled below as well
          }
          return dp[n][m] = n!=0? n:m;
          
        }
        int count = 0;
        if(word1.charAt(n-1) == word2.charAt(m-1))
          count = minDistance2(word1,word2,n-1,m-1,dp);

        else{
          count = Math.min(minDistance2(word1,word2,n,m-1,dp), //Insert
                          Math.min(minDistance2(word1,word2,n-1,m,dp), //Delete
                          minDistance2(word1,word2,n-1,m-1,dp))) + 1; //Replace
        }

        return dp[n][m] = count;
      }
    
    public static int minDistance2_DP(String word1, String word2, int N, int M, int[][] dp) {
        
        for(int n=0; n<=N; n++){
          for(int m=0; m<=M; m++){
            if(n==0 || m==0){
              if(n==0 && m==0){
                dp[n][m] = 0;
                //This if is Redundant. It's handled below as well

              }
              else
                dp[n][m] = n!=0? n:m;
              continue;
            }

            int count = 0;
            if(word1.charAt(n-1) == word2.charAt(m-1))
              count = dp[n-1][m-1];
    
            else{
              count = Math.min(dp[n][m-1], //Insert
                              Math.min(dp[n-1][m], //Delete
                              dp[n-1][m-1])) + 1; //Replace AND +1 for the Operation performed
            }
    
            dp[n][m] = count;

          }
        }
        
        return dp[N][M];


      }
      //================================================================
      //Leetcode 1035 : Uncrossed Lines
      public static int LCS_Leet(int[] A, int[] B, int n, int m, int[][] dp) {
        // if(n==0 && m==0)    return dp[n][m] = 1;
        //Don't write it since +1 is handled in the IF 
        
        if(n==0 || m==0)    return dp[n][m] = 0;
        
        if(dp[n][m] != -1)  return dp[n][m];
        
        int count = 0;
        if(A[n-1] == B[m-1]){
            count = LCS(A,B,n-1,m-1,dp) + 1;
        }
        else{
            count = Math.max(LCS(A,B,n,m-1,dp),LCS(A,B,n-1,m,dp));
            
        }
        
        return dp[n][m] = count;
        
        
    }
    public static int LCS_Leet_DP(int[] A, int[] B, int N, int M, int[][] dp) {
        // if(n==0 && m==0)    return dp[n][m] = 1;
        //Don't write it since +1 is handled in the IF 
        for(int n=0; n<=A.length; n++){
            for(int m=0; m<=B.length; m++){
                 if(n==0 || m==0){
                     dp[n][m] = 0;
                     continue;
                 }    
                int count = 0;
                if(A[n-1] == B[m-1]){
                    count = dp[n-1][m-1] + 1;
                }
                else{
                    count = Math.max(dp[n][m-1],dp[n-1][m]);

                }

                dp[n][m] = count;
                
                        
            }    
        }

        
        
        return dp[N][M];
        
    }

      //=================================================================
      //Leetcode 1143 - Longest Common Subsequence
      public static int LCS_DP(String text1, String text2, int N, int M, int[][] dp) {
        // if(n==0 && m==0)    return dp[n][m] = 1;
        //Don't write it since +1 is handled in the IF 
        //Fill DP with -1 IMPORTANT
        
        
        for(int n=0; n<=text1.length(); n++){
            for(int m=0; m<=text2.length(); m++){
                 if(n==0 || m==0){
                     dp[n][m] = 0;
                     continue;
                 }    
                int count = 0;
                if(text1.charAt(n-1) == text2.charAt(m-1)){
                    count = dp[n-1][m-1] + 1;
                }
                else{
                    count = Math.max(dp[n][m-1],dp[n-1][m]);

                }

                dp[n][m] = count;
                
                        
            }    
        }

        
        
        return dp[N][M];
        
    }
    public static int LCS(String text1, String text2, int n, int m, int[][] dp) {
        // if(n==0 && m==0)    return dp[n][m] = 1;
        //Don't write it since +1 is handled in the IF 
        //Fill DP with -1 IMPORTANT
        
        if(n==0 || m==0)    return dp[n][m] = 0;
        
        if(dp[n][m] != -1)  return dp[n][m];
        
        int count = 0;
        if(text1.charAt(n-1) == text2.charAt(m-1)){
            count = LCS(text1,text2,n-1,m-1,dp) + 1;
        }
        else{
            count = Math.max(LCS(text1,text2,n,m-1,dp),LCS(text1,text2,n-1,m,dp));
            
        }
        
        return dp[n][m] = count;
        
        
    }
      //================================================================
      //GFG - count-palindromic-subsequence
      public static long countPS2(String s, int i, int j, long[][] dp){
        long mod = 1000000000+7;
        //MOD is used for extremely high inputs

        if(i>=j)    return dp[i][j] = i==j?1:0;
        
        if(dp[i][j]!=0) return dp[i][j];
        
        long x = countPS2(s,i+1,j-1,dp);
        long y = countPS2(s,i,j-1,dp);
        long z = countPS2(s,i+1,j,dp);
        
        if(s.charAt(i) == s.charAt(j))
            dp[i][j] = ((x%mod+1)%mod + (y%mod+z%mod-x%mod + mod)%mod)%mod;
        else
            dp[i][j] = (y%mod+z%mod-x%mod + mod)%mod;
            
        return dp[i][j];
        
    }
    long countPS2_DP(String s, int I, int J, long[][] dp){
      long mod = 1000000000+7;
      for(int gap = 0; gap<=s.length(); gap++){
          for(int i=0,j=gap; j<s.length(); i++,j++){
              if(i>=j){
                  dp[i][j] = i==j?1:0;
                  continue;
              }
              if(dp[i][j]!=0) return dp[i][j];
              
              long x = countPS2(s,i+1,j-1,dp);
              long y = countPS2(s,i,j-1,dp);
              long z = countPS2(s,i+1,j,dp);
              
              if(s.charAt(i) == s.charAt(j))
                  dp[i][j] = ((x%mod+1)%mod + (y%mod+z%mod-x%mod + mod)%mod)%mod;
              else
                  dp[i][j] = (y%mod+z%mod-x%mod + mod)%mod;
              
          }
      }
          
      return dp[I][J];
      
  }
      //===============================================================
      //Leetcode 115
      public int numDistinct(String s, String t) {
        int[][] dp = new int[s.length()+1][t.length()+1];
        //Important to fill DP with -1 since 0 is a possible answer which
        //leads to TLE in 3 cases
        for(int i=0; i<s.length(); i++)     Arrays.fill(dp[i],-1);
        return numDistinct2(s,t,0,0,dp); 
    }
    
    public static int  numDistinct2(String s, String t, int i, int j,int[][] dp) {
        
        if(j == t.length()) return dp[i][j]= 1;

        if(i == s.length() || s.length() - i < t.length() - j) return dp[i][j] = 0;
        //2nd condition above is very important and it saves time, from 8ms to 2ms
        //checking if Length of T is greater than S, then, no answer is possible

        if(dp[i][j]!=-1) return dp[i][j];
        int count = 0;

        if(s.charAt(i) == t.charAt(j))
          count+= numDistinct2(s,t,i+1,j+1,dp);

        //Common to both scenarios
        count+= numDistinct2(s,t,i+1,j,dp);
  
        return dp[i][j] = count;
    }

    public static int  numDistinct3(String s, String t, int n, int m,int[][] dp) {
      //Class code, here, we started comparing the strings from backwards
      //I like going forwards much better
      if(m == 0)  return dp[n][m] = 1;

      if(n<m) return dp[n][m] = 0;

      if(dp[n][m]!= -1) return dp[n][m];

      int count  =0;
      if(s.charAt(n-1) == t.charAt(m -1)){
        count += numDistinct3(s,t,n-1,m-1,dp);
      }
      count += numDistinct3(s,t,n-1,m,dp);

      return dp[n][m] = count;
    }

    public static int  numDistinct2DP(String s, String t, int i, int j,int[][] dp) {
        
      int n = s.length(), m = t.length();
      for(i=n; i>=0 ; i--){
        for(j=m; j>=0; j--){
          if(j == m){
            dp[i][j]= 1;
            continue;
          }
          if(i == n){
            dp[i][j]= 0;
            continue;
          }

          int count = 0;

          if(s.charAt(i) == t.charAt(j))
            count+= dp[i+1][j+1];
    
          count+= dp[i+1][j];
    
          dp[i][j] = count;

        }
      }

      return dp[0][0];

      // if(i == s.length()) return dp[i][j] = 0;

      // if(dp[i][j]!=-1) return dp[i][j];

      // return dp[i][j] = count;
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