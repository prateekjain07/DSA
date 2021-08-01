import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;


class l003 {

  public static void main(String[] args) {
    // coinChangeDriver();
    // subsetSumDriver();
    // KnapsackDriver();
    // LC_416_Driver();
    // noOfSolutionsDriver();
    LIS_Driver();
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
  public static int calls = 0;
  //=====================================================================
      //LC 354: Russian Doll Envelopes
    //Same code is valid for GFG Building Bridges
    public static int RDE_Driver(int[][] env) {
      Arrays.sort(env, new Comparator<int[]>(){
          public int compare(int[] a, int[] b){
              // if(a[0] < b[0]) return -1;
              // if(a[0] > b[0]) return 1;

              // if(a[1] < b[1]) return 1;
              // return -1;

              if(a[0] != b[0])    return a[0] - b[0];
              return b[1] - a[1];
          }
          }
      });
      return RDE(env);
  }
  public static int RDE(int[][] env) {
      int[] dp = new int[env.length];
      int max = 1;
      // dp[0] = 1;
      for(int i=0; i<env.length; i++){
          dp[i] = 1;
          for(int j=i-1; j>=0; j--){
              if(env[i][1] > env[j][1])
                  dp[i] = Math.max(dp[i], dp[j]+1);
          }
          max = Math.max(max, dp[i]);
      }
      return max;
  }
  //=================================================================
  //Longest Increasing Subsequence
  public static void LIS_Driver() {
    int arr[] = {0,8,4,12,2,10,6,14,1,9,5,13,3,11,7,15};
    int maxLen = 0;
    int dp_LIS[] = new int[arr.length];
    int dp_LDS[] = new int[arr.length];
    int dp_LBS[] = new int[arr.length];

    print(arr);
    // for(int i=arr.length-1; i>=0; i--){
    //   maxLen = Math.max(maxLen,LIS_Rec(arr,i,dp));
    // }

    //LIS
    // maxLen = LIS_DP(arr,dp_LIS);
    // print(dp_LIS);    
    // System.out.println("LIS: " + maxLen);

    // //LDS
    // maxLen=0;
    // maxLen = LDS_DP(arr,dp_LDS);
    // print(dp_LDS);
    // System.out.println("LDS: " + maxLen);

    //LBS 
    maxLen=0;
    maxLen = LBS(arr,dp_LIS,dp_LDS,dp_LBS);
    print(dp_LBS);
    System.out.println("LBS: " + maxLen);

  }
  public static int LIS_Rec(int[] arr, int n, int[] dp) {
    if(n==0){//Redundant
      return dp[n] = 1;
    }
    if(dp[n]!=0)  return dp[n];
    int maxLen = 1;
    for(int i = n-1; i>=0; i--){
      if(arr[i]<arr[n]){
        int len = LIS_Rec(arr,i,dp);
        maxLen = Math.max(maxLen,len+1);
      }
    }

    return dp[n] = maxLen;
  }
  public static int LIS_DP(int[] arr, int[] dp) {
    int maxLen = 1;
    //we can also initialise our DP with 1 since it's the lowest possible ans
    for(int idx=0; idx<arr.length; idx++){
      dp[idx] = 1;
      for(int i=idx-1; i>=0; i--){
        if(arr[i]<arr[idx]){
          dp[idx] = Math.max(dp[idx], dp[i] + 1);
        }
      }
      maxLen = Math.max(maxLen,dp[idx]);
    }
    return maxLen;
  }

  public static int LDS_DP(int[] arr, int[] dp) {
    int maxLen = 1;
    int n = arr.length;
    //Going other way than LIS
    //we can also initialise our DP with 1 since it's the lowest possible ans
    for(int idx=n-1; idx>=0; idx--){
      dp[idx] = 1;
      for(int i=idx+1; i<n; i++){
        if(arr[i]<arr[idx]){
          dp[idx] = Math.max(dp[idx], dp[i] + 1);
        }
      }
      maxLen = Math.max(maxLen,dp[idx]);
    }
    return maxLen;
  }
        //Longest Bitonic Sequence
  public static int LBS(int []arr, int[] dp_LIS, int[] dp_LDS, int[] dp_LBS) {
    //LBS = LDS + LIS - 1  at particular point
    // LIS_DP(arr,dp_LIS);
    // LDS_DP(arr,dp_LDS);
    
    int maxLen = 1;
    for(int i=0; i<dp_LIS.length; i++){
      dp_LBS[i] = dp_LDS[i] + dp_LIS[i] - 1;
      maxLen = Math.max(maxLen, dp_LBS[i]);
    }
    
    return maxLen;
  }

