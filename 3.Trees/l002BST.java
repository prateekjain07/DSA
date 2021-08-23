import java.util.HashSet;
public class l002BST{
    public static void main(String[] args) {
        
    }

    //====================================================================
    //LC #108 : Convert a Sorted Array into a BST
    public static TreeNode createBST(int[] arr, int si, int ei) {
        if(si > ei) return null;
        
        int mid = (si + ei)/2;
        TreeNode node = new TreeNode(arr[mid]);

        node.left = createBST(arr, si, mid - 1);
        node.right = createBST(arr, mid + 1, ei);
        
        return node;
    }
    //====================================================================
    // LC #653 : Two Sum IV : Input is a BST
    public boolean findTarget2(TreeNode root, int k, HashSet<Integer> aSet) {
        if(root == null)    return false;
        
        boolean res = false;
        
        res = res || findTarget2(root.left, k, aSet);
        
        if(aSet.contains(k - root.val)) return true;    
        aSet.add(root.val);
        
        res = res || findTarget2(root.right, k, aSet);

        
        return res;
        
    }
    //====================================================================
    //LC #235 : Lowest Common Ancestor of Binary Search Tree
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode curr = root;
        while(curr != null){
            // System.out.println("VAL : " + curr.val);
            if(curr.val > p.val && curr.val > q.val)
                curr = curr.left;
            else if(curr.val < p.val && curr.val < q.val)
                curr = curr.right;
            else
                return curr;
            
        }
        return null;
    }
    //====================================================================
    //LC #450 : Delete From  a Binary Search Tree
    public static TreeNode deleteNode2(TreeNode root, int key) {
        if(root == null)    return null;
        if(root.val > key)
            root.left = deleteNode2(root.left, key);
        else if(root.val < key)
            root.right = deleteNode2(root.right, key);

        else{
            if(root.left == null || root.right == null){
                return root.left != null ? root.left : root.right;
            }

            int maxVal = maximumEle(root.left);
            root.val = maxVal;
            root.left = deleteNode2(root.left, maxVal);
        }

        return root;
    }
    //====================================================================
    //LC #701 : Insert Into a Binary Search Tree
    public static TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null)    return new TreeNode(val);

        if(root.val > val)
            root.left = insertIntoBST(root.left, val);
        if(root.val < val)
            root.right = insertIntoBST(root.right, val);

        return root;
    }
     public static TreeNode insertIntoBST_02(TreeNode root, int data) {//Iterative
        TreeNode curr = root, prev = null;
        while(curr != null){
            prev = curr;
            if(curr.val < data)     curr = curr.right;
            else if(curr.val > data)    curr = curr.left;
        }

        TreeNode node = new TreeNode(data);
        if(prev == null)
            return node;
        else if(prev.val < data)
            prev.right = node;
        else
            prev.left = node;

        return root;  
    }
 
    //==================== BST Specific Questions=========================
    public static int maximumEle(TreeNode root) {
        TreeNode curr = root;
        while(curr.right!= null){
            curr = curr.right;
        }
        return curr.val;
    }
    public static int minimumEle(TreeNode root) {
        TreeNode curr = root;
        while(curr.left!= null){
            curr = curr.left;
        }
        return curr.val;
    }
    public static boolean findData(TreeNode root, int data) {//Iterative
        TreeNode curr = root;
        while(curr != null){
            if(curr.val < data) curr = curr.right;
            else if(curr.val > data) curr = curr.left;
            else    return true;
            
        }
        return false;  
    }
    public static boolean findData2(TreeNode root, int data) {//Recursive
        if(root == null)    return false;
        if(root.val == data)    return true;

        if(root.val < data)
            return findData2(root.right, data);
        else
            return findData2(root.left, data);

        return false;  
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

    //==========================BASIC FUNCTIONS===========================
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