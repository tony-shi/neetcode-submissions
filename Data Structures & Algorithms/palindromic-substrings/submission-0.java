class Solution {
    public int countSubstrings(String s) {
        int ans = 0;


        // 奇数，以第i为中心节点的统计个数。
        for(int i = 0;i<s.length();i++){
            // 因为就是说短的也会被算上，所以，逻辑上不是直接就是算最大的。
            // 而是计算最大的长度之后，然后+上去才对。
            // 自身肯定是，然后j成立一个，增加一个。
            ans++;
            for(int j = 1;(i-j)>=0 && (i+j)<s.length();j++){
                if(s.charAt(i-j) == s.charAt(i+j)){
                    ans++;
                }else{
                    break;
                }
            }
        }

        // 偶数。以第i个，为中心对称的左侧节点的统计个数。
        for(int i = 0;i<s.length();i++){
            // 不能直接加。
            if( i+1 <s.length() && s.charAt(i)==s.charAt(i+1)){
                ans++;
                for(int j = 1;(i-j)>=0 && (i+j+1)<s.length();j++){
                    if(s.charAt(i-j) == s.charAt(i+j+1)){
                        ans++;
                    }else{
                        break;
                    }
                }
            }else{
                continue;
            }
        }

        return ans;
    }
}
