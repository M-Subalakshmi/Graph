class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] res=new int[numCourses];
        if(numCourses== 0){
            return res;
        }
        if(prerequisites==null || prerequisites.length==0){
            for(int i=0;i<numCourses;i++){
                res[i]=i;
            }
            return res;
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
                return new int[0];
            }
        }
        return res;
    }
}
