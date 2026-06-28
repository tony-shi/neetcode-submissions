// O 就是棋子。 X被围。围了的话就替换为X。



// 核心思路，从边出发，然后看他能dfs到多少？
// 之后的话，对于剩下的，O全部设置为X。
// 逻辑上，为了避免一个就是说逻辑？我们应该怎么处理？
// 一个思路，就是说，转化为另外一个字符？
// 让我们思考一下，已经是X的，要做什么？什么都不用做？但是回反复会被访问到啊？但是没有影响啊？
// 你设置访问也是一样的。
// 对于O才特别。有两种O，一个是可以保留的O，另外一个，悬空的O，怎么区分呢？
// 我的想法，可以保留的O，我们就是放.。
// 不可以的话呢？仍旧是O。
// 之后的话，把所有的O换成x，然后把。换成O。

class Solution {
    public void solve(char[][] board) {




        // OK，从所有的边开始触发。
        for(int i = 0;i<board.length;i++){
            for(int j = 0;j<board[0].length;j++){
                if(i==0 || i==board.length-1 || j==0 || j== board[0].length-1){
                    dfs(board,i,j);
                }
            }
        }

        for(int i = 0;i<board.length;i++){
            for(int j = 0;j<board[0].length;j++){
                if(board[i][j]=='O'){
                    board[i][j] = 'X';
                }
            }
        }

        for(int i = 0;i<board.length;i++){
            for(int j = 0;j<board[0].length;j++){
                if(board[i][j]=='.'){
                    board[i][j] = 'O';
                }
            }
        }
    }

    public void dfs(char[][] board, int i,int j){
        // 开始访问第i、j个元素。
        if(i<0 || i>=board.length || j<0 || j >= board[0].length ){
            return ;
        }
        // 有效的元素，开始访问。
        if(board[i][j]=='O'){
            // 可以触达的点。我们设置为.
            board[i][j] = '.';
            // 这个点可以触达，我们去访问所有的其他方向。
            dfs(board,i-1,j);
            dfs(board,i+1,j);
            dfs(board,i,j-1);
            dfs(board,i,j+1);
        }
        // 其他的可能是X、.，完全不用访问，结束。
    }
}
