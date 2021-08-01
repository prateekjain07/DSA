import java.util.Arrays;

class practiceDP{
    public static void main(String[] args) {
        // MCAM_Driver();
        MinMax_Driver();
    }
    public static void print1D(int[] arr) {
        for(int i: arr){
            System.out.print(i + ", ");
        }
        System.out.println();
    }
    public static void print2D(int[][] arr) {
        for(int[] i: arr){
            print1D(i);
        }
    }
    //==================================================================
    //GFG.org/minimum-maximum-values-expression/
    public static void MinMax_Driver() {
        int[] arr = {1,2,3,4,5};
        char[] optr = {'+', '*','+', '*'};
        pair[][] dp = new pair[arr.length][arr.length];
        pair res = MinMax_Memo(arr, optr, 0, arr.length-1, dp);
        // pair res = minMaxEval(arr, optr, dp, 0, arr.length-1);

        System.out.println("Min: " + res.minVal + "\tMax: " + res.maxVal);

    }
    static class pair{
        int minVal = 0, maxVal = 0;
        pair(int maxVal, int minVal){
            this.maxVal = maxVal;
            this.minVal = minVal;
        }
        public String toString(){
            return "maxV : " + this.maxVal + "\tminV: " + this.minVal;
        }
    }

    public static int evaluate(char ch, int a, int b) {
        if(ch == '+')   return a + b;
        return a*b;
    }

    public static pair minMaxEval(int[] nums, char[] operator, pair[][] dp, int si, int ei) {
        
        if(si == ei){
            return new pair(nums[si], nums[si]);
        }
        if(dp[si][ei] != null)  return dp[si][ei];

        pair myAns = new pair(-(int)1e6,(int)1e6);
        // pair recAns = null;
        for(int cut = si; cut<ei; cut++){
            pair leftTree = minMaxEval(nums, operator, dp, si, cut);
            pair rightTree = minMaxEval(nums, operator, dp, cut+1, ei);

            char ch = operator[cut];

            myAns.minVal = Math.min(myAns.minVal, evaluate(ch, leftTree.minVal,rightTree.minVal));
            myAns.maxVal = Math.max(myAns.maxVal, evaluate(ch, leftTree.maxVal,rightTree.maxVal));
        }
        // System.out.println(si + "---"  +ei + "---" + myAns);
        
        return dp[si][ei] = myAns;
    }

    public static pair eval(char opr, pair cur, pair lTree, pair rTree) {
        pair ans = new pair(cur.maxVal, cur.minVal);
        if(opr == '+') {
            ans.minVal = Math.min(ans.minVal, lTree.minVal + rTree.minVal);
            ans.minVal = Math.max(ans.maxVal, lTree.maxVal + rTree.maxVal); 
            return ans;  
        }

        //Wrong Ans by this Function
        ans.minVal = Math.min(ans.minVal, lTree.minVal * rTree.minVal);
        ans.minVal = Math.min(ans.minVal, lTree.maxVal * rTree.minVal);
        ans.minVal = Math.min(ans.minVal, lTree.minVal * rTree.maxVal);
        ans.minVal = Math.min(ans.minVal, lTree.maxVal * rTree.maxVal);
        
        
        ans.maxVal = Math.max(ans.minVal, lTree.minVal * rTree.minVal);
        ans.maxVal = Math.max(ans.minVal, lTree.maxVal * rTree.minVal);
        ans.maxVal = Math.max(ans.minVal, lTree.minVal * rTree.maxVal);
        ans.maxVal = Math.max(ans.minVal, lTree.maxVal * rTree.maxVal);
        
        return ans;
    }
    public static pair MinMax_Memo(int[] arr, char[] optr, int si, int ei, pair[][] dp) {
        // System.out.println(si + "---"  +ei);
        
        if(si == ei){
            return new pair(arr[si],arr[si]);
        }

        if(dp[si][ei]!=null)    return dp[si][ei];

        pair recAns = new pair(-(int)1e6, (int)1e6);

        for(int cut = si; cut < ei; cut++){
            pair leftTree = MinMax_Memo(arr, optr, si, cut, dp);
            pair rightTree = MinMax_Memo(arr, optr, cut+1, ei, dp);
            
            // recAns.minVal = Math.min(recAns.minVal, minMax[0]);
            // recAns.maxVal = Math.max(recAns.maxVal, minMax[1]);

            recAns = eval(optr[cut], recAns, leftTree, rightTree);
            // System.out.println(recAns);
        }

        return dp[si][ei] = recAns;
    }

