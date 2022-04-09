package leetcode.java.twonum;

public class ListNodeUtils {
    static int getValue(ListNode ln) {
        if (ln == null) {
            return 0;
        } else {
            return ln.val;
        }
    }

    static ListNode getNext(ListNode prev) {
        if (prev == null) {
            return null;
        }
        return prev.next;
    }
}
