import java.util.LinkedList;

class l001 {

  public static void main(String[] args) {
    // twoPointer();
    // floodfill();
    // gfg();
    // noOfWays();
    numDecodings("***");
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

  //==================================================================
  //Leetcode #639 - No of Ways to decode
  static int mod = 1000000000 + 7;

  public static void numDecodings(String s) {
    long[] dp = new long[s.length() + 1];
    // for(int i=0; i<=s.length(); i++)	dp[i] = -1;
    Arrays.fill(dp, -1);
    System.out.println(numDecodings2(s, 0, dp));
  }

  public static long numDecodingsAB(String s, int idx, long[] dp) {
    // if(idx == s.length())	return dp[idx] = 1;
    // if(s.charAt(idx) == '0')	return dp[idx] = 0;
    // if(dp[idx]!=-1)		return dp[idx];

    long a = 1, b = 0;
	//A is for Single Digit Calls and B for double digit calls
    for (int idx = s.length() - 1; idx >= 0; idx--) {
      long count = 0;

      char ch = s.charAt(idx);
      char ch2 = '-';
      if (idx < s.length() - 1) ch2 = s.charAt(idx + 1);

      if (ch == '0') 
	  	count = 0; 

	  else if (ch == '*') {
        //Single Digit Calls
        count = (count + 9 * a % mod) % mod;

        if (
          idx < s.length() - 1 && ch2 >= '0' && ch2 <= '6'
        ) count = (count + 2 * b % mod) % mod; 
		else if ( // *0-6 - 10-16,20-26
          idx < s.length() - 1 && ch2 >= '7'
        ) count = (count + b % mod) % mod; 
		else if ( // *7,8,9 - 17,18,19
          idx < s.length() - 1 && ch2 == '*'
        ) count = (count + 15 * b % mod) % mod; //** - 11-19, 21-26
      } else {
        //Single Digit Calls
        count = (count + a % mod) % mod;

        if (idx < s.length() - 1 && ch2 == '*') {
          if (ch == '1') count = (count + 9 * b % mod) % mod; 
		  else if (ch == '2') count = (count + 6 * b % mod) % mod;
        } else if (idx < s.length() - 1) {
          int num = (ch - '0') * 10 + (ch2 - '0');
          if (num <= 26) count = (count + b % mod) % mod;
        }
      }

      b = a;
      a = count;
    }

    return a;
  }

  public static long numDecodings2(String s, int idx, long[] dp) {
    if (idx == s.length()) return dp[idx] = 1;
    if (s.charAt(idx) == '0') return dp[idx] = 0;
    if (dp[idx] != -1) return dp[idx];

    long count = 0;
    char ch = s.charAt(idx);
    char ch2 = '-';
    if (idx < s.length() - 1) ch2 = s.charAt(idx + 1);

    if (ch == '*') {
      //Single Digit Calls
      count = (count + 9 * numDecodings2(s, idx + 1, dp) % mod) % mod;

      if (
        idx < s.length() - 1 && ch2 >= '0' && ch2 <= '6'
      ) count = // *0-6 - 10-16,20-26
        (count + 2 * numDecodings2(s, idx + 2, dp) % mod) % mod; else if (
        idx < s.length() - 1 && ch2 >= '7'
      ) count = (count + numDecodings2(s, idx + 2, dp) % mod) % mod; else if ( // *7,8,9 - 17,18,19
        idx < s.length() - 1 && ch2 == '*'
      ) count = (count + 15 * numDecodings2(s, idx + 2, dp) % mod) % mod; //** - 11-19, 21-26
    } else {
      //Single Digit Calls
      count = (count + numDecodings2(s, idx + 1, dp) % mod) % mod;

      if (idx < s.length() - 1 && ch2 == '*') {
        if (ch == '1') count =
          (count + 9 * numDecodings2(s, idx + 2, dp) % mod) % mod; else if (
          ch == '2'
        ) count = (count + 6 * numDecodings2(s, idx + 2, dp) % mod) % mod;
      } else if (idx < s.length() - 1) {
        int num = (ch - '0') * 10 + (ch2 - '0');
        if (num <= 26) count =
          (count + numDecodings2(s, idx + 2, dp) % mod) % mod;
      }
    }

    return dp[idx] = count;
  }

  //===================================================================
  //No of Ways with Roll Dice
  public static void noOfWays() {
    int n = 10;
    int[] dp = new int[n + 1];
    // System.out.println(NFWRollDice_memo(n,dp,0));
    // print(dp);
    System.out.println(NFWRollDice_DP(n, dp));
    print(dp);

    System.out.println(NFWRollDice_DP_Opti(n));
    System.out.println(NFWRollDice_DP_Opti2(n));
  }

  public static int NFWRollDice_memo(int n, int[] dp, int idx) {
    if (idx == n) return dp[n] = 1;

    if (dp[idx] != 0) return dp[idx];

    int count = 0;
    for (int i = 1; i + idx <= n && i <= 6; i++) {
      count += NFWRollDice_memo(n, dp, i + idx);
    }

    return dp[idx] = count;
  }

  public static int NFWRollDice_DP(int n, int[] dp) {
    for (int i = n; i >= 0; i--) {
      if (i == n) {
        dp[i] = 1;
        continue;
      }

      int count = 0;
      for (int idx = 1; i + idx <= n && idx <= 6; idx++) {
        count += dp[i + idx];
      }

      dp[i] = count;
    }

    return dp[0];
  }

  public static int NFWRollDice_DP_Opti(int n) {
    //Using a Linked List
    LinkedList<Integer> ll = new LinkedList<>();
    int sum = 0;
    for (int i = n; i > 0; i--) {
      if (i == n) {
        ll.add(1);
        sum = 1;
        continue;
      }

      if (ll.size() < 6) {
        ll.add(sum);
        sum += sum;
      } else {
        ll.add(sum);
        sum += sum - ll.removeFirst();
      }
    }
    return sum;
  }

  public static int NFWRollDice_DP_Opti2(int n) {
    //Using Array
    int len = 7;
    int[] arr = new int[len];
    for (int i = 0; i <= n; i++) {
      if (i <= 1) {
        arr[i] = 1;
        continue;
      }
      if (arr[i % len] == 0) arr[i % len] = 2 * arr[(i - 1) % len]; else arr[i %
        len] =
        2 * arr[(i - 1) % len] - arr[i % len];
    }
    return arr[n % len];
  }

  //===================================================================
  //					GEEKSFORGEEKS
  public static void gfg() {
    // gfgGoldMine();
    gfgFPP();
  }

  //================gfg/friends-pairing-problem========================
  public static void gfgFPP() {
    int n = 4;
    int[] dp = new int[n + 1];
    System.out.println("Friends Pair: " + friendsPair(n, dp));
    print(dp);
  }

  public static int friendsPair(int n, int[] dp) {
    if (n == 1 || n == 0) return dp[n] = 1;

    if (dp[n] != 0) return dp[n];

    int single = friendsPair(n - 1, dp);
    int pairUp = (n - 1) * friendsPair(n - 2, dp);
    return dp[n] = single + pairUp;
  }

  public static int friendsPairDP(int N, int[] dp) {
    for (int n = 0; n <= N; n++) {
      if (n == 0 || n == 1) {
        dp[n] = 1;
        continue;
      }

      int single = dp[n - 1];
      int pairUp = (n - 1) * dp[n - 2];
      dp[n] = single + pairUp;
    }
    return dp[N];
  }

  public static long friendsPairDP_Opti(int N) {
    long a = 1, b = 1;
    long div = 1000000007;
    for (int n = 2; n <= N; n++) {
      long ans = (b % div + ((n - 1) * a) % div) % div;
      a = b;
      b = ans;
    }
    return b % div;
  }

  //================ gfg/gold-mine-problem/ ===========================
  public static void gfgGoldMine() {
    int[][] mat = { { 2, 1 }, { 1, 2 } };
    int[][] dp = new int[mat.length][mat[0].length];
    for (int i = 0; i < mat.length; i++) {
      for (int j = 0; j < mat[0].length; j++) {
        dp[i][j] = -1;
      }
    }
    int max = -1;
    for (int i = 0; i < mat.length; i++) max =
      Math.max(max, goldMine(mat, dp, i, 0));

    // System.out.println("Gold Mine: " + max);
    // print2D(dp);
    System.out.println("Gold Mine DP: " + goldMineDP(mat, dp));
    print2D(dp);
  }

  public static int goldMine(int[][] mat, int[][] dp, int r, int c) {
    if (c == mat[0].length - 1) {
      return dp[r][c] = mat[r][c];
    }
    if (dp[r][c] != -1) return dp[r][c];
    int max = -1;

    if (r - 1 >= 0 && c + 1 < mat[0].length) max =
      Math.max(max, goldMine(mat, dp, r - 1, c + 1));

    if (c + 1 < mat[0].length) max = Math.max(max, goldMine(mat, dp, r, c + 1));

    if (r + 1 < mat.length && c + 1 < mat[0].length) max =
      Math.max(max, goldMine(mat, dp, r + 1, c + 1));

    return dp[r][c] = max + mat[r][c];
  }

  public static int goldMineDP(int[][] mat, int[][] dp) {
    //Going Column Major from Last Column to First Column
    int maxAns = -1;
    for (int c = mat[0].length - 1; c >= 0; c--) {
      for (int r = mat.length - 1; r >= 0; r--) {
        if (c == mat[0].length - 1) {
          dp[r][c] = mat[r][c];

          //For R x 1 Matrices
          if (c == 0) maxAns = Math.max(maxAns, dp[r][c]);

          continue;
        }

        int max = -1;

        if (r - 1 >= 0 && c + 1 < mat[0].length) max =
          Math.max(max, dp[r - 1][c + 1]);

        if (c + 1 < mat[0].length) max = Math.max(max, dp[r][c + 1]);

        if (r + 1 < mat.length && c + 1 < mat[0].length) max =
          Math.max(max, dp[r + 1][c + 1]);

        dp[r][c] = max + mat[r][c];

        if (c == 0) maxAns = Math.max(maxAns, dp[r][c]);
      }
    }
    return maxAns;
  }

  //========================Floodfill==================================
  public static void floodfill() {
    int n = 4;
    int[][] dp = new int[n][n];
    int[][] dir = { { 1, 0 }, { 1, 1 }, { 0, 1 } };
    // System.out.println("1 Jump Memo: " + FF_RDB_1(dp,0,0,dir));
    // System.out.println("1 Jump DP: " + FF_RDB_1_DP(dp,dir));

    // System.out.println("Infi Jump Memo: " + FF_RDB_infi(dp,0,0,dir));
    System.out.println("Infi Jump DP: " + FF_RDB_infi_DP(dp, dir));
    print2D(dp);
  }

  //Since, no restriction on board is there
  //and only moving forward, so no need of board[][]
  public static int FF_RDB_infi_DP(int[][] dp, int[][] dir) {
    for (int r = dp.length - 1; r >= 0; r--) {
      for (int c = dp[0].length - 1; c >= 0; c--) {
        if (r == dp.length - 1 && c == dp[0].length - 1) {
          dp[r][c] = 1;
          continue;
        }

        // if(dp[r][c]!=0) return dp[r][c];

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
          for (int rad = 1; rad < dp.length; rad++) {
            int x = dir[d][0] * rad + r;
            int y = dir[d][1] * rad + c;

            if (x < dp.length && y < dp[0].length) count += dp[x][y];
          }
        }
        dp[r][c] = count;
      }
    }

    return dp[0][0];
  }