    //==================================================================
    //Min Cost of Array Multiplication
    public static void MCAM_Driver() {
        int arr[] = {40,20,30,10,30};
        int n = arr.length;
        int minCostMultiplication = MCAM_Rec(arr, 0, n-1);

        int[][] dp = new int[n][n];
        int[][] dp2 = new int[n][n];
        String[][] sdp = new String[n][n];
        for(int i=0; i<n; i++)  Arrays.fill(dp[i],-1);
        String str = "A";
        for(int i=1; i<n; i++)  str += str.charAt(i-1);
        minCostMultiplication = MCAM_Memo(arr,0,n-1,dp);

        System.out.println("MinCost: " +  minCostMultiplication );

        System.out.println("MinCostDP: " + MCAM_DP(arr, dp2) );

        print2D(dp2);

        System.out.println("MinCostArrayChain: " + MCAM_DP_String(arr, dp2, sdp) );

    }
    public static int MCAM_Rec(int arr[], int si, int ei) {
        // System.out.println(si + "---"  +ei);
        if(si + 1 == ei){
            return 0;
        }
        int minCost = (int)1e8;
        for(int i = si + 1; i < ei; i++){
            int cost = 0;
            cost += MCAM_Rec(arr, si, i);
            cost += MCAM_Rec(arr, i, ei);
            cost += arr[si] * arr[i] * arr[ei];

            minCost = Math.min(minCost, cost);
        }

        return minCost;
        
    }
    public static int MCAM_Memo(int arr[], int si, int ei, int[][] dp) {
       if(si + 1 == ei){
            return dp[si][ei] = 0;
        }
        if(dp[si][ei]!=-1)  return dp[si][ei];

        int minCost = (int)1e8;
        for(int i = si + 1; i < ei; i++){
            int cost = 0;
            cost += MCAM_Memo(arr, si, i, dp);
            cost += MCAM_Memo(arr, i, ei, dp);
            cost += arr[si] * arr[i] * arr[ei];

            minCost = Math.min(minCost, cost);
        }

        return dp[si][ei] = minCost;
        
    }
    public static int MCAM_DP(int arr[], int[][] dp) {
        int n = arr.length;
        int minCost = (int)1e8;
        for(int gap = 1; gap < n ; gap++){
            for(int si = 0, ei = gap; ei < n; si++, ei++){
                if(si + 1 == ei){
                    dp[si][ei] = 0;
                    continue;
                }
                int cost = (int)1e8;
                for(int cut = si+1; cut < ei; cut++){
                    cost = Math.min(cost, dp[si][cut] + 
                    (arr[si] * arr[cut] * arr[ei]) + dp[cut][ei] );
                }
                dp[si][ei] = cost;
            }
        }
        return dp[0][n-1];
    }
    //Same Question but with brackets in Strings
    public static String MCAM_DP_String(int arr[], int[][] dp, String[][] sdp) {
        int n = arr.length;
        for(int gap = 1; gap < n ; gap++){
            for(int si = 0, ei = gap; ei < n; si++, ei++){
                if(si + 1 == ei){
                    dp[si][ei] = 0;
                    sdp[si][ei] = (char)(si + 'A') + "";
                    continue;
                }
                int cost = (int)1e8, minSI = 0, minEI = 0;
                for(int cut = si+1; cut < ei; cut++){
                if(cost >  dp[si][cut] + (arr[si] * arr[cut] * arr[ei]) + dp[cut][ei]){
                        cost = dp[si][cut] + (arr[si] * arr[cut] * arr[ei]) + dp[cut][ei];
                        sdp[si][ei] = "(" + sdp[si][cut] + sdp[cut][ei] + ")";
                    }
                }
                dp[si][ei] = cost;
            }
        }
        return sdp[0][n-1];
    }  
}