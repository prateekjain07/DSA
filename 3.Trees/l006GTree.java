import java.util.ArrayList;
public class l006GTree{

    public static class Node {
        int val = 0;
        ArrayList<Node> childs;

        Node(int data){
            this.val = data;
            this.childs = new ArrayList<Node>();
        }
    }

    public static void main(String[] args) {
        
    }

    public static int size(Node node) {
        if(node == null)    return 0; 
        //Handling the case if someone adds Null to childs, else no base case needed
        
        int sz = 0;
        for(Node child : node.childs){
            sz += size(child);
        }

        return sz + 1;
    }

    public static int height(Node node) { // wrt Edges
        int h = -1;
        for(Node child : node.childs){
            h = Math.max(h, height(child));
        }

        return h + 1;
    }
    public static int maximum(Node node) {
        int maxEle = node.val;
        for(Node child : node.childs){
            maxEle = Math.max(maxEle, maximum(child));
        }

        return maxEle;
    }
    public static int minimum(Node node) {
        int minEle = node.val;
        for(Node child : node.childs){
            minEle = Math.min(minEle, minimum(child));
        }

        return minEle;
    }
    public static boolean find(Node node, int data) {
        if(node.val == data)    return true;

        for(Node child : node.childs){
            if(find(child, data))
                return true;
        }

        return false;
    }

    public static boolean rootToNodePath(Node root, int data, ArrayList<Node> ans){
        if(root.val == data){
            ans.add(root);
            return true;
        }
        boolean res = false;
        for(Node child : root.childs){
            res = res || rootToNodePath(child, data, ans);
        }

        if(res)     ans.add(root);
        
        return res;
    }
    public static ArrayList<Node> rootToNodePathDriver(Node root, int data) {
        ArrayList<Node> ans = new ArrayList<>();
        rootToNodePath(root, data, ans);
        return ans;
    }

    //Lowest Common Ancestor
    public static Node LCA(Node node, int d1, int d2) {
        ArrayList ans1 = rootToNodePathDriver(node, d1);
        ArrayList ans2 = rootToNodePathDriver(node, d2);

        Node lca = null;
        for(int i = ans1.size()-1, j = ans2.size()-1; i>=0 && j>=0; i--, j--){
            if(ans1.get(i) != ans2.get(j))  break;

            lca = ans1.get(i);
        }
        return lca;
    }

    //=======================================================================
    //Ques : Burning Tree in a Generic Tree

    public static void KDown(Node root, Node blockNode, int time, ArrayList<ArrayList<Integer>> ans) {
        if(root == blockNode)   return;

        if(ans.size() == time)  ans.add(new ArrayList<>());
        ans.get(time).add(root.val);
        
        for(Node child : root.childs){
            KDown(child, blockNode, time + 1, ans);
        }

    }
    public static ArrayList<ArrayList<Integer>> burningTree_01(Node root, int target, int K) {
        ArrayList<Node> list = new ArrayList<>();
        rootToNodePath(root, target, list);

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

        Node blockNode = null;

        for(int i=0; i< list.size(); i++){
            KDown(list.get(i), blockNode, i, ans);
            blockNode = list.get(i);
        }
        return ans;
    }

    //2nd Way
     public static int burningTree_02(Node root, int target, ArrayList<ArrayList<Integer>> ans) {
        if(root.val == target){
            KDown(root, null, 0, ans);
            return 1;
        }

        int time = -1;
        Node blockNode = null;

        for(Node child : root.childs){
            time = burningTree_02(child, target, ans);
            if(time != -1){
                blockNode = child;
                break;
            }
        }

        if(time!=-1){
            KDown(root, blockNode, time, ans);
            time++;
        }
        return time;
    }
 
    //=============================================================================
    // Flatten Generic Tree to a LinkedList in PreOrder
    public static Node flattenGT(Node node) {
        int n = node.childs.size();
        Node lchild = node.childs.get(n-1);
        Node gTail = flattenGT(lchild);

        for(int i = n-2; i>=0; i--){
            Node tempTail = flattenGT(node.childs.get(i));
            tempTail.childs.add(node.childs.get(i+1));
            node.childs.remove(i+1);
        }

        return gTail;
        
    }


    //=============================================================================
    // Mirror Image Check Question
    public static boolean isMirror(Node node1, Node node2) {
        if(node1.childs.size() != node2.childs.size())  return false;

        if(node1.val != node2.val) //Remove this check for Isomorphic Trees
            return false;

        for(int i = 0, j = node2.childs.size() - 1; j>=0 ; i++, j--){
            Node child1 = node1.childs.get(i);
            Node child2 = node2.childs.get(j);

            if(!isMirror(child1, child2)){
                return false;
            }
        }

        return true;
    }


    //=============================================================================
    //Generic Tree : Level Order
    public static void genericLevelOrder(Node root) {
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(root);
        int level = 0;
        while(que.size()!=0){
            int sz = que.size();
            System.out.print("Level " + level + " :-  ");
            while(sz-- > 0){
                Node rn = que.removeFirst();
                System.out.print(rn.val +  ", ");
                
                for(Node child : rn.childs){
                    que.addLast(child);
                }
            }
            level++;
            System.out.println();
        }
        
    }

}