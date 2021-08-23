import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class l000_question{
    public static void main(String[] args) {
        
        //LC #99: Recover Binary Search Tree

        // int[] treeArr = {2,3,1};
        // TreeNode root = createTree(treeArr);
        // while(!isValidBST2_02(root, Long.MIN_VALUE, Long.MAX_VALUE, null, null));
        // displayTree(root);


    }
    //===============================================================================
    //LC #99: Recover Binary Search Tree
    public static void recoverBST_Driver(TreeNode root) {
        a = null; b = null; prev = null;
        
        recoverBST_Inorder(root);
        if(a != null){
            int temp = a.val;
            a.val = b.val;
            b.val = temp;
        }
    }
    public static TreeNode a = null, b = null, prev = null;
    public static boolean recoverBST_Inorder(TreeNode root) {
        if(root == null)    return true;

        if(!recoverBST_Inorder(root.left))      return false;
        //InOrder Start

        if(prev != null && prev.val > root.val){
            b = root;
            if(a == null)   a = prev;
            else    return false; //Both Anomaly Found, so return from the code
        }
        
        prev = root;
        //InOrder End
        if(!recoverBST_Inorder(root.right))      return false;

        return true;
    }
    public static boolean recoverBST2_02(TreeNode root, long ll, long ul, TreeNode leftMin, TreeNode rightMax) {
        if(root == null)    return true;
        
        if(root.val <= ll || root.val>= ul){
            if(root.val <= ll)  swap(root, leftMin);
            else swap(root, rightMax);
            return false;
          
        } 
        
        return recoverBST2_02(root.left,ll, root.val, leftMin, root) 
        && recoverBST2_02(root.right, root.val, ul, root, rightMax);
        
    }
    public static void swap(TreeNode A, TreeNode B){
        // System.out.println("tried to swap: " + A.val + " & " + B.val);
        int temp = A.val;
        A.val = B.val;
        B.val = temp;
        // System.out.println("Swapped: " + A.val + " & " + B.val);
        
    }

    //================================================================
    //LC #98 : Validate Binary Search Tree
    public boolean isValidBST_Driver(TreeNode root) {
        return isValidBST2(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    public boolean isValidBST2(TreeNode root, long ll, long ul) {
        if(root == null)    return true;
        // if(root.val == Integer.MAX_VALUE || root.val == Integer.MIN_VALUE)  return false;
        
        if((root.left!=null && root.left.val >= root.val) || (root.right!=null && root.right.val <= root.val))
            return false;
        
        if(root.val <= ll || root.val>= ul) return false;
        
        
        return isValidBST2(root.left,ll, root.val) && isValidBST2(root.right, root.val, ul);
        
    }
    public static long prevBSTNode = -(long)1e13;
    public static boolean isValidBST_Inorder(TreeNode root) {
        if(root == null)    return true;

        if(!isValidBST_Inorder(root.left))      return false;
        //InOrder Start

        if(root.val <= prevBSTNode)     return false;

        prevBSTNode = root.val;

        //Since we won't check the next(right side) value in an array to see if array is
        //sorted or not, similarly checking right side(next) value is not needed
        //InOrder End
        if(!isValidBST_Inorder(root.right))      return false;

        return true;
    }

    //================================================================
    // LC #124 : Binary Tree Maximum Path Sum
    public int maxPathSum124(TreeNode root){ 
        // code here
        maxPS = -(int)1e9;
        int ans = maxPathSum2_124(root);
        return maxPS != -(int)1e9 ? maxPS : ans;
    } 
    public  int maxPS = -(int)1e9;
    public  int maxPathSum2_124(TreeNode root){ 
        // code here
        if(root == null)    return -(int)1e9;
        if(root.left == null && root.right == null){
            maxPS = Math.max(maxPS, root.val);
            return root.val;
            
        }
    
        int left = 0, right = 0;
        //left Max Node to Root Sum
        left = maxPathSum2_124(root.left);
        //right Max Node to Root Sum
        right = maxPathSum2_124(root.right);
        
        
        //When answer goes left Sub tree -> Root -> right subtree
        maxPS = Math.max(maxPS, left + right + root.val);

        //When answer goes left Sub tree -> Root and then returns
        maxPS = Math.max(maxPS, left +  root.val);

        //When answer goes right Sub tree -> Root and then returns
        maxPS = Math.max(maxPS, right + root.val);
        
        //When answer goes to Root and then returns
        maxPS = Math.max(maxPS, root.val);
        
        return Math.max(Math.max(left, right) + root.val, root.val);
    }
    
    //=================================================================
    //GFG: Maximum Path Sum between 2 Leaf Nodes
    public int maxPathSum(TreeNode root){ 
        // code here
        maxPS = -(int)1e9;
        int ans = maxPathSum2(root);
        return maxPS != -(int)1e9 ? maxPS : ans;
    } 
    // public  int maxPS = -(int)1e9;
    public  int maxPathSum2(TreeNode root){ 
        // code here
        if(root == null)    return -(int)1e9;
        if(root.left == null && root.right == null)
            return root.val;
    
        int left = 0, right = 0;
        //left Max Node to Root Sum
        left = maxPathSum2(root.left);
        //right Max Node to Root Sum
        right = maxPathSum2(root.right);
        if(root.left != null && root.right != null){
            maxPS = Math.max(maxPS, left + right + root.val);
        }
        return Math.max(left, right) + root.val;
    }
    
    //=================================================================
    // LC #112 : Path Sum
    public boolean hasPathSum2(TreeNode root, int targetSum, int sumSoFar) {
        if(root == null)    return false;

        if(root.left == null && root.right == null){
            return targetSum == sumSoFar + root.val;
        }

        boolean res = false;
        // if(sumSoFar + root.val < targetSum)
            res = res || hasPathSum2(root.left, targetSum, sumSoFar + root.val);
    
        // if(sumSoFar + root.val < targetSum)
            res = res || hasPathSum2(root.right, targetSum, sumSoFar + root.val);
    
        return res;
    }   //=================================================================
    //LC 543 : Diameter of a Tree
    public static int diameterOfBinaryTree(TreeNode root) {
        
        if(root == null)    return -1;

        int leftTreeDia = diameterOfBinaryTree(root.left);
        int rightTreeDia = diameterOfBinaryTree(root.right);

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        
        return Math.max(Math.max(leftTreeDia, rightTreeDia), leftHeight + rightHeight + 2);
    }

    //{diameter, height}
    public static int[] diameterOfBinaryTree_02(TreeNode root) {
        if(root == null)    return new int[]{-1,-1};

        int[] leftAns = diameterOfBinaryTree_02(root.left);
        int[] rightAns = diameterOfBinaryTree_02(root.right);

        int[] ans = new int[2];
        ans[0] = Math.max(Math.max(leftAns[0], rightAns[0]), leftAns[1] 
                                                + rightAns[1] + 2);

        ans[1] = Math.max(leftAns[1], rightAns[1]) + 1;

        return ans;
    }
    //Diameter with Static Variable
    public static int maxDia = 0;
    public static int diameterOfBinaryTree_03(TreeNode root) {
        if(root == null)    return -1;

        int lh = diameterOfBinaryTree_03(root.left);
        int rh = diameterOfBinaryTree_03(root.right);

        maxDia = Math.max(maxDia, lh + rh + 2);

        return Math.max(lh, rh) + 1;
    }


    //================================================================
    // GFG: burn-the-binary-tree-from-target-node
    public List<Integer> burnTree_Driver(TreeNode root, TreeNode target, int k) {
        //Assuming Tree has already been given
        List<Integer> ans = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> theList = new ArrayList<>();
        burnTree2(root, target, k, null, theList, -1);
        for(int i = 0; i < theList.size(); i++){
            System.out.println(theList.get(i));
        }
        if(theList.size() > k && theList.get(k).size() > 0){
            for(int i=0; i < theList.get(k).size(); i++)    ans.add(theList.get(k).get(i));
        }
        return ans;
    }
    
    public static void addToList(ArrayList<ArrayList<Integer>> list, int idx, int val) {
        if(idx >= list.size()){
            ArrayList<Integer> newOne = new ArrayList<>();
            newOne.add(val);
            list.add(newOne);
        }
        else{
            list.get(idx).add(val);
        }
    }
    public static int burnTree2(TreeNode root, TreeNode target, int K, TreeNode blocked, ArrayList<ArrayList<Integer>> list, int idx) {
        if(root == null)    return -1;
        int newK = K;
        int valueToReturn = -1;
        int left = -1, right = -1;

        if(idx>-1)  addToList(list, idx++, root.val);

        if(root == target){
            addToList(list, 0, root.val);
            valueToReturn = 1;
            newK = K - 1;
            idx = 1;
        }

        if(root.left != blocked)
            left = burnTree2(root.left,target, newK, blocked, list, idx);
        if(left != -1){
            newK = K - left;
            idx = left + 1;
            addToList(list, left, root.val);
            valueToReturn = left + 1;
            blocked = root.left;
        }

        if(root.right != blocked)
            right = burnTree2(root.right,target, newK, blocked, list, idx);
        if(right != -1){
            newK = K - right;
            addToList(list, right, root.val);
            idx = right + 1;
            valueToReturn = right + 1;
            blocked = root.right;
            burnTree2(root.left, target, newK, blocked, list, idx);
        }
        return valueToReturn;
    }

    public static int burnTree(TreeNode root, TreeNode target, int K, ArrayList<Integer>[] list) {

        if(root == null)    return -1;

        if(root == target)    return 0;

        int left = burnTree(root.left, target, K, list); 
        if(left != -1){
            
            return left + 1;
        }
        int right = burnTree(root.right, target, K, list);
        if(right!= -1){
            return right + 1;
        }
        return -1;
    }
    
    //================================================================
    //LC 863: All Nodes Distance K in Binary Tree
    //---- Try This Question without using RootToNodePath
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        ArrayList<TreeNode> pathT = new ArrayList<TreeNode>();
        find(root,target, pathT); //Used from other code
        List<Integer> ans = new ArrayList<Integer>();
        TreeNode blockNode = null;        
        for(int i=0; i<pathT.size(); i++){
            findKDistance(pathT.get(i), blockNode, k-i, ans);   
            blockNode = pathT.get(i);
        }
        return ans;
    }
    public static void findKDistance(TreeNode root, TreeNode avoid, int k, List<Integer> ans){
        if(root == null || root == avoid || k < 0)    return;
        if(k == 0){
            ans.add(root.val);
            return;
        }

        findKDistance(root.left,avoid, k-1,ans);
        findKDistance(root.right,avoid, k-1,ans);
        
    }
    //=================================================================
    //Q: Find RootToNode path of a given data
    //LC 236: LowestCommonAncestor

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ArrayList<TreeNode> pathP = RootToNodePath(root, p);
        ArrayList<TreeNode> pathQ = RootToNodePath(root, q);
        
        TreeNode LCA = null;
        for(int i = 0; i<pathP.size() && i<pathQ.size(); i++){
            if(pathP.get(i) == pathQ.get(i))    LCA = pathQ.get(i);
            else    return LCA;
        }
        return LCA;
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
    //======================================================================
    public static int size(TreeNode root) {
        return (root == null) ? 0 : size(root.left) + size(root.right);
    }
    public static int height(TreeNode root) {
        //In Respect of Edges, return -1, for Nodes, return 0;
        return (root == null) ? -1 : Math.max(height(root.left), height(root.right)) + 1;        
    }
    public static int maximum(TreeNode root) {
        //Same way for Minimum
        if(root == null)    return -(int)1e9;

        int lmv = maximum(root.left);
        int rmv = maximum(root.right);
        
        return Math.max(Math.max(lmv, rmv), root.val);
    }
    public static boolean isPresent(TreeNode root, int data) {
        if(root == null)    return false;
        if(root.val == data)    return true;
        
        return isPresent(root.left, data) || isPresent(root.right, data);
    }
    public static boolean find(TreeNode root, TreeNode data, ArrayList<TreeNode> ans) {
        // Returns Node TO Root Path
        if(root == null)    return false;
        if(root == data){
            ans.add(root);
            return true;
        }
        boolean res = find(root.left, data,ans) || find(root.right, data,ans);
        if(res)     ans.add(root);
        return res;
    }
    public static ArrayList<TreeNode> RootToNodePath_Class(TreeNode root, TreeNode data) {
        // Returns Node TO Root Path
        if(root == null)    return new ArrayList<>();

        if(root == data){
            ArrayList<TreeNode> base = new ArrayList<>();
            base.add(root);
        }

        ArrayList<TreeNode> leftAns = RootToNodePath_Class(root.left,data);
        if(leftAns.size() > 0){
            leftAns.add(root);
            return leftAns;
        }        

        ArrayList<TreeNode> rightAns = RootToNodePath_Class(root.right,data);
        if(rightAns.size() > 0){
            rightAns.add(root);
            return rightAns;
        }        
        return new ArrayList<>();
    }
    
    public static ArrayList<TreeNode> RootToNodePath(TreeNode root, TreeNode data) {
        // Returns Root TO Node Path
        if(root == null)    return new ArrayList<TreeNode>();

        if(root == data){
            ArrayList<TreeNode> baseAns = new ArrayList<>();
            baseAns.add(root);
            return baseAns;
        }

        ArrayList<TreeNode> recAns = new ArrayList<TreeNode>();
        ArrayList<TreeNode> leftAns = RootToNodePath(root.left, data);

        if(leftAns.size()>0){
            recAns.add(root);
            for(TreeNode node: leftAns)     recAns.add(node);
        }
        else{
            ArrayList<TreeNode> rightAns = RootToNodePath(root.right, data);

            if(rightAns.size()>0){
                recAns.add(root);
                for(TreeNode node: rightAns)     recAns.add(node);
            }
    
        }
        return recAns;
    }

    

}