class l004_sudoku{
    public static void main(String[] args) {
        
        //crossword
        cw();

        //LC1307
        // String[] words = {"SEND","MORE"};
        // String result="MONEY";
        // System.out.println(isSolvable(words,result));

        //Sudoku
        // char[][] board = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};      
        // solveSudoku(board);
        // for(int i=0; i<9; i++){
        //     for(int j=0; j<9; j++)
        //         System.out.print(board[i][j] + " ");
        //     System.out.println();
        // }    
    }

    //==================================================================
    //Hackerrank - Cross word Puzzle
    public static String[] ans = new String[10];
    public static void cw() {
        // char[][] board = {
        //     {'+','-','+','+','+','+','+','+','+','+'},
        //     {'+','-','+','+','+','+','+','+','+','+'},
        //     {'+','-','+','+','+','+','+','+','+','+'},
        //     {'+','-','-','-','-','-','+','+','+','+'},
        //     {'+','-','+','+','+','-','+','+','+','+'},
        //     {'+','-','+','+','+','-','+','+','+','+'}.
        //     {'+','+','+','+','+','-','+','+','+','+'},
        //     {'+','+','-','-','-','-','-','-','+','+'},
        //     {'+','+','+','+','+','-','+','+','+','+'},
        //     {'+','+','+','+','+','-','+','+','+','+'}
        // }
        char[][] board = {
            "+-++++++++".toCharArray(),
            "+-++++++++".toCharArray(),
            "+-++++++++".toCharArray(),
            "+-----++++".toCharArray(),
            "+-+++-++++".toCharArray(),
            "+-+++-++++".toCharArray(),
            "+++++-++++".toCharArray(),
            "++------++".toCharArray(),
            "+++++-++++".toCharArray(),
            "+++++-++++".toCharArray()
        };
        String[] words = {"LONDON","DELHI","ICELAND","ANKARA"};
        ans = new String[board.length];
        System.out.println(crossword(board,words,0));
        
    }
    public static void store(char[][] board) {
        for(int i=0; i<board.length; i++){
            ans[i] = new String(board[i]);
            System.out.println(ans[i]);
        }
        System.out.println();
    }
    public static boolean canPlaceHor(char[][] board, String word, int r, int c) {
        int idx=0;
        for(int j=c;j<board[r].length && idx<word.length(); j++,idx++){
            if(!(board[r][j] == '-' || board[r][j] == word.charAt(idx)))
                return false;
        }
        return idx == word.length();
    }
    public static char[][] PlaceHor(char[][] board, String word, int r, int c) {
        char[][] newBoard = new char[board.length][board[0].length];
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){  
                newBoard[i][j] = board[i][j];
                
            }
        }
        
        for(int idx=0,j=c;j<board[r].length && idx<word.length(); j++,idx++){
            newBoard[r][j] = word.charAt(idx);
        }
        return newBoard;
    }
    public static boolean canPlaceVer(char[][] board, String word, int r, int c) {
        int idx=0;
        for(int i=r;i<board.length && idx<word.length(); i++,idx++){
            if(!(board[i][c] == '-' || board[i][c] == word.charAt(idx)))
                return false;
        }
        return idx == word.length();
    }
    public static char[][] PlaceVer(char[][] board, String word, int r, int c) {
        char[][] newBoard = new char[board.length][board[0].length];
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                newBoard[i][j] = board[i][j];
            }
        }
        for(int idx=0,i=r;i<board.length && idx<word.length(); i++,idx++){
            newBoard[i][c] = word.charAt(idx);
        }
        return newBoard;
    }
    
    public static int crossword(char[][] board, String[] words,int idx) {
        if(idx == words.length){
            store(board);
            return 1;
        }
        int count=0;
        String word = words[idx];
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                if(board[i][j] == '-' || board[i][j] == word.charAt(0)){
                    if(canPlaceHor(board,word, i,j)){
                        count += crossword(PlaceHor(board,word, i,j),words,idx+1);
                    
                    }
                    if(canPlaceVer(board,word, i,j)){
                        count += crossword(PlaceVer(board,word, i,j),words,idx+1);    
                    }
                }
    
            }
        }
        return count;
    }
    //===================================================================
    //Leetcode 1307: Verbal Arithmetic Puzzle
    public static boolean isSolvable(String[] words, String result) {
        String unique="", noZero="";
        for(String word: words){
            for(int i=0;i<word.length();i++){
                if(i==0 && word.length()>1)    noZero+=word.charAt(i);
                if(unique.indexOf(word.charAt(i)) == -1){
                    unique += word.charAt(i);
                    
                }
            }
        }
        for(int i=0;i<result.length();i++){
                if(i==0 && result.length()>1)    noZero+=result.charAt(i);

                if(unique.indexOf(result.charAt(i)) == -1){
                    unique += result.charAt(i);
                }
        }
        // syso(unique);
        int[] arr = new int[26];
        for(int i=0; i<26; i++) arr[i] = -1;

        return cryptoSolver(words,result,unique,noZero,arr,0);

    }
    public static boolean isSolution(String[] words,String result, int[] arr) {
        long LHS = 0, RHS = 0, num =0;
        for(String word: words){
            num = 0;
            for(int i=0; i<word.length(); i++){
                int digit = arr[word.charAt(i) - 'A'];
                num = num*10 + digit;
            }
            LHS+=num;
        }
        num = 0;
        for(int i=0; i<result.length(); i++){
            int digit = arr[result.charAt(i) - 'A'];
            num = num*10 + digit;
        }
        RHS = num;
        return LHS == RHS;
            

    }
    public static boolean cryptoSolver(String[] words, String result, String unique, String noZero, int[] arr, int flag){
        if(unique.length() == 0){
            return isSolution(words,result,arr);
        }
        boolean ans = false;
        for(int num = 0; num<10; num++){
            if(num ==0 && noZero.indexOf(unique.charAt(0))!=-1) continue;
            if((flag & (1<<num)) == 0 && !ans){
                flag^=(1<<num);
                arr[unique.charAt(0) - 'A'] = num;
                // System.out.println(unique.charAt(0) + "  -  " + num);
                
                ans = ans || cryptoSolver(words,result,unique.substring(1),noZero,arr,flag);
                flag^=(1<<num);
                arr[unique.charAt(0) - 'A'] = -1;
            }
        }

        return ans;
    }

    //===================================================================
    //Sudoku
    public static void solveSudoku(char[][] board) {
        int[] row = new int[9], col = new int[9];
        int[][] box = new int[3][3];
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(board[i][j]!='.'){
                    int num = board[i][j] - '0';
                    row[i]^= (1<<num);
                    col[j]^= (1<<num);
                    box[i/3][j/3]^= (1<<num);

                }
            }
        }
        sudokuSolver2(board,0,row,col,box);
        
    }
    public static boolean isValidToPlace(char[][] board, int x, int y, int num) {
        
        //row
        for(int i=0;i<9; i++)  if(board[x][i] - '0' == num)    return false;

        //col
        for(int i=0;i<9; i++)  if(board[i][y] - '0' == num)    return false;

        //3x3 check
        int r=x/3 *3, c = y/3 * 3;
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
                if(board[r+i][c+j] - '0' == num)    return false;

        return true;
    }
    public static boolean sudokuSolver(char[][] board, int idx) {
        if(idx == 81)   
            return true;


        int x = idx/9;
        int y = idx%9;
        int count = 0;

        if(board[x][y] == '.'){
            for(int num = 1; num<=9; num++){
                if(isValidToPlace(board,x,y,num)){
                    board[x][y] = (char)(num + '0');
                    if(sudokuSolver(board,idx +1))  return true;
                    board[x][y] = '.';
                }
            }
        }
        else
            return sudokuSolver(board, idx + 1);
        
        return false;
    }
        public static boolean sudokuSolver2(char[][] board, int idx, int[] row, int[] col, int[][] box) {
        if(idx == 81)   
            return true;

        int x = idx/9;
        int y = idx%9;
        int count = 0;

        if(board[x][y] == '.'){
            for(int num = 1; num<=9; num++){
                if((row[x] & (1<<num)) == 0 &&(col[y] & (1<<num)) == 0 && (box[x/3][y/3] & (1<<num)) == 0 ){
                    board[x][y] = (char)(num + '0');
                    row[x]^= (1<<num);
                    col[y]^= (1<<num);
                    box[x/3][y/3]^= (1<<num);
                    
                    if(sudokuSolver2(board,idx +1,row,col,box))  return true;
                    board[x][y] = '.';
                    row[x]^= (1<<num);
                    col[y]^= (1<<num);
                    box[x/3][y/3]^= (1<<num);                    
                }
                
            }
                       }
        else{
            return sudokuSolver2(board, idx + 1,row,col,box);
        }
        return false;
    }

}