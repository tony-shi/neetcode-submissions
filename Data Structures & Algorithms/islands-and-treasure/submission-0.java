class Solution {


    public static class Node {
        public int x;
        public int y;

        public Node(int x,int y){
            this.x = x;
            this.y = y;
        }
    }

    public void islandsAndTreasure(int[][] grid) {
        LinkedList<Node> list = new LinkedList<>();
        for(int i=0;i<grid.length;i++){
            for(int j = 0;j<grid[0].length;j++){
                if(grid[i][j]==0){
                    list.add(new Node(i,j));
                }
            }
        }


        // OK，开始广度优先遍历。
        int step = 1;
        while(list.size()!=0){
            // 当前的维度上，设置我们的，可以触达的，新的节点，为step这个值。
            // 同时把这个新的节点入队。
            int count = list.size();
            for(int i=0;i<count;i++){
                Node node = list.pop();
                // 初始化时0，刚刚好，重复了。所以就简单一些了。
                // 现在，让我们加入新的。
                if(node.x-1>=0 && grid[node.x-1][node.y]==2147483647){
                    grid[node.x-1][node.y] =step;
                    list.add(new Node(node.x-1,node.y));
                }
                if(node.x+1<grid.length && grid[node.x+1][node.y]==2147483647){
                    grid[node.x+1][node.y] =step;
                    list.add(new Node(node.x+1,node.y));
                }
                if(node.y-1>=0 && grid[node.x][node.y-1]==2147483647){
                    grid[node.x][node.y-1] =step;
                    list.add(new Node(node.x,node.y-1));
                }
                if(node.y+1<grid[0].length && grid[node.x][node.y+1]==2147483647){
                    grid[node.x][node.y+1] =step;
                    list.add(new Node(node.x,node.y+1));
                }
            }
            step++;
        }
    }

}
