//Complete https://leetcode.com/tag/linked-list/
public class l000_quest{
    public static void main(String[] args) {
        
    }

    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val){
            this.val = val;
        }
    }
    //===========================================================================
    //LC #148 & Pep : Quick Sort in Linked List
    public static ListNode[] quickSort(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode dummySmall = new ListNode(-1), dummyLarge = new ListNode(-1); 
        //dummy Small, dummy Large
        ListNode pivotNode = midNode(head), curr = head, dummyS = dummySmall, dummyL = dummyLarge;
        // while(pivotNode.next != null)    pivotNode = pivotNode.next;
        int pivotNodeVal = pivotNode.val;
        while(curr != null){
            if(curr == pivotNode){
                //Do Nothing
            }
            else if(curr.val <= pivotNodeVal){
                dummyS.next = curr;
                dummyS = dummyS.next;
            }
            else{
                dummyL.next = curr;
                dummyL = dummyL.next;    
            }
            curr = curr.next;
        }
        dummyL.next = dummyS.next = null; 
        //Make it a habit to terminate the tails even though it seems redundant
        //This Code gives three Lists, small List, pivotNode, Large List


        ListNode[] leftAns = quickSort(dummySmall.next);
        ListNode[] rightAns = quickSort(dummyLarge.next);

        dummySmall.next = leftAns[0];
        dummyLarge.next = rightAns[0];

        // ListNode dummySTail = getTail(dummySmall.next);
        if(leftAns[1] == null)
            dummySmall.next = pivotNode;
        else
            dummySTail.next = pivotNode;

        pivotNode.next = dummyLarge.next;

        return new ListNode[]{dummySmall.next, dummyL};
    }

    
    public static ListNode quickSort(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode dummySmall = new ListNode(-1), dummyLarge = new ListNode(-1); 
        //dummy Small, dummy Large
        ListNode pivotNode = midNode(head), curr = head, dummyS = dummySmall, dummyL = dummyLarge;
        // while(pivotNode.next != null)    pivotNode = pivotNode.next;
        int pivotNodeVal = pivotNode.val;
        while(curr != null){
            if(curr == pivotNode){
                //Do Nothing
            }
            else if(curr.val <= pivotNodeVal){
                dummyS.next = curr;
                dummyS = dummyS.next;
            }
            else{
                dummyL.next = curr;
                dummyL = dummyL.next;    
            }
            curr = curr.next;
        }
        dummyL.next = dummyS.next = null; 
        //Make it a habit to terminate the tails even though it seems redundant
        //This Code gives three Lists, small List, pivotNode, Large List

        dummySmall.next = quickSort(dummySmall.next);
        dummyLarge.next = quickSort(dummyLarge.next);

        ListNode dummySTail = getTail(dummySmall.next);
        if(dummySTail == null)
            dummySmall.next = pivotNode;
        else
            dummySTail.next = pivotNode;

        pivotNode.next = dummyLarge.next;

        return dummySmall.next;
    }

    //===========================================================================
    //Pep : Segregate Nodes of a Linked over Pivot Index

    public static ListNode segregate(ListNode head, int pivotIdx) {
        ListNode dummySmall = new ListNode(-1), dummyLarge = new ListNode(-1); 
        //dummy Small, dummy Large
        ListNode pivotNode = head, curr = head, dummyS = dummySmall, dummyL = dummyLarge;
        while(pivotNode.next != null && pivotIdx-- > 0)    pivotNode = pivotNode.next;
        int pivotNodeVal = pivotNode.val;
        while(curr != null){
            if(curr == pivotNode){
                //Do Nothing
            }
            else if(curr.val <= pivotNodeVal){
                dummyS.next = curr;
                dummyS = dummyS.next;
            }
            else{
                dummyL.next = curr;
                dummyL = dummyL.next;    
            }
            curr = curr.next;
        }
        dummyL.next = dummyS.next = null; 
        //Make it a habit to terminate the tails even though it seems redundant

        //This Code gives three Lists, small List, pivotNode, Large List

        dummyS.next = pivotNode;
        pivotNode.next = dummyLarge.next;

        return dummySmall.next;
      }
    
    //===========================================================================
    //Pep : Segregate Nodes of a Linked over Last Index
    public static ListNode segregateOnLastIndex(ListNode head) {
        ListNode dummySmall = new ListNode(-1), dummyLarge = new ListNode(-1); 
        //dummy Small, dummy Large
        ListNode lastNode = head, curr = head, dummyS = dummySmall, dummyL = dummyLarge;
        while(lastNode.next != null)    lastNode = lastNode.next;
        int lastNodeVal = lastNode.val;
        while(curr != null){
            if(curr.val <= lastNodeVal){
                dummyS.next = curr;
                dummyS = dummyS.next;
            }
            else{
                dummyL.next = curr;
                dummyL = dummyL.next;    
            }
            curr = curr.next;
        }
        dummyL.next = dummyS.next = null; 
        //Make it a habit to terminate the tails even though it seems redundant

        dummyS.next = dummyLarge.next;

        return dummyS;
    }
    
    
    //===========================================================================
    //Pep : Segregate 012 Node of a Linked List Over Swapping Nodes

    public static ListNode segregate012(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode curr = head, dummyEven = new ListNode(0),
        dummyOdd = new ListNode(-1), dummy2 = new ListNode(-2);

        ListNode dEven = dummyEven, dOdd = dummyOdd, d2 = dummy2;

        while(curr != null){
        // ListNode nextNode = curr.next;

            if(curr.val == 0){
                dEven.next = curr;
                dEven = dEven.next;
            }
            else if(curr.val == 1){
                dOdd.next = curr;
                dOdd = dOdd.next;
            }
            else{
                d2.next = curr;
                d2 = d2.next;
            }

            curr = curr.next;
        }

        //Special Case :  2 - 2 - 2 - null
        //Connections need to be handled better in this code
        dOdd.next = dummy2.next; // LL 1 -> LL2
        dEven.next = dummyOdd.next; // LL 0 -> LL1
        d2.next = null;
        //In general, join the list in the reverse order that is
        // LL n - 1 -> LL n
        // LL n - 2 -> LL n - 1
        // LL n - 3 -> LL n - 2
        // -----
        // LL 1 -> LL 2
        // LL 0 -> LL 1

                
        dummyOdd.next = null;
        dummy2.next = null;
        return dummyEven.next;

    }
    //===========================================================================
    //Pep : Segregate 01 Node of a Linked List Over Swapping Nodes

    public static ListNode segregate01(ListNode head) {
        if (head == null || head.next == null)
            return head;
        //Same Code of Segregating Odd Even Nodes work here
        ListNode curr = head, dummyEven = new ListNode(-2),
        dummyOdd = new ListNode(-1);

        ListNode dEven = dummyEven, dOdd = dummyOdd;

        while(curr != null){
        // ListNode nextNode = curr.next;

            if(curr.val % 2 == 0){
                dEven.next = curr;
                dEven = dEven.next;
            }else{
                dOdd.next = curr;
                dOdd = dOdd.next;
            }

            curr = curr.next;
        }

        dEven.next = dummyOdd.next;
        dOdd.next = null;
                
        dummyOdd.next = null;
        return dummyEven.next;

    }

    //===========================================================================
    //LC #92 & Pep : Reverse Nodes of a Linked List in a Given Range

    //-- Actual Class Code
    public static ListNode reverseInRange(ListNode head, int n, int m) {
        if (head == null || head.next == null || m == n)
            return head;

        ListNode curr = head;
        ListNode prev = null;

        int idx = 1;
        while (idx < m) {
            while (idx >= n && idx <= m) {
                ListNode forw = curr.next;
                curr.next = null;

                addFirst(curr);

                curr = forw;
                idx++;
            }

            // if (idx > n) {
            if (idx > m) {  //Logically, this makes more sense
                    tt.next = curr;
                if (prev != null) {
                    prev.next = th;
                    return head;
                }

                return th;
            }
            idx++;
            prev = curr;
            curr = curr.next;
        }

        return head;
    }

    //-- Class Approach
    public static ListNode reverseInRange(ListNode head, int n, int m) {
        if(head == null || head.next == null || n == m)
            return head;
        n--;    //Hack since the code was working for index n+1 to m
        tH = tT = null;
        ListNode oH = null, oT = null; //originalHead, originalTail
        ListNode curr = head, forw = null, prev = null, newHead = null;
        int idx = 0;
        while(idx < n){
            idx++;
            prev = curr; 
            curr = curr.next;
        }
        idx++;
        while(idx>= n && idx<=m){
            forw = curr.next;
            curr.next = null;
            addFirstNode(curr);
            curr = forw;
            idx++;
        }
        if(prev != null){
            prev.next = tH;
            newHead = head;
        }
        else{
            newHead = tH;
        }
        tT.next = forw; 
                
        return newHead;

    }

    // -- my Code : find the suitable pointers and reverse LL from n to m
    public static ListNode reverseInRange_00(ListNode head, int n, int m) {
        if(head == null || head.next == null || n == m)
            return head;
        
        int idx = 0;
        ListNode curr = head, tH = null, tT = null, tHP = null, newHead = null;
        while(curr != null){
            idx++;
            if(idx == n)    tH = curr;
            if(idx == n - 1)    tHP = curr;
            if(idx == m)    tT = curr;
            curr = curr.next;
        }

        ListNode tTN = tT.next, tHC = tH;
        tT.next = null;
        tH = reverseLL(tH);
        tHC.next = tTN;

        if(tHP == null){
            newHead = tH;
        }
        else{
            newHead = head;
            tHP.next = tH;
        }

        return newHead;
    }

    
    
    //===========================================================================
    //LC #25 & Pep : Reverse Nodes of a Linked List in K Group
    
    public static int lengthOfList(ListNode head) {
        int len = 0;
        ListNode curr = head;
        while(curr != null){
            curr = curr.next;
            len++;
        }
        return len;
    }

    static  ListNode tH = null, tT = null; //temporaryHead, temporaryTail

    public static void addFirstNode(ListNode node) {
        if(tH == null){
            tH = tT = node;
        }
        else{
            node.next = tH;
            tH = node;
        }
    }
    
    public static ListNode reverseInKGroup(ListNode head, int k) {
        if(head == null || head.next == null || k <= 1)
            return head;
            
        tH = tT = null;
        ListNode oH = null, oT = null; //originalHead, originalTail
        int len = lengthOfList(head);
        ListNode curr = head, forw = null;
        while(curr != null && len >= k){
            int tempK = k;
            while(curr != null && tempK-->0){
                forw = curr.next;
                curr.next = null;
                addFirstNode(curr);
                curr = forw;
            }

            if(oH == null){
                oH = tH;
                oT = tT;
            }else{
                oT.next = tH;
                oT = tT;
            }

            tH = tT = null;
            len -= k;

        }
        oT.next = curr;

        return oH;
    }

    //===========================================================================
    //Pep : Segregate Even and Odd Nodes in a Linked List
    public static ListNode segregateEvenOdd(ListNode head) {
        ListNode curr = head, dummyEven = new ListNode(-2),
                             dummyOdd = new ListNode(-1);
 
        ListNode dEven = dummyEven, dOdd = dummyOdd;
        
        while(curr != null){
            // ListNode nextNode = curr.next;

            if(curr.val % 2 == 0){
                dEven.next = curr;
                dEven = dEven.next;
            }else{
                dOdd.next = curr;
                dOdd = dOdd.next;
            }

            curr = curr.next;
        }

        dEven.next = dummyOdd.next;
        dOdd.next = null;
                            
        dummyOdd.next = null;
        return dummyEven.next;
    }

    //===========================================================================
    //LC #23 & Pep : Merge K Sorted Linked Lists
    //-- Best Way -- Another Method : Divide & Conquer Way 
    public static ListNode mergeKLists4(ListNode[] lists) {
        if(lists.length == 0)   return null;
        return mergeKLists4_Code(lists, 0, lists.length - 1);
    }
    public static ListNode mergeKLists4_Code(ListNode[] lists, int si, int ei) {
        if(si == ei)    return lists[si];

        int mid = (si + ei)/2;

        return mergeTwoLists(mergeKLists4_Code(lists, si, mid), 
                            mergeKLists4_Code(lists, mid + 1, ei));
    }

    //-- Another Brute Force : Merge All the Lists head to tail and sort it
    public static ListNode mergeKLists3(ListNode[] lists) {
        ListNode dummy = new ListNode(-1), head = null;
        ListNode prev = getTail(dummy);
        for(ListNode listHead : lists){
            if(listHead!= null){
                prev.next = listHead;
                continue;
            }
            prev = getTail(listHead);
        }
        prev.next = null;
        head = dummy.next;
        dummy.next = null;
        return mergeSort(head);

    }
    public static ListNode getTail(ListNode head) {
        if(head == null)    return head;
        ListNode curr = head;
        while(curr.next!=null)  curr = curr.next;
        return curr;
    }

    //-- Another Brute Force : Take Two Lists and Keep on Combining Them
    public static ListNode mergeKLists2(ListNode[] lists) {
        ListNode res = null;
        for(ListNode head : lists){
            res = mergeTwoLists(res, head);
        }
        return res;    
    }
    

    //-- MyCode : Brute Force in which we go over all the List Heads together
    public static ListNode mergeKLists(ListNode[] lists) {
        ListNode head = null, dummy = new ListNode(-1);        
        ListNode curr = dummy;
       boolean flag = true;
        while(flag){
            flag = false;
            int nIdx = 0;
            while(nIdx != lists.length && lists[nIdx] == null)  nIdx++;
            if(nIdx == lists.length){
                curr.next = null;
                break;
            }
            
            for(int i=1; i<lists.length; i++){
                if(lists[i] != null){
                    flag = true;
                    if(lists[i].val < lists[nIdx].val)
                        nIdx = i;
                }
            }

            curr.next = lists[nIdx];
            lists[nIdx] = lists[nIdx].next;
            curr = curr.next;
        }
        
        head = dummy.next;
        dummy.next = null;
        return head;
    }

    //=======================================================================
    //Pep : Merge Sort a Linked List
    public static ListNode mergeSort(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }        
        ListNode mid = midNode(head);
        ListNode newHead = mid.next;
        mid.next = null;

        return mergeTwoLists(mergeSort(head), mergeSort(newHead));
    }
    //=======================================================================
    //LC #21 & Pep : Merge Two Sorted LL
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null)  return l2;
        if(l2 == null)  return l1;
        // ListNode head = null;
        // if(l1.val < l2.val){
        //     head = l1;
        //     l1 = l1.next;
        // }
        // else{
        //     head = l2;
        //     l2 = l2.next;    
        // }
        //Making it similar to class code
        ListNode head = null, dummy = new ListNode(-1);        
        ListNode curr = dummy;
        // ListNode curr = head;
        while(l1 != null && l2 != null){ 
            if(l1.val < l2.val){
                curr.next = l1;
                l1 = l1.next;
            }
            else{
                curr.next = l2;
                l2 = l2.next;    
            }
            curr = curr.next;
        }

        if(l1 != null){
            curr.next = l1;
        }
        else if(l2 != null){
            curr.next = l2;
        }

        //Class Code Compliant
        head = dummy.next;
        dummy.next = null;
        return head;

    }

    //======================================================================
    //Pep : Unfold of a LL
    public static void unfold(ListNode head) {
        if(head == null || head.next == null){
            return;
        }

        ListNode c1 = head, c2 = head.next, newHead = head.next;    
        while(c1.next != null && c2.next != null){
            ListNode f1 = c1.next.next, f2 = c2.next.next;

            c1.next = f1;
            c2.next = f2;

            c1 = f1;
            c2 = f2;
        }
        if(c2 != null)
            c2.next = null;
        c1.next = reverseLL(newHead);
    }

    //======================================================================
    //Pep : Fold of a LL
    //CLASS CODE
    public static void foldClassCode(ListNode head) {
        if(head == null || head.next == null){
            return;
        }

        ListNode mid = midNode(head);
        ListNode newHead = mid.next;
        mid.next = null;
        newHead = reverseLL(newHead);

        ListNode c1 = head, c2 = newHead;
        while(c1 != null && c2 != null){
            ListNode f1 = c1.next, f2 = c2.next;

            c1.next = c2;
            c2.next = f1;

            c1 = f1;
            c2 = f2;
        }

        newHead = reverseLL(newHead);
        mid.next = newHead;

    }




    //---MY CODE
    public static void fold(ListNode head) {
        if(head == null || head.next == null){
            return;
        }

        ListNode fast = head, slow = head;

        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode mid1 = slow, mid2 = fast.next == null ? slow : slow.next;

        ListNode head2 = reverseLL(mid2);
        mid1.next = null;
        mid2.next = null;
        
        ListNode retHead = head;
        while(head != null && head2 != null){
            ListNode n1 = head, n2 = head2;
            head = head.next;
            head2 = head2.next;
            n1.next = n2;
            n2.next = head;
        }
        head = retHead;
    }

    //======================================================================
    //LC #234 & Pep : is LL a Palindrome
    //Class Code
    public static boolean isPalindromeClassCode(ListNode head) {
        if(head == null || head.next == null){
            return true;
        }

        ListNode mid = midNode(head);
        ListNode newHead = mid.next;
        mid.next = null;
        newHead = reverseLL(newHead);

        boolean flag = true;
        ListNode c1 = head, c2 = newHead;
        while(c1 != null && c2 != null){
            if(c1.val != c2.val){
                flag = false;
                break;
            }
            c1 = c1.next;
            c2 = c2.next;
        }

        newHead = reverseLL(newHead);
        mid.next = newHead;

        return flag;
    }


    //----- MY CODE
    public static boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null){
            return true;
        }

        ListNode fast = head, slow = head;

        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode mid1 = slow, mid2 = fast.next == null ? slow : slow.next;

        ListNode head2 = reverseLL(mid2);
        mid1.next = null;
        mid2.next = null;
        while(head != null && head2 != null){
            if(head.val != head2.val)   return false;
            head = head.next;
            head2 = head2.next;
        }
        
        return true;
    }


    //======================================================================
    //LC #206 & Pep : Reverse a LL
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

    //======================================================================
    //LC #876 & Pep : Middle Node in LL
    public static ListNode midNode(ListNode head) {
        //Finds lower Mid of the list, 
        // for upper mid, while(fast!=null && fast.next != null)
        if(head == null || head.next == null)
            return head;

        ListNode slow = head, fast = head;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}