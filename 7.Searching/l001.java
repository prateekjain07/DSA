public class l001 {
    // IMPORTANT LC #4 : Median of Two Sorted Arrays
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        if(n1>n2){ //keeping n1 always smaller in size else bMid can go negative 
            return findMedianSortedArrays(nums2, nums1);
        }
        int si = 0, ei = n1, total = n1 + n2;
        while(si <= ei) {
            int aMid = (si + ei)/2;
            int bMid = (total + 1)/2 - aMid; //gets lower middle for both odd and even total length

            int aLeft = aMid == 0 ? -(int)1e9 : nums1[aMid - 1];
            int aRight = aMid == n1? (int)1e9 : nums1[aMid];
            int bLeft = bMid == 0 ? -(int)1e9 : nums2[bMid - 1];
            int bRight = bMid == n2? (int)1e9 : nums2[bMid];

            if(aLeft <= bRight && bLeft <= aRight) { //Partition is right
                if(total%2 == 0) {
                    return (Math.max(aLeft, bLeft) + Math.min(aRight, bRight))/2.0;
                } else { //since we've coded considering 1st partition is bigger
                    return Math.max(aLeft, bLeft);
                }
            } else {
                if(bLeft > aRight)      si = aMid + 1;
                else if(aLeft > bRight)     ei = aMid - 1;
            }

        }

        return 0; //Dummy return 
    }

    //======================================================================
    //LC #1011: Capacity To Ship Packages within D Days
    public static int shipWithinDays(int[] weights, int days) {
        int maxEle = 0;
        for(int i : weights){
            maxEle = Math.max(maxEle, i);
            
        }
        int si = maxEle, n = weights.length, ei = maxEle*n;
         while(si < ei){
            int mid = (si + ei)/2;
            if(daysNeeded(weights, mid) <= days){
                ei = mid;
            }
            else{
                si = mid + 1;
            }
        }
        return si;
    }
    public static int daysNeeded(int[] weights, int capacity){
        int days = 0;
        int w = 0;
        for(int i : weights){
            if(w + i <= capacity)
                w += i;
            else{
                w = i;
                days++;
            }
        }
        return ++days;
    }
    //======================================================================
    //LC #875 : Koko Eating Bananas
    public int minEatingSpeed(int[] piles, int h) {
        int maxEle = 0;
        for(int i : piles){
            maxEle = Math.max(maxEle, i);
        }
        int si = 1, ei = maxEle;
        while(si < ei){
            int mid = (si + ei)/2;
            if(eatingHours(piles, mid) <= h){
                ei = mid;
            }
            else{
                si = mid + 1;
            }
            
        }
        return si;
        
    }
    public static int eatingHours(int[] piles, int speed){
        int hours = 0;
        for(int i : piles){
            hours += (i-1)/speed + 1;
        }
        return hours;
    }
    //======================================================================
    //IMPORTANT LC #154: Find Minimum in Rotated Sorted Array (Duplicates)
    public int findMinInRSAWithDuplicates(int[] nums) {
        int si = 0, ei = nums.length - 1;
        while(si < ei){
            int mid = (si + ei)/2;
            if(nums[mid]<nums[ei]){  // Sorted from si to mid
                ei = mid;
            } else if(nums[mid] > nums[ei]){
                si = mid + 1;
            } else{
                ei--;
            }
        }

        return nums[si];
    }
    //IMPORTANT LC #153: Find Minimum in Rotated Sorted Array (Unique)
    public static int findMin(int[] nums) {
        int si = 0, ei = nums.length - 1;
        while(si < ei){
            int mid = (si + ei)/2;
            if(nums[mid]<nums[ei]){  // Sorted from si to mid
                ei = mid;
            } else{
                si = mid + 1;
            }
        }

        return nums[si];
    }
    //======================================================================
    //IMPORTANT LC #81: Search in a Rotated Sorted Array (Duplicates Involved)
    public static boolean searchRSA2(int[] nums, int target) {
        int si = 0, ei = nums.length - 1;
        while(si <= ei){
            int mid = si + (ei - si)/2;
            if(nums[mid] == target || nums[si] == target) return true;

            if(nums[si] < nums[mid]){ // si to mid, nums is sorted
                if(nums[si] < target && nums[mid] > target){
                    ei = mid - 1;
                }
                else
                    si = mid  + 1;
            }
            else if(nums[ei] > nums[mid]){
                if(nums[mid] < target && nums[ei] >= target){
                    si = mid + 1;
                }
                else
                    ei = mid - 1;
            }
            else{
                si++;
            }
        }
        return false;   
    }
    //IMPORTANT LC #33 : Search in a Rotated Sorted Array
    public static int searchRSA(int[] nums, int target) {
        int si = 0, ei = nums.length - 1;
        while(si <= ei){
            int mid = si + (ei - si)/2;
            if(nums[mid] == target) return mid;

            // if(nums[si] <= nums[mid]){ // si to mid, nums is sorted
            if(si == mid || nums[si] < nums[mid]){ // si to mid, nums is sorted
                //Condition is <= and not just < for the time si == mid
                if(nums[si] <= target && nums[mid] > target){
                    ei = mid - 1;
                }
                else
                    si = mid  + 1;
            }
            else{
                if(nums[mid] < target && nums[ei] >= target){
                    si = mid + 1;
                }
                else
                    ei = mid - 1;
            }
        }
        return -1;
    }    
}
