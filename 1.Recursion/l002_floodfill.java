class l002_floodfill{

    public static void floodfillQues() {
        // System.out.println(allWays(0,0,3,3, new boolean[4][4], "" ));
            
        int[][] dir = {{0,1}, {1,0}, {0,-1}, {-1,0}};
        System.out.println(allWaysWithDirectionVector(0,0,2,2, new boolean[3][3], "" ,dir, "RBLT" ));
        
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