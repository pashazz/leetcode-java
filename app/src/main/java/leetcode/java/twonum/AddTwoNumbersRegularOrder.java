package leetcode.java.twonum;


public class AddTwoNumbersRegularOrder implements AddTwoNumbers {
    AddTwoNumbersReversedOrder engine = new AddTwoNumbersReversedOrder();
    @Override
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return reverseList(
                        engine.addTwoNumbers(reverseList(l1), reverseList(l2)));
    }

    public ListNode reverseList(ListNode list) {
        if (list.next == null) {
            return list;
        }
        ListNode previous = null;
        ListNode current = list;
        while (true) { //4 -> 3 -> 2 -> 1
            // 1. 4 -> null | 3 -> 2 -> 1
            if (current == null) {
                return previous;
            }
            var next = current.next;

            current.next = previous;

            previous = current;
            current = next;
        }
    }

}
