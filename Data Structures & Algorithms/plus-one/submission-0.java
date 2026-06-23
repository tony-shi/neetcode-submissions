class Solution {
    public int[] plusOne(int[] digits) {
        // 不好直接判断，我们是从哪个位置开始。
        // 先从地位开始吧？逆序？
        int[] tmp = new int[digits.length+1];
        int index = 0;
        for(int i = digits.length-1;i>=0;i--){
            if(i==(digits.length-1)){
                tmp[index+1] =  (tmp[index]+ digits[i] + 1) / 10;
                tmp[index] = (tmp[index]+ digits[i] + 1) % 10;
            }else{
                tmp[index+1] =  (tmp[index]+ digits[i] ) / 10;
                tmp[index]  = (tmp[index] + digits[i])%10;
            }
            index++;
        }

        // 已经结束了。查看一下，这个最顶位置，是否不是0？
        int maxIndex ;
        if(tmp[digits.length]==0){
            // 等于0，逻辑上就是逆序就可以了。
            maxIndex = digits.length;
        }else{
            // 不等于0了。OK，这个长度是 digits.length+1；
            maxIndex = digits.length+1;
        }

        int[] ans = new int[maxIndex];
        for(int i =0;i< maxIndex;i++){
            ans[i] = tmp[maxIndex-i-1];
        }
        return ans;

    }
}
