package leetcode.java.twonum;

import java.util.Optional;

public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ListNode)) {
            return false;
        }
        ListNode node = (ListNode)obj;
        if (val != node.val) {
            return false;
        }
        Optional<ListNode> myNext = Optional.ofNullable(next);
        Optional<ListNode> theirNext = Optional.ofNullable(node.next);
        return myNext.equals(theirNext);
    }

    @Override
    public String toString() {
        return String.format("%s -> %s", val, next);
    }
}