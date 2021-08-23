import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;

public class misc_ques{
    public static void main(String[] args) {
        System.out.println("MWS : " + minWindow_04("ADOBECODEBANC", "ABC"));
    }


    //======================================================================================
    // HashMaps and HashTables : LC 76 : Minimum Window Substring
    //4th Try: Using a Sliding Window Approach and HashMap
	public static String minWindow_04(String s, String t) {
		if(s.length() < t.length())  return "";
		HashMap<Character, Integer> map1 = new HashMap<>();
		HashMap<Character, Integer> map2 = new HashMap<>();

		int i = -1, j = -1, mct = 0, dmct = t.length(), finalJ = 0, finalI = s.length(); 
		// here i and j are -1 because we will preincrement idx to use them
		// mct - map count current, dmct - desired map count
		//finalI & J to store final start and end index of desired ans
		//Creating map2 for reference of freq
		for(int k = 0; k < t.length(); k++){
			char ch = t.charAt(k);
			map2.put(ch, map2.getOrDefault(ch, 0) + 1);
		}

		while(true){
			boolean f1 = false, f2 = false;

			//Adding chars to string S
			while(i<s.length()-1 && mct < dmct){
				i++;

				char ch = s.charAt(i);

				if(map1.getOrDefault(ch,0) < map2.getOrDefault(ch,0)){
					mct++;
				}

				map1.put(ch, map1.getOrDefault(ch, 0) + 1);
				f1= true;
			}

			//Releasing extra chars from string S
			while(j <= i && mct == dmct){
				j++;
				char ch = s.charAt(j);

				if(map1.get(ch) == 1)  
					map1.remove(ch);
				else
				map1.put(ch, map1.get(ch) - 1);
					
				if(map1.getOrDefault(ch,0) < map2.getOrDefault(ch,0)){
					mct--;
				}
				f2= true;

			}

			//Comparing possible answers and finding minimum
			if(finalI - finalJ > i - j){
				finalJ = j;
				finalI = i;
			}
			

			//Loop Terminate Condition
			if(f1 == false && f2 == false){
				break;
			}
		}
		return finalI - finalJ == s.length() ? "" :  s.substring(finalJ, finalI + 1);
 	}
 //3rd Try : Using a Sliding Window Approach -------------------------------------------------------
    public static String minWindow_03_Driver(String s, String t) {
        char charToSub = 'A';
        int m = t.length(), n = s.length(), i = 0, size = 0;
        int[] staticCharArr = new int[58];
        for(i = 0; i < m; i++) {
              staticCharArr[t.charAt(i) - charToSub]++;  
        } 
        // int[][] dp = new int[n][2];
        // // int[] dummy = {-1,-1};
        // for(int[] d : dp)   Arrays.fill(d, -1);

        // int[] ans =  minWindow_02(s,t,0,s.length()-1, staticCharArr, dp);
        // if(ans[0] == -1)    return "";
        return minWindow_03(s,t, staticCharArr);
    }
  
    public static boolean willWindowBeValid(int[] windowcCharArr, int[] staticCharArr, char charToCheck) {
        char charToSub = 'A';
        if(staticCharArr[charToCheck - charToSub] == 0) return true;

        // System.out.println("VALUES for  " + charToCheck + " are : " + windowcCharArr[charToCheck - charToSub] + ", "+ staticCharArr[charToCheck - charToSub]);
         if(windowcCharArr[charToCheck - charToSub] > staticCharArr[charToCheck - charToSub]){
             windowcCharArr[charToCheck - charToSub]--;
             return true;
         }
         return false;
    }
    public static String minWindow_03(String s, String t, int[] staticCharArr) {
        int[] charArr = {}, windowcCharArr = new int[58];
        char charToSub = 'A';
        int left =0, right =0, size = 0, m = t.length(), n = s.length(), i=0;
        int[] ans = {-1,-1};
        //Finding The Window
        charArr = staticCharArr.clone();
        for(i=0, size = m; i < n && size > 0; i++){
            char ch = s.charAt(i);
            if(staticCharArr[ch-charToSub] > 0) windowcCharArr[ch-charToSub]++;
                    
            if(charArr[ch-charToSub] > 0){
                size--;
                charArr[ch-charToSub]--;
                // windowcCharArr[ch-charToSub]++;
                if(size == 0){
                    right = i;
                    break;
                }
            }
        }
        if(size>0)  return "";
        ans[0] = left;
        ans[1] = right;

        //Sliding the window
        while(left <n && right < n){
            char charToCheck = s.charAt(left);
            if(willWindowBeValid(windowcCharArr, staticCharArr, charToCheck)){
                // System.out.println("IDX IF: " + left + ", " + right);
                left++;
            
                if(right - left < ans[1] - ans[0]){
                    ans[1] = right;
                    ans[0] = left;
                }
            }
            else{
                left++;
                // System.out.println("IDX ELSE: " + (++left) + ", " + right);
                charArr = staticCharArr.clone();
                windowcCharArr = new int[58];
                for(i=left, size = m; i < n && size > 0; i++){
                    char ch = s.charAt(i);
                    if(staticCharArr[ch-charToSub] > 0) windowcCharArr[ch-charToSub]++;
                        
                    if(charArr[ch-charToSub] > 0){
                        size--;
                        charArr[ch-charToSub]--;
                        if(size == 0){
                            right = i;
                            break;
                        }
                    }
                }
                if(size > 0)    break;

                if(right - left < ans[1] - ans[0]){
                    ans[1] = right;
                    ans[0] = left;
                }
        
            }
    
        }
        return s.substring(ans[0], ans[1] + 1);
    }

