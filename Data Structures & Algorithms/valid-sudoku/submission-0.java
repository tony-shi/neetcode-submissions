class Solution {

    // 个人理解比较简单。主要的逻辑似乎就是判断一下这个每一行，每一列。然后，就是每一个loop就OK了。
    // 非常简单我理解。
    public boolean isValidSudoku(char[][] board) {
        // 1-9

        // 先处理行。
        for(int i = 0; i<board.length;i++){
            int[] shown = new int[10];
            for(int j = 0;j<board[0].length;j++){
                int value = board[i][j]!='.'? board[i][j]-'0' : -1;
                if(value != -1 && shown[value] !=0){
                    // 重复出现了。
                    return false;
                }
                if(value != -1){
                    shown[value] = 1;
                }
            }
        }
        // 处理列。
        for(int i =0;i<board.length;i++){
            int[] shown = new int[10];
            for(int j = 0;j<board[0].length;j++){
                int value = board[j][i]!='.'? board[j][i]-'0' : -1;
                if(value != -1 && shown[value] !=0){
                    // 重复出现了。
                    return false;
                }
                if(value != -1){
                    shown[value] = 1;
                }
            }
        }

        // 开始处理，就是每一个9个的结合点。
        // 如何快速实现呢？
        // 逻辑上，就是每一个的中心点。
        // 然后去遍历他的所有的case？
        // 逻辑上，似乎比较简单？
        // i<3,j<3?
        // 然后的话，起始点比较关键。逻辑上，直接就是一个9的数组算了。这样最简单。最快速。

        int[][] index = new int[][]{{0,0},{0,3},{0,6},{3,0},{3,3},{3,6},{6,0},{6,3},{6,6}};


        for(int k = 0;k<index.length;k++){
            int[] shown = new int[10];
            for(int i = index[k][0];i<index[k][0]+3;i++){
                for(int j = index[k][1];j<index[k][1]+3;j++){
                    int value = board[i][j]!='.'? board[i][j]-'0' : -1;
                    if(value != -1 && shown[value] !=0){
                        // 重复出现了。
                        return false;
                    }
                    if(value != -1){
                        shown[value] = 1;
                    }
                }
            }
        }
        return true;
    }
}
