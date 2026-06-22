class Solution {
    // 对于第一列，以及第一行，我们特别处理。用两个boolean表征。
    // 除了第一列以及第一行之外的，如果说，第i，j是0。我们特别的去设置，这个第一列的[i,0] 以及第一行的[0,j] = 0。
    // 之后，我们去根据这个，设置整个其余的行。
    // 最后再去处理第一列以及第一行的逻辑。
    public void setZeroes(int[][] matrix) {
        boolean firstRowToZero = false;
        boolean firstColumnToZero = false;
        for(int i =0;i<matrix[0].length;i++){
            if(matrix[0][i]==0){
                firstRowToZero = true;
                break;
            }
        }

        for(int i =0;i<matrix.length;i++){
            if(matrix[i][0]==0){
                firstColumnToZero = true;
                break;
            }
        }

        for(int i = 1;i<matrix.length;i++){
            for(int j =1;j<matrix[0].length;j++){
                if(matrix[i][j] ==0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // 处理第一行中的0的逻辑。跳过第一列的处理！
        for(int i =1;i<matrix[0].length;i++){
            if(matrix[0][i] == 0){
                // 把这一个整个的列，设置为0。
                for(int j=1;j<matrix.length;j++){
                    matrix[j][i] = 0;
                }
            }
        }

        // 处理第一列中的0的逻辑。跳过第一行的处理！
        for(int i = 1;i<matrix.length;i++){
            if(matrix[i][0] == 0){
                // 把这一个整个的列，设置为0。
                for(int j=1;j<matrix[0].length;j++){
                    matrix[i][j] = 0;
                }
            }
        }

        // 开始处理第一行
        if(firstRowToZero){
            for(int i=0;i<matrix[0].length;i++){
                matrix[0][i]=0;
            }
        }

        // 开始处理第一列

        if(firstColumnToZero){
            for(int i=0;i<matrix.length;i++){
                matrix[i][0]=0;
            }
        }
    }
}
