class l002_floodfill{

    public static void floodfillQues() {
        // System.out.println(allWays(0,0,3,3, new boolean[4][4], "" ));
            
        int[][] dir = {{0,1}, {1,0}, {0,-1}, {-1,0}};
        // System.out.println(allWaysWithDirectionVector(0,0,2,2, new boolean[3][3], "" ,dir, "RBLT" ));
        // Knights Tour I/O
        // int[][] dir2 = {{2,1}, {1,2}, {2,-1}, {1,-2},{-2,1}, {-1,2}, {-2,-1}, {-1,-2}};
        // int n=8;
        // int[][] matrix = new int[n][n];
        // int[][] ans = new int[n][n];
        // knightTour(0,0,matrix,0,ans,dir2);
        // for(int i=0; i<matrix.length; i++){
        //     for(int j=0; j<matrix.length; j++){
        //         System.out.print(ans[i][j] + " ");
        //     }
        //     System.out.println();    
        // }
        
        int[][] matrix = new int[5][5];
        System.out.println(shortestFloodfill(matrix, 1,2,4,4,dir));
    }
    //Longest & Shortest path in Floodfill
    public static int longestFloodfill(int[][] matrix, int sr, int sc, int dr, int dc, int[][] dir) {
        if(sr == dr && sc == dc){
            return 1;
        }
        matrix[sr][sc]= 1;
        int maxPath = 0;
        for(int d=0; d<dir.length; d++){
            int r = sr + dir[d][0];
            int c = sc + + dir[d][1];

            if(r>=0 && r<matrix.length && c>=0 && c<matrix[0].length && matrix[r][c]==0){
                maxPath = Math.max(maxPath,longestFloodfill(matrix,r,c,dr,dc,dir));
            } 
        }
        
        
        matrix[sr][sc]= 0;
        return maxPath;        

    }

    public static int shortestFloodfill(int[][] matrix, int sr, int sc, int dr, int dc, int[][] dir) {
        if(sr == dr && sc == dc){
            return 1;
        }
        matrix[sr][sc]= 1;
        int minPath = matrix[0].length*matrix.length +1;
        for(int d=0; d<dir.length; d++){
            int r = sr + dir[d][0];
            int c = sc + + dir[d][1];

            if(r>=0 && r<matrix.length && c>=0 && c<matrix[0].length && matrix[r][c]==0){
                minPath = Math.min(minPath,shortestFloodfill(matrix,r,c,dr,dc,dir));
            } 
        }
        
        
        matrix[sr][sc]= 0;
        return minPath;        

    }

    public static void knightTour(int sr, int sc, int[][] matrix, int sq, int[][] ans, int[][] dir){
        if(sq == matrix.length*matrix.length-1){
            for(int i=0; i<matrix.length; i++){
                for(int j=0; j<matrix.length; j++){
                    ans[i][j] = matrix[i][j];
                }    
            }
            return;
        }
        matrix[sr][sc] = sq;
        System.out.println(sr + " - " + sc + " - " + (sq));

        for(int d=0; d<dir.length; d++){
            int r = sr + dir[d][0];
            int c = sc + + dir[d][1];

            if(r>=0 && r<matrix.length && c>=0 && c<matrix.length && matrix[r][c]==0 && (r+c) != 0){
                knightTour(r, c, matrix, sq+1, ans,dir);
            } 
        }
        matrix[sr][sc] = 0;
        System.out.println(sr + " - " + sc + " - " + matrix[sr][sc]);

    }

    public static int allWaysWithDirectionVector(int sr, int sc, int dr, int dc, boolean[][] vis, String ans, int[][] dir, String RBLT) {
        //It becomes more of a generic code which can handle even 8 dirns or 12 or any no of calls
        if(sr == dr && sc ==dc ){
            System.out.println(ans);
            return 1;
            
        }
        vis[sr][sc] = true;
        int count=0;
        for(int d=0; d<dir.length; d++){
            int r = sr + dir[d][0];
            int c = sc + + dir[d][1];

            if(r>=0 && r<=dr && c>=0 && c<=dc && !vis[r][c]){
                count+= allWaysWithDirectionVector(r, c, dr, dc, vis , ans + RBLT.charAt(d), dir, RBLT);
            }
                            
        }
        
        vis[sr][sc] = false;

        return count;     
    }
    
    public static int allWays(int sr, int sc, int dr, int dc, boolean[][] vis, String ans) {
        
        if(sr == dr && sc ==dc ){
            System.out.println(ans);
            return 1;
            
        }
        vis[sr][sc] = true;
        int count=0;

        if(sc+1<=dc && !vis[sr][sc+1] )//right
            count+= allWays(sr, sc+1, dr, dc,vis,ans+"R");
        
        if(sr+1<=dr && !vis[sr+1][sc] )//Bottom
            count+= allWays(sr+1, sc, dr, dc,vis,ans+"B");
        
        if(sc-1>=0 && !vis[sr][sc-1] )//left
            count+= allWays(sr, sc-1, dr, dc,vis,ans+"L");
        
        if(sr-1>=0 && !vis[sr-1][sc] )//Top
            count+= allWays(sr-1, sc, dr, dc,vis,ans+"T");
        
        vis[sr][sc] = false;

        return count;     
    }
    
    public static void main(String[] args) {
        floodfillQues();    
    }
}