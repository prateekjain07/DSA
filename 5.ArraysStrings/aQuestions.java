
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
    //=============================================================
    //LC #159 : Longest Substring with atmost two Distinct Characters
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