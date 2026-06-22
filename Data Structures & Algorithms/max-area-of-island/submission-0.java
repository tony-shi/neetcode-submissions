class Solution {


    // 0 海水，1是岛。
    // 比较简单，逻辑上就是一个dfs + 一个visited就足够了。
    public int maxAreaOfIsland(int[][] grid) {
        int ans = 0;
        int[][] visited = new int[grid.length][grid[0].length];

        for(int i = 0;i<grid.length;i++){
            for(int j = 0;j<grid[0].length;j++){
                ans = Math.max(ans,dfs(visited,grid,i,j));
            }
        }
        return ans;
    }

    public int dfs(int[][] visited,int[][] grid, int x,int y){
        // 首先x，y得是就是说是1，否则进入不了。也没必要进入了？不过这里问题我理解不大。
        if(x<0 || x>=grid.length || y<0 || y>=grid[0].length) return 0;
        if(grid[x][y]==0)
            return 0;
        if(visited[x][y]==1)
            return 0;
        visited[x][y] = 1;
        // 去遍历他的其他的方向，然后标记对应的值回来进行加和。
        return  dfs(visited,grid,x-1,y)+dfs(visited,grid,x+1,y)+dfs(visited,grid,x,y-1)+dfs(visited,grid,x,y+1) + 1;
    }
}
