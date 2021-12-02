/* weird test cases to handle
1. ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]

2.["LRUCache","put","get","put","get","get"]
[[1],[2,1],[2],[3,2],[2],[3]]

3. ["LRUCache","put","put","put","put","get","get","get","get","put","get","get","get","get","get"]
[[3],[1,1],[2,2],[3,3],[4,4],[4],[3],[2],[1],[5,5],[1],[2],[3],[4],[5]]

*/

public class LRUCache_Class {
    //To make this code more time efficient, an Array of LNodes can
    //be used with the size of key range + 1 ie. here, it's 10^4 + 1
    //Only in this code where key is the unique identifier
    //data members
    private class ListNode {
        Integer key, value;
        ListNode prev = null, next = null;

        ListNode(Integer key, Integer value){
            this.key = key;
            this.value = value;
        }
    }
    private HashMap <Integer, ListNode> map;
    // private ListNode[] map;
    private ListNode head = null, tail = null;
    private int capacity=0;
    private int size=0;

    private void initialize(int capacity){
        this.capacity = capacity;
        this.map = new HashMap<>();
        //this.map = new ListNode[(int)1e4 + 1];
        this.size = 0;
        this.head = this.tail = null;
    }
    public LRUCache_Class(int capacity){
        initialize(capacity);
    }
    

    private void makeRecent(ListNode node) {
        if(this.tail == node) //Writing this as a shortcut but will
            return;           //still handle this case in the functions
        removeNode(node);
        addLast(node);
    }

    private ListNode removeFirst() {
        ListNode node = this.head;
        if(this.head == this.tail){
            this.head = this.tail = null;
        }
        else{
            this.head = node.next;
            node.next = this.head.prev = null;
            //A little confusing but here, head means the 2nd node    
        }
        this.size--;
        return node;
    }

    private ListNode removeLast() {
        //Seems redundant with the condition in makeRecent
        //this is to cover our bases
        ListNode node = this.tail;
        if(this.head == this.tail){
            this.head = this.tail = null;
        }
        else{
            this.tail = node.prev;
            this.tail.next = node.prev = null;
            //A little confusing but here, tail means the 2nd last node    
        }
        this.size--;
        return node;
    }

    private ListNode removeNode(ListNode node) {
        if(node == this.head)
            return removeFirst();
        else if(node == this.tail)
            return removeLast();
        else{
            ListNode prev = node.prev, forw = node.next;
            prev.next = forw;
            forw.prev = prev;

            node.next = node.prev = null;
            this.size--;
            return node;
        }

    }

    private void addLast(ListNode node) {
        if(this.head == null)
            this.head = this.tail = node;
        else{
            node.prev = this.tail;
            this.tail.next = node;
            this.tail = node;
        }
        this.size++;
    }

    public int get(int key) {
        if(!map.containsKey(key))   return -1;
        // if(map[key] == null)   return -1;

        ListNode node = map.get(key);
        // ListNode node = map[key];
        makeRecent(node);

        return node.value;
    }

    public void put(int key, int value) {
        // if(map[key] != null){
        if(map.containsKey(key)){
            ListNode node = map.get(key);
           // ListNode node = map[key];
            node.value = value;
            makeRecent(node);
        }else{
            ListNode node = new ListNode(key, value);
            if(this.size == this.capacity){
                //Order here is important if fns are void
                this.map.remove(head.key);
                // this.map[head.key] = null;
                removeFirst();

                //For fns with return type
                // ListNode rn = removeFirst();
                // this.map.remove(rn.key);
            }

            addLast(node);
            map.put(key, node);
            // map[key] = node;
        }
    }
    
}


//============= MY CODE ==========================================
public class LRUCache{
    //data members
    private class ListNode {
        Integer key, value;
        ListNode prev = null, next = null;

        ListNode(Integer key, Integer value){
            this.key = key;
            this.value = value;
        }
    }

    private HashMap <Integer, ListNode> map;
    private ListNode head = null, tail = null;
    private int capacity=0;
    private int size=0;

    private void makeRecent(ListNode node) {
        removeNode(node);
        addLast(node);
    }
    private void removeNode(ListNode node) {
        if(this.map.containsKey(node.key)){
            if(node == head){
                if(this.size == 1){
                    head = tail = null;
                }
                else{
                    ListNode headToRemove = head;
                    head = head.next;
                    head.prev = null;    
                    headToRemove.next = null;
                }
            }
            else if(node == tail){
                ListNode tailToRemove = tail;
                tail = tail.prev;
                tail.next = null;    
                tailToRemove.prev = null;
            }
            else{
                ListNode prevNode = node.prev;
                ListNode nextNode = node.next;

                prevNode.next = nextNode;
                nextNode.prev = prevNode;
            }
            this.size--;
            this.map.remove(node.key);
        }
        else if(this.size == this.capacity){
            ListNode headToRemove = head;
            if(this.capacity == 1){
                head = tail = null;

            }
            else{
                head = head.next;
                head.prev = null;
    
                headToRemove.prev = headToRemove.next = null;    
            }
            this.size--;
            this.map.remove(headToRemove.key);
        }
        node.prev = node.next = null;                

    }
    private void addLast(ListNode node) {
        if(head == null){
            head = tail = node;
        }
        else{
            tail.next = node;
            node.prev = tail;
            tail = tail.next;    
        }
        this.size++;
        this.map.put(node.key, node);
    }

    public LRUCache(int capacity){
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.size = 0;
    }

    public int get(int key) {
        if(!map.containsKey(key))   return -1;

        ListNode node = map.get(key);
        makeRecent(node);

        return node.value;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            ListNode node = map.get(key);
            node.value = value;
            makeRecent(node);
        }else{
            ListNode node = new ListNode(key, value);
            // if(this.size == this.capacity){
            //     removeNode(head);
            // }

            // addLast(node);
            makeRecent(node);
        }
    }
}