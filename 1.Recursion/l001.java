//For output
//javac l001.java && java l001 > output.txt

//For input
//javac l001.java && java l001 < input.txt

//mainly be working here in Java
import java.util.Scanner;
import java.util.ArrayList;

public class l001{
    public static Scanner scn = new Scanner(System.in);
    
    public static void solve() {
        // set1();
        set2();
    }


    //===================================================================================================
    public static void set2() {
        // for(String a: subSeqReturn("abc")){
        //     System.out.println(a);
        // }
        
        // System.out.println(subSeqCount("abc", ""));
        
        String[] keypad = {" _",".,;:", "abc", "def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        int i=1;
        for(String a: keypadStringReturn("589", keypad)){
            System.out.println(i++ +  " - " + a);
        }
        System.out.println(keypadStringVoid("589",keypad,""));
    }

    public static int keypadStringVoid(String str, String[] keypad, String ans){
        if(str.length() == 1){
            for(int i=0; i < keypad[str.charAt(0)-'0'].length(); i++)
            {
                System.out.println(ans + keypad[str.charAt(0)-'0'].charAt(i));
            }
            return keypad[str.charAt(0)-'0'].length();
        }
        int count=0;
        for(int i=0; i < keypad[str.charAt(0)-'0'].length(); i++)
            count+= keypadStringVoid(str.substring(1), keypad, ans + keypad[str.charAt(0)-'0'].charAt(i) );
        
        return count;
    }

    public static ArrayList<String> keypadStringReturn(String str, String[] keypad){
        if(str.length() == 0){
            ArrayList<String> baseAns = new ArrayList<>();
            baseAns.add("");
            return baseAns;
        }

        ArrayList<String> RecAns = keypadStringReturn(str.substring(1), keypad);
        ArrayList<String> mainAns = new ArrayList<>();
        for(String a: RecAns){
            for(int i=0; i < keypad[str.charAt(0)-'0'].length(); i++)
                mainAns.add(keypad[str.charAt(0)-'0'].charAt(i) + a);
        }

        return mainAns;
    }


    public static int subSeqCount(String str, String ans){
        if(str.length() == 1){
            System.out.println(ans);
            System.out.println(ans+str);
            return 2;
        }

        int count=0;

        count+= subSeqCount(str.substring(1),ans+ str.charAt(0));
        count+= subSeqCount(str.substring(1),ans);
        
        return count;

    }
    public static ArrayList<String> subSeqReturn(String str){
        if(str.length() == 1){
            ArrayList<String> baseAns = new ArrayList<>();
            baseAns.add("");
            baseAns.add(str);
            return baseAns;
        }

        ArrayList<String> RecAns = subSeqReturn(str.substring(1));
        ArrayList<String> mainAns = new ArrayList<>();
        for(String a: RecAns){
            mainAns.add(str.charAt(0) + a);
            mainAns.add(a);
        }

        return mainAns;

    }

    //=================================================================================
    public static void set1() {
        // printIncDec(scn.nextInt(),scn.nextInt());
        // System.out.println(fact(6));
        // System.out.println(AtoB(3,5));
        maxOfArrayInput();
    }

    public static void maxOfArrayInput() {
        int arr[] = {19,20,54,1,20,67,3,5,44,20,32};
        System.out.println(maxOfArray(arr, 0));
        for(int a: allOccurenceInArray(arr,0,0,20)){
            System.out.print(a+" - ");
        }
    }
    
    public static int[] allOccurenceInArray(int[] arr, int count, int idx, int data) {
        if(idx == arr.length)
            return new int[count];
        
        if(arr[idx] == data)    count++;
        
        int[] ansArr = allOccurenceInArray(arr,count,idx+1,data);

        if(arr[idx] == data) ansArr[count-1] = idx;
        
        return ansArr;
    }
    
    public static int maxOfArray(int[] arr, int idx) {
        if(idx == arr.length-1)
            return arr[idx];
        
        return Math.max(maxOfArray(arr, idx+1),arr[idx]);
    }
    
    public static int AtoB(int a, int b) {
        // return b<=0?1: AtoB(a,b-1)*a;
        if(b==1)    return a;
        int ans = AtoB(a,b/2);
        return b%2==0 ? ans*ans : ans*ans*a;
    }
    public static int fact(int n) {
        return n<=1?1: fact(n-1)*n;
    }
    public static void  printIncDec(int a, int b){
        if(a>b){
            sysoln("");
            return;       
        }
        syso(a + "-");
        printIncDec(a+1,b);
        syso(a + "-");
    }
    
    public static void main(String[] args) {
        solve();
    }
    public static void sysoln(String a) {
        System.out.println(a);
    }
    public static void syso(String a) {
        System.out.print(a);
    }
}