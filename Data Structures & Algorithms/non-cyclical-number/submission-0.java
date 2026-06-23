
class Solution {
    public boolean isHappy(int n) {
        if(n==1){
            return true;
        }

        Set<Integer> seen = new HashSet<>();

        while(!seen.contains(n)){
            seen.add(n);
            int next = cal(n);
            if(next==1){
                return true;
            }
            n = next;
        }
        return false;
    }

    public int cal(int n){
        int ans = 0;
        while(n!=0){
            int digit = n % 10;
            ans+=(digit * digit);
            n = n/10;
        }
        return ans;
    }
}
