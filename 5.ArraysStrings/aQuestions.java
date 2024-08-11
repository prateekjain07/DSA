import java.util.HashMap;
import java.util.Map;

public class aQuestions{
    public static void main(String[] args) {
        // === Operation -------------------------------
        // int[] arr = {1,2,3,-4,-5,6,-7};
        //segregateNegPos(arr);
        
        // int[] arr = {0,1,0,1,0,2,2,2,0,1,1,1,0,0,0,1};
        // segregate0and1(arr);

    
        //output
        // System.out.print("Output: \t");
        // for (int i : arr) {
        //     System.out.print(i + ", ");
        // }
    }
    // IMP - Kadane's Algorithm
    public static int KadanesAlgo(int[] arr) {
        int gSum = 0, cSum = 0, gsi = 0, gei = 0, csi = 0;
        for(int i =0; i<arr.length; i++){
            int ele = arr[i];
            cSum += ele;
            if(cSum > gSum) { //If asked which array, it can be coded here
                gSum = cSum;
                gsi = csi;
                gei = i;
            }
            if(cSum < 0) { //To move si 
                cSum = 0;
                csi = i + 1;
            }
        }
        return gSum;
    }

    //=============================================================
    //=============================================================
    //=============================================================
    // GFG : No of Subarrays with Equal 1s and 0s
    static int countSubarrWithEqualZeroAndOne(int arr[], int n)
    {
        // add your code here
        Map<Integer, Integer> map  = new HashMap<>();
        map.put(0,1);
        int ei = 0, count = 0, sum = 0;
        while(ei < n) {
            sum += (arr[ei++] == 0) ? -1 : 1;
            count += map.getOrDefault(sum, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
    //=============================================================
    // LC #974 : No. of Subarrays whose sum is divisible by K
    public int subarraysDivByK(int[] arr, int k) {
        int n = arr.length;
        int[] remainder = new int[k];
        int sum = 0, ans = 0, ei = 0;
        while(ei < n){
            sum += arr[ei++];
            int r = (sum % k + k)%k;

            ans += remainder[r];
            remainder[r]++;
        }

        return ans;
    }
    //=============================================================
    // LC #1004 : Max Consecutive Ones III  by flipping atmost K '0'
    public int findMaxConsecutiveOnesFlip1_conventional(int[] arr, int k) {
        int n = arr.length, si = 0, ei = 0, count0 = 0, len = 0;
        while(ei<n){
            if(arr[ei++] == 0)  count0++;

            while(count0 > k) {
                if(arr[si++] == 0)  count0--;
            }

            len = Math.max(len, ei - si);
        }
        
        return len;
    }

    //=============================================================
    // LC #485 : Max Consecutive Ones
    public int findMaxConsecutiveOnes_simple(int[] nums) {
        int ones = 0, maxOnes = 0;  
        for(int a: nums) {
            if(a == 1) {
                ones++;
                maxOnes = Math.max(maxOnes, ones);
            } else {
                ones = 0;
            }
        }
        return maxOnes;
        
    }
    public int findMaxConsecutiveOnes_optimised(int[] arr) {
        int n = arr.length, si = 0, ei = 0, count0 = 0, len = 0;
        while(ei<n){
            if(arr[ei++] == 0)
                si = ei;

            len = Math.max(len, ei - si);
        }
        
        return len;
    }
    public int findMaxConsecutiveOnes_conventional(int[] arr) {
        int n = arr.length, si = 0, ei = 0, count0 = 0, len = 0;
        while(ei<n){
            if(arr[ei++] == 0)  count0++;

            while(count0 > 0) {
                if(arr[si++] == 0)  count0--;
            }

            len = Math.max(len, ei - si);
        }
        
        return len;
    }
    //=============================================================
    // LC #930 : Binary Subarrays with Sum
    public int numSubarraysWithSum(int[] nums, int goal) {
        return atmostSum(nums, goal) - (goal > 0 ? atmostSum(nums, goal - 1) : 0);
    }

    public int atmostSum(int[] arr, int tar) {
        int n = arr.length, ei = 0, si = 0, sum = 0, count = 0;
        while(ei<n) {
            sum += arr[ei++];
            while(sum > tar) {
                sum -= arr[si++];
            }
            count += ei - si;
        }
        return count;   
    }
    //=============================================================
    // LC #904 : Fruits into Basket
    public int totalFruit(int[] fruits) {
        // Map<Integer, Integer> map = new HashMap<>();
        int[] arr = new int[100001];
        int si = 0, n = fruits.length, ei = 0, len = 0, count = 0, k =2;
        while(ei<n) {
            if(arr[fruits[ei++]]++ == 0)    count++;

            while(count > k) {
                if(arr[fruits[si++]]-- == 1)    count--;
            }

            len = Math.max(len,ei - si);

        }
        return len;
    }
    //=============================================================
    //LC #1248 : Count Number of Nice Subarrays
    public static int numberOfSubarrays(int[] nums, int k) {
        return atmostKOddIntegers(nums, k) - atmostKOddIntegers(nums, k-1);
    }
    // Count All Subarrays with Atmost K Odd Integers
    public static int atmostKOddIntegers(int[] arr, int k) {
        int si = 0, ei = 0, count = 0, numCount = 0;
        // int[] freq = new int[100001];
        while(ei < arr.length){
            if((arr[ei] & 1) == 1){
                numCount++;
            }
            // freq[arr[ei++]]++;
            ei++;
            while(numCount > k){
                if((arr[si] & 1) == 1)  numCount--;
                
                // freq[arr[si++]]--;
                si++;
            }
            count+= ei - si;
        }
        // System.out.println(count);
        return count;
    }
    //=============================================================
    //LC #992 : Count All Subarrays with K Distinct Integers
    public static int subarraysWithKDistinct(int[] nums, int k) {
        return atmostKDistinct(nums, k) - atmostKDistinct(nums, k-1);
    }
    // -- Count All Subarrays with Atmost K Distinct Integers
    public static int atmostKDistinct(int[] arr, int k) {
        int si = 0, ei = 0, count = 0, numCount = 0;
        int[] freq = new int[20001];
        while(ei < arr.length){
            if(freq[arr[ei++]]++ == 0)    numCount++;

            while(numCount > k){
                if(freq[arr[si++]]-- == 1)  numCount--;
            }
            count+= ei - si;
        }
        return count;
    }
    public static int atmostKDistinct_Class(int[] arr, int k) {
        int si = 0, ei = 0, count = 0; // numCount = 0;
        HashMap<Integer, Integer> freq = new HashMap<>();
        while(ei < arr.length){
            freq.put(arr[ei], freq.getOrDefault(arr[ei], 0) + 1);
            ei++;

            while(freq.size() > k){
                freq.put(arr[si], freq.getOrDefault(arr[si], 0) - 1);    
                if(freq.get(arr[si]) == 0)  freq.remove(arr[si]);

                si++;
            }
            count+= ei - si;
        }
        return count;
    }
    //=============================================================
    //LC #1456 : Max Number of Vowels in a Sub-string of Length K
    public static int maxVowels(String s, int k) {
        String vowels = "aeiou";
        int maxCount = 0, count = 0, ei = 0, si=0;
        for(;ei < k; ei++){
            char ch = s.charAt(ei);
            if(vowels.indexOf(ch) != -1)    count++;
        }
        maxCount = Math.max(maxCount, count);
        
        while(ei < s.length()){
            char ch2 = s.charAt(si++);
            if(vowels.indexOf(ch2) != -1)    count--;
        
            char ch = s.charAt(ei++);
            if(vowels.indexOf(ch) != -1)    count++;
            
            maxCount = Math.max(maxCount, count);
    
        }
        return maxCount;
        
    }
    //=============================================================
    //LC #159 : Longest Substring with atmost two Distinct Characters
    //GFG : Smallest Distinct Window -- Alteration of this code only 
    //  where String T is determined by us containing all distinct letters
    public static String minWindow(String s, String t) {
        if(s.length() < t.length()) return "";
        int si = 0,ei = 0, n = t.length(), minLen = (int)1e8, count = 0, i=0, gsi = 0, gei=0;
        int[] freqS = new int[128]; 
        int[] freqT = new int[128];
        //0-127 ASCII covers letters, digits, symbols and spaces
        while(i < n)    freqT[t.charAt(i++)]++;
        
        while(ei < s.length()){
            char ch = s.charAt(ei++);
            if(freqS[ch]++ < freqT[ch])  count++;

            while (count == n) {
                char ch2 = s.charAt(si);
                if(freqS[ch2]-- <= freqT[ch2]){
                   count--;
                    if(minLen > ei - si){
                        minLen = ei - si;
                        gsi = si;
                        gei = ei;
                    }
                }  
                si++;
            }
        }
        return s.substring(gsi, gei);
    }
    //=============================================================
    //LC #159 & LintCode #968: Longest Substring with atmost two Distinct Characters
    //LC #340 & LintCode #386: Longest Substring with atmost K Distinct Characters 
    //--- Just check charCount > K in while loop
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int maxLen = 0, si = 0, ei = 0, charCount = 0;
        int freq[] = new int[128];
        while(ei < s.length()){
            if(freq[s.charAt(ei++)]++ == 0)     charCount++;

            while(charCount > 2){
                if(freq[s.charAt(si++)]-- == 1)     charCount--;
            }

            maxLen = Math.max(maxLen, ei - si);
        }
        return maxLen;
    }
    //=============================================================
    //LC #3: Longest Substring without repeating Characters
    public static int lengthOfLongestSubstring(String s) {
        if(s.length() <= 1) return s.length();
        int si = 0,ei = 0, n = s.length(), maxLen = 0, count = 0;
        int[] freq = new int[128]; 
        //0-127 ASCII covers letters, digits, symbols and spaces
        while(ei < n){
            if(freq[s.charAt(ei++)]++ > 0)  count++;

            while (count > 0) {
                if(freq[s.charAt(si++)]-- > 1)  count--;
            }
            maxLen = Math.max(maxLen, ei - si);
        }
        return maxLen;
    }
    //=============================================================
    //LC #11 & GFG : Container with Most Water
    public static int maxArea(int[] height) {
        int maxAns = 0, n = height.length, i = 0, j = n-1;
        while(i<j){
            int width = j - i;
            int newArea = height[i] < height[j] ? width * height[i++] 
                                                : width * height[j--] ;
            maxAns = Math.max(maxAns, newArea);
        }        
        return maxAns;
    }
    
    //=============================================================
    // GFG : Max Sum in the Configuration
    public static int maxSumConfiguration(int[] arr) {
        int totalSum = 0, maxSumConfig = 0, sumWithIdx = 0, n = arr.length;
        for(int i = 0; i<n; i++){
            totalSum += arr[i];
            sumWithIdx += i*arr[i];
        }
        maxSumConfig = sumWithIdx;
        for(int i = 1; i<n; i++){
            sumWithIdx = sumWithIdx - totalSum + n*arr[i-1];
            maxSumConfig = Math.max(maxSumConfig, sumWithIdx);
        }
        
        return maxSumConfig;
    }
    //=============================================================
    //-- Segregate zeros & Ones & Twos
    public static void segregate0and1and2_Class(int[] arr) {
        int n = arr.length, pt1 = -1, pt2=n-1, itr = 0;
        while(itr <= pt2){
            if(arr[itr] == 0)
                swap(arr, ++pt1, itr++);
            else if(arr[itr] == 2)
                swap(arr, pt2--, itr);
            else
                itr++;
        }
    }
    
    //=============================================================
    //-- Segregate zeros & Ones
    public static void segregate0and1_Class(int[] arr) {
        int n = arr.length, pt = -1, itr = 0;
        while(itr < n){
            if(arr[itr] == 0){
                swap(arr, ++pt, itr);
            }
            itr++;
        }
    }
    
    public static void segregate0and1(int[] arr) {
        int n = arr.length, itrZ = 0, itrO = 0;
        while(itrO < n){
            if(arr[itrO] >= 1){
                itrO++; 
                continue;
            }else{
                swap(arr, itrZ, itrO);
                while(arr[itrZ] == 0){
                    itrZ++;
                    itrO++;
                }
            }
        }
    }

    //=============================================================
    //-- Segregate Negative and Positive Elements (Order not imp)
    public static void segregatePositiveAndNegative_Class(int[] arr) {
        int n = arr.length, pt = -1, itr = 0;
        while(itr < n){
            if(arr[itr] < 0){
                swap(arr, ++pt, itr);
            }
            itr++;
        }
    }
    
    public static void segregateNegPos(int[] arr) {
        int n = arr.length, itrN = 0, itrP = 0;
        while(itrP < n){
            if(arr[itrP] >= 0){
                itrP++; 
                continue;
            }else{
                swap(arr, itrN, itrP);
                while(arr[itrN] < 0){
                    itrN++;
                    itrP++;
                }
            }
        }
    }
    
    //=============================================================
    // -- Rotate an Array by a Factor R 
    //where R > 0 means left rotation & R < 0 means right rotation

    public static void rotateByK(int[] arr, int r) {
        int n = arr.length;
        r %= n;
        if(r < 0)   r += n; 
        
        reverseArray(arr, 0, n - 1);
        reverseArray(arr, n - r, n - 1);
        reverseArray(arr, 0, n - r - 1);
    }
    public static void reverseArray(int[] arr, int i, int j) {
        while(i<j){
            swap(arr, i++, j--);
        }
    }
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}