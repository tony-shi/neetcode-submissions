// You are given an encoded string s, return its decoded string.

// The encoding rule is: k[encoded_string], where the encoded_string 
// inside the square brackets is being repeated exactly k times. 
// Note that k is guaranteed to be a positive integer.

// You may assume that the input string is always valid; there are no extra white spaces, 
// square brackets are well-formed, etc. There will not be input like 3a, 2[4], a[a] or a[2].

// The test cases are generated so that the length of the output will never exceed 100,000.

// Example 1:

// Input: s = "2[a3[b]]c"

// Output: "abbbabbbc"
// Example 2:

// Input: s = "axb3[z]4[c]"

// Output: "axbzzzcccc"
// Example 3:

// Input: s = "ab2[c]3[d]1[x]"

// Output: "abccdddx"
// Constraints:

// 1 <= s.length <= 30
// s is made up of lowercase English letters, digits, and square brackets '[]'.
// All the integers in s are in the range [1, 300].
// s is guaranteed to be a valid input.

class Solution {

    // 逻辑上，似乎可以用栈。
    // 但是我们从这个做题的角度考虑，用递归，更直观，因此考虑使用递归？
    public String decodeString(String s) {
        int[] pairIndex = pairCal(s);
        return decodeString(s,pairIndex,0,s.length()).toString();
        
    }

    public int[] pairCal(String s){
        int[] pairIndex = new int[s.length()];
        LinkedList<Integer> list = new LinkedList<>();
        for(int i = 0;i<s.length();i++){
            if(s.charAt(i)=='['){
                list.add(i);
            }
            if(s.charAt(i)==']'){
                // OK。现在是找到了一个匹配的。pop出来，然后的话，更新。
                int leftIndex = list.getLast();
                pairIndex[leftIndex] = i;
                list.removeLast();
            }
        }
        return pairIndex;
    }

    // [start,end)
    public StringBuilder decodeString(String s,int[] pairIndex, int start,int end) {
        // 要从头开始。
        if(start>=end || start < 0 || end > s.length()){
            // 非法，逻辑上，返回""也是OK的。
            return new StringBuilder();
        }
        StringBuilder ans = new StringBuilder();

        for(int i = start;i<end;){
            // 从头处理，只有这么几种可能，字母、数字、【】
            // 逻辑上，只有可能是字母、数字、【（这个也不用处理）

            // 这里不对，你错误的判断的了，因为还有10[a]这种case了！！！！！
            if(s.charAt(i)>='1' && s.charAt(i)<='9'){
                // 是一个数字。逻辑上，数字的出现，就以为着递归了？
                // 是这个问题。
                // 从这个往后找，你还要找到第一个匹配的对应的]
                // OK，这个动作就是按照一个堆栈来去找吗？
                // 可以，但是很tricky，干脆，就是第一步统计好算了？
                // 这样处理起来不是更快吗？
                // 我觉得可以？
                // OK，开始递归了！
                // 开始一直处理到这个第i个才行！！又错误了！
                int count = 0;
                do{
                    count = count * 10 +s.charAt(i)-'0';
                    i=i+1;
                }while(s.charAt(i)>='0' && s.charAt(i)<='9' && i < end);

                StringBuilder tmp = decodeString(s,pairIndex,i+1,pairIndex[i]);
                for(int j = 0;j<count;j++){
                    ans.append(tmp);
                }
                // OK，我们的i的位置变化了。
                // i位置变化为：pairIndex[i+1]+1
                i = pairIndex[i]+1;
            }else{
                ans.append(s.charAt(i));
                i++;
            }
        }
        return ans;
    }
}