  public static int FF_RDB_infi(int[][] dp, int r, int c, int[][] dir) {
    if (r == dp.length - 1 && c == dp[0].length - 1) {
      return dp[r][c] = 1;
    }

    if (dp[r][c] != 0) return dp[r][c];

    int count = 0;
    for (int d = 0; d < dir.length; d++) {
      for (int rad = 1; rad < dp.length; rad++) {
        int x = dir[d][0] * rad + r;
        int y = dir[d][1] * rad + c;

        if (x < dp.length && y < dp[0].length) count +=
          FF_RDB_infi(dp, x, y, dir);
      }
    }

    return dp[r][c] = count;
  }

  public static int FF_RDB_1(int[][] dp, int r, int c, int[][] dir) {
    if (r == dp.length - 1 && c == dp[0].length - 1) {
      return dp[r][c] = 1;
    }

    if (dp[r][c] != 0) return dp[r][c];

    int count = 0;
    for (int d = 0; d < dir.length; d++) {
      int x = dir[d][0] + r;
      int y = dir[d][1] + c;

      if (x < dp.length && y < dp[0].length) count += FF_RDB_1(dp, x, y, dir);
    }

    return dp[r][c] = count;
  }

  public static int FF_RDB_1_DP(int[][] dp, int[][] dir) {
    for (int r = dp.length - 1; r >= 0; r--) {
      for (int c = dp[0].length - 1; c >= 0; c--) {
        if (r == dp.length - 1 && c == dp[0].length - 1) {
          dp[r][c] = 1;
          continue;
        }
        // if(dp[r][c]!=0) return dp[r][c];
        int count = 0;
        for (int d = 0; d < dir.length; d++) {
          int x = dir[d][0] + r;
          int y = dir[d][1] + c;
          if (x < dp.length && y < dp[0].length) count += dp[x][y];
        }

        dp[r][c] = count;
      }
    }
    return dp[0][0];
  }

  //==============Two Pointer Type=====================================
  public static void twoPointer() {
    fibo();
  }

  //Fibonacci Series
  public static void fibo() {
    int n = 7;
    int[] dp = new int[n + 1];
    // System.out.println("Ans: " + fib01(n,dp));
    System.out.println("Ans: " + fib01DP(n, dp));

    print(dp);
  }

  public static int fib01(int n, int[] dp) {
    if (n <= 1) return dp[n] = n;

    if (dp[n] != 0) return dp[n];

    int a = fib01(n - 1, dp);
    int b = fib01(n - 2, dp);

    return dp[n] = a + b;
  }

  public static int fib01DP(int N, int[] dp) {
    for (int n = 0; n <= N; n++) {
      if (n <= 1) {
        dp[n] = n;
        continue;
      }
      // if(dp[n]!=0)    return dp[n];
      int a = dp[n - 1];
      int b = dp[n - 2];

      dp[n] = a + b;
    }

    return dp[N];
  }

  public static int fib01DPOpti(int n) {
    int a = 0, b = 1;
    for (int i = 2; i <= n; i++) {
      int sum = a + b;
      a = b;
      b = sum;
    }
    return b;
  }
}