    //2nd Try : Passes 265/266 Test Cases ------------------------------------------------------------
    public static String minWindow_Driver(String s, String t) {
        char charToSub = 'A';
        int m = t.length(), n = s.length(), i = 0, size = 0;
        int[] staticCharArr = new int[58];
        for(i = 0; i < m; i++) {
              staticCharArr[t.charAt(i) - charToSub]++;  
        } 
        int[][] dp = new int[n][2];
        // int[] dummy = {-1,-1};
        for(int[] d : dp)   Arrays.fill(d, -1);

        int[] ans =  minWindow_02(s,t,0,s.length()-1, staticCharArr, dp);
        if(ans[0] == -1)    return "";
        return s.substring(ans[0], ans[1] + 1);
    }
    public static int[] minWindow_02(String s, String t, int si, int ei, int[] staticCharArr, int[][] dp) {
        int[] ans = {-1, -1};
        char charToSub = 'A';

        // System.out.println("AT   " + si + ", " + ei);

        if( si>ei || ei - si + 1 < t.length())    return ans;

        if(dp[si][0] != -1 )    return dp[si];

        int  lastIdxFront = -1,firstIdxFront = -1, m = t.length(), size = 0, i = 0;
        int[] charArr = {};

        //Finding Front IDX
        //Front Iteration
        charArr = staticCharArr.clone();
        
        for(i=si, size = m; i <= ei && size > 0; i++){
            char ch = s.charAt(i);
            if(charArr[ch-charToSub] > 0){
                size--;
                charArr[ch-charToSub]--;
                if(size == 0){
                    lastIdxFront = i;
                    break;
                }
            }
        }

        if(size>0) {
            // System.out.println("RETURNING ABRUPTLY : (" + ans[0] + ", "+ ans[1] +  ")    from   (" + si + ", " + ei + ")");
            return ans;
        
        } //Back Iteration
        charArr = staticCharArr.clone();
        for(i=lastIdxFront, size = m; i >=si && size > 0; i--){
            char ch = s.charAt(i);
            if(charArr[ch-charToSub] > 0){
                size--;
                charArr[ch-charToSub]--;
                if(size == 0){
                    firstIdxFront = i;
                    break;
                }
            }
        }

        ans[0] = firstIdxFront;
        ans[1] = lastIdxFront;

        // System.out.println("FIRST Calculated IDX : (" + ans[0] + ", "+ ans[1] +  ")    from   (" + si + ", " + ei + ")");


        //Recursion from the code
        for(i = firstIdxFront+1; i<=lastIdxFront; i++){
            char ch = s.charAt(i);
            if(staticCharArr[ch - charToSub] == 0)  continue;
            int[] recAns = minWindow_02(s, t, i, ei, staticCharArr, dp);
            if(recAns[0] != -1){
                if(recAns[1] - recAns[0] < ans[1] - ans[0]){
                    ans[0] = recAns[0]; 
                    ans[1] = recAns[1];
                }
            }
        }

        // System.out.println("Returning : (" + ans[0] + ", "+ ans[1] +  ")    from   (" + si + ", " + ei + ")");

        return dp[si] = ans;

    }