  //=====================================================================
  //LC 494: Target Sum
  public int findTargetSumWays(int[] nums, int target) {
    int sum = 0;
    for(int a: nums)    sum+= a;
    if(nums.length == 0)    return 0;
    if(target>sum || target<-sum)   return 0;
    int[][] dp = new int[nums.length+1][2*sum +1];
    for(int[] d: dp)    Arrays.fill(d,-1);
    return  findTargetSumWays2(nums, target, 0,dp,sum);
    
}
public int findTargetSumWays2(int[] nums, int tar, int idx, int[][] dp,int sum) {
    if(tar>sum|| tar<-sum) return 0;
    if(tar == 0 && idx == nums.length){
        // System.out.println(str);
        return dp[idx][tar+sum] = 1;
    }    
    if(idx == nums.length){
        return dp[idx][tar+sum] = 0;
    }
    if(dp[idx][tar+sum] != -1) return dp[idx][tar+sum];
    int count = 0;
    count += findTargetSumWays2(nums, tar - nums[idx], idx+1,dp,sum);//Positive
    count += findTargetSumWays2(nums, tar + nums[idx], idx+1,dp,sum);//Negative
    
    return dp[idx][tar+sum] = count;
} 
  //==================================================================
  //https://www.geeksforgeeks.org/find-number-of-solutions-of-a-linear-equation-of-n-variables/
  public static void noOfSolutionsDriver() {
    int[] coeff = {2,2,3};
    int rhs = 4;
    int[][] dp = new int[coeff.length+1][rhs+1];
    for(int[] a: dp)  Arrays.fill(a, -1);
    System.out.println(noOfSolutions(coeff,rhs,0, "",dp) + "  " + calls);
    print2D(dp);
  }
  public static int noOfSolutions(int[] coeff, int rhs, int idx, String str, int[][] dp) {
    calls++;
    if(rhs == 0){
      System.out.println(str);
      return dp[idx][rhs] = 1;
    }
    if(idx == coeff.length) return dp[idx][rhs]  = 0;

    if(dp[idx][rhs]!= -1 )  return dp[idx][rhs];
    int m = 1;
    int count = 0;
    while(rhs - coeff[idx]*m>=0){
      count += noOfSolutions(coeff, rhs - coeff[idx]*m, idx+1, str + coeff[idx]+" * " + m + " + ",dp);
      m++;
    }
    count += noOfSolutions(coeff, rhs, idx+1, str +coeff[idx]+" * " + 0 + " + ",dp);
    
    return dp[idx][rhs] = count;
  }

