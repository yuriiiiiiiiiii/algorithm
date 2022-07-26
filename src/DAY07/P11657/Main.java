// 벨만-포드
package DAY07.P11657;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/DAY07/P11657/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] D = new long[N+1];
        int[][] edgeList = new int[M][3];
        long INF = Long.MAX_VALUE;
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edgeList[i][0] = Integer.parseInt(st.nextToken());
            edgeList[i][1] = Integer.parseInt(st.nextToken());
            edgeList[i][2] = Integer.parseInt(st.nextToken());
        }
        Arrays.fill(D, INF);
        D[1] = 0;
        int a, b, w;
        boolean flag = false;
        for(int i = 1; i <= N; i++) {
            for(int j = 0; j < M; j++) {
                a = edgeList[j][0];
                b = edgeList[j][1];
                w = edgeList[j][2];
                if(D[a] == INF) continue;
                if(D[b] > D[a] + w) {
                    if(i == N) flag = true;
                    D[b] = D[a] + w;
                }
            }
        }
        StringBuffer sb = new StringBuffer();
        if(flag) sb.append(-1);
        else {
            for(int i = 2; i <= N; i++) {
                sb.append((D[i] == INF ? -1 : D[i]) + "\n");
            }
        }
        System.out.print(sb);

    }
}