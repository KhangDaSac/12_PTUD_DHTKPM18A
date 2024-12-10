package GUI.applications;

// Java program to find single source shortest path using
// Bellman-Ford algorithm

import java.util.Arrays;

public class GFG {
    static int[] bellmanFord(int V, int[][] edges, int src) {

        // Initially distance from source to all other vertices
        // is not known(Infinite).
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Relaxation of all the edges V times, not (V - 1) as we
        // need one additional relaxation to detect negative cycle
        for (int i = 0; i < V; i++) {
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int wt = edge[2];
                if (dist[u] != Integer.MAX_VALUE && dist[u] + wt < dist[v]) {

                    // If this is the Vth relaxation, then there is
                    // a negative cycle
                    if (i == V - 1)
                        return new int[]{-1};

                    // Update shortest distance to node v
                    dist[v] = dist[u] + wt;
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        Integer M = Integer.MAX_VALUE;
        int[][] weightMatrix = {
                {0, 1, 4, 2, M, M, M, M, M},
                {M, 0, M, M, M, M, M, M, -2},
                {M, -5, 0, M, M, M, M, M, 3},
                {M, M, -3, 0, 3, 2, M, M, M},
                {M, M, M, M, 0, -4, -3, M, M},
                {M, M, 3, M, M, 0, 6, 3, -3},
                {M, M, M, M, M, M, 0, -4, M},
                {M, M, M, M, M, M, M, 0, M},
                {M, M, M, M, M, M, M, 3, 0}
        };
        int V = 9;
        int src = 0;
        int[] ans = bellmanFord(V, weightMatrix, src);
        for (int dist : ans)
            System.out.print(dist + " ");
    }
}