  //==================================================================
  //LC 416 : Partition Equal Subset Sum
  public boolean canPartitionDriver(int[] nums) {
    int sum = 0;
    for(int ele: nums)  sum+= ele;
    if(sum%2!=0)    return false;
    int tar = sum/2;
    int[][] dp = new int[nums.length + 1][tar+1];
    for(int[] d: dp) Arrays.fill(d,-1);
    return canPartition2(nums,0,0,0, dp,tar)  == 1;
}
public int canPartition2(int[] nums, int s1, int s2, int idx, int[][] dp,int tar) {
    if(idx == nums.length || tar == 0){
        if(tar == 0)    return dp[idx][tar] = 1;;
        return dp[idx][tar] = 0;
    }
    if(dp[idx][tar]!=-1) return dp[idx][tar];
    boolean res = false;
    if(tar-nums[idx]>=0)
    res = res || canPartition2(nums,s1+nums[idx],s2,idx+1,dp, tar - nums[idx]) == 1;
    res = res || canPartition2(nums,s1,s2+nums[idx],idx+1,dp, tar) == 1;
    
    return dp[idx][tar] = res?1:0;
}
  //===================================================================
  //GFG: Knapsack Driver
  public static void KnapsackDriver() {
    // int N = 3, W = 4;
    // int values[] = {1,2,3}, weight[] = {4,5,1};
    int N = 58, W = 41;
    int[] values = new int[N], weight = new int[N];
    Scanner scn = new Scanner(System.in);
    N = scn.nextInt();
    W = scn.nextInt();
    for(int n=0; n<N; n++)  values[n] = scn.nextInt();
    for(int n=0; n<N; n++)  weight[n] = scn.nextInt();
    
    int[][] dp = new int[N+1][W+1];
    for(int[] d: dp)  Arrays.fill(d,-1);
    System.out.println("Knapsack No Rep : " + KnapsackNoRepDP(values,weight,W,N,dp));
    print2D(dp);
  }
  //GFG: 0-1 Knapsack Problem=====================================================
  //Not Totally Correct
  public static int KnapsackNoRep(int[] values,int[] wts, int w, int n, int[][] dp) {
    if(n == 0 || w == 0){
      return dp[n][w] = 0;
    }
    if(dp[n-1][w]!=-1)  return dp[n-1][w];

    int max = 0;
    if(w - wts[n-1]>=0){
      max = Math.max(max, KnapsackNoRep(values,wts,w-wts[n-1],n-1,dp)+ values[n-1]);
    }
    max = Math.max(max, KnapsackNoRep(values,wts,w,n-1,dp));

    return dp[n][w] = max ;
  }
  //Memo : Going from 0 to N works for some reason
  public static int KnapsackNoRepFront(int[] values,int[] wts, int w, int idx, int[][] dp) {
    // System.out.println(idx + " -- " + w);
    if(idx == wts.length || w == 0){
      return dp[idx][w] = 0;
    }
    if(dp[idx][w]!=-1)  return dp[idx][w];

    int max = 0;
    if(w - wts[idx]>=0){
      max = Math.max(max, KnapsackNoRepFront(values,wts,w-wts[idx],idx+1,dp)+ values[idx]);
    }
    max = Math.max(max, KnapsackNoRepFront(values,wts,w,idx+1,dp));

return dp[idx][w] = max ;
}
  //DP Works for some reason  
  public static int KnapsackNoRepDP(int[] values,int[] wts, int W, int N, int[][] dp) {
    for(int n=0; n<=N;n++){
        for(int w = 0; w<=W; w++){
            if(n == 0 || w == 0){
              dp[n][w] = 0;
              continue;
            }
            
            int max = 0;
            if(w - wts[n-1]>=0){
              max = Math.max(max, dp[n-1][w-wts[n-1]]+ values[n-1]);
            }
            max = Math.max(max, dp[n-1][w]);
            
            dp[n][w]= max;
        }
    }
     return dp[N][W];
  }


  //GFG: Unbounded Knapsack==============================================
  public static int KS_unbounded_DP_Class(int[] values,int[] wts,int w){
    int[] dp = new int[w+1];
    for(int i = 0; i<wts.length; i++){
        for(int tar = wts[i]; tar<=w; tar++){
            dp[tar] = Math.max(dp[tar],dp[tar-wts[i]] + values[i]);
        }
    }
    
    return dp[w];
  }
   //Memo : Going from 0 to N works for some reason
  public static int KnapsackFront(int[] values,int[] wts, int w, int idx, int[][] dp) {
    // System.out.println(idx + " -- " + w);
    if(idx == wts.length || w == 0){
      return dp[idx][w] = 0;
    }
    if(dp[idx][w]!=-1)  return dp[idx][w];

    int max = 0;
    if(w - wts[idx]>=0){
      max = Math.max(max, KnapsackFront(values,wts,w-wts[idx],idx,dp)+ values[idx]);
    }
    max = Math.max(max, KnapsackFront(values,wts,w,idx+1,dp));

return dp[idx][w] = max ;
}
  //GFG: Fractional Knapsack (Greedy)======================================
  
  class Item {
    int value, weight;
    Item(int x, int y){
        this.value = x;
        this.weight = y;
    }
  }
  class ItemCompare implements Comparator<Item>{
    public int compare(Item a, Item b){
        double r1 = a.value*1.0/a.weight;
        double r2 = b.value*1.0/b.weight;
        if(r1-r2>0)   return -1;
        else return 1;
        // int ans = (int)(r2-r1);
        // System.out.println(r2 + " --- " + r1);
        // return ans;
        //ans < 0 => a first
        //ans >= 0 => b first
    }
  }

