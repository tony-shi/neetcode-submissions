// You are given a string s, rearrange the characters of s so that any two adjacent characters are not the same.

// You can return any possible rearrangement of s or return "" if not posssible.

// Example 1:

// Input: s = "axyy"

// Output: "xyay"
// Example 2:

// Input: s = "abbccdd"

// Output: "abcdbcd"
// Example 3:

// Input: s = "ccccd"

// Output: ""
// Constraints:

// 1 <= s.length <= 500.
// s is made up of lowercase English characters.

class Solution {
    public String reorganizeString(String s) {
        // 给我的感觉，非常像之前的CPU调度的问题。

        // count，之后，从多到小走。
        // 一次取出来2个
        // 这样似乎可以。但是要考虑如何排布了。


        // ababab bc?
        // 这个不可能，因为一定是babababc?这个模式？

        // 如果是这样的话，实际上可以非常快速的处理完毕？
        // 数量又不大？

        Map<Character,Integer> counts = new HashMap<>();
        for(int i = 0;i<s.length();i++){
            if(!counts.containsKey(s.charAt(i))){
                counts.put(s.charAt(i),0);
            }
            counts.put(s.charAt(i),counts.get(s.charAt(i))+1);
        }

        // 现在统计完了。逻辑上，就是按照从大到小来进行排序了？
        // 确实如此，但是从这个角度看，因为map的数量比较优先，似乎不需要排队也OK。

        // loop一下，找到其中最大的两个？
        StringBuilder ans = new StringBuilder();

        while(counts.size()!=0){
            // 有数据，找其中最大的两个？
            if(counts.size()==1){
                // 是不是超过1个呢？
                if(counts.get(counts.keySet().iterator().next())>1){
                    return "";
                }else{
                    // 没有超过1个。这个时候，应该就是返回了吧？
                    ans.append(counts.keySet().iterator().next());
                    return ans.toString();
                }
            }else{
                // OK，不止一个。我们就是找top 2，然后top 2都减一，似乎就还好了。
                char largestChar = ' ';
                int largestCount = 0;
                char sencondChar =' ';
                int sencondCount = 0;
                for(Character key : counts.keySet()){
                    if(counts.get(key) > largestCount){
                        largestChar = key;
                        largestCount = counts.get(key);
                    }
                }
                System.out.println(largestChar);

                for(Character key : counts.keySet()){
                    if(counts.get(key) > sencondCount && key!=largestChar){
                        sencondChar = key;
                        sencondCount = counts.get(key);
                    }
                }
                System.out.println(sencondChar);


                // OK，找到了。现在都去减一。
                decrease(counts,largestChar);
                decrease(counts,sencondChar);
                ans.append(largestChar);
                ans.append(sencondChar);

            }
        }
        return ans.toString();
    }


    public void decrease(Map<Character,Integer> counts,Character key){
        if(counts.get(key)==1){
            counts.remove(key);
        }else{
            counts.put(key,counts.get(key)-1);
        }
    }
}