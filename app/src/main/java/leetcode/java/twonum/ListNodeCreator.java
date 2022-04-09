package leetcode.java.twonum;

import java.util.ArrayList;
import java.util.List;

public class ListNodeCreator {
    public static ListNode createFromNumber(int number) {
        List<Integer> digits = new ArrayList<>();
        while(true) {
            digits.add(number % 10);
            if (number < 10) {
                break;
            }
            number /= 10;
        }
        return createFromReversedList(digits);
    }

    private static ListNode createFromReversedList(List<Integer> digits) {
        ListNode result = new ListNode();
        ListNode current = result;
        for (int i = 0; i < digits.size(); ++i) {
            current.val = digits.get(i);
            if (i < digits.size() - 1) {
                current.next = new ListNode();
                current = current.next;
            }
        }
        return result;
    }

    public static int createFromNode(ListNode node) {
        int result = 0;
        int multiplier = 1;
        while (node != null) {
            result += node.val * multiplier;
            multiplier *= 10;
            node = node.next;
        }
        return result;
    }
}
