You have a graph of n nodes. You are given an integer n and an array edges where edges[i] = [ai, bi] indicates that there is an edge between ai and bi in the graph.

Return the number of connected components in the graph.

 

Example 1:


Input: n = 5, edges = [[0,1],[1,2],[3,4]]
Output: 2
Example 2:


Input: n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]
Output: 1
 

Constraints:

1 <= n <= 2000
1 <= edges.length <= 5000
edges[i].length == 2
0 <= ai <= bi < n
ai != bi
There are no repeated edges.



// DFS 

class Solution {
    public int countComponents(int n, int[][] edges) {
        int count=0;
        boolean[] visited=new boolean[n];
        List<List<Integer>> adj=new ArrayList<List<Integer>>();
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<Integer>());
        }
        for(int i=0;i<edges.length;i++){
            int u=edges[i][0];
            int v=edges[i][1];
            adj.get(v).add(u);
            adj.get(u).add(v);
        }
        
        for(int i=0;i<n;i++){
            if(!visited[i]){
                dfs(i,visited,adj);
                count++;
            }
        }
        return count;
    }
    public void dfs(int index,boolean[] v,List<List<Integer>> adj){
        if(v[index]) return ;
        v[index]=true;
        for(int i=0;i<adj.get(index).size();i++){
            dfs(adj.get(index).get(i),v,adj);
        }
    }
}
