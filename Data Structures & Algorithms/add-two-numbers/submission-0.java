/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode();
        ListNode resultPre = dummyHead;
        while(l1!=null || l2!=null){
            ListNode newNode = new ListNode();
            int val = (l1!=null? l1.val:0) + (l2!=null? l2.val : 0) + resultPre.val / 10;
            newNode.val = val;
            resultPre.val = resultPre.val % 10;
            l1 = l1!=null?l1.next:null;
            l2 = l2!=null?l2.next:null;
            resultPre.next = newNode;
            resultPre = newNode;
        }
        // 到了最后一个节点了。l1==null && l2==null
        // 计算一下
        if(resultPre.val >= 10){
            ListNode newNode = new ListNode();
            newNode.val = resultPre.val / 10;
            resultPre.val = resultPre.val % 10;
            resultPre.next = newNode;
        }

        return dummyHead.next;
    }
}
