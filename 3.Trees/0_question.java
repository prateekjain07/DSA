public class 0_question{
    public static void main(String[] args) {
        
    }
    
    //================================================================
    // GFG: burn-the-binary-tree-from-target-node
    
    
    }
    //================================================================
    //LC 863: All Nodes Distance K in Binary Tree
    //---- Try This Question without using RootToNodePath
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<TreeNode> pathT = new ArrayList<TreeNode>();
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
    public class TreeNode{
        int val = 0;
        TreeNode left = null, right = null;
        
        TreeNode(int val){
            this.val = val;
        }
    }
    //============ BASIC FUNCTIONS =====================================
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
        if(root == null)    retun new ArrayList<>();

        if(root == data){
            ArrayList<TreeNode> base = new ArrayList<>();
            baseAns.add(root);
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