  double fractionalKnapsack(int W, Item arr[], int n) 
    {
        // Your code here
        
        // ArrayList<Item> al = new ArrayList<>();
        // for(Item a: arr)    al.add(a);
        Arrays.sort(arr, new ItemCompare());
        double value = 0;
        for(Item a: arr){
            // System.out.println(a.value + "---" + a.weight);
            if(a.weight<=W){
                W -= a.weight;
                value+= a.value;
            }
            else{
                value+= W*(a.value*1.0/a.weight);
            
                break;
            }
        }
        return value;
    }
  
  //===================================================================
  //GFG: Subset Sum Problem - Find no. of ways to achieve the given sum
  public static void subsetSumDriver() {
    int set[] = { 3, 34, 4, 12, 5, 2 }, sum = 9;
    int dp[] = new int[sum + 1];
    Arrays.fill(dp, -1);
    System.out.println("No. of ways 1D DP : " + subsetSumDP(set, sum, dp, 0));
    print(dp);
    System.out.println("Ans 1D DP: " + subsetSum_Sol(dp,set, sum, 0));
    
    int[] arr = {2,3,5,7};
    int tar = 10;
    int[][] dp2 = new int[arr.length+1][tar+1];
    for(int[] d: dp2) Arrays.fill(d,-1);
    System.out.println("No. of ways Class 2D DP: " + subsetSumDP_Class(arr, arr.length, tar, dp2));
    print2D(dp2);
    calls = 0;
    System.out.println("Answers Class 2D DP: " + subsetSum_Class_Sol(dp2, arr, tar, arr.length));
    // System.out.println("calls : " + calls);
        
  }

  public static int subsetSum(int[] arr, int tar, int[] dp, int idx) {
    if (tar == 0) {
      return dp[tar] = 1;
    }
    if (idx == arr.length) return 0;

    if (dp[tar] != -1) return dp[tar];

    int count = 0;
    if (tar - arr[idx] >= 0) count +=
      subsetSum(arr, tar - arr[idx], dp, idx + 1);

    count += subsetSum(arr, tar, dp, idx + 1);

    return dp[tar] = count;
  }
  public static int subsetSumDP(int[] arr, int T, int[] dp, int IDX) {
    
    for(int idx = arr.length; idx>=0; idx--){
      for(int tar=T; tar>=0; tar--){
        if (tar == 0) {
          dp[tar] = 1;
          continue;
        }
        if (idx == arr.length){
          dp[tar] = 0;
          continue;
        } 
        int count = 0;
        if (tar - arr[idx] >= 0) 
          count +=  dp[tar - arr[idx]];
    
        count += dp[tar];
    
        dp[tar] = count;
      }
    }
    return dp[T];
  }

  public static ArrayList<String> subsetSum_Sol(int[] dp, int[] arr, int tar, int idx) {
    calls++;
    if(tar == 0){
      ArrayList<String> baseAns = new ArrayList<String>();
      baseAns.add("");
      return baseAns;
    }
    if(idx == arr.length)  return null;

    ArrayList<String> ans = new ArrayList<String>();
    ArrayList<String> recAns = null;

    if(tar-arr[idx]>=0 && dp[tar-arr[idx]]>0){
      recAns = subsetSum_Sol(dp,arr,tar-arr[idx],idx+1);
      if(recAns!=null)
        for(String str: recAns) ans.add(str +arr[idx] + " - ");
    }

    if(dp[tar]>0){
      recAns = subsetSum_Sol(dp,arr,tar,idx+1);
      if(recAns!=null)
        for(String str: recAns) ans.add(str);
    }
    
    return ans;
    
  }
  
  public static int subsetSum_Class(int[] arr, int n, int tar,int[][] dp) {
    if(tar == 0)  return dp[n][tar] = 1;
    if(n == 0)  return dp[n][tar] = 0;

    if(dp[n][tar] != -1)  return dp[n][tar];
    int count = 0;
    if(tar - arr[n-1]>=0) count+= subsetSum_Class(arr,n-1,tar-arr[n-1],dp);
    
    count+= subsetSum_Class(arr,n-1,tar,dp);

    return dp[n][tar] = count;
  }
  public static int subsetSumDP_Class(int[] arr, int N, int T,int[][] dp) {
    for(int n=0; n<=N; n++){
      for(int tar = 0; tar<=T; tar++){
        if(tar == 0){
          dp[n][tar] = 1;
          continue;
        }
        if(n == 0){
          dp[n][tar] = 0;
          continue;
        }

        int count = 0;
        if(tar - arr[n-1]>=0) 
          count+= dp[n-1][tar-arr[n-1]];
        
        count+= dp[n-1][tar];
        dp[n][tar] = count;

      }
    }    
    return dp[N][T];
  }
  public static ArrayList<String> subsetSum_Class_Sol(int[][] dp, int[] arr, int tar, int n) {
    calls++;
    if(tar == 0){
      ArrayList<String> baseAns = new ArrayList<String>();
      baseAns.add("");
      return baseAns;
    }
    if(n == 0)  return null;

    ArrayList<String> ans = new ArrayList<String>();
    ArrayList<String> recAns = null;

    if(tar-arr[n-1]>=0 && dp[n-1][tar-arr[n-1]]>0){
      recAns = subsetSum_Class_Sol(dp,arr,tar-arr[n-1],n-1);
      if(recAns!=null) // Not needed in 2D DP, just for Uniformity
        for(String str: recAns) ans.add(str +arr[n-1] + " - ");
    }

    if(dp[n-1][tar]>0){
      recAns = subsetSum_Class_Sol(dp,arr,tar,n-1);
      if(recAns!=null)
        for(String str: recAns) ans.add(str);
    }
    
    return ans;
  }

  //===================================================================
  //LeetCode 322: min Coin Change
  public static int minCoinChangeDriver(int[] coins, int amount) {
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, -1);
    int ans = minCoinChangeDP(coins, amount, dp);
    return ans == 10001 ? -1 : ans;
  }

  public static int minCoinChange(int[] arr, int tar, int[] dp) {
    int minCoin = 10001;
    if (tar == 0) return 0;
    if (dp[tar] != -1) return dp[tar];
    for (int ele : arr) {
      if (tar - ele >= 0) {
        // minCoin = Math.min(minCoin, minCoinChange(arr,tar-ele,dp) + 1);
        int val = minCoinChange(arr, tar - ele, dp);
        if (val != 10001 && val + 1 < minCoin) minCoin = val + 1; //This is done to prevent overflow
      }
    }

    return dp[tar] = minCoin;
  }

  public static int minCoinChangeDP(int[] arr, int target, int[] dp) {
    int minCoin = 10001;
    dp[0] = 0;
    for (int tar = 1; tar <= target; tar++) {
      minCoin = 10001;

      for (int ele : arr) {
        if (tar - ele >= 0) {
          // minCoin = Math.min(minCoin, minCoinChange(arr,tar-ele,dp) + 1);
          int val = dp[tar - ele];
          if (val != 10001 && val + 1 < minCoin) minCoin = val + 1; //This is done to prevent overflow
        }
      }

      dp[tar] = minCoin;
    }
    return dp[target];
  }

  //=================================================================
  //CoinChange Questions
  public static void coinChangeDriver() {
    int[] arr = { 2, 3, 5, 7 };
    int target = 10;
    int[] dp = new int[target + 1];
    //   Arrays.fill(dp,-1);
    //   System.out.println(CoinChangePermInfi(arr,target,dp));
    // System.out.println(CoinChangePermDPInfi(arr,target,dp));
    System.out.println(CoinChangeCombDPInfi(arr, target, dp));

    print(dp);
  }

  public static int CoinChangePermInfi(int[] arr, int target, int[] dp) {
    if (target == 0) {
      return dp[target] = 1;
    }

    if (dp[target] != -1) return dp[target];

    int count = 0;
    for (int ele : arr) {
      if (target - ele >= 0) count += CoinChangePermInfi(arr, target - ele, dp);
    }

    return dp[target] = count;
  }

  public static int CoinChangePermDPInfi(int[] arr, int T, int[] dp) {
    for (int target = 0; target <= T; target++) {
      if (target == 0) {
        dp[target] = 1;
        continue;
      }
      int count = 0;
      for (int ele : arr) {
        if (target - ele >= 0) count += dp[target - ele];
      }

      dp[target] = count;
    }
    return dp[T];
  }

  public static int CoinChangeCombDPInfi(int[] arr, int T, int[] dp) {
    //Must Fill DP with 0 only and not -1
    dp[0] = 1;
    for (int ele : arr) {
      // for(int target = 1; target<=T; target++){
      //     if(target - ele >=0)
      //         dp[target] += dp[target-ele];
      // }
      for (int target = ele; target <= T; target++) {
        dp[target] += dp[target - ele];
      }
    }
    return dp[T];
  }
}
