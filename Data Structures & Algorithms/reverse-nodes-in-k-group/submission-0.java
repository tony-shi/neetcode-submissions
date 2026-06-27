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

    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode tmp = head;
        int count = 0;

        
        while(tmp!=null){
            count++;
            tmp = tmp.next;
        }


        // 有这么多个。
        // 我们过几次这样的逻辑呢？

        ListNode dummyHead = new ListNode();
        dummyHead.next = head;


        ListNode pre = dummyHead;
        for(int i = 0;i< (count/k);i++){
            // 逻辑上应该就是count/k次吧。
            pre = revertK(pre,k);
        }

        // OK，翻转ready，现在可以就是说返回了。
        return dummyHead.next;
        
    }


    // 采用头插法解决。
    // 不用考虑就是说插到不够K个。我们先跑一遍避免计数，代码更简单。

    // pre ->   .... current -> currentNext -> ...
    // 把这个current的下面的一个，插入到pre，然后的话，就是说current指向原来的下一个。
    // 逻辑上，翻转K个元素，似乎只用k-1次操作？

    public ListNode revertK(ListNode pre,int k){

        ListNode current =  pre.next;
        for(int i = 0;i<(k-1);i++){
            // 暂存一下。
            ListNode next = current.next.next;
            ListNode elementToInject  = current.next;
            ListNode oldHead = pre.next;

            // 真正的交换。把currentNext插入到pre。
            pre.next = elementToInject;
            elementToInject.next = oldHead;
            // current指向下一个。

            current.next = next;
        }
        // 结尾似乎是下一个的起点了。思考一下，看上去没问题。
        return current;
    }





}
