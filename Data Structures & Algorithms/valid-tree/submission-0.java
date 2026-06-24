class Solution {
// n个节点。edges条边。
    // 判断是否就是说数？
    // 首先，就是，如果边的数量过多，一定不是。
    // 成树的先觉条件，全部可达。
    // 然后就是没有环。
    // 逻辑上，n个点。然后每个点，我们看是否被访问过。
    // 每次进来的边，应该是有一个点，指向一个没有被访问的点？否则的话，肯定就是重复了。-> 不对，联通两个分量的话，是有可能的。
    // 用并查集的逻辑来去看。
    // 可达：所有的节点最终parent是一个。
    // 个数，一共只有n-1个边。
    // 无环，每次遍历边，不会存在就是说，两者的元素的root都一致的case。
    // 三个条件。但是是否只需要有两个呢？
    // 考虑一下，n-1个边，无环，但是不可达？这个是否不可能的？
    public boolean validTree(int n, int[][] edges) {
        if(edges.length!=(n-1)){
            return false;
        }
        int[] parent = new int[n];
        for(int i = 0;i<n;i++){
            parent[i] = i;
        }

        for(int i = 0;i<edges.length;i++){
            int   fromRoot = find(parent,edges[i][0]);
            int targetRoot = find(parent,edges[i][1]);
            if(fromRoot == targetRoot){
                // 非预期。不应该两个元素的root相等。
                return false;
            }
            // OK，不相等。
            // 把两者串联起来。
            parent[targetRoot] = fromRoot;
        }
        return true;
    }

    // 查询这个index对应的root的索引点，并且顺带拍平。
    public int find(int[] parent,int index){
        while(parent[index]!=index){
            // 顺带拍一下。
            parent[index] = parent[parent[index]];
            // 往上走。
            index = parent[index];
        }
        return index;
    }
}
