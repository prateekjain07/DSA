class l003_PnC{

    static int calls= 0;
    public static void main(String[] args) {
        // CoinChangePnC();
        NQueenPnC();
    }
    public static void NQueenPnC() {

        //1D Boxes
        int queen = 3;
        int[] boxes = new int[5];
        
        // System.out.println("Comb:  " + NQueenCombNoRes_Summation(queen,boxes,0,0,""));
        // System.out.println("CombSUbSeq:  " + NQueenCombNoRes_Subseq(queen,boxes,0,0,""));
                
        // System.out.println("Perm:  " + NQueenPermNoRes_Summation(queen,boxes,0,0,""));
        // System.out.println("PermSubseq:  " + NQueenPermNoRes_Subseq(queen,boxes,0,0,""));

        //2D Boxes
        int que = 4;
        int[][] boxes2D = new int[4][4];
        // System.out.println("PermSubseq:  " + NQueenPermNoRes_Subseq2D(que,boxes2D,0,0,""));

        // System.out.println("CombSubseq:  " + NQueenCombNoRes_Subseq2D(que,boxes2D,0,0,""));

        //NQueenNoKill
        int m = boxes2D.length;
        int n = boxes2D[0].length;

        calls = 0;
        System.out.println("CombSummationNQueenNoKill:  " + NQueenCombNoKill_Summation(que,boxes2D,0,0,""));
        System.out.println("Call: " + calls);
        // System.out.println("PermSummationNQueenNoKill:  " + NQueenPermNoKill_Summation(que,boxes2D,0,0,""));
        // System.out.println();
        calls = 0;

        System.out.println("CombSummationNQueenNoKill_Optimised:  " + NQueenCombNoKill_Summation_02(que,boxes2D,0,0,""
        ,new boolean[m],new boolean[n],new boolean[m+n-1],new boolean[m+n-1]));

        System.out.println("Call: " + calls);
        
        calls = 0;

        System.out.println("CombSummationNQueenNoKill_OptimisedSubseq:  " + NQueenCombNoKill_Subseq_02(que,boxes2D,0,0,""
        ,new boolean[m],new boolean[n],new boolean[m+n-1],new boolean[m+n-1]));
        
        System.out.println("Call: " + calls);
        
        calls = 0;

        System.out.println("CombSummationNQueenNoKill_Optimised(PerRow)Summation:  " + NQueenCombNoKill_Summation_03(que,boxes2D,0,0,""
        ,new boolean[m],new boolean[n],new boolean[m+n-1],new boolean[m+n-1]));

        System.out.println("Call: " + calls);
        
        calls = 0;

        System.out.println("CombSummationNQueenNoKill_Optimised(PerRow)SummationWithBits:  " + NQueenCombNoKill_Summation_04Bits(que,boxes2D,0,0,""
        ,0,0,0,0));

        System.out.println("Call: " + calls);
        

        //Adding a Static Variable Call to figure out how many calls are required in each function.
    }

//qpsf - Queen placed so far
//NoQ - Num of Queen

public static int NQueenCombNoKill_Summation_04Bits(int NoQ, int[][] boxes, int idx, int qpsf, String ans,
    int rows, int cols, int mainD, int otherD) {
        calls++;
        //Optimised Function wihtout needing to use isSafeToPlaceQueen function
        //Don't even need the 2D Box now
        
        //To make this function for Permutation, 
        //just replace int i = 0 instead of idx, basically idx becomes obsolete
        
        //Addition to previous codes, there's one queen per row.
        //Boxes array is still useless here.
        //Mapping: Q0 => R0
        //Q1 => R1
        //and so on 
        //QPSF variable can be used to keep this track.

        //Addition is that since now, we'll use Bits instead of boolean Arrays,
        //all of the operations in finding shadow values will become O(1) and 
        //also the space will become O(1) as only 1 int variable is required for 32 bits
        // and 1 long int variable for 64 Bits boolean array operations

        //This is the Most Optimised Solution

        if(qpsf == NoQ){
            System.out.println(ans);
            return 1;
        }
        int count=0;

        int m = boxes.length;
        int n = boxes[0].length;

        for(int c = 0; c<n; c++ ){
            int r = qpsf;
            // int c = i%boxes[0].length;

            if(((rows & (1<<r)) == 0) && ((cols & (1<<c)) == 0) && 
            ((mainD & (1<<(r+c))) == 0) && ((otherD & (1<<(r-c + (m+n-1)/2))) == 0)){
                rows ^= (1<<r);
                cols ^=(1<<c); 
                mainD ^= (1<<(r+c));
                otherD ^= (1<<(r-c + (m+n-1)/2));
                // boxes[r][c] = 1;

                //idx in the parameters is of no use now.

                count+= NQueenCombNoKill_Summation_04Bits(NoQ, boxes, idx+1, qpsf+1, ans + "b"+r+""+c + "q"+qpsf + " - ",rows,cols,mainD,otherD);
                // boxes[r][c] = 0;
                rows ^= (1<<r);
                cols ^=(1<<c);
                mainD ^= (1<<(r+c));
                otherD ^= (1<<(r-c + (m+n-1)/2));
                                
            }
        }

        return count;
    }


    public static int NQueenCombNoKill_Summation_03(int NoQ, int[][] boxes, int idx, int qpsf, String ans,
    boolean[] rows, boolean[] cols, boolean[] mainD, boolean[] otherD) {
        calls++;
        //Optimised Function wihtout needing to use isSafeToPlaceQueen function
        //Don't even need the 2D Box now
        
        //To make this function for Permutation, 
        //just replace int i = 0 instead of idx, basically idx becomes obsolete
        
        //Addition to previous codes, there's one queen per row.
        //Boxes array is still useless here.
        //Mapping: Q0 => R0
        //Q1 => R1
        //and so on 
        //QPSF variable can be used to keep this track.

        if(qpsf == NoQ){
            System.out.println(ans);
            return 1;
        }
        int count=0;

        int m = boxes.length;
        int n = boxes[0].length;

        for(int c = 0; c<n; c++ ){
            int r = qpsf;
            // int c = i%boxes[0].length;

            if(!rows[r] && !cols[c] && !mainD[r+c] && !otherD[r-c + (m+n-1)/2]){
                rows[r] = !rows[r];
                cols[c] = !cols[c];
                mainD[r+c] = !mainD[r+c];
                otherD[r-c + (m+n-1)/2] = !otherD[r-c + (m+n-1)/2];
                // boxes[r][c] = 1;

                //idx in the parameters is of no use now.

                count+= NQueenCombNoKill_Summation_03(NoQ, boxes, idx+1, qpsf+1, ans + "b"+r+""+c + "q"+qpsf + " - ",rows,cols,mainD,otherD);
                // boxes[r][c] = 0;
                rows[r] = !rows[r];
                cols[c] = !cols[c];
                mainD[r+c] = !mainD[r+c];
                otherD[r-c + (m+n-1)/2] = !otherD[r-c + (m+n-1)/2];                
            }
        }

        return count;
    }


    public static int NQueenCombNoKill_Subseq_02(int NoQ, int[][] boxes, int idx, int qpsf, String ans,
    boolean[] rows, boolean[] cols, boolean[] mainD, boolean[] otherD) {
        calls++;
        
        //Optimised Function wihtout needing to use isSafeToPlaceQueen function
        //Don't even need the 2D Box now
        
        //To make this function for Permutation, 
        //just replace int i = 0 instead of idx, basically idx becomes obsolete
        if( qpsf == NoQ || idx == boxes.length * boxes[0].length){
            if(qpsf == NoQ){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count=0;

        int m = boxes.length;
        int n = boxes[0].length;
        int r = idx/boxes[0].length;
        int c = idx%boxes[0].length;

        if(!rows[r] && !cols[c] && !mainD[r+c] && !otherD[r-c + (m+n-1)/2]){
            rows[r] = !rows[r];
            cols[c] = !cols[c];
            mainD[r+c] = !mainD[r+c];
            otherD[r-c + (m+n-1)/2] = !otherD[r-c + (m+n-1)/2];
            // boxes[r][c] = 1;
            count+= NQueenCombNoKill_Subseq_02(NoQ, boxes, idx+1, qpsf+1, ans + "b"+r+""+c + "q"+qpsf + " - ",rows,cols,mainD,otherD);
            // boxes[r][c] = 0;
            rows[r] = !rows[r];
            cols[c] = !cols[c];
            mainD[r+c] = !mainD[r+c];
            otherD[r-c + (m+n-1)/2] = !otherD[r-c + (m+n-1)/2];                
        }
        
            count+= NQueenCombNoKill_Subseq_02(NoQ, boxes, idx+1, qpsf, ans,rows,cols,mainD,otherD);
        
    

        return count;
    }


    public static int NQueenCombNoKill_Summation_02(int NoQ, int[][] boxes, int idx, int qpsf, String ans,
    boolean[] rows, boolean[] cols, boolean[] mainD, boolean[] otherD) {
        calls++;
        
        //Optimised Function wihtout needing to use isSafeToPlaceQueen function
        //Don't even need the 2D Box now
        
        //To make this function for Permutation, 
        //just replace int i = 0 instead of idx, basically idx becomes obsolete
        if(qpsf == NoQ){
            System.out.println(ans);
            return 1;
        }
        int count=0;

        int m = boxes.length;
        int n = boxes[0].length;

        for(int i = idx; i<boxes.length * boxes[0].length; i++ ){
            int r = i/boxes[0].length;
            int c = i%boxes[0].length;

            if(!rows[r] && !cols[c] && !mainD[r+c] && !otherD[r-c + (m+n-1)/2]){
                rows[r] = !rows[r];
                cols[c] = !cols[c];
                mainD[r+c] = !mainD[r+c];
                otherD[r-c + (m+n-1)/2] = !otherD[r-c + (m+n-1)/2];
                // boxes[r][c] = 1;
                count+= NQueenCombNoKill_Summation_02(NoQ, boxes, i+1, qpsf+1, ans + "b"+r+""+c + "q"+qpsf + " - ",rows,cols,mainD,otherD);
                // boxes[r][c] = 0;
                rows[r] = !rows[r];
                cols[c] = !cols[c];
                mainD[r+c] = !mainD[r+c];
                otherD[r-c + (m+n-1)/2] = !otherD[r-c + (m+n-1)/2];                
            }
        }

        return count;
    }


    public static boolean isSafeToPlaceQueen(int[][] boxes, int r, int c, boolean C_or_P) {
        int[][] dir = {{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}};
        int limit = C_or_P? dir.length/2: dir.length;
        for(int d = 0; d<limit; d++){
            for(int rad = 1; rad<=boxes.length || rad<=boxes[0].length; rad++){
                int x = r + dir[d][0]*rad;
                int y = c + dir[d][1]*rad;
                if(x>=0 && y>=0 && x<boxes.length && y<boxes[0].length){
                    if(boxes[x][y] == 1)    return false;
                }                
            }
        }
        return true;
    }

    public static int NQueenPermNoKill_Summation(int NoQ, int[][] boxes, int idx, int qpsf, String ans) {
        calls++;
        
        //For Permutation, You Gotta check all 8 directions instead of just 4 like in COmbination
        //Send False for Permutation
        if(qpsf == NoQ){
            System.out.println(ans);
            return 1;
        }
        int count=0;

        for(int i = idx; i<boxes.length * boxes[0].length; i++ ){
            int r = i/boxes[0].length;
            int c = i%boxes[0].length;

            if(boxes[r][c] == 0 && isSafeToPlaceQueen(boxes,r,c,false)){
                boxes[r][c] = 1;
                count+= NQueenPermNoKill_Summation(NoQ, boxes, 0, qpsf+1, ans + "b"+r+""+c + "q"+qpsf + " - ");
                boxes[r][c] = 0;                
            }
        }

        return count;
    }

    public static int NQueenCombNoKill_Summation(int NoQ, int[][] boxes, int idx, int qpsf, String ans) {
        calls++;
        
        if(qpsf == NoQ){
            System.out.println(ans);
            return 1;
        }
        int count=0;

        for(int i = idx; i<boxes.length * boxes[0].length; i++ ){
            int r = i/boxes[0].length;
            int c = i%boxes[0].length;

            if(isSafeToPlaceQueen(boxes,r,c,true)){
                boxes[r][c] = 1;
                count+= NQueenCombNoKill_Summation(NoQ, boxes, i+1, qpsf+1, ans + "b"+r+""+c + "q"+qpsf + " - ");
                boxes[r][c] = 0;
                
            }
        }

        return count;
    }


    //==========================NQueen with NO Restriction ==========================================
    public static int NQueenPermNoRes_Subseq2D(int NoQ, int[][] boxes, int idx, int qpsf, String ans) {
        if(qpsf == NoQ || idx == boxes.length*boxes[0].length){
            if(qpsf == NoQ){
                // System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count=0;
        int r = idx/boxes[0].length;
        int c = idx%boxes[0].length;

        if(boxes[r][c]!=-1){
            boxes[r][c] = -1;
            count+= NQueenPermNoRes_Subseq2D(NoQ, boxes, 0, qpsf+1, ans + "b"+r+""+c+"q"+qpsf + " - ");
            boxes[r][c] = 1;
        }
        count+= NQueenPermNoRes_Subseq2D(NoQ, boxes, idx+1, qpsf, ans );
        
        return count;
    }

    public static int NQueenCombNoRes_Subseq2D(int NoQ, int[][] boxes, int idx, int qpsf, String ans) {
        
        if(qpsf == NoQ || idx == boxes.length*boxes[0].length){
            if(qpsf == NoQ){
                // System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count=0;
        int r = idx/boxes[0].length;
        int c = idx%boxes[0].length;

        count+= NQueenCombNoRes_Subseq2D(NoQ, boxes, idx+1, qpsf+1, ans + "b"+r+""+c+"q"+qpsf + " - ");

        count+= NQueenCombNoRes_Subseq2D(NoQ, boxes, idx+1, qpsf, ans);

        return count;
    }



    public static int NQueenPermNoRes_Subseq(int NoQ, int[] boxes, int idx, int qpsf, String ans) {
        if(qpsf == NoQ || idx == boxes.length){
            if(qpsf == NoQ){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count=0;

        if(boxes[idx]!=-1){
            boxes[idx] = -1;
            count+= NQueenPermNoRes_Subseq(NoQ, boxes, 0, qpsf+1, ans + "b"+idx + "q"+qpsf + " - ");
            boxes[idx] = 1;
        }
        count+= NQueenPermNoRes_Subseq(NoQ, boxes, idx+1, qpsf, ans );
        
        return count;
    }

    public static int NQueenCombNoRes_Subseq(int NoQ, int[] boxes, int idx, int qpsf, String ans) {
        
        if(qpsf == NoQ || idx == boxes.length){
            if(qpsf == NoQ){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count=0;

        count+= NQueenCombNoRes_Subseq(NoQ, boxes, idx+1, qpsf+1, ans + "b"+idx + "q"+qpsf + " - ");
    
        count+= NQueenCombNoRes_Subseq(NoQ, boxes, idx+1, qpsf, ans);
 
        return count;
    }

    public static int NQueenCombNoRes_Summation(int NoQ, int[] boxes, int idx, int qpsf, String ans) {
        if(qpsf == NoQ){
            System.out.println(ans);
            return 1;
        }
        int count=0;

        for(int i = idx; i<boxes.length; i++ ){
            count+= NQueenCombNoRes_Summation(NoQ, boxes, i+1, qpsf+1, ans + "b"+i + "q"+qpsf + " - ");
        }

        return count;
    }


    public static int NQueenPermNoRes_Summation(int NoQ, int[] boxes, int idx, int qpsf, String ans) {
        if(qpsf == NoQ){
            System.out.println(ans);
            return 1;
        }
        int count=0;

        for(int i = idx; i<boxes.length; i++ ){
            if(boxes[i]!=-1){
                boxes[i] = -1;
                count+= NQueenPermNoRes_Summation(NoQ, boxes, 0, qpsf+1, ans + "b"+i + "q"+qpsf + " - ");
                boxes[i] = 1;
            }
        }

        return count;
    }

//==============================COIN CHANGE============================================
    public static int PermWithRep(int[] arr, int tar, int idx, String ans) {
        if(tar == 0){
            System.out.println(ans);
            return 1;
        }
        int count=0;
        for(int i=idx; i<arr.length; i++){
            int ele = arr[i];
            if(tar - ele>= 0){
                count+= PermWithRep(arr, tar - ele, idx , ans + ele + "-" );
            }
        }
        return count;
    }
    
    public static int CombWithRep(int[] arr, int tar, int idx, String ans ) {
        if(tar == 0){
            System.out.println(ans);
            return 1;
        }
        int count=0;
        for(int i=idx; i<arr.length; i++){
            int ele = arr[i];
            if(tar - ele>= 0){
                count+= CombWithRep(arr, tar - ele, i , ans + ele + "-" );
            }
        }
        return count;
    }

    public static int CombWithRepDP(int[] arr, int tar ) {
        //Leetcode 518
        //Not in compliance with the Videos. A step further basically
        int m = arr.length;
        int n = tar+1;
        int[][] dp = new int[m][n];
        
        //Initialise first column with 1s
        for(int i=0; i<m;i++)   dp[i][0] = 1;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(i==0){
                    dp[i][j] = j%arr[i] == 0 ? 1: 0;
                    continue;    
                }
                dp[i][j] = dp[i-1][j];
                if(j - arr[i]>=0)
                    dp[i][j]+= dp[i][j-arr[j]];
            }           
        }

        return dp[m-1][n-1];
    }
    public static int PermWithoutRep(int[] arr, int tar, int idx, String ans) {
        if(tar == 0){
            System.out.println(ans);
            return 1;
        }
        int count=0;
        for(int i=idx; i<arr.length; i++){
            int ele = arr[i];
            if(tar - ele>= 0 && arr[i]>0){
                arr[i] *= -1;
                count+= PermWithoutRep(arr, tar - ele, idx , ans + ele + "-");
                arr[i] *= -1;
            }
        }
        return count;
    }
    
    public static int CombWithoutRep(int[] arr, int tar, int idx, String ans) {
        if(tar == 0){
            System.out.println(ans);
            return 1;
        }
        int count=0;
        for(int i=idx; i<arr.length; i++){
            int ele = arr[i];
            if(tar - ele>= 0){
                count+= CombWithoutRep(arr, tar - ele, i+1 , ans + ele + "-" );
            }
        }
        return count;
    }
    
    public static int CombWithoutRep_SubSeq(int[] arr, int tar, int idx, String ans) {
        if(tar == 0 || idx == arr.length){
            if(tar ==0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count=0;
        int ele = arr[idx];
        if(tar - ele>= 0){
            count+= CombWithoutRep_SubSeq(arr, tar - ele, idx+1 , ans + ele + "-" );
        }

        count+= CombWithoutRep_SubSeq(arr, tar, idx+1 , ans);        
        return count;
    }
    
    public static int CombWithRep_SubSeq(int[] arr, int tar, int idx, String ans ) {
        if(tar == 0 || idx == arr.length){
            if(tar ==0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count=0;
        int ele = arr[idx];
        if(tar - ele>= 0){
            count+= CombWithRep_SubSeq(arr, tar - ele, idx , ans + ele + "-" );
        }
        count+= CombWithRep_SubSeq(arr, tar, idx+1 , ans );
        
        return count;
    }

    public static int PermWithRep_SubSeq(int[] arr, int tar, int idx, String ans) {
        if(tar == 0 || idx == arr.length){
            if(tar ==0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count=0;

        
        int ele = arr[idx];
        if(tar - ele>= 0){
            count+= PermWithRep_SubSeq(arr, tar - ele, 0 , ans + ele + "-" );
        }

        count+= PermWithRep_SubSeq(arr, tar, idx +1 , ans);
        
        return count;
    }
    public static int PermWithoutRep_SubSeq(int[] arr, int tar, int idx, String ans) {
        if(tar == 0 || idx == arr.length){
            if(tar ==0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count=0;

        
        int ele = arr[idx];
        if(tar - ele>= 0 && arr[idx]>0){
            arr[idx] *= -1;
            count+= PermWithoutRep_SubSeq(arr, tar - ele, 0 , ans + ele + "-");
            arr[idx] *= -1;
        }
        count+= PermWithoutRep_SubSeq(arr, tar, idx+1 , ans);

        return count;
    }
    
    public static void CoinChangePnC() {
        int target = 10;
        int[] arr = {2,3,5,7};
        
        // System.out.println("PermWithRep:  " + PermWithRep(arr, target, 0, ""));
        // System.out.println("PermWithRep:  " + PermWithRep_SubSeq(arr, target, 0, ""));
        
        // System.out.println("CombWithRep:  " + CombWithRep(arr, target, 0, ""));
        // System.out.println("CombWithRep:  " + CombWithRep_SubSeq(arr, target, 0, ""));
        
        
        // //waste call// System.out.println("PermWithoutRep:  " + PermWithoutRep(arr, target, 0, "", new int[arr.length]));
         
        System.out.println("PermWithoutRep:  " + PermWithoutRep(arr, target, 0, "" ));
        System.out.println("PermWithoutRep:  " + PermWithoutRep_SubSeq(arr, target, 0, "" ));
        
        
        // System.out.println("CombWithoutRep:  " + CombWithoutRep(arr, target, 0, ""));
        // System.out.println("CombWithoutRep:  " + CombWithoutRep_SubSeq(arr, target, 0, ""));

    }

}