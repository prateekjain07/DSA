public class l001{
    public static void main(String[] args) {
        
    }

    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val){
            this.val = val;
        }
    }
    class Node {
        int val;
        Node next;
        Node random;
    
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    //===========================================================================
    //LC #138 & Pep : Copy Linked List with Random Pointers
    public static Node copyRandomList(Node head) {
        Node curr = head;
        //S1: Attaching new nodes in between the list
        while(curr != null){
            Node copyNode = new Node(curr.val);
            Node forw = curr.next;
            copyNode.next = forw;
            curr.next = copyNode;
            curr = forw;
        }

        //S2: Random Nodes Attachment
        curr = head;
        while(curr != null){
            Node nextRandom = curr.random, currCopy = curr.next;
            
            currCopy.random = (nextRandom != null) ? nextRandom.next : null;

            curr = currCopy.next;
        }

        //S3: Segregating Two Lists
        return segregateEvenOdd(head);

    }

    public static Node segregateEvenOdd(Node head) {
        //Segregates ODD EVEN LISTS and
        //Returns Even List
        Node curr = head, dummyEven = new Node(-2),
                             dummyOdd = new Node(-1);
 
        Node dEven = dummyEven, dOdd = dummyOdd;
        int idx = 1;
        while(curr != null){
            // Node nextNode = curr.next;

            if(idx % 2 == 0){
                dEven.next = curr;
                dEven = dEven.next;
            }else{
                dOdd.next = curr;
                dOdd = dOdd.next;
            }
            idx++;
            curr = curr.next;
        }

        dEven.next = dOdd.next = null;
        // dEven.next = dummyOdd.next;
        // dOdd.next = null;
                            
        // dummyOdd.next = null;
        return dummyEven.next;
    }


    //===========================================================================
    //LC #43(Converted String to LL) & Pep : Multipy Two Linked List as if the List Represents digits of a Number

    //--- CLASS CODE-----------------------------------------------------
    public static ListNode multiplyDigitWithLL(ListNode list, int digit) {
        ListNode dummy = new ListNode(-1), prev = dummy, c = list;
        int carry = 0;
        while(c!= null || carry != 0){
            int prod = carry + (c!=null ? c.val : 0) * digit;
            carry = prod/10;
            int num = prod % 10;

            prev.next = new ListNode(num);
            prev = prev.next;

            if(c!= null)    c = c.next;
        }
        return dummy.next;

    }
    public static void addTwoLL(ListNode curr, ListNode prev) {
        //here prev list is the main List we're adding lists to.
        int carry = 0;
        while(curr != null || carry != 0){
            int sum = carry + (curr != null ? curr.val : 0)
             + (prev.next != null ? prev.next.val : 0);

            carry = sum / 10;
            if(prev.next == null)   prev.next = new ListNode(0);
            prev = prev.next;
            prev.val = sum % 10;

            if(curr != null)    curr = curr.next;
        }
    }

    public static ListNode multiplyTwoLL_Class(ListNode l1, ListNode l2){
        l1 = reverseLL(l1);
        l2 = reverseLL(l2);

        ListNode dummy = new ListNode(-1), prev = dummy, l2_itr = l2;
        while(l2_itr != null){
            ListNode smallAnsList = multiplyDigitWithLL(l1, l2_itr.val);
            addTwoLL(smallAnsList, prev);

            prev = prev.next;
            l2_itr = l2_itr.next;
        }

        return reverseLL(dummy.next);

    }

    //----MY CODE-------------------------------------------------------
    public static ListNode multiplyTwoLL(ListNode l1, ListNode l2) {
        l1 = reverseLL(l1);
        l2 = reverseLL(l2);

        ListNode sumList = new ListNode(0), c1 = l1, c2 = l2;
        int digCount = 0;
        while(c2 != null){
            ListNode dummy = new ListNode(-1), dummyCopy = dummy; 
            // dummy Node to create Temp List to Add in the main sumList
            int carry = 0;
            c1 = l1;
            while(c1 != null){
                int sum = c1.val * c2.val + carry;
                carry = sum/10;
                dummy.next = new ListNode(sum%10);
                dummy = dummy.next;
                c1 = c1.next;
            }
            if(carry > 0){
                dummy.next = new ListNode(carry);
                dummy = dummy.next;
            }
            ListNode tempHead = addZeroesFront(dummyCopy.next, digCount++);
            sumList = addListWOReverse(sumList, tempHead);

            c2 = c2.next;
        }

        ListNode head = reverseLL(sumList);
        while(head.val == 0 && head.next != null){
            ListNode nextNode = head.next;
            head.next = null;
            head = nextNode;
        }  
        return head;  
    }

    public static ListNode addZeroesFront(ListNode head, int n) {
        ListNode dummy = new ListNode(-1), dummyCopy = dummy;
        while(n-- > 0){
            dummy.next = new ListNode(0);
            dummy = dummy.next;
        }
        dummy.next = head;
        return dummyCopy.next;
    }
    public static ListNode addListWOReverse(ListNode l1, ListNode l2) {
       ListNode dummy = new ListNode (-1), head = dummy;
        int carry = 0;
        while(l1 != null || l2 != null || carry != 0){
            int digit = carry;
            digit += l1 == null ? 0 : l1.val;
            digit += l2 == null ? 0 : l2.val;
            if(digit >= 10){
                digit = digit - 10;
                carry = 1;
            }
            else{
                carry = 0;
            }
            dummy.next = new ListNode(digit);
            dummy = dummy.next;
            if(l1!=null)    l1 = l1.next;
            if(l2!=null)    l2 = l2.next;
        }
           head = head.next;

         return head;
    
    }

    //===========================================================================
    //Pep : Subtract Two Linked List as if the List Represents digits of a Number

    public static ListNode subtractTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverseLL(l1);
        l2 = reverseLL(l2);

        ListNode dummy = new ListNode (-1), prev = dummy, c1 = l1, c2 = l2;
        while(c1 != null || c1 != null){
            int diff = (c1 == null ? 0 : c1.val) - (c2 == null ? 0 : c2.val);
            
           if(diff < 0){
                ListNode temp = c1.next;
                while(temp.val == 0){
                    temp.val = 9;
                    temp = temp.next;
                }
                temp.val = temp.val - 1;
                diff += 10;
            }


            prev.next = new ListNode(diff);
            prev = prev.next;
            if(c1!=null)    c1 = c1.next;
            if(c2!=null)    c2 = c2.next;
        }

        ListNode head = reverseLL(dummy.next);
        while(head.val == 0 && head.next != null){
            ListNode nextNode = head.next;
            head.next = null;
            head = nextNode;
        }  
        return head;  
    }
    
    //===========================================================================
    //-- Recursion Based : LC #445 & Pep : Add Two Linked List

    public ListNode addTwoNumbersWRecursion(ListNode l1, ListNode l2) {
        int n1 = sizeLL(l1);
        int n2 = sizeLL(l2);
        
        if(n1 > n2){
             l2 = addZeroesFront(l2, n1 - n2);
        }
        else{
             l1 = addZeroesFront(l1, n2 - n1);
        }
        
        ListNode ans = addTwoNumbers2(l1, l2);
        if(ans.val >=10){
            ans.val%=10;
            ans = new ListNode(1, ans);
        }
        return ans;
        
    }
    public static int sizeLL(ListNode head){
        int size = 0;
        while(head != null){
            size++;
            head = head.next;
        }
        return size;
    }
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null)    return null;

        ListNode retNode = addTwoNumbers2((l1 != null && l1.next != null ? l1.next : null), 
                                        (l2 != null && l2.next != null ? l2.next : null));

        int carry = 0;

        if(retNode != null && retNode.val >= 10){
            retNode.val%=10;
            carry++;
        }

        int sum = carry + (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val);

        ListNode newHead = new ListNode(sum);
        newHead.next = retNode;
        return newHead;
    }
   
    //===========================================================================
    //LC #445 & Pep : Add Two Linked List as if the List Represents digits of a Number

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverseLL(l1);
        l2 = reverseLL(l2);

        ListNode dummy = new ListNode (-1), head = dummy;
        int carry = 0;
        while(l1 != null || l2 != null || carry != 0){
            int digit = carry;
            digit += l1 == null ? 0 : l1.val;
            digit += l2 == null ? 0 : l2.val;
            if(digit >= 10){
                digit = digit - 10;
                carry = 1;
            }
            else{
                carry = 0;
            }
            dummy.next = new ListNode(digit);
            dummy = dummy.next;
            if(l1!=null)    l1 = l1.next;
            if(l2!=null)    l2 = l2.next;
        }
        // if(carry == 1){
        //     dummy.next = new ListNode(1);
        //     dummy = dummy.next;
        // }
        
        head = head.next;

        head = reverseLL(head);
        return head;
    
    }

    public static ListNode reverseLL(ListNode head) {
        if(head == null || head.next == null)
            return head;

        ListNode curr = head, prev = null;

        while(curr != null){
            ListNode forw = curr.next; //Backup

            curr.next = prev; //Link
            
            prev = curr;//Move
            curr = forw;
        }

        return prev;
    }

    //===========================================================================
    //LC #19 & Pep : Remove Nth Node from End of Linked List
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p1 = head, p2 = new ListNode(-1);
        p2.next = head;
        while(n-- > 0){
            p1 = p1.next;
        }
        while(p1 != null){
            p2 = p2.next;
            p1 = p1.next;
        }
        if(p2.next == head) head = head.next;
        p2.next = p2.next.next;

        return head;
      }
    
}