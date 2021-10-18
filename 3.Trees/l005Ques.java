class l005Ques{
    // BT / BST Ques
    public static class TreeNode{
        int val = 0;
        TreeNode left = null, right = null;
        
        TreeNode(int val){
            this.val = val;
        }
    }
    public static void main(String[] args) {
        
    }

    //=============================================================================
    // Construct BST From Level Order
    public static class levelPair {
        TreeNode parent = null;
        int lb = -(int)1e8, rb = (int)1e8;
        levelPair(TreeNode parent, int lb, int rb){
            this.parent = parent;
            this.lb = lb;
            this.rb = rb;
        }
        levelPair(){}
    }
    public static TreeNode constructBSTFromLevelOrder(int[] arr) {
        int idx = 0;
        LinkedList<levelPair> que = new LinkedList<>();
        que.add(new levelPair());

        TreeNode root = null;

        while(que.size() != 0 && idx < arr.length){
            levelPair pair = que.removeFirst();

            if(arr[idx] < pair.lb || arr[idx] > pair.rb)    continue;

            TreeNode node = new TreeNode(arr[idx++]);

            if(pair.parent == null)
                root = node;  
            else{
                if(node.val < pair.parent.val) pair.parent.left = node;
                else    pair.parent.right = node;
            }

            que.add(new levelPair(node, pair.lb, node.val)); //Left kid
            que.add(new levelPair(node, node.val, pair.rb)); // Right kid

        }
    }
    
    
    //=============================================================================
    // LC #1008 : Construct BST From PreOrder
    int bst_idx = 0;
    // lr - left range, rr - right range
    public static TreeNode constructBSTFromPreOrder(int[] arr, int lr, int rr) {
        if(bst_idx == arr.length || arr[bst_idx] < lr || arr[bst_idx] > rr)
            return null;

        TreeNode node = new TreeNode(arr[bst_idx++]);

        node.left = constructBSTFromPreOrder(arr, lr, node.val);
        node.right = constructBSTFromPreOrder(arr, node.val, rr);

        return node;
    }

    //=============================================================================
    //STACK BASED TRAVERSAL
    public static class tPair {
        TreeNode node = null;
        boolean selfDone = false, leftDone = false, rightDone = false;

        tPair(TreeNode node, boolean selfDone, boolean leftDone, boolean rightDone){
            this.node = node;
            this.selfDone = selfDone;
            this.leftDone = leftDone;
            this.rightDone = rightDone;
        }
    }

    public static void iterTraversal(TreeNode root) {
        LinkedList<tPair> ll = new LinkedList<>(); //Using LL as Stack
        while(ll.size() != 0){
            tPair pair = ll.getFirst();
            if(!pair.leftDone){
              pair.leftDone = true;
              if(pair.node.left != null)
                ll.addFirst(new tPair(pair.node.left, false, false, false));  
            } 
            else if(!pair.selfDone){ 
                //Move selfDone IF Block up for PreOrder
                // and down for PostOrder
                System.out.print(pair.node.val + " ");
            }
            else if(!pair.rightDone){
                pair.rightDone = true;
                if(pair.node.right != null)
                  ll.addFirst(new tPair(pair.node.right, false, false, false));                    
            }
            else{
                ll.removeFirst();
            }
        }
    }

    
    //MORRIS TRAVERSAL
    public static TreeNode rightMostNodeFn(TreeNode next, TreeNode curr) {
        while(next.right != null && next.right!=curr){
            next = next.right;
        }
        return next;
    }

    public static void morrisTraversal(TreeNode root) {
        TreeNode curr = root;
        while(curr != null){
            TreeNode next = curr.left;
            if(next == null){
                //Print and move right
                System.out.print(curr.val + ", ");
                curr = curr.right;
            }
            else{
                TreeNode rightMost = rightMostNodeFn(next, curr);
                if(rightMost.right == null){
                    //create Thread and move left
                    // System.out.print(curr.val + ", ");    //ADD THIS LINE FOR PREORDER
                    rightMost.right = curr;
                    curr = curr.left;
                }
                else if(rightMost.right == curr){ 
                    //Break Thread, print and move right
                    rightMost.right = null;
                    System.out.print(curr.val + ", ");  //REMOVE THIS LINE FOR PREORDER
                    curr = curr.right;
                }
            }
        }
    }


    //=============================================================================
    //LC 297 : Serialize and Deserialize a Binary Tree
    static int idx = 0;
    public static TreeNode createTree(int[] arr) {
        if(idx == arr.length || arr[idx] == -1){
            idx++;
            return null;
        }
        
        TreeNode node = new TreeNode(arr[idx++]);

        node.left = createTree(arr);
        node.right = createTree(arr);
        
        return node;
    }
    public static void serializeTree(TreeNode node, ArrayList<Integer> arr) {
        if(node == null){
            arr.add(-1);
            return;
        }
        arr.add(node.val);

        serializeTree(node.left, arr);
        serializeTree(node.right, arr);
    }
    //=============================================================================
    //LC 426 & GFG : Binary Tree to Doubly Linked List
    static TreeNode prev = new TreeNode(-1);
    static TreeNode head = null;

    public static void treeToDLL2(TreeNode root) {
        if(root == null)    return;

        treeToDLL2(root.left);

        if(head == null)    head = root;
        else{
            prev.right = root;
            root.left = prev;
        }

        prev = root; 
        // prev = prev.right;

        treeToDLL2(root.right);
    }
    public static TreeNode treeToDLL(TreeNode root) {
        if(root == null)    return root;
        head = null;
        treeToDLL2(root);
        // Node head = dummy.right;
        prev.right = null;    // Connect Prev and Head for LC 426 
        head.left = null;      // for a Circular DLL
        // dummy.right = null;

        return head;
    }
    //=============================================================================
    //LC 114 : Flatten Binary Tree to Linked List
    //o(n) solution
    public static TreeNode flatten2(TreeNode node) {
        if(node == null || (node.left == null && node.right == null))
            return node;

        TreeNode leftTail = flatten2(node.left);
        TreeNode rightTail = flatten2(node.right);

        if(leftTail != null){
            leftTail.right = node.right;
            node.right = node.left;
            node.left = null;
        }

        return rightTail != null ? rightTail : leftTail;
    }
    //------------------------------------------------
    // N^2 Solution
    public static TreeNode getTail(TreeNode root) {
        if(root == null)    return null;
        TreeNode curr = root;
        while(curr.right!=null) curr = curr.right;
        return curr;
    }
    public void flatten(TreeNode root) {
        
        if(root == null)    return;

        flatten(root.left);
        flatten(root.right);

        TreeNode leftTail = getTail(root.left);
        if(leftTail != null){
            leftTail.right = root.right;
            root.right = root.left;
            root.left = null;
        }
    }
    //=============================================================================
    //LC 889: Construct Binary Tree from Postorder and Preorder traversal array
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        return constructFromPrePost2(preorder,0, preorder.length - 1, postorder, 0, postorder.length - 1);                
    }
    public TreeNode constructFromPrePost2(int[] preorder, int psi, int pei, int[] postorder, int ppsi, int ppei) {
        if(psi > pei)   return null;
        TreeNode root = new TreeNode(preorder[psi]);
        if(psi == pei)  return root;

        int idx = ppsi;
        while(preorder[psi + 1] != postorder[idx])  idx++;

        int num_ele = idx - ppsi + 1;

        root.left = constructFromPrePost2(preorder, psi + 1, psi + num_ele, postorder, ppsi, idx);
        root.right = constructFromPrePost2(preorder, psi + num_ele + 1, pei, postorder, idx + 1, ppei-1);

        return root;

    }
    //=============================================================================
    //LC 105: Construct Binary Tree from Postorder and Inorder traversal array
    public TreeNode constructTreeInPost(int[] postorder, int[] inorder) {
        return constructTreeInPost2(postorder,0, postorder.length - 1, inorder, 0, inorder.length - 1);        
    }
    public TreeNode constructTreeInPost2(int[] postorder, int psi, int pei, int[] inorder, int isi, int iei){
        if(psi > pei || isi > iei)  return null;
        int idx = iei, num_ele = 0;
        TreeNode root = new TreeNode(postorder[pei]);
        while(inorder[idx] != postorder[pei])   idx--;
        num_ele = idx - isi;

        root.left = constructTreeInPost2(postorder, psi, psi + num_ele - 1, inorder, isi, idx-1);
        root.right = constructTreeInPost2(postorder, psi + num_ele, pei - 1, inorder, idx + 1, iei);

        return root;
    }

    //=============================================================================
    //LC 105: Construct Binary Tree from Preorder and Inorder traversal array

    public TreeNode constructTreeInPre(int[] preorder, int[] inorder) {
        return constructTreeInPre2(preorder,0, preorder.length - 1, inorder, 0, inorder.length - 1);        
    }
    public TreeNode constructTreeInPre2(int[] preorder, int psi, int pei, int[] inorder, int isi, int iei){
        if(psi > pei || isi > iei)  return null;
        int idx = -1, num_ele = 0;
        TreeNode root = new TreeNode(preorder[psi]);
        for(int i = isi; i<=iei; i++){
            if(inorder[i] == preorder[psi]){
                idx = i;
                break;
            }
        }
        num_ele = idx - isi;

        root.left = constructTreeInPre2(preorder, psi + 1, psi + num_ele, inorder, isi, idx-1);
        root.right = constructTreeInPre2(preorder, psi + num_ele + 1, pei, inorder, idx + 1, iei);

        return root;
    }
    //=============================================================================
    //LC 510: InOrder Successor of a Node in a BST
    public static class NodeP{
        int val = 0;
        NodeP left = null, right = null, parent = null;
        
        NodeP(int val){
            this.val = val;
        }
    }
    
    public static NodeP inorderSuccNodeBST(NodeP node) {
        if(node.right!= null){
            node = node.right;
            while(node.left!=null)  node = node.left;
            return node;
        }
        else{
            while(node != null){
                if(node.parent != null && node.parent.left == node)
                    return node.parent;

                node = node.parent;

            }
        }
        return null;
    }
    //=============================================================================
    //LC 173 : Implement BSTIterator Class for Inorder traversal
    class BSTIterator {
        public Stack<TreeNode> st = new Stack<>();
    
        public BSTIterator(TreeNode root) {
           addAllLeft(root);
        }
        public void addAllLeft(TreeNode node){
            TreeNode curr = node;
            while(curr != null){
                st.push(curr);
                curr = curr.left;
            }
        }
        
        public int next() {
            TreeNode rn = st.pop();
            addAllLeft(rn.right);
            return rn.val;
        }
        
        public boolean hasNext() {
            return st.size()!=0;
            
        }
    }
    

    //======================================================================
    //Pred Succ BST
    public static void predSuccessBST(TreeNode node, int data, allSolPair pair) {
        TreeNode curr = node, pred = node, succ = node;

        while(curr != null){
            if(curr.val == data){
                TreeNode temp = null;

                if(curr.left != null){
                    temp = curr.left;
                    while(temp.right != null)
                        temp = temp.right;
                    pred = temp;
                }

                if(curr.right != null){
                    temp = curr.right;
                    while(temp.left != null)
                        temp = temp.left;
                    succ = temp;    
                }
                break;
            }
            else if(curr.val < data){
                pred = curr;
                curr = curr.right;
            }
            else{
                succ = curr;
                curr = curr.left;
            }
        }
        pair.pred = pred;
        pair.succ = succ;
    }

    //Predecessor and Successor of a node in BT
    //---Class Code
    public static class allSolPair {
        TreeNode prev = null;
        TreeNode pred = null;
        TreeNode succ = null;
    }
    public static void allSolution(TreeNode node, int data, allSolPair pair) {
        if(node == null)    return;
        
        allSolution(node.left, data, pair);

        if(node.val == data && pair.pred == null)
            pair.pred = pair.prev;
        if(pair.prev.val == data && pair.succ == null)
            pair.succ = node;

        pair.prev = node;

        allSolution(node.right, data, pair);

    }
    //---My Code
    public static TreeNode predSuccessBTDriver_mine(TreeNode root, TreeNode x) {
        pred = null; success = null; found = false;
        predSuccessBT(root,x);
        return success;
    }
    public static Node pred = null, success = null;
    public static Boolean found = false;
    public static void predSuccessBT(TreeNode root, TreeNode dataNode) {
        if(root == null)    return;
        // System.out.println(root.data + " -- " + found);
        
        predSuccessBT(root.left, dataNode);
        // System.out.println("root.data" + " -- " + found);
        
        if(found && success == null){
            success = root;
            return;
        }

        if(root.val != dataNode.val)    pred = root;
        else    found = true;

        predSuccessBT(root.right, dataNode);

    }

}