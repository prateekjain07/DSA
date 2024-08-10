import java.util.Arrays;
import java.util.LinkedList;

public class l001 {
    public static void main(String[] args) {
        int[] arr = {2,1,3,2,1,0,4,9,6};
        System.out.println("NextGreaterOnRight : " + toString(NextGreaterOnRight(arr)));
        System.out.println("NextSmallerOnRight : " + toString(NextSmallerOnRight(arr)));
        System.out.println("NextGreaterOnLeft : " + toString(NextGreaterOnLeft(arr)));
        System.out.println("NextSmallerOnLeft : " + toString(NextSmallerOnLeft(arr)));
    }

    public static String toString(int[] arr){
        String s = "[";
        for(int e : arr){
            s += (e + ", ");
        }
        return s.substring(0, s.length()-2) + "]";
    }

    public static int[] NextGreaterOnRight(int[] arr) {
        int n = arr.length, res[] = new int[n];
        Arrays.fill(res, n);
        LinkedList<Integer> st = new LinkedList<Integer>();
        st.addFirst(-1);
        for(int i = 0; i<n; i++) {
            while (st.getFirst() != -1 && arr[st.getFirst()] < arr[i]) {
                res[st.removeFirst()] = i;
            }
            st.addFirst(i);
        }
        return res;
    }

    public static int[] NextSmallerOnRight(int[] arr) {
        int n = arr.length, res[] = new int[n];
        Arrays.fill(res, n);
        LinkedList<Integer> st = new LinkedList<Integer>();
        st.addFirst(-1);
        for(int i = 0; i<n; i++) {
            while (st.getFirst() != -1 && arr[st.getFirst()] > arr[i]) {
                res[st.removeFirst()] = i;
            }
            st.addFirst(i);
        }
        return res;
    }

    public static int[] NextGreaterOnLeft(int[] arr) {
        int n = arr.length, res[] = new int[n];
        Arrays.fill(res, -1);
        LinkedList<Integer> st = new LinkedList<Integer>();
        st.addFirst(-1);
        for(int i = n - 1; i>=0; i--) {
            while (st.getFirst() != -1 && arr[st.getFirst()] < arr[i]) {
                res[st.removeFirst()] = i;
            }
            st.addFirst(i);
        }
        return res;
    }

    public static int[] NextSmallerOnLeft(int[] arr) {
        int n = arr.length, res[] = new int[n];
        Arrays.fill(res, -1);
        LinkedList<Integer> st = new LinkedList<Integer>();
        st.addFirst(-1);
        for(int i = n - 1; i>=0; i--) {
            while (st.getFirst() != -1 && arr[st.getFirst()] > arr[i]) {
                res[st.removeFirst()] = i;
            }
            st.addFirst(i);
        }
        return res;
    }

    //-- #LC : 503 - Next Greater Element II - Circular
    public int[] nextGreaterElements(int[] arr) {
        int n = arr.length, res[] = new int[n];
        Arrays.fill(res, -1);
        LinkedList<Integer> st = new LinkedList<Integer>();
        st.addFirst(-1);
        for(int i = 0; i<2*n; i++) {
            while (st.getFirst() != -1 && arr[st.getFirst()] < arr[i%n]) {
                res[st.removeFirst()] = arr[i%n];
            }
            if(i<n) st.addFirst(i%n);
        }
        return res;
    }

    //GFG : Stock Span Problem
    public static int[] calculateSpan(int arr[], int n) {
        // Your code here
        int res[] = new int[n];
        Arrays.fill(res, n);
        LinkedList<Integer> st = new LinkedList<Integer>();
        st.addFirst(-1);
        for(int i = 0; i<n; i++) {
            int ct = 1;
            while (st.getFirst() != -1 && arr[st.getFirst()] <= arr[i]) { // Here it's <= instead of < -- Greater or Equal is expected
               ct += res[st.removeFirst()];
            }
            st.addFirst(i);
            res[i] = ct;
        }
        return res;
        
    }

    // LC #901 Solution - Inspired by class code
    class StockSpanner {
        LinkedList<Integer[]> st;
        int idx = -1;
        public StockSpanner() {
            st = new LinkedList<Integer[]>();
            st.addFirst(new Integer[]{100001, idx});
        }   
        
        public int next(int price) {
            while(st.getFirst()[0] <= price) {
                st.removeFirst();
            }
    
            int rv = (++idx) - st.getFirst()[1];
            st.addFirst(new Integer[]{price, idx});
            return rv;
        }
    }
    
}
