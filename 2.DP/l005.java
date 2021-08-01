//EXTRA DP QUESTIONS

import java.util.Arrays;
public class l005{
    public static void main(String[] args) {
        // countWays_Driver();
        // BoolParent_Driver();
        // minCut_Driver();
        PP3_Driver();
    }
    public static void print(int[] arr) {
        for(int a: arr){
            System.out.print(a + ", ");
        }
        System.out.println();
    }
    public static void print2D(int[][] arr) {
        for(int[] a: arr ){
            print(a);
        }
    }

    //================================================================
    // LC:1278 Palindrome Partition III
    public static void PP3_Driver() {
        String str = "abc";
        int k = 2;
        int ans = palindromePartition(str,k);
        System.out.println("ANS: " + ans);
    }
    //Idea is to create a 2D DP that contains min number of moves required to make a string a Palindrome
    public static int palindromePartition(String str, int k) {
        int n = str.length();
        int[][] makePalindrome = new int[n][n];
        int[][] dp = new int[k+1][n+1];
        for(int gap = 0; gap < n; gap++){
            for(int si = 0, ei = gap; ei < n; si++, ei++){
                if(si == ei){
                    makePalindrome[si][ei] = 0;
                    continue;
                }
                if(str.charAt(si) == str.charAt(ei)){
                    if(gap == 1)    makePalindrome[si][ei] = 0;
                    
                    else makePalindrome[si][ei] = makePalindrome[si+1][ei-1];
                }
                else{
                    if(gap == 1)    makePalindrome[si][ei] = 1;
                    
                    else makePalindrome[si][ei] = makePalindrome[si+1][ei-1] + 1;
                }   
            }
        }
        // print2D(makePalindrome);
        for(int[] a: dp)    Arrays.fill(a, -1);
        
        int ans = palindromePartition_Memo(str, 0, n-1, k, makePalindrome, dp);
        
        return ans;
    }
      public static int palindromePartition_Memo(String str, int si, int ei, int k, int[][] makePalindrome, int[][] dp) {
        if(k == 1)  {
           return dp[k][si] =  makePalindrome[si][ei]; 
        }
        if(ei-si + 1 <= k)   return dp[k][si] = (ei-si+1 < k) ? (int)1e6 : 0;
        
        if(dp[k][si]!=-1)  return dp[k][si];
        int min = ei-si+1;
         for(int cut = si; cut<ei ; cut++){
            int lTree = makePalindrome[si][cut];
            int rTree = palindromePartition_Memo(str, cut+1, ei, k -1, makePalindrome, dp);
            min = Math.min(min, lTree  + rTree);
        }
        
        return dp[k][si] = min;
    }
    
    //================================================================
    // LC:132 Palindrome Partition II
    public static void minCut_Driver() {
        String s = "ab";
        
        int ans = minCut(s);
        System.out.println("ANS: " + ans);    
    }
    public static int minCut(String s) {
        if(s.length()<=1)     return 0;
        int[] dp = new int[s.length()];
        boolean[][] isPalindrome = new boolean[s.length()][s.length()];
        createBoolPalinDP(s, isPalindrome);
         Arrays.fill(dp, -1);
        // return minCut_Memo(s, 0, s.length()-1, dp, isPalindrome);        
        return minCut_DP(s, dp, isPalindrome);               
    }
   public static void createBoolPalinDP(String str, boolean[][] isPalindrome) {
        int n = isPalindrome.length;
        for(int gap = 0; gap < n; gap++){
            for(int si = 0, ei = gap; ei < n; si++, ei++){
                if(si == ei){
                    isPalindrome[si][ei] = true;
                    continue;
                }
                if(str.charAt(si) == str.charAt(ei)){
                    if(gap == 1)    isPalindrome[si][ei] = true;
                    
                    else isPalindrome[si][ei] = isPalindrome[si+1][ei-1];
                }
                else    isPalindrome[si][ei] = false;
            }
        }
   }

  public static int minCut_Memo(String str, int si, int ei, int[] dp, boolean[][] isPalindrome) {
        if(isPalindrome[si][ei])    return  dp[si] = 0;
        if(si == ei)    return dp[si] = 0;
        if(dp[si]!=-1)   return dp[si];
        
        int min = (int)1e8, rTree = 0;
        for(int cut = si; cut<ei; cut++){
            if(isPalindrome[si][cut]){
                rTree = minCut_Memo(str, cut+1, ei, dp, isPalindrome);
                min = Math.min(min, rTree);                
            }
            

        }
        return dp[si] = min + 1;
  }
    public static int minCut_DP(String str, int[] dp, boolean[][] isPalindrome){
        int n = dp.length;
            for(int si = 0, ei = n - 1; si < n; si++){
                if(isPalindrome[si][ei] || si == ei){
                    dp[si] = 0;
                    continue;
                }
                int min = (int)1e8, rTree = 0;
                for(int cut = si; cut<ei; cut++){
                    if(isPalindrome[si][cut]){
                        rTree = minCut_Memo(str, cut+1, ei, dp, isPalindrome);
                        min = Math.min(min, rTree);                
                    }


                }
                dp[si] = min + 1;
        }
        return dp[0];
    }

    //================================================================
    //GFG: Boolean Parenthesization - Class code
    public static void BoolParent_Driver() {
        String str = "T|T&F^T";
        int N = str.length();
        pair_[][] dp = new pair_[N][N];
        System.out.println(booleanParen(str,0,N - 1,dp).TCount % mod);
    }
    public static class pair_{
        int TCount = 0;
        int FCount = 0;
 
        pair_(int T,int F){
            this.TCount = T;
            this.FCount = F;
        }
    }
    
    static int mod = 1003;
    public static void EvaluateBooleanAns(pair_ leftAns, pair_ rightAns,char oper,pair_ ans){
         int totalWays =  ( (leftAns.TCount % mod + leftAns.FCount % mod) % mod * (rightAns.TCount % mod + rightAns.FCount % mod) % mod ) % mod;
         if(oper == '|'){
            
            int fcount = (leftAns.FCount % mod * rightAns.FCount % mod) % mod;
            ans.TCount += (totalWays % mod - fcount  % mod + mod) % mod;
            ans.FCount += fcount;
 
        }else if(oper == '&'){
            
            int tcount = (leftAns.TCount % mod * rightAns.TCount % mod) % mod;
            ans.TCount += tcount;
            ans.FCount += (totalWays % mod - tcount  % mod + mod) % mod;
       
        }else{
            
            int tcount = ( (leftAns.TCount % mod * rightAns.FCount % mod) % mod + (leftAns.FCount % mod * rightAns.TCount % mod) % mod) % mod;
            ans.TCount += tcount;
            ans.FCount += (totalWays % mod - tcount  % mod + mod) % mod;
        
        }
 
 
    }
 
    public static pair_ booleanParen(String str,int si,int ei,pair_[][] dp){
        if(si == ei){
            int t = str.charAt(si) == 'T' ? 1 : 0;
            int f = str.charAt(si) == 'F' ? 1 : 0;
            pair_ base = new pair_(t,f);
            return dp[si][ei] = base;
        }
 
        if(dp[si][ei] != null) return dp[si][ei];
 
        pair_ ans = new pair_(0,0);
        for(int cut = si + 1;cut < ei; cut+=2){
            pair_ leftAns = booleanParen(str,si,cut-1,dp);
            pair_ rightAns = booleanParen(str,cut+1,ei,dp);
 
            char oper = str.charAt(cut);
            EvaluateBooleanAns(leftAns,rightAns,oper,ans);
        }
 
        return dp[si][ei] = ans;
    }
 
 

