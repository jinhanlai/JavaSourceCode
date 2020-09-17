package leetcode.tree;

/**
 * @Author laijinhan
 * @date 2020/9/17 11:18 上午
 */

/**
 * 使用并查集的思想
 */
class UnionFind {
    int[] ancestor;// 记录父节点

    public UnionFind(int n) {
        ancestor = new int[n];
        // 初始化父节点指向自身
        for (int i = 0; i < n; i++) {
            ancestor[i] = i;
        }
    }

    //合并两棵树
    public void union(int index1, int index2) {
        ancestor[find(index1)] = find(index2);
    }

    // 找到指定结点的跟结点,包含递归调用，会找到结点的第一个根结点
    // eg:ancestor=[0,2,3,4,4,5]  ;index=1  --->> ancestor=[0,4,4,4,4,5] 及1，2，3，4的跟结点都是4
    public int find(int index) {
        if (ancestor[index] != index) {
            ancestor[index] = find(ancestor[index]);
        }
        return ancestor[index];
    }
}

public class findRedundantDirectedConnection {
    /**
     * 给定一个有向图，图中包含一条冲突边，使得删除这一条边是一个n-1条边的有向图。
     * 当访问到边 [u,v]时，进行如下操作:
     * 如果此时有parent[v]!=u,说明v存在两个父节点，当前边即为冲突的边
     * 如果此时有parent[v]==u,然后在并查集中分别找到 u 和 v 的祖先（即各自的连通分支中的根节点），如果祖先相同，说明这条边导致环路出现，将当前的边 [u,v][u,v] 记为导致环路出现的边，如果祖先不同，则在并查集中将 uu 和 vv 进行合并。
     *
     *
     */
    public static int [] findRedundantDirectedConnections(int[][] edges){
        int nodesCount = edges.length;
        UnionFind uf = new UnionFind(nodesCount + 1);
        int[] parent = new int[nodesCount + 1];
        for (int i = 1; i <= nodesCount; ++i) {
            parent[i] = i;
        }
        int conflict = -1;
        int cycle = -1;
        for (int i = 0; i < nodesCount; ++i) {
            int[] edge = edges[i];
            int node1 = edge[0], node2 = edge[1];
            if (parent[node2] != node2) {
                conflict = i;
            } else {
                parent[node2] = node1;
                if (uf.find(node1) == uf.find(node2)) {
                    cycle = i;
                } else {
                    uf.union(node1, node2);
                }
            }
        }
        if (conflict < 0) {
            int[] redundant = {edges[cycle][0], edges[cycle][1]};
            return redundant;
        } else {
            int[] conflictEdge = edges[conflict];
            if (cycle >= 0) {
                int[] redundant = {parent[conflictEdge[1]], conflictEdge[1]};
                return redundant;
            } else {
                int[] redundant = {conflictEdge[0], conflictEdge[1]};
                return redundant;
            }
        }

    }

    public static void main(String[] args) {
        int[][] input=new int[][]{{1,2},{2,3},{3,4},{4,1},{1,5}};
        int [] ans=findRedundantDirectedConnections(input);
        System.out.println(ans[0]+""+ans[1]);
    }

}
