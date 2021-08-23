import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;


public class l001{
    public static void main(String[] args) {
        int arr[] = {1,3,2,2,2,3,4,3,1};
        // int arr[] = {1};
        // System.out.println(removeBoxes(arr));
    }

    //=====================================================================
    // Conceptual :  Vertical Level Printing

    public static void verticalLevels_Driver(TreeNode root) {
        maxLevel = -(int)1e9;
        minLevel = (int)1e9;
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        verticalLevels_BFS(root, map);
        // verticalLevelsDFS(root, 0, map);
        for(int i = minLevel; i<=maxLevel; i++){
            ArrayList<Integer> arr = map.getOrDefault(i, null);
            if(arr != null){
                System.out.print("Level " + (i - minLevel) + " : " );
                System.out.println(arr);
            }
        }
    }
    public static int maxLevel = -(int)1e9, minLevel = (int)1e9;

    //BFS or Level Order Traversal : Right way to do it
    public static class pair {
        TreeNode node = null;
        int level = 0;
        pair(TreeNode node, int level){
            this.node = node; 
            this.level = level;
        }
    }
    public static void verticalLevels_BFS(TreeNode root, HashMap<Integer, ArrayList<Integer>> map) {
        LinkedList<pair> que = new LinkedList<>();
        que.addLast(new pair(root, 0));

        while(que.size()!=0){
            int SIZE = que.size();

            while(SIZE-->0){
                pair rn = que.removeFirst();
                int level = rn.level;
                
                //Finding min and max levels
                maxLevel = Math.max(maxLevel, level);
                minLevel = Math.min(minLevel, level);


                //Adding to the Map
                
                // if(map.getOrDefault(level, null) == null){
                //     ArrayList<Integer> aList = new ArrayList<Integer>();
                //     aList.add(rn.node.val);
                //     map.put(level, aList);
                // }
                // else{
                //     map.get(level).add(rn.node.val);
                // }

                //Alternate to Adding like above
                map.putIfAbsent(level, new ArrayList<Integer>());
                map.get(level).add(rn.node.val);
                



                if(rn.node.left != null)
                    que.addLast(new pair(rn.node.left, level - 1));
                if(rn.node.right != null)
                    que.addLast(new pair(rn.node.right, level + 1));

            }
        }
    }

    //DFS : Code is working fine but logically prints same level order wrong sometimes
    public static void verticalLevelsDFS(TreeNode root, int level, HashMap<Integer, ArrayList<Integer>> map) {
        if(root == null)    return;

        //Finding min and max levels
        maxLevel = Math.max(maxLevel, level);
        minLevel = Math.min(minLevel, level);
        
        //Adding to the Map
        if(map.getOrDefault(level, null) == null){
            ArrayList<Integer> aList = new ArrayList<Integer>();
            aList.add(root.val);
            map.put(level, aList);
        }
        else{
            map.get(level).add(root.val);
        }

        verticalLevelsDFS(root.left, level - 1, map);
        verticalLevelsDFS(root.right, level + 1, map);

    }

    //==================================================================
    //Level Order Traversal / Breadth First Search
    public static List<List<Integer>> levelOrderLineWise_M3(TreeNode root) {
        //This code also passes 'LC #102: Binary Tree Level Order Traversal'
        //and with very little tweaks, 'LC #199: Binary Tree Right Side View'


        if(root == null)    return new ArrayList<>();
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);
        int level = 0;
        List<List<Integer>> ans = new ArrayList<>();

        while(que.size() != 0){
            int SIZE = que.size();
            System.out.print("Level " + level + " : ");
            List<Integer> levelAns = new ArrayList<>();

            while(SIZE-- > 0){
                TreeNode rn = que.removeFirst(); // rn : Removed Node
                System.out.print(rn.val + " ");
                levelAns.add(rn.val);

                if(rn.left!=null)   que.addLast(rn.left);
    
                if(rn.right!=null)   que.addLast(rn.right);    
            }
            System.out.println();
            level++;
            ans.add(levelAns);
            
        }
        return ans;
    }
 
    public static void levelOrderLineWise_M2(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);
        que.addLast(null);
        
        int level = 0;
        System.out.print("Level " + level + " : ");
        
        while(que.size() != 1){
            TreeNode rn = que.removeFirst(); // rn : Removed Node
            System.out.print(rn.val + " ");

            if(rn.left!=null)   que.addLast(rn.left);

            if(rn.right!=null)   que.addLast(rn.right);
         
            if(que.getFirst() == null){
                System.out.println();
                if(que.size() != 1)
                    System.out.print("Level " + (++level) + " : ");

                que.addLast(que.removeFirst());
            }
        }
    }
   
    public static void levelOrderLineWise_M1(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        LinkedList<TreeNode> childQue = new LinkedList<>();
        que.addLast(root);
        int level = 0;
        System.out.print("Level " + level + " : ");
        while(que.size() != 0){
            TreeNode rn = que.removeFirst(); // rn : Removed Node
            System.out.print(rn.val + " ");

            if(rn.left!=null)   childQue.addLast(rn.left);

            if(rn.right!=null)   childQue.addLast(rn.right);

            if(que.size() == 0){
                System.out.println();
                if(childQue.size() != 0)
                    System.out.print("Level " + (++level) + " : ");

                LinkedList<TreeNode> temp = que;
                que = childQue;
                childQue = temp;
            }
            
        }
    }
    public static void levelOrderSimple(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);

        while(que.size() != 0){
            TreeNode rn = que.removeFirst(); // rn : Removed Node
            System.out.print(rn.val + " ");

            if(rn.left!=null)   que.addLast(rn.left);

            if(rn.right!=null)   que.addLast(rn.right);
            
        }
    }
    //============= TREE CLASS ===================================
    public static class TreeNode{
        int val = 0;
        TreeNode left = null, right = null;
        
        TreeNode(int val){
            this.val = val;
        }
    }
    //============ BASIC FUNCTIONS =====================================
    //DISPLAY TREEE FUNCTION
    public static void displayTree(TreeNode root) {
        if(root == null)    return;

        String left = root.left == null ? "null" : root.left.val + "";
        String right = root.right == null ? "null" : root.right.val + "";
        String rootVal = root.val + "";

        System.out.println(left + " <-- " + rootVal + " -->" + right);

        displayTree(root.left);
        displayTree(root.right);
    }
    
    //CREATE TREE FUNTION
    public static int idxForCreateTree = 0;
    public static TreeNode createTree(int[] arr) {
        idxForCreateTree = 1;
        TreeNode root = new TreeNode(arr[0]);
        createTree_(root, arr);
        return root;
    }
    public static void createTree_(TreeNode root, int[] arr) {
        
        if(idxForCreateTree == arr.length)  return;

        root.left = new TreeNode(arr[idxForCreateTree++]);

        if(idxForCreateTree == arr.length)  return;

        root.right = new TreeNode(arr[idxForCreateTree++]);

        createTree_(root.left, arr);
        createTree_(root.right, arr);

    }
}