// Palindrome Partitioning
// Medium
// Topics
// Company Tags
// Hints
// Given a string s, split s into substrings where every substring is a palindrome. 
// Return all possible lists of palindromic substrings.

// You may return the solution in any order.

// Example 1:

// Input: s = "aab"

// Output: [["a","a","b"],["aa","b"]]
// Example 2:

// Input: s = "a"

// Output: [["a"]]
// Constraints:

// 1 <= s.length <= 20
// s contains only lowercase English letters.


// 这个更像是DP了，而不是单纯的逻辑了。


// 首先要输出所有的回文的组合。
// 那么就是回到之前的，逐个字符的递推的感觉。
// 前面s【i】的队列的组合已知了，那么s【i+1】呢？
// 核心点就是取决于这个新的char in s【i+1】和之前的组合点？
// 往前找，看能匹配到多少个呢？
// 逻辑上，你还不能直接中断了，你必须要得找所有以这个能结尾的，回文点。
// 这个逻辑还不容易处理，因为你得找中央点，然后去看是否可以追加吧。
// 朴素的方案，很复杂，你这个是o（n）的匹配的计算了？
// 为了避免每次都重复计算，实际上，就是抓到这个最大的，记录长度？
// 这样的话，后面就可以方便一点了。

// OK，从这个逻辑看时间复杂度的分析。

// 计算所有的，以i为点的max的长度，时间复杂度o（n^2），存储空间o（n）。
// 之后，开启进行递归。
// s【i】 = sum（s【i-k】，s【i-k+1】到s【i】形成回文）。
// 所以，关键点在于，实际上这个s【i】最大有多大？这个还真的不容易处理，因为可能真的很大。我们考虑一下，就是说全部都是一致的话，那么还可能真的比较大。
// 像是一个斐波那契数列的增长感？
// 斐波那契的话，是指数增长还是o（n^2）？
// 直接分析这个逻辑。

// s【i】= s【0】+s【1】+s【2】+。。。的呢？
// 这个不是简单的回文了吧？这个是指数增长了我的理解？下一个几乎是前面的一倍了。
// 从这个角度看，最差的场景，指数增长，逻辑上还行？
// so 时间复杂度不容易分析的。
// 理解了。
// 只是编码会复杂一些？


class Solution {
    public List<List<String>> partition(String s) {
        // 为了避免复杂的计算，尽管没有必要，我们仍旧是一次性处理干净！！！相当于练手了！！！
        // 第i个元素为中心点，构成的奇数个回文的最大的长度。
        int[] maxLength1 = new int[s.length()];
        // 第i个元素为中心点的左侧，构成的偶数个回文的最大长度。
        int[] maxLength2 = new int[s.length()];

        for(int i = 0;i<s.length();i++){
            char c = s.charAt(i);
            maxLength1[i] = 1;
            for(int j =1; i-j>=0 && i+j<s.length() && s.charAt(i-j)==s.charAt(i+j);j++){
                maxLength1[i]+=2;
            }
        }

        for(int i = 0;i<s.length();i++){
            char c = s.charAt(i);
            maxLength2[i] = 0;
            if(i+1<s.length() && s.charAt(i+1) == s.charAt(i) ){
                maxLength2[i] = 2;
                for(int j =1; i-j>=0 && i+1+j<s.length() && s.charAt(i-j)==s.charAt(i+j+1);j++){
                     maxLength2[i]+=2;
                }
            }
        }

        // 现在所有的准备OK了。开始遍历核心逻辑。

        // 第i个元素这块，然后对应的结果？
        // 比如，第一个元素？
        // 那如果是完全，没有元素呢？有没有解？
        // 关注一下，先不设置，先考虑写递推了。

        List<List<List<String>>> result = new ArrayList<>();



        List<List<String>> empty = new ArrayList<>();
        empty.add(new ArrayList<>());


        for(int i = 0;i<s.length();i++){
            // 开始衡量，这个元素。
            // 现在，我们多了一个元素s.charAt(i)
            // 这个元素本身可以成为一个回文。
            // 然后，去看历史，的每一个索引位置？
            // 这里，就要遍历我们知道前面存储的maxLength1以及maxLength2了。
            // 逻辑上，肯定是只看历史的就可以了？


            // 首先，你得先append自身，这个非常简单没有问题，正好也相当于初始化了我理解。
            List<List<String>> tmp = new ArrayList<>();
            result.add(tmp);


            // 裸string，先塞入自身。
            if(i!=0){
                add(tmp,copy(result.get(i-1), s.substring(i,i+1)));
            }else{
                // 当前是第一个。逻辑上，这个tmp。我们组织一下，塞入自身就可以了？
                add(tmp,copy(empty, s.substring(i,i+1)));
            }

            for(int j = 0;j<i;j++){
                // 奇数个，从这个j开始，到目前的i，是否包含了？

                if(maxLength1[j]>=(1+(i-j)*2)){
                    // 包含，肯定是一个回文了。
                    // copy目标的已经存在的数据，append我们的新的回文。

                    List<List<String>> copyOne = 2*j-i-1 >=0?result.get(2*j-i-1):empty;
                    add(tmp,copy(copyOne, s.substring(2*j-i,i+1)));

                }
                if(maxLength2[j]>=(i-j)*2){
                    // 包含，以j的话，也是一个回文。
                    List<List<String>> copyOne = 2*j-i >=0?result.get(2*j-i):empty;
                    add(tmp,copy(copyOne, s.substring(2*j-i+1,i+1)));
                }
                
            }
        }


        return result.get(s.length()-1);
    
    }

    public List<List<String>> copy(List<List<String>> original,String newString){
        List<List<String>> result = new ArrayList<>();
        for(List<String> strings : original){
            List<String> newStrings = new ArrayList<>();
            for(String s: strings){
                newStrings.add(s);
            }
            newStrings.add(newString);
            result.add(newStrings);
        }
        return result;
    }

    public void add(List<List<String>> old,List<List<String>> newOne){
        // 把old的和newOne的组合插入。
        for(List<String> strings : newOne){
            old.add(strings);
        }
    }
}
