// 네트워크 연결
package DAY06.P1922;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] parent;
    static int a, b, c;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/DAY06/P1922/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2]; // c 기준으로 a,b,c 묶음을 오름차순 정렬
            }
        });

        parent = new int[N+1];
        for(int i = 1; i <= N; i++){
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            pq.add(new int[]{a,b,c});
        }

        int cnt = 0, total = 0;
        while(cnt < N-1 && !pq.isEmpty()){ // 간선 수가 N-1개가 되면 끝
            int[] tmp = pq.poll();
            // a와 b가 모두 아직 연결된 정점에 없다면 간선 추가하고 비용 누적, 아니면 패스
            if(find(tmp[0]) != find(tmp[1])){
                cnt++;
                union(tmp[0], tmp[1]);
                total += tmp[2];
            }
        }
        System.out.println(total); // 누적해놨던 비용(c들의 합)을 출력
    }
    static int find(int x){
        return parent[x] = (parent[x] == x) ? x : find(parent[x]);
    }
    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        parent[a] = b;
    }
}