    //================================================================
    //GFG: Boolean Parenthesization
    static void countWays_Driver(){
        String S = "T|T&F^T";
        // code here
        int i=0;
        String sym = ""; 
        String opr = "";
        for(i=0; i<S.length()-1;){
            sym += S.charAt(i++);
            opr += S.charAt(i++);
        }
        sym += S.charAt(i);
        BooleanPair[][] dp = new BooleanPair[sym.length()][sym.length()];
        // BooleanPair ans = countWays2(sym, opr, 0, sym.length()-1, dp);
        BooleanPair ans = countWays2_DP(sym, opr, dp);


        System.out.println("Bool P Ans: " + ans.trueWays);

        for(BooleanPair[] a: dp){
            for(BooleanPair b: a){
                System.out.print(b + ", ");
            }
            System.out.println();
        }

    }
    static class BooleanPair{
        int trueWays = 0, falseWays = 0;
        BooleanPair(int trueWays, int falseWays){
            this.trueWays = trueWays;
            this.falseWays = falseWays;
        }
        public String toString(){
            return this.trueWays + "/" + this.falseWays;
        }
    }
    // public static int mod = 1003;
    public static BooleanPair NoOfBooleanAns(BooleanPair lTree, BooleanPair rTree, char ch) {
        int count = 0;
        int total = ((lTree.trueWays * rTree.trueWays)%mod + (lTree.trueWays * rTree.falseWays)%mod
                  + (lTree.falseWays * rTree.trueWays)%mod + (lTree.falseWays * rTree.falseWays)%mod)%mod;

        if(ch == '|'){
            //Total - lT.F * rT.F
            count = (total - (lTree.falseWays * rTree.falseWays)%mod + mod)%mod;
        }
        else if(ch == '&'){
            count = (lTree.trueWays * rTree.trueWays)%mod;
        }
        else if(ch == '^'){
            count = ((lTree.falseWays * rTree.trueWays)%mod + (lTree.trueWays * rTree.falseWays)%mod)%mod;
        }
        BooleanPair recAns = new BooleanPair(count, total - count);
        // System.out.println("calculation : " + recAns);
        return  recAns;
                
    
    }
   
    static BooleanPair countWays2(String sym, String opr, int si, int ei, BooleanPair dp[][]){
        if(dp[si][ei]!=null)   return dp[si][ei];

        if(si == ei){
            if(sym.charAt(si) == 'T')   dp[si][ei] = new BooleanPair(1,0);
            else        dp[si][ei] = new BooleanPair(0,1);

            return dp[si][ei];
        } 
        
        
        BooleanPair myAns = new BooleanPair(0,0);
        for(int cut = si; cut < ei; cut++){
         
            BooleanPair lTree =  countWays2(sym, opr, si, cut, dp);
            BooleanPair rTree =   countWays2(sym, opr, cut+1, ei, dp);

            BooleanPair evalAns = NoOfBooleanAns(lTree, rTree, opr.charAt(cut));
            
            myAns.trueWays += evalAns.trueWays;
            myAns.falseWays += evalAns.falseWays;
            
        }
        return dp[si][ei] = myAns;
    }
    static BooleanPair countWays2_DP(String sym, String opr, BooleanPair dp[][]){
        int n = sym.length();
        for(int gap = 0; gap < n; gap++){
            for(int si = 0, ei = gap; ei < n; si++, ei++){
                if(si == ei){
                    if(sym.charAt(si) == 'T')   dp[si][ei] = new BooleanPair(1,0);
                    else        dp[si][ei] = new BooleanPair(0,1);
                    // return dp[si][ei];
                    continue;
                }
                BooleanPair myAns = new BooleanPair(0,0);
                for(int cut = si; cut < ei; cut++){
                    BooleanPair lTree = dp[si][cut];
                    BooleanPair rTree = dp[cut+1][ei];

                    BooleanPair evalAns = NoOfBooleanAns(lTree, rTree, opr.charAt(cut));
                    myAns.trueWays += evalAns.trueWays;
                    myAns.falseWays += evalAns.falseWays; 
                }
                dp[si][ei] = myAns; 
            }
        }
        return dp[0][n-1];
    }
    
}