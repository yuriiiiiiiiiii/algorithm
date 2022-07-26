// 다익스트라
package DAY07.P1753;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int V, E, K;
    static ArrayList<int[]> adj[];
    static int dist[];

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/DAY07/P1753/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        adj = new ArrayList[V + 1];
        dist = new int[V + 1];
        for(int i = 1; i <= V; i++) {
            adj[i] = new ArrayList<int[]>();
        }
        for(int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj[u].add(new int[] {v, w});
        }
        dijkstra();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            sb.append(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void dijkstra() {
        PriorityQueue<Route> pq = new PriorityQueue<Route>();
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[K] = 0;
        pq.offer(new Route(K, 0));
        while(!pq.isEmpty()) {
            Route curr = pq.poll();
            if(dist[curr.v] < curr.w) continue;
            for(int[] next : adj[curr.v]) {
                if (dist[next[0]] > curr.w + next[1]) {
                    dist[next[0]] = curr.w + next[1];
                    pq.offer(new Route(next[0], dist[next[0]]));
                }
            }
        }
    }

    public static class Route implements Comparable<Route>{
        int v, w;
        public Route(int v, int w) {
            super();
            this.v = v;
            this.w = w;
        }
        @Override
        public int compareTo(Route o) {
            return this.w - o.w;
        }
    }
}