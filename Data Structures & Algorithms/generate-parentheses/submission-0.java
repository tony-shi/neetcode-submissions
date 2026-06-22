// 生成所有合法的这个括号。



//

class Solution {
    // 索引点，从1开始吧，方便理解？
    ArrayList<List<String>> history = new ArrayList<>();
    
    {
        // 因为就是说，数量有限。我们就直接初始化一下，方便处理我理解，开销也不大。
        for(int i = 0;i< 20;i++){
            history.add(new LinkedList<>());
        }
        history.get(0).add("");
        // n = 1的话，对应为
        history.get(1).add("()");
    }


    public List<String> generateParenthesis(int n) {
        LinkedList<String> result = new LinkedList<>();
        // 逻辑上，你应该去查看一下，是否已经有了？有了的话，直接返回。
        if(history.get(n).size()!=0){
            // 处理过了。不要二次处理。直接返回。
            return history.get(n);
        }else{            
            // 没有处理过，第一次进入，需要进行递归处理。
            for(int i = 0;i<n;i++){
                // i 对应着，就是说，我们的中间有几个括号？
                // i = 0，那么就是说，左侧只有 (i+1) 对括号，然后，我们就是去找，n - （i+1）个的组合？

                // 举例来说。n =4， i =0，那么左侧，1对括号，右侧3对括号。
                // n=4，i=2，那么左侧3对括号，右侧1对括号。
                // 左侧就是说，是i+1对，右侧的话，一共是n-i-1对括号。
                // 左侧实际上是（get（i））
                // 如果i = 0，因为0，我们设置的对应的是""的话，似乎也没有问题？

                // 插入对应的所有的子点。
                for(String pre : generateParenthesis(i)){

                    for(String next : generateParenthesis(n-i-1)){
                        StringBuilder sb = new StringBuilder();
                        sb.append('(');
                        sb.append(pre);
                        sb.append(')');
                        sb.append(next);
                        result.add(sb.toString());
                    }
                }
            }
            history.set(n,result);
            // System.out.println(n);
            // print(result);
            return result;
        }
    }


    public void print( LinkedList<String> result){
        for(String s: result){
            System.out.print(s+" ");
        }
        System.out.println();
    }
}