    //1st Try : Didn't Work ------------------------------------------------------------------------------------
    public String minWindow_01(String s, String t) {
        if(t.length() > s.length())     return "";
        // char charToSub = Character.isUpperCase(s.charAt(0)) ? 'A'  : 'A';
        char charToSub = 'A';
        int m = t.length(), n = s.length(), i = 0, size = 0;
        int[] charArr  = new int[58], staticCharArr = new int[58];
        for(i = 0; i < m; i++) {
              staticCharArr[t.charAt(i) - charToSub]++;  
        } 
        
        int  lastIdxFront = -1, lastIdxBack = -1, 
            firstIdxFront = -1, firstIdxBack = -1,
            firstIdxMiddle = -1, lastIdxMiddle = -1;
        
        //Finding Front IDX
        //Front Iteration
        charArr = staticCharArr.clone();
        
        for(i=0, size = m; i < n && size > 0; i++){
            char ch = s.charAt(i);
            if(charArr[ch-charToSub] > 0){
                size--;
                charArr[ch-charToSub]--;
                if(size == 0){
                    lastIdxFront = i;
                    break;
                }
            }
        }
        if(size>0)  return "";
        //Back Iteration
        charArr = staticCharArr.clone();
        for(i=lastIdxFront, size = m; i >=0 && size > 0; i--){
            char ch = s.charAt(i);
            if(charArr[ch-charToSub] > 0){
                size--;
                charArr[ch-charToSub]--;
                if(size == 0){
                    firstIdxFront = i;
                    break;
                }
            }
        }

        //Finding Back IDX
        //Back Iteration
        charArr = staticCharArr.clone();
        for(i=n-1, size = m; i >=0 && size > 0; i--){
            char ch = s.charAt(i);
            if(charArr[ch-charToSub] > 0){
                size--;
                charArr[ch-charToSub]--;
                if(size == 0){
                    firstIdxBack = i;
                    break;
                }
            }
        }
        //Front Iteration
        charArr = staticCharArr.clone();
        for(i=firstIdxBack, size = m; i < n && size > 0; i++){
            char ch = s.charAt(i);
            if(charArr[ch-charToSub] > 0){
                size--;
                charArr[ch-charToSub]--;
                if(size == 0){
                    lastIdxBack = i;
                    break;
                }
            }
        }
//         System.out.println("firstIdxFront : " + firstIdxFront +
//         "\tlastIdxFront : " + lastIdxFront +
//         "\nfirstIdxBack : " + firstIdxBack +
//         "\tlastIdxBack : " + lastIdxBack ); 
        
        if(lastIdxFront - firstIdxFront < lastIdxBack - firstIdxBack){
            return s.substring(firstIdxFront, lastIdxBack+1);
        }
        return s.substring(firstIdxBack, lastIdxBack + 1);
    }

    //======================================================================================
    // DP :  LC 546: Remove Boxes 
    //2st Try : Working and Fair Code --------------------------------------------------------------------
    public static int removeBoxes02_Driver(int[] boxes) {
        int n = boxes.length;
        int[][][] dp =  new int[n][n][n];
        //dp[left][right][count]
        return removeBoxes02DP(dp, boxes, 0, n-1, 0);
    }
    public static int removeBoxes02DP(int[][][] dp, int[] boxes, int left, int right, int count) {
        if(left > right)    return 0;

        if(dp[left][right][count]!=0)   return dp[left][right][count];

        int leftOriginal = left, countOriginal = count;

        while(left + 1 <= right && boxes[left] == boxes[left+1]){
            count++;
            left++;
        }

        //We use count + 1 in calculations instead of count because we count from zero instead 
        //of 1 where a value less than 1 won't be possible
        int maxVal = (count + 1) * (count + 1) + removeBoxes02DP(dp, boxes, left+1, right, 0);

        for(int k = left + 1; k <= right; k++){
            if(boxes[left] == boxes[k]){
                maxVal = Math.max(maxVal, removeBoxes02DP(dp, boxes, left + 1, k - 1, 0)  
                                            //Solve Future Sub Array first
                                        + removeBoxes02DP(dp, boxes, k, right, count + 1));  
                                            //Solve the array left with more continuous elements
            }
        }

        return dp[leftOriginal][right][countOriginal] = maxVal;
    }


    //1st Try : Didn't work ---------------------------------------------------------------------------
    public static int removeBoxes_Driver(int[] boxes) {
        return removeBoxes2(boxes, new boolean[boxes.length], 0,0, new int[boxes.length][boxes.length]);
    }
    public static void printLogs(boolean[] used, int noOfUsed, int idx, int idx2) {
        String ans = (idx == -1) ? "SOL" : "I: " + idx;

        ans += "\t\t Bool = ";
        for(boolean u: used)    ans += u + ", ";
        ans += "\t\t NOU = " + noOfUsed + "\t Index = " + idx;
        System.out.println(ans);
    }
    public static int removeBoxes2(int[] boxes, boolean[] used, int noOfUsed, int idx, int[][] dp){
        printLogs(used, noOfUsed, -1, idx);

        if(noOfUsed == boxes.length || idx == boxes.length )   return 0;
        
        if(dp[idx][noOfUsed]!=0)    return dp[idx][noOfUsed];
        
        int numOfElements = 0, n = boxes.length, max = 0, nOU = 0;
        List<Integer> useIdx = new ArrayList<>();
        for(int i = 0; i < n; ){
            if(used[i]){
                nOU++;
                i++;
                continue;
            }
            int ele = boxes[i];
            // useIdx.add(i);
            for(int j = i; j<n; j++){
                if(used[j] && j<n-1){
                    nOU++;
                    continue;
                }
                if(ele == boxes[j] && !used[j]){
                    useIdx.add(j);
                }
                
                if(ele != boxes[j] || j == n-1 ){
                    
                    for(int idx2: useIdx){
                        used[idx2] = true;
                    }
                    while(i<n && used[i])   i++;
                    
                    printLogs(used, noOfUsed, i, idx);
                    max = Math.max(max, removeBoxes2(boxes, used, noOfUsed + useIdx.size(), i, dp)
                                         + useIdx.size() * useIdx.size());
                    
                    
                    for(int idx2: useIdx){
                        used[idx2] = false;
                    }
                    useIdx = new ArrayList<Integer>();
                    break;
                }
                

            }
        }
        return max = dp[idx][noOfUsed];
    }
    
}