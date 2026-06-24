class Solution {

    public int numDecodings(String s) {
        if(s.length()==0) return 0;
        int ans_1 = s.charAt(0)-'0' >0 ? 1:0;
        if(s.length()==1) return ans_1;
        int ans[] =new int[s.length()];
        ans[0] = ans_1;
        for(int i =1;i<s.length();i++){
            ans[i] = s.charAt(i)-'0' >0 ? ans[i-1]:0;
            // 判断s【i-1】，s[i]是否是一个合法的值？
            // 首先，这里是两位，所以必须要是【10，26】才可以。
            // 前面要是0就不行。
            int value = (s.charAt(i-1)-'0') * 10 + (s.charAt(i)-'0');
            if(value >=10 && value <=26){
                ans[i] = ans[i] + (i==1?1:ans[i-2]);
            }
        }        
        return ans[s.length()-1];
    }
}
