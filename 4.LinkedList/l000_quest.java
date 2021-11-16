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

    //=======================================================================
    //LC #21 & Pep : Merge Two Sorted LL
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null)  return l2;
        if(l2 == null)  return l1;
        ListNode head = null;
        if(l1.val < l2.val){
            head = l1;
            l1 = l1.next;
        }
        else{
            head = l2;
            l2 = l2.next;    
        }
        ListNode curr = head;
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