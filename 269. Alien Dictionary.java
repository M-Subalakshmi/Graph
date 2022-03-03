There is a new alien language that uses the English alphabet. However, the order among the letters is unknown to you.

You are given a list of strings words from the alien language's dictionary, where the strings in words are sorted lexicographically by the rules of this new language.

Return a string of the unique letters in the new alien language sorted in lexicographically increasing order by the new language's rules. If there is no solution, return "". If there are multiple solutions, return any of them.

A string s is lexicographically smaller than a string t if at the first letter where they differ, the letter in s comes before the letter in t in the alien language. If the first min(s.length, t.length) letters are the same, then s is smaller if and only if s.length < t.length.

 

Example 1:

Input: words = ["wrt","wrf","er","ett","rftt"]
Output: "wertf"
Example 2:

Input: words = ["z","x"]
Output: "zx"
Example 3:

Input: words = ["z","x","z"]
Output: ""
Explanation: The order is invalid, so return "".
 

Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 100
words[i] consists of only lowercase English letters.



class Solution {
    public String alienOrder(String[] words) {
      Map<Character,List<Character>> graph=new HashMap<>();
      Map<Character,Integer> indegree=new HashMap<>();
      for(String i:words){
          for(char ch:i.toCharArray()){
              graph.putIfAbsent(ch,new ArrayList<>());
              indegree.putIfAbsent(ch,0);
          }
      } 
      for(int i=0;i<words.length-1;i++){
          String s1=words[i];
          String s2=words[i+1];
          
          if(s1.length()>s2.length() && s1.startsWith(s2)) return "";
          
          int len=Math.min(s1.length(),s2.length());
          for(int j=0;j<len;j++){
              char ch1=s1.charAt(j);
              char ch2=s2.charAt(j);
              if(ch1!=ch2){
                  graph.get(ch1).add(ch2);
                  indegree.put(ch2,indegree.get(ch2)+1);
                  break;
              }
          }
      }
    Queue<Character> queue=new LinkedList<>();
        for(char key:indegree.keySet()){
            if(indegree.get(key)==0){
                queue.offer(key);
            }
        }
        StringBuilder result=new StringBuilder();
        while(!queue.isEmpty()){
            char ch=queue.poll();
            result.append(ch);
            for(char child:graph.get(ch)){
                indegree.put(child,indegree.get(child)-1);
                if(indegree.get(child)==0){
                    queue.offer(child);
                }
            }
        }
        return result.length()==indegree.size()?result.toString():"";
    }
}
