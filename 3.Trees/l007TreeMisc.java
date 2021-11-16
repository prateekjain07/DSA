//git add . && git commit -m "Commit" && git push origin master
public class l007TreeMisc{
    public static class TreeNode{
        int val = 0;
        TreeNode left = null, right = null;
        
        TreeNode(int val){
            this.val = val;
        }
    }
    //------------------------------------------------------------------
    public static void main(String[] args) {
        
    }

    //===================================================================
    // LC 662 : Maximum Width of Binary Tree
    public int widthOfBinaryTree(TreeNode root) {
        if(root == null)    return 0;

        long ans = 0;
        LinkedList<pairG> que = new LinkedList<>();
        que.addLast(new pairG(root, 1)); //We're starting idx from 1 for reference
        while(que.size() != 0){
            int size = que.size();
            long fi = que.getFirst().idx;
            long li = que.getFirst().idx;

            while(size-- > 0){
                pairG p = que.removeFirst();
                TreeNode node = p.node;
                long idx = p.idx;

                if(node.left!=null) que.addLast(new pairG(node.left, 2* idx));
                if(node.right!=null) que.addLast(new pairG(node.right, 2* idx + 1));


            }
        }

    }
    public static class pairG {
        TreeNode node = null;
        long idx = 0;
        pairG(TreeNode node, long idx){
            this.node = node;
            this.idx = idx;
        }
    }
    
    //===================================================================
    //LC 437 : Path Sum III
    public static int ans = 0;
    public static int pathSum_Driver(TreeNode root, int targetSum) {
        ans = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0,1);
        pathSum_ans(root, targetSum, 0, map);
        return ans;
    }
    public static void pathSum_ans(TreeNode root, int targetSum, int prefixSum, HashMap<Integer, Integer> map) {
        if(root == null)    return;

        prefixSum += root.val;
        ans += map.getOrDefault(prefixSum - targetSum, 0);

        map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);

        pathSum_ans(root.left, targetSum, prefixSum, map);
        pathSum_ans(root.right, targetSum, prefixSum, map);

        map.put(prefixSum, map.get(prefixSum) - 1);
        if(map.get(prefixSum) == 0)     map.remove(prefixSum);

    }
    
    //===================================================================
    //LC 653 : Two Sum Problem in a BST
    public static boolean findTarget(TreeNode root, int k) {
        return findTarget_ans(root, k).size() > 0;
    }
    public static void pushLeftNodes(TreeNode node, LinkedList<TreeNode> st) {
        while(node != null){
            st.addFirst(node);
            node = node.left;
        }
    }
    public static void pushRightNodes(TreeNode node, LinkedList<TreeNode> st) {
        while(node != null){
            st.addFirst(node);
            node = node.right;
        }
    }
    public static List<List<Integer>> findTarget_ans(TreeNode root, int k) {
        LinkedList<TreeNode> LST = new LinkedList<>();
        LinkedList<TreeNode> RST = new LinkedList<>();
        List<List<Integer>> myAns = new ArrayList<>();
        pushLeftNodes(root, LST);
        pushRightNodes(root, RST);

        TreeNode leftTop = LST.getFirst(), rightTop = RST.getFirst();
        while(leftTop.val <= rightTop.val && leftTop != rightTop){
            if(leftTop.val + rightTop.val > k){
                TreeNode rn = RST.removeFirst();
                pushRightNodes(rn.left, RST);
                rightTop = RST.getFirst();
            }
            else if(leftTop.val + rightTop.val < k){
                TreeNode ln = LST.removeFirst();
                pushLeftNodes(ln.right, LST);
                leftTop = LST.getFirst();
            }
            else{
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(leftTop.val);
                temp.add(rightTop.val);
                myAns.add(temp);
                TreeNode rn = RST.removeFirst();
                pushRightNodes(rn.left, RST);
                rightTop = RST.getFirst();
                TreeNode ln = LST.removeFirst();
                pushLeftNodes(ln.right, LST);
                leftTop = LST.getFirst();    
            }
        }
        return myAns;
    }
    //===================================================================
    //LC 230. Kth Smallest Element in a BST
    //------- O(1) space O(N) time
    public static TreeNode rightMostNodeFn(TreeNode next, TreeNode curr) {
        while(next.right != null && next.right!=curr){
            next = next.right;
        }
        return next;
    }

    public static void kthSmallest2(TreeNode root, int k) {
        TreeNode curr = root;
        while(curr != null){
            TreeNode next = curr.left;
            if(next == null){
                //Print and move right
                if(k == 1)  return curr.val;

                k--;
                // System.out.print(curr.val + ", ");
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
                    // System.out.print(curr.val + ", ");  //REMOVE THIS LINE FOR PREORDER
                
                    if(k == 1)  return curr.val;
                    k--;
                    curr = curr.right;
                }
            }
        }
    }



    //-------- O(NLogN) space O(N) time
    public int kthSmallest(TreeNode root, int k) {
        LinkedList<TreeNode> st = new LinkedList<>();
        insertLeftMost(root, st);
        while(k-- > 1){
            TreeNode node = st.removeFirst();
            insertLeftMost(node.right, st);
        }
        return st.removeFirst().val;
    }
    public static void insertLeftMost(TreeNode root, LinkedList<TreeNode> st) {
        while(root != null){
            st.addFirst(root);
            root = root.left;
        }
    }
    
    
    //===================================================================
    //LC 1372 : Longest ZigZag Path in a Binary Tree

    public int longestZigZag(TreeNode root) {
        
    }
    //arr[0] = Going Left from the Node, arr[1] = Going Right from Node
    public static int[] longestZigZag2(TreeNode root) {
        if(root == null)    return new int[2];

        int[] lArr = longestZigZag2(root.left);
        int[] rArr = longestZigZag2(root.right);

        int[] retAns = [0,0];

        retAns[0] = lArr[1] + 1;
        retAns[1] = rArr[0] + 1;

        return retAns;
    }



    //===================================================================
    //LC 337 : House Robber III
    public static int rob_Driver(TreeNode root) {
        int ans[] = rob2(root);
        return Math.max(ans[0], ans[1]);
        
    }
    //Returns a 1x2 Array with arr[0] = maxAmount when Robbed, arr[1] =  maxAmount when not Robbed
    //Use something like Class Pair and use informative Variables in Interviews
    public static int[] rob2(TreeNode root) {
        if(root == null)    return new int[2];

        int[] lArr = rob2(root.left);
        int[] rArr = rob2(root.right);
        int[] retAns = new int[2];

        retAns[0] = lArr[1] + root.val + rArr[1];
        retAns[1] = Math.max(lArr[0], lArr[1]) + Math.max(rArr[0], rArr[1]);
        
        return retAns;
    }
    //===================================================================
    //LC 968 : Binary Tree Cameras
    public static int cameras = 0;
    public static int minCameraCover_Driver(TreeNode root) {
        cameras = 0;
        if(minCameraCover(root) == -1)  cameras++;

        return cameras;

    } 
    public static int minCameraCover(TreeNode root) {
        // -1 Camera Needed . 0 Node Covered. 1 Camera Present
        if(node == null)    return 0;

        int lans = minCameraCover(root.left);
        int rans = minCameraCover(root.right);

        if(lans == -1 || rans == -1){ // if any child needs camera
            cameras++;
            return 1;
        }

        if(lans == 1 || rans == 1){ // if any child has camera
            return 0;
        }

        return -1; //Camera Required

    } 



}