
class Solution {
    public int[] findRedundantConnection(int[][] edges) {

        // n条边，n个节点。有一个边成环了。
        // n个节点的话，我们从0，n-1。但是edges中的是从1开始，需要关注。
        int[] parent = new int[edges.length];

        // 最开始，自然这个集合都是只指向自身。
        for(int i = 0;i<parent.length;i++){
            parent[i] = i;
        }

        for(int i = 0;i<edges.length;i++){
            int sourceIndex = edges[i][0]-1;
            int targetIndex = edges[i][1]-1;

            int sourceRoot = find(parent,sourceIndex);
            int targetRoot = find(parent,targetIndex);

            if(sourceRoot==targetRoot){
                // 成环了。就是这个结果了。
                return edges[i];
            }else{
                System.out.println("link "+targetIndex +" to "+ sourceRoot);
                // 不想等。那么这个逻辑上是什么？就是说，把这个新节点加入进去到这个点。
                parent[targetRoot] = sourceRoot;
            }
        }

        return new int[]{-1,-1};
    }


    public int find(int[] parent, int x){
        System.out.print("find "+ x +" ");
        // 有一个元素的index，x。我们去找他的root。
        while(parent[x]!=x){
            // 拍平逻辑？ 但是这个，只拍了一层吧？没有持续拍平吧？
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        System.out.println("root " + x);
        return x;
    }
}