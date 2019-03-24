import java.util.*; 
import java.lang.*; 
import java.io.*; 

  
public class MaximumFlow
{ 
    public static final int S = 6;    //S la so luong dinh 
    //tra ve gia tri la true neu di tu s den t co trong do thi
    boolean BFS(int Graph[][],int s,int t,int parent[]) 
    { 
        boolean visited[] = new boolean[S]; 
        for(int i=0; i<S; ++i) 
            visited[i]=false; 
        LinkedList<Integer> queue = new LinkedList<Integer>(); 
        queue.add(s); 
        visited[s] = true; 
        parent[s]=-1; 
        while (queue.size()!=0) 
        { 
            int u = queue.poll();
            for (int v=0; v<S; v++) 
            { 
                if (visited[v]==false && Graph[u][v] > 0) 
                { 
                    queue.add(v); 
                    parent[v] = u; 
                    visited[v] = true; 
                } 
            } 
        } 
        return (visited[t] == true); 
    } 
  
    public int maxFlow(int graph[][],int s,int t) 
    { 
        int u, v; 
        int rGraph[][] = new int[S][S]; 
        for (u = 0; u < S; u++) 
            for (v = 0; v < S; v++) 
                rGraph[u][v] = graph[u][v]; 
        int parent[] = new int[S]; 
        int maxFlow = 0; 
        while (BFS(rGraph, s, t, parent)) 
        { 
            int pathFlow = Integer.MAX_VALUE; 
            for (v=t; v!=s; v=parent[v]) 
            { 
                u = parent[v]; 
                pathFlow = Math.min(pathFlow, rGraph[u][v]); 
            } 
            for (v=t; v != s; v=parent[v]) 
            { 
                u = parent[v]; 
                rGraph[u][v] -= pathFlow; 
                rGraph[v][u] += pathFlow; 
            } 
            maxFlow += pathFlow; 
        } 
        return maxFlow; 
    } 
	
	private static void DFS(int[][]rGraph,int s,boolean[] visited) { 
        visited[s] = true; 
        for (int i = 0; i < S; i++) { 
            if (rGraph[s][i] > 0 && !visited[i]) { 
                    DFS(rGraph, i, visited); 
            } 
        } 
    } 
	
    public  void minCut(int[][] graph, int s, int t) { 
        int u,v; 
        int[][] rGraph = new int[S][S];  
        for (int i = 0; i < S; i++) { 
            for (int j = 0; j < S; j++) { 
                rGraph[i][j] = graph[i][j]; 
            } 
        } 
        int[] parent = new int[S];  
        while (BFS(rGraph, s, t, parent)) {  
            int pathFlow = Integer.MAX_VALUE;          
            for (v = t; v != s; v = parent[v]) { 
                u = parent[v]; 
                pathFlow = Math.min(pathFlow, rGraph[u][v]); 
            } 
            for (v = t; v != s; v = parent[v]) { 
                u = parent[v]; 
                rGraph[u][v] = rGraph[u][v] - pathFlow; 
                rGraph[v][u] = rGraph[v][u] + pathFlow; 
            } 
        } 
        boolean[] isVisited = new boolean[S];      
        DFS(rGraph, s, isVisited); 
            
        for (int i = 0; i < S; i++) { 
            for (int j = 0; j < S; j++) { 
                if (graph[i][j] > 0 && isVisited[i] && !isVisited[j]) { 
                    System.out.println(i + " - " + j); 
                } 
            } 
        } 
    } 
    public static void main (String[] args) 
    { 
        // Tạo biểu đồ
        int graph[][] = { {0, 16, 13, 0, 0, 0}, 
                {0, 0, 10, 12, 0, 0}, 
                {0, 4, 0, 0, 14, 0}, 
                {0, 0, 9, 0, 0, 20}, 
                {0, 0, 0, 7, 0, 4}, 
                {0, 0, 0, 0, 0, 0} 
            }; 
        MaximumFlow n = new MaximumFlow(); 
        System.out.println("Maximum Flow: " + n.maxFlow(graph, 0, 5)); //giả sử đi từ đỉnh 0 đến đỉnh 5
		System.out.println("Minimum Cut:"); 
		n.minCut(graph,0,5);
    } 
} 