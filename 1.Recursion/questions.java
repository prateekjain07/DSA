class questions{
    //============Leet #980=======================
    public static int uniquePathIII(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int sr, sc, er,ec, freeCell = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(grid[i][j]!= -1) freeCell++;

                if(grid[i][j]== 1){
                    sr = i; sc = j;
                }
                else if(grid[i][j]== 2){
                    er = i; ec = j;
                }
            }   
        }
    }
    
    public static void main(String[] args) {
        
    }
}