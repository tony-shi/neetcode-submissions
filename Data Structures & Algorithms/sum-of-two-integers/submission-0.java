class Solution {
    public int getSum(int a, int b) {
        


        //  获取sum，但是不可以用+/-
        // 先进性一个测试。
        //  逻辑上，我认为 a+b 的话，似乎就是
        // 按位进行处理吗？
        // 例如



        // 0100 = 4
        // 0001 = 1
        // 0101 = 5


        // 相当于什么呢？按位异或吗？
        // 但是没有进位处理。这个不行吧？
        // 以及进位的话，怎么处理呢？
        // 思考一下。


        // 逻辑上，如果是1，1，此时会产生进位。
        // 进位只有，变成了向前有一个多的1了。
        // 这个如何实现呢？
        // 逻辑上，每次只需要处理三个元素，a，b，以及addon？
        // 逻辑上，异或实际上就是一个判断了。感觉很简单？都不需要这个算法实际上。


        // 实际上，要看这个位移动作的逻辑？
        // 位移到底移动的是什么？补码补的是什么？


        // 000001  1
        // 111110
        // 111111 -1
        // 000000
        // 我们认为，应该移动的是1？
        // System.out.println(i>>1);
        // // -1
        // // 说明什么？顶部补的，是1，仍旧是11111，所以仍旧是-1


        // System.out.println(i & 1);


        //  让我们关注，正数+负数的逻辑。
        //  所以没有区别，就是位移然后处理了？

        // 每一个位置的i，如何放到第i个位置上呢？
        // OK，逻辑上，我们直接存储一下呢？促出之后，逐步的右移？似乎也可以的。
        // 临时模拟，也没办法，至少先能跑吧？


        // 计算的时候，你的就是从这个低位开始吧。

        // 你用了循环，这个循环就是i++了，怎么处理呢？先不考虑，先处理。

        // 怎么处理这个循环呢？ 要循环32位。如何模拟？
        // 也就是32次？
        // 1>>32？用这个？
        // 简单一点，用这List模拟呢？
        // 可以，但是当然也很奇怪。
        // 先work，再考虑其他的？
        // 注意，这里有一个减法，这个不对呀。


        // 换一个思路呢？
        // 看这个数字位移之后的逻辑？
        // 如何呢？
        if(a==0){
            return b;
        }
        int[] store = new int[32];
        int addon = 0;
        // a !=0，如何计算其对应已经边界切换了？

        List<Integer> loop = new ArrayList<>();

        loop.add(0);loop.add(1);loop.add(2);loop.add(3);loop.add(4);loop.add(5);
        loop.add(6);loop.add(7);loop.add(8);loop.add(9);loop.add(10);loop.add(11);
        loop.add(12);loop.add(13);loop.add(14);loop.add(15);loop.add(16);loop.add(17);
        loop.add(18);loop.add(19);loop.add(20);loop.add(21);loop.add(22);loop.add(23);
        loop.add(24);loop.add(25);loop.add(26);loop.add(27);loop.add(28);loop.add(29);
        loop.add(30);loop.add(31);


        List<Integer> revertLoop = new ArrayList<>();

        revertLoop.add(31);revertLoop.add(30);revertLoop.add(29);revertLoop.add(28);revertLoop.add(27);revertLoop.add(26);
        revertLoop.add(25);revertLoop.add(24);revertLoop.add(23);revertLoop.add(22);revertLoop.add(21);revertLoop.add(20);
        revertLoop.add(19);revertLoop.add(18);revertLoop.add(17);revertLoop.add(16);revertLoop.add(15);revertLoop.add(14);
        revertLoop.add(13);revertLoop.add(12);revertLoop.add(11);revertLoop.add(10);revertLoop.add(9);revertLoop.add(8);
        revertLoop.add(7);revertLoop.add(6);revertLoop.add(5);revertLoop.add(4);revertLoop.add(3);revertLoop.add(2);
        revertLoop.add(1);revertLoop.add(0);

        for(Integer i : loop){
            int[] tmp = cal((a>>i)&1,(b>>i)&1,addon);
            System.out.println("val: "+tmp[0]+" addon: "+tmp[1]);
            store[i] = tmp[0];
            addon = tmp[1];
        }

        for(Integer i: revertLoop){
            System.out.print(store[i]+" ");
        }

        System.out.println();

        // 32全部就绪了？
        // 第一个位置，就是最高位了？
        // 逻辑上，如何处理？
        // 要倒过来
        int ans = 0;


        for(Integer i: revertLoop){
            ans = (ans << 1)|store[i];
        }

        return ans;
    }

    // int[0] 新的值，int[1] 新的addon
    public int[] cal(int a,int b,int c){
        System.out.println(a+" "+b+" "+ c);
        if(a==1 && b==1 && c==1){
            return new int[]{1,1};
        }
        if((a==1 &&b==1 && c==0)||(a==1 &&b==0 && c==1)||(a==0 &&b==1 && c==1)){
            return new int[]{0,1};
        }
        if((a==1 &&b==0 && c==0)||(a==0 &&b==1 && c==0)||(a==0 &&b==0 && c==1)){
            return new int[]{1,0};
        }
        return new int[]{0,0};
        
    }
}
