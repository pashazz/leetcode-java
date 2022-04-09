package leetcode.java.twonum;

//2. Add Two Numbers
// https://leetcode.com/problems/add-two-numbers/
public class AddTwoNumbersReversedOrder implements AddTwoNumbers {

    @Override
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode current = new ListNode();
        ListNode result = current;

        int remainder = 0;
        while (true) {
            int sum = ListNodeUtils.getValue(l1) + ListNodeUtils.getValue(l2) + remainder;
            remainder = sum / 10;
            current.val = sum % 10;
            l1 = ListNodeUtils.getNext(l1);
            l2 = ListNodeUtils.getNext(l2);
            if (l1 == null && l2 == null && remainder == 0) {
                break;
            } else {
                current.next = new ListNode();
                current = current.next;
            }
        }
        return result;
    }

}
