import java.util.*; 
class ShortestPath { 
    static final int V = 6; 
    int minDistance(int dist[], Boolean set[]) 
    { 
        //Tao gia tri nho nhat
        int min = Integer.MAX_VALUE, minIndex = -1; 
  
        for (int v = 0; v < V; v++) 
            if (set[v] == false && dist[v] <= min) { 
                min = dist[v]; 
                minIndex = v; 
            } 
        return minIndex; 
    } 
	
    void printSolution(int dist[]) 
    { 
        System.out.println("Khoang cach tu s toi t la: "+dist[V-1] ); 
    } 
	
    void dijkstra(int graph[][], int src) 
    { 
        int dist[] = new int[V]; 
        Boolean set[] = new Boolean[V]; 
        //Khoi tao khoang cach la INFI va set[i] la false 
        for (int i = 0; i < V; i++) { 
            dist[i] = Integer.MAX_VALUE; 
            set[i] = false; 
        } 
        //Khoang cach tu source toi source = 0
        dist[src] = 0; 
        //Tim duong di ngan nhat cho cac dinh 
        for (int count = 0; count < V - 1; count++) { 
            int u = minDistance(dist, set); 
            set[u] = true; 
            for (int v = 0; v < V; v++)
                if (!set[v] && graph[u][v] != 0 &&  
                   dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) 
                    dist[v] = dist[u] + graph[u][v]; 
        } 
        printSolution(dist); 
    } 
  
    public static void main(String[] args) 
    { 
  
        int graph[][] = { 
					{0, 16, 13, 0, 0, 0}, 
				{0, 0, 10, 12, 0, 0}, 
                {0, 4, 0, 0, 14, 0}, 
                {0, 0, 9, 0, 0, 20}, 
                {0, 0, 0, 7, 0, 4}, 
                {0, 0, 0, 0, 0, 0}}; 
 
        ShortestPath t = new ShortestPath(); 
        t.dijkstra(graph, 0); 
    } 
} 