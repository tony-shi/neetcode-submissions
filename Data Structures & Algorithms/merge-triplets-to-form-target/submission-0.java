class Solution {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        Integer candidateFor1=-1;
        //先处理第一个？
        for(int i = 0;i<triplets.length;i++){
            // 判断是否是这个candidate？
            if(triplets[i][0] == target[0] && triplets[i][1] <= target[1] && triplets[i][2] <= target[2]){
                // 是candiate
                candidateFor1 = i;
                // 逻辑上，找到了一个是不是就可以退出了呢？
                // 我感觉似乎可以吧？
                break;
            }
        }
         Integer candidateFor2=-1;
        //先处理第一个？
        for(int i = 0;i<triplets.length;i++){
            // 判断是否是这个candidate？
            if(triplets[i][1] == target[1] && triplets[i][0] <= target[0] && triplets[i][2] <= target[2]){
                // 是candiate
                candidateFor2 = i;
                // 逻辑上，找到了一个是不是就可以退出了呢？
                // 我感觉似乎可以吧？
                break;
            }
        }
        Integer candidateFor3=-1;
        //先处理第一个？
        for(int i = 0;i<triplets.length;i++){
            // 判断是否是这个candidate？
            if(triplets[i][2] == target[2] && triplets[i][0] <= target[0] && triplets[i][1] <= target[1]){
                // 是candiate
                candidateFor3 = i;
                // 逻辑上，找到了一个是不是就可以退出了呢？
                // 我感觉似乎可以吧？
                break;
            }
        }

        if(candidateFor1!=-1 && candidateFor2!=-1 && candidateFor3!=-1 ){
            return true;
        }
        return false;
    }
}
