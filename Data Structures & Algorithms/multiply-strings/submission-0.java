// You are given two strings num1 and num2 that represent non-negative integers.

// Return the product of num1 and num2 in the form of a string.

// Assume that neither num1 nor num2 contain any leading zero, unless they are the number 0 itself.

// Note: You can not use any built-in library to convert the inputs directly into integers.

// Example 1:

// Input: num1 = "3", num2 = "4"

// Output: "12"
// Example 2:

// Input: num1 = "111", num2 = "222"

// Output: "24642"
// Constraints:

// 1 <= num1.length, num2.length <= 200
// num1 and num2 consist of digits only.


class Solution {
    public String multiply(String num1, String num2) {
        String ans = "0";
        for(int i=0;i<num2.length();i++){
            String current = multiply(num1,num2.charAt(num2.length()-1-i));
            for(int j=0;j<i;j++){
                current = current +"0";
            }
            ans = add(ans,current);
            
        }
        return ans;
    }

    // 12346 8

    // StringBuilder sb = new StringBuilder("World");
    // sb.insert(0, "Hello "); 
    public String multiply(String s, char c){
        if(c=='0'){
            return "0";
        }
        // 前面一个元素，集成的进位值。最大也就是8似乎？9*9？
        int addon = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = s.length()-1;i>=0;i--){
            int tmp =   (s.charAt(i) -'0') * (c-'0') + addon;
            // 当前这一位的值：
            sb.insert(0,tmp % 10);
            addon = tmp/10;
        }
        if(addon!=0){
            sb.insert(0,addon);
        }
        return sb.toString();
    }

    // 12345 5678
    public String add(String a,String b){
        int addon = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < a.length() || i < b.length();i++){
            // a char?
            // b char?
            int charInA = i < a.length()? a.charAt(a.length()-i-1)-'0':0;
            int charInB = i < b.length()? b.charAt(b.length()-i-1)-'0':0;
            int tmp = charInA + charInB + addon;
            sb.insert(0,tmp % 10);
            addon = tmp/10;
        }
        if(addon!=0){
            sb.insert(0,addon);
        }
        return sb.toString();
    }
}

