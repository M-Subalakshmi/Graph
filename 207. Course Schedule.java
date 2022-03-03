There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.

 

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 

Constraints:

1 <= numCourses <= 105
0 <= prerequisites.length <= 5000
prerequisites[i].length == 2
0 <= ai, bi < numCourses
All the pairs prerequisites[i] are unique.


class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] res=new int[numCourses];
        if(numCourses== 0 ||  prerequisites.length==0){
            return true;
        }
        int[] indegree=new int[numCourses];
        List<List<Integer>> adj=new ArrayList<>();
        for(int i=0;i<numCourses;i++){
            adj.add(new ArrayList<>());
        }
        for(int[] req: prerequisites){
            adj.get(req[1]).add(req[0]);
            indegree[req[0]]++;
        }
        Queue<Integer> que=new LinkedList<>();
        for(int i=0;i<numCourses;i++){
            if(indegree[i]==0){
                que.add(i);
            }
        }
        int index=0;
        while(!que.isEmpty()){
            int curr=que.poll();
            res[index++]=curr;
            for(int course:adj.get(curr)){
                indegree[course]--;
                if(indegree[course]==0){
                    que.add(course);
                }
            }
        }
        for(int i=0;i<numCourses;i++){
            if(indegree[i]!=0){
                return false;
            }
        }
        if(res.length==numCourses) return true;
        else return false;
    }
}
