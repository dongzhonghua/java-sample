package xyz.dsvshx.leetcode;

/**
 * @author dongzhonghua
 * Created on 2021-06-24
 */
public class RemoveNthFromEnd {
    public static void main(String[] args) {

    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 快慢指针，最容易出错的是需要新建一个节点，指向头结点，因为存在删除头结点的情况。
        ListNode first = new ListNode(0);
        first.next = head;
        ListNode t = first;
        ListNode cur = first;
        for (int i = 0; i < n; i++) {
            t = t.next;
        }
        while (t.next != null) {
            cur = cur.next;
            t = t.next;
        }
        cur.next = cur.next.next;
        return first.next;
    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
