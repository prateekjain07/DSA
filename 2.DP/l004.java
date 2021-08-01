import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;

public class l004{
    public static void main(String[] args) {
        // MCM_Driver();
        // minMaxEval_Driver();
        // burstBalloons_Driver();
        // OBST_Driver();
        MSTP_Driver();
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
    public static void printTree(Node root) {
        if(root == null)    return;

        String lNode = "null", rNode = "null";
        if(root.left != null){
            lNode = root.left.key + "/" + root.left.freq;
        }
        if(root.right != null){
            rNode = root.right.key + "/" + root.right.freq;
        }
        
        System.out.println(lNode + " <--- " +
        root.key + '/' + root.freq + " ---> " + rNode );

        printTree(root.left);
        printTree(root.right);    
    }
    //================================================================
    //LC: 1039: Min Score Triangulation of Polygon
    public static void MSTP_Driver() {
        int arr[] = {1,3,1,4,1,5};
        int dp[][] = new int[arr.length][arr.length];
        int ans = MSTP_Memo(arr, 0, arr.length-1, dp);
        System.out.println("MSTP Ans: " + ans);
    }
    public static int MSTP_Memo(int[] arr, int si, int ei, int[][] dp) {
        if(dp[si][ei]!=0)   return dp[si][ei];

        int min = (int)1e9;
        for(int cut = si+1; cut < ei; cut++){
            int leftTree = cut - si < 2 ? 0 : MSTP_Memo(arr, si, cut,dp);
            int rightTree = ei - cut < 2 ? 0 : MSTP_Memo(arr, cut, ei,dp);
            int myCost = arr[si] * arr[cut] * arr[ei];

            min = Math.min(min, leftTree + rightTree + myCost);
        }
        return dp[si][ei] = min;
    }
    //================================================================
    //GFG: Optimal Binary Search Tree
    public static void OBST_Driver() {
        int keys[] = {10, 12, 20}, freq[] = {34, 8, 50};
        int[][] dp = new int[keys.length][keys.length];

        // int ans = Optimal_BST_Cost(keys,freq,dp,0, keys.length - 1, 1);
        // System.out.println("OBST Ans: " + ans);

        int ans = OBST_DP(keys,freq,dp);
        System.out.println("OBST DP Ans: " + ans);
        print2D(dp);

        Node ansNode = Optimal_BST_Construct(keys,freq,dp,0, keys.length - 1, 1);
        printTree(ansNode);
        System.out.println("OBST_Construct Ans: " + ansNode.minCost);
    }
    public static int OBST_DP(int[] keys, int[] freq, int[][] dp) {
        int n = keys.length;
        for(int gap = 0; gap < n; gap++){
            for(int si=0, ei = gap; ei < n; si++,ei++){
                if(si == ei){
                    dp[si][ei] = freq[si];
                    continue;
                }
                int sum = 0, minCost = (int)1e9;
                for(int cut = si; cut <= ei; cut++){
                    int lTree = (cut == si) ? 0 : dp[si][cut-1];
                    int rTree = (cut == ei) ? 0 : dp[cut+1][ei];
                    
                    sum += freq[cut];
                    minCost = Math.min(minCost, lTree + rTree);
                }
                dp[si][ei] = minCost + sum;
            }
        }
        return dp[0][n-1];
    }
    public static int Optimal_BST_Cost(int[] keys, int[] freq, int[][] dp, int si, int ei, int level) {
        
        if(si == ei){
            return dp[si][ei] = level * freq[si];
        }

        if(dp[si][ei]!=0)   return dp[si][ei];

        int min = (int)1e9;
        for(int cut = si; cut<=ei; cut++){
            int leftTree = cut == si? 0 : Optimal_BST_Cost(keys, freq, dp, si, cut-1,level + 1);
            int rightTree = cut == ei? 0 :Optimal_BST_Cost(keys, freq, dp, cut+1, ei,level + 1);
            
            min = Math.min(min, leftTree + freq[cut]*level + rightTree);
        }
        return dp[si][ei] = min;
    }
    
    static class Node{
        int key = 0, freq = 0, minCost = 0;
        Node left = null, right = null;
        Node(int key, int freq, int minCost){
            this.key = key;
            this.freq = freq;
            this.minCost = minCost;
        }
    }
    public static Node Optimal_BST_Construct(int[] keys, int[] freq, int[][] dp, int si, int ei, int level) {
        if(si == ei){
            return new Node(keys[si], freq[si], freq[si]*level);
        }
        Node root = null;
        int min = (int)1e9;
        for(int cut = si; cut <= ei; cut++){
            Node leftTree = cut == si? null : Optimal_BST_Construct(keys, freq, dp, si, cut-1,level + 1);
            Node rightTree = cut == ei? null :Optimal_BST_Construct(keys, freq, dp, cut+1, ei,level + 1);
            
            int cost = (leftTree == null? 0 : leftTree.minCost) + 
            (freq[cut] * level) + (rightTree == null? 0 : rightTree.minCost);
            if(min > cost){
                min = cost;
                root = new Node(keys[cut], freq[cut], cost);
                root.left = leftTree;
                root.right = rightTree;
            }
            // min = Math.min(min, leftTree + freq[cut]*level + rightTree);
        }
        return root;
        
    }
    //=================================================================
    //LC #312: Burst Balloons
    public static void burstBalloons_Driver() {
        int[] nums = {3,1,5,8};
        // char[] operator = {'+','*','+','*'};
        int n = nums.length;
        int[][] dp =  new int[n][n];

        // int ans = burstBalloons_Class(nums, dp, 0, n-1);
        int ans = burstBalloons_DP(nums,dp);
        System.out.println("ans : " + ans);
        print2D(dp);
    }

    //My code
    public static int burstBalloons(int[] nums, int[][] dp, int si, int ei) {
        if(si>ei)   return 0;
        if(si == ei){
            int cutVal = nums[si];
            if(si > 0){
                cutVal *= nums[si-1];
            }
            if(si < nums.length-1){
                cutVal*= nums[si+1];
            }
            return cutVal;
        }

        if(dp[si][ei]!=0)   return dp[si][ei];

        int max = -(int)1e9;
        for(int cut = si; cut <= ei; cut++){
            int leftTree = burstBalloons(nums, dp, si, cut-1);
            int rightTree = burstBalloons(nums, dp, cut+1, ei);

            //lastCut denotes the cost involved with bursting the cut index
            //balloon at the end
            int lastCut = nums[cut];
            if(si>0)   lastCut *= nums[si-1];
            if(ei<nums.length-1)    lastCut *= nums[ei+1];


            max = Math.max(max, leftTree + rightTree + lastCut);
            
        }
        return dp[si][ei] = max;
    }

    public static int burstBalloons_Class(int[] nums, int[][] dp, int si, int ei) {
        int leftVal = si == 0 ? 1: nums[si-1];
        int rightVal = ei == nums.length-1 ? 1: nums[ei+1];

        if(si == ei){
            return leftVal * nums[si] * rightVal;
        }

        if(dp[si][ei]!= 0)  return dp[si][ei];

        int max = -(int)1e9;
        for(int cut = si; cut <= ei; cut++){
            int leftTree = cut == si ? 0 : burstBalloons_Class(nums,dp,si,cut-1);
            int rightTree = cut == ei ? 0 : burstBalloons_Class(nums,dp,cut+1,ei);

            max = Math.max(max, leftTree + (leftVal * nums[cut] * rightVal) + rightTree);        
        }
        return dp[si][ei] = max;
    }
    
    public static int burstBalloons_DP(int[] nums, int[][] dp) {
        
        int n = nums.length;
        for(int gap = 0; gap < n; gap++ ){
            for(int si = 0, ei = gap; ei < n; si++, ei++ ){
                int leftVal = si == 0 ? 1: nums[si-1];
                int rightVal = ei == nums.length-1 ? 1: nums[ei+1];

                int max = -(int)1e9;
                for(int cut = si; cut <= ei; cut++){
                    int leftTree = cut == si ? 0 : dp[si][cut-1];
                    int rightTree = cut == ei ? 0 : dp[cut+1][ei];

                    max = Math.max(max, leftTree + (leftVal * nums[cut] * rightVal) + rightTree);        
                }
                dp[si][ei] = max;
            }
        }
        return dp[0][n-1];
    }
    
    //=================================================================
    //GFG: minimum-maximum-values-expression
    public static class pair {
        int minVal = 0;
        int maxVal = 0;

        pair(int minVal, int maxVal){
            this.minVal = minVal;
            this.maxVal = maxVal;
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

        pair myAns = new pair((int)1e9,-(int)1e9);
        // pair recAns = null;
        for(int cut = si; cut<ei; cut++){
            pair leftTree = minMaxEval(nums, operator, dp, si, cut);
            pair rightTree = minMaxEval(nums, operator, dp, cut+1, ei);

            char ch = operator[cut];

            myAns.minVal = Math.min(myAns.minVal, evaluate(ch, leftTree.minVal,rightTree.minVal));
            myAns.maxVal = Math.max(myAns.maxVal, evaluate(ch, leftTree.maxVal,rightTree.maxVal));
        }
        return dp[si][ei] = myAns;
    }

    public static void minMaxEval_Driver() {
        int[] nums = {1,2,3,4,5};
        char[] operator = {'+','*','+','*'};
        int n = nums.length;
        pair[][] dp =  new pair[n][n];

        pair ans = minMaxEval(nums, operator, dp, 0, n-1);
        System.out.println("minVal = " + ans.minVal + "\n" + "maxVal = " + ans.maxVal + "\n");
    }

    //=================================================================
    //Minimum Cost of Multiplication
    public static String matrices = "";
    public static void MCM_Driver() {
        int[] arr = {10, 20, 30, 40, 30};
        int n = arr.length;
        int[][] dp = new int[n][n];
        char ch = 'A';
        // matrices = "";
        // for(int i=1; i<n; i++)  matrices += (ch++);
        
        //Recursion
        // for(int[] a: dp)    Arrays.fill(a,-1);
        // int ans = MCM(arr,0,arr.length-1,dp);

        //DP
        // int ans = MCM_DP(arr,0,arr.length-1,dp);

        //DP with Ans
        // int[][] dp2 = new int[n][n];
        // int ans = MCM_DP_Ans(arr,0,arr.length-1,dp,dp2);

        //DP StrindDPArr
        String[][] sdp = new String[n][n];
        for(String[] s: sdp)    Arrays.fill(s, "");
        int ans = MCM_StringDPArr(arr,0,n-1,dp,sdp);
        System.out.println(ans);
        print2D(dp);
        System.out.println('\n');
        for(String[] s: sdp){
            System.out.println(Arrays.asList(s));
        }
        
        System.out.println("Ans String: " + sdp[0][n-1]);
        
        // MCM_GetAnsString(dp2,0,n-1);
        // System.out.println(matrices);
        
    }
    public static int MCM(int[] arr, int si, int ei, int[][] dp) {
        //Here, ei is exclusive 

        if(si + 1 == ei)    return dp[si][ei] = 0;
        if(dp[si][ei]!=-1)  return dp[si][ei];
        int minCost = (int)1e9;
        for(int cut = si+1; cut<ei; cut++){
            int leftTree = MCM(arr, si, cut, dp);
            int rightTree = MCM(arr, cut, ei, dp);
            
            int costOfMultiplication = leftTree + (arr[si]*arr[cut]*arr[ei]) + rightTree;
            minCost = Math.min(minCost, costOfMultiplication); 
        }
        return dp[si][ei] = minCost;
    }

    public static int MCM_StringDPArr(int[] arr, int SI, int EI, int[][] dp, String[][] sdp) {
        //Here, ei is exclusive 
        //Gap Strategy is used
        for(int gap = 1; gap<arr.length; gap++){
            for(int si = 0, ei = gap; ei < arr.length; si++, ei++){
                if(si + 1 == ei){
                    dp[si][ei] = 0;
                    sdp[si][ei] = (char)(si+'A') + "";
                    continue;
                }
                // if(dp[si][ei]!=-1)  return dp[si][ei];
                int minCost = (int)1e9;
                for(int cut = si+1; cut<ei; cut++){
                    int leftTree = dp[si][cut];
                    int rightTree = dp[cut][ei];
                    
                    int costOfMultiplication = leftTree + (arr[si]*arr[cut]*arr[ei]) + rightTree;
                    // minCost = Math.min(minCost, costOfMultiplication);
                    if(minCost > costOfMultiplication){
                        minCost = costOfMultiplication;
                        sdp[si][ei] ='(' +  sdp[si][cut] + sdp[cut][ei] + ')';
                    } 
                }
                dp[si][ei] = minCost;
            }
        }
        return dp[SI][EI];

    }


    public static int MCM_DP(int[] arr, int SI, int EI, int[][] dp) {
        //Here, ei is exclusive 
        //Gap Strategy is used
        for(int gap = 1; gap<arr.length; gap++){
            for(int si = 0, ei = gap; ei < arr.length; si++, ei++){
                if(si + 1 == ei){
                    dp[si][ei] = 0;
                    continue;
                }
                // if(dp[si][ei]!=-1)  return dp[si][ei];
                int minCost = (int)1e9;
                for(int cut = si+1; cut<ei; cut++){
                    int leftTree = dp[si][cut];
                    int rightTree = dp[cut][ei];
                    
                    int costOfMultiplication = leftTree + (arr[si]*arr[cut]*arr[ei]) + rightTree;
                    minCost = Math.min(minCost, costOfMultiplication); 
                }
                dp[si][ei] = minCost;
            }
        }
        return dp[SI][EI];

    }

}