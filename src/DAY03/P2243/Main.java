package DAY03.P2243;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, A, B, C;
    static int MAX = 1000000;
    static int[] tree;
    static int S;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/DAY03/P2243/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = 1;
        while (S < MAX) {
            S *= 2;
        }
        tree = new int[2 * S];

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            if (A == 1) { // 사탕 꺼내기
                B = Integer.parseInt(st.nextToken()); // 순위 (어떤 맛이 몇 개 있는지 계산)
                int index = qurey(1, S, 1, B);
                update(1, S, 1, index, -1);
                System.out.println(index);
            } else { // 사탕 넣기
                B = Integer.parseInt(st.nextToken()); // 맛 (B+S-1 => 노드번호)
                C = Integer.parseInt(st.nextToken()); // 개수
                update(1, S, 1, B, C);
            }
        }
    }
    static int qurey(int left, int right, int node, int count) {
        // 1. leaf노드에 도착햇을 때 -> 사탕 번호 반환
        if (left == right) {
            return left;
        }
        else {
            int mid = (left + right) / 2;
            // 루트 노드부터, 구하려는 순위가 현재 노드의 왼쪽 자식 값보다
            // 2. 작거나 같다면 왼쪽자식으로 이동하고
            if (tree[node * 2] >= count) {
                return qurey(left, mid, node * 2, count);
            }
            // 3. 크다면 오른쪽 자식으로 이동하고, 순위에서 왼쪽 자식의 값을 뺀다
            else {
                count -= tree[node * 2];
                return qurey(mid + 1, right, node * 2 + 1, count);
            }
        }
    }

    static void update(int left, int right, int node, int index, long diff) {
        if (left <= index && index <= right) {
            tree[node] += diff;
            if (left != right) {
                int mid = (left + right) / 2;
                update(left, mid, node * 2, index, diff);
                update(mid + 1, right, node * 2 + 1, index, diff);
            }
        }
    }
}
