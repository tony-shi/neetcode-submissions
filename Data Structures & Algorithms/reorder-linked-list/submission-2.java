class Solution {

    


    public void reorderList(ListNode head) {

        
        // 奇数？偶数？
        // 先考虑奇数。


        // 把n/2个元素，放到末尾。
        // 从 0123456 -> 0123654 ，这里的话也可以变成 456 321 要这样吗？
        // 0->6->1->5->2->4->3 这样就解了。OK了。


        // 先统计个数，之后走N/2个。
        ListNode current = head;
        int count = 0;
        while(current!=null){
            count++;
            current = current.next;
        }

        if(count==1){
            return;
        }
        // 一共有几个count？
        // 6个的话，走（n-1）/2 步，也就是2步。
        // 7个话，走
        ListNode revertHead = head;
        for(int i =0;i<(count-1)/2;i++){
            revertHead = revertHead.next;
        }


        // 现在，开始revert这个节点。
        revert(revertHead);


        // 6个的话，是012543，停在了2上。  新的值是，0->5->1->4->2->3
        // 7个的话，是0123654，停在了3上。 新的值是  0->6->1->5->2->4->3

        // 我们从head以及这个revert head.next开始，串接。
        // 如果是偶数，那就是走 n/2 3次就结束了。
        // 如果是奇数，那么就是走 n/2，但是这个时候，head要多走一次。


        ListNode dummyHead = new ListNode();
        ListNode preEnd = dummyHead;
        current = head;
        revertHead = revertHead.next;
        for(int i = 0;i<count/2;i++){
            preEnd.next = current;
            // 暂存一下。
            ListNode headNext = current.next;
            current.next = revertHead;

            preEnd = revertHead;


            current = headNext;
            revertHead = revertHead.next;
        }


        // OK，走完了。
        if(count % 2 ==0){
            // 偶数。
            preEnd.next =null;
        }else{
            // 奇数。
            preEnd.next = current;
            current.next =null;
        }
    }

    public void revert(ListNode head){

        // head(pre) -> a(current) -> b -> c -> null
        // head(pre) -> a(current) -> b (next)-> c -> null
        // head -> b (pre) -> a (current)-> c -> null


        // 最开始的逻辑错误了。
        // 是插入到头。理解错了！


        // head(pre) ----------->  a(current) -> b(next) -------> null

        // pre -> current -> next -> tail

        // 1. pre.next = next;
        // 2. next.next = current;
        // 3. current.next = next.next;

        // 

        // head -> b -> c -> a(current) -> null


        // head不去边，把current后面的元素，和current进行交换？
        // 把head之后的元素，对应的列表进行翻转。
        ListNode current = head.next;
        ListNode next = current.next;
        while(next!=null){
            current.next = next.next;
            next.next = head.next;
            head.next = next;
            next = current.next;
        }

        while(head!=null){
            System.out.print(head.val);
            head = head.next;
        }
        System.out.println();
    }
}

