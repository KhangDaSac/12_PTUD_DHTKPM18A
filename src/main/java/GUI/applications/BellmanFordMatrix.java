package GUI.applications;

import java.util.Arrays;

import java.util.Arrays;

public class BellmanFordMatrix {

    // Hàm Bellman-Ford với đầu vào là ma trận trọng số
    public static void bellmanFord(int[][] weightMatrix, int V, int src) {
        // Mảng lưu khoảng cách từ đỉnh nguồn đến các đỉnh khác
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE); // Khởi tạo khoảng cách là vô cùng
        dist[src] = 0; // Khoảng cách từ nguồn đến chính nó bằng 0

        // In tiêu đề
        System.out.print("Step\t");
        for (int i = 0; i < V; i++) {
            System.out.print((char) ('A' + i) + "\t");
        }
        System.out.println();

        // In khoảng cách khởi tạo
        System.out.print("0\t");
        printDistances(dist);

        // Lặp qua tất cả các cạnh V-1 lần
        for (int step = 1; step <= V - 1; step++) {
            for (int u = 0; u < V; u++) {
                for (int v = 0; v < V; v++) {
                    if (weightMatrix[u][v] != Integer.MAX_VALUE && dist[u] != Integer.MAX_VALUE
                            && dist[u] + weightMatrix[u][v] < dist[v]) {
                        dist[v] = dist[u] + weightMatrix[u][v];
                    }
                }
            }
            // In khoảng cách sau mỗi lần lặp
            System.out.print(step + "\t");
            printDistances(dist);
        }
    }

    // Hàm in khoảng cách
    private static void printDistances(int[] dist) {
        for (int d : dist) {
            if (d == Integer.MAX_VALUE) {
                System.out.print("∞\t");
            } else {
                System.out.print(d + "\t");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Ma trận trọng số: Integer.MAX_VALUE biểu thị không có cạnh
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

        int V = weightMatrix.length; // Số đỉnh
        int src = 0; // Đỉnh nguồn (A)

        bellmanFord(weightMatrix, V, src);
    }
}

