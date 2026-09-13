import java.util.*;
import java.util.LinkedList;

public class PathTarget {
    static class Edge {
        int src;
        int dest;

        public Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }

    public static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0, 1));
        graph[0].add(new Edge(0, 2));

        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 3));

        graph[2].add(new Edge(2, 0));
        graph[2].add(new Edge(2, 4));

        graph[3].add(new Edge(3, 1));
        graph[3].add(new Edge(3, 4));
        graph[3].add(new Edge(3, 5));

        graph[4].add(new Edge(4, 2));
        graph[4].add(new Edge(4, 3));
        graph[4].add(new Edge(4, 5));

        graph[5].add(new Edge(5, 3));
        graph[5].add(new Edge(5, 4));
        graph[5].add(new Edge(5, 6));

        graph[6].add(new Edge(6, 5)); // Fixed incorrect edge addition
    }

    public static void bfs(ArrayList<Edge>[] graph, int V, boolean[] vis, int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()) {
            int curr = q.remove();
            if (!vis[curr]) {
                System.out.print(curr + " ");
                vis[curr] = true;

                for (Edge e : graph[curr]) {
                    q.add(e.dest);
                }
            }
        }
    }

    public static void dfs(ArrayList<Edge>[] graph, int curr, boolean[] vis) {
        System.out.print(curr + " ");
        vis[curr] = true;

        for (Edge e : graph[curr]) {
            if (!vis[e.dest]) {
                dfs(graph, e.dest, vis);
            }
        }
    }

    public static void printAllPath(ArrayList<Edge>[] graph, boolean[] vis, int curr, String path, int tar) {
        if (curr == tar) {
            System.out.println(path);
            return;
        }

        vis[curr] = true; // Mark node as visited
        for (Edge e : graph[curr]) {
            if (!vis[e.dest]) {
                printAllPath(graph, vis, e.dest, path + " -> " + e.dest, tar);
            }
        }
        vis[curr] = false; // Backtrack
    }

    public static void main(String[] args) {
        int V = 7;

        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[V];
        createGraph(graph);

        int src = 0, tar = 5;
        System.out.println("All paths from " + src + " to " + tar + ":");
        printAllPath(graph, new boolean[V], src, src + "", tar);
    }
}
