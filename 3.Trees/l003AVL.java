public class l003AVL{
    public static void main(String[] args) {
        TreeNode root = null;
        // Random rand = new Random();
        for(int i=1; i<=15; i++){
            root = insertIntoBST(root, i * 10);

            displayAVL(root);
            System.out.println("=============================================================");
            System.out.println();    
        }
        // root = postOrder(root);
        // displayAVL(root);
    }
    //Display a Tree
    public static void displayAVL(TreeNode root) {
        if(root == null)    return;

        String str = "";
        if(root.left!=null){
            str += root.left.val + "(" + root.left.bal + ")";
        }
        str+= " <-- ";
        str += root.val + "(" + root.bal + ")";
        str+= " --> ";
        if(root.right!=null){
            str += root.right.val + "(" + root.right.bal + ")";
        }
        System.out.println(str);
        displayAVL(root.left);
        displayAVL(root.right);
    }

    //=================================================================
    // MOdified Insert Into BST for AVL
    public static TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null)    //return getRotation(new TreeNode(val));
            return new TreeNode(val);
        if(root.val > val)
            root.left = insertIntoBST(root.left, val);
        if(root.val < val)
            root.right = insertIntoBST(root.right, val);

        return getRotation(root);
        // return root;
    }

    //Deleting nodes from an AVL
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

        return getRotation(root);
    }
 
    public static void updateBalanceAndHeight(TreeNode node) {
        if(node == null)    return;

        int lh = -1, rh = -1; // left height, right height

        if(node.left != null)   lh = node.left.height;
        if(node.right != null)   rh = node.right.height;

        node.bal = lh - rh;
        node.height = Math.max(lh, rh) + 1;

    }
    public static TreeNode rightRotation(TreeNode A) { // for LL -> O(1)
        TreeNode B = A.left;
        TreeNode BkaRight = B.right;

        B.right = A;
        A.left = BkaRight;

        updateBalanceAndHeight(A); // Must update A before  B
        updateBalanceAndHeight(B); // since A is below B now

        return B; //Since B is now above A and hence, the root now
    }
    public static TreeNode leftRotation(TreeNode A) { // for RR  -> O(1)
        TreeNode B = A.right;
        TreeNode BkaLeft = B.left;

        B.left = A;
        A.right = BkaLeft;

        // updateBalanceAndHeight(A); // Must update A before  B
        // updateBalanceAndHeight(B); // since A is below B now

        updateBalanceAndHeight(A); //More generalised way
        updateBalanceAndHeight(B);// to handle the AVL tree

        return B; //Since B is now above A and hence, the root now
    }
    
    public static TreeNode getRotation(TreeNode node) {
        updateBalanceAndHeight(node);
        if(node.bal == 2){ // LL or LR
            if(node.left.bal == 1){ //LL
                return rightRotation(node);
            }
            else{ // LR or -1
                node.left = leftRotation(node.left); 
                return rightRotation(node);
            }
        }
        else if(node.bal == -2){ // RL or RR
            if(node.right.bal == -1){ //RR
                return leftRotation(node);
            }
            else{ // RL or 1
                node.right = rightRotation(node.right);
                return leftRotation(node);
            }

        }

        return node;
    }

    //============= TREE CLASS ===================================
    public static class TreeNode{
        int val = 0;
        TreeNode left = null, right = null;
        int bal = 0, height = -1;

        TreeNode(int val){
            this.val = val;
        }
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
    }
    
}