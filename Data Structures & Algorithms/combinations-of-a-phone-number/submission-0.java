class Solution {



    List<List<Character>> dict = new ArrayList<>();

    {
        // 0
        dict.add(new ArrayList<>());
        // 1
        dict.add(new ArrayList<>());
        // 2
        ArrayList<Character> list2 = new ArrayList<>();
        list2.add('a');
        list2.add('b');
        list2.add('c');
        dict.add(list2);
        // 3
        ArrayList<Character> list3 = new ArrayList<>();
        list3.add('d');
        list3.add('e');
        list3.add('f');
        dict.add(list3);
        // 4
        ArrayList<Character> list4 = new ArrayList<>();
        list4.add('g');
        list4.add('h');
        list4.add('i');
        dict.add(list4);
        // 5
        ArrayList<Character> list5 = new ArrayList<>();
        list5.add('j');
        list5.add('k');
        list5.add('l');
        dict.add(list5);
        // 6
        ArrayList<Character> list6 = new ArrayList<>();
        list6.add('m');
        list6.add('n');
        list6.add('o');
        dict.add(list6);
        // 7
        ArrayList<Character> list7 = new ArrayList<>();
        list7.add('p');
        list7.add('q');
        list7.add('r');
        list7.add('s');
        dict.add(list7);
        // 8
        ArrayList<Character> list8 = new ArrayList<>();
        list8.add('t');
        list8.add('u');
        list8.add('v');
        dict.add(list8);
        // 9
        ArrayList<Character> list9 = new ArrayList<>();
        list9.add('w');
        list9.add('x');
        list9.add('y');
        list9.add('z');
        dict.add(list9);
    }


    public List<String> letterCombinations(String digits) {
        if(digits.length()==0){
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();
        int[] choice = new int[digits.length()];
        dfs(digits,0,choice,result);
        return result;
    }


    public void dfs(String digits,int index,int[] choice,List<String> result){
        if(index==digits.length()){
            // 已经到了就是说结尾了。这里，我们应该是输出结果了。
            // 一旦输出，这里的话就直接退出就可以了。不用往下面走了。
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<choice.length;i++){
                int c = (int)(digits.charAt(i) -'0');
                sb.append(dict.get(c).get(choice[i]));
            }
            result.add(sb.toString());
            return ;
        }
        // 开始处理当前的这一次的递归点。
        // 当前的话，我们选中的节点是什么？
        int c = (int)(digits.charAt(index) -'0');
        // 我们开始处理这个数字。
        // OK，获取对应的这个c，他对应的choice有几个呢？
        // 
        List<Character>  chars = dict.get(c);
        for(int i = 0;i<chars.size();i++){
            choice[index] = i;
            // OK，我们已经锁定了这个值。
            // 往下面走一步。
            dfs(digits,index+1,choice,result);
        }
    }
}
