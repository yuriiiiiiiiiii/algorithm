package DAY03.P2042; /* 인덱스 트리 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static long[] nums;
    static long[] tree;

    static int command, param1;
    static long param2;

    static int S;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/DAY03/P2042/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 수 입력받기
        nums = new long[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Long.parseLong(br.readLine());
        }

        // 트리 초기화
        S = 1;
        while (S < N) {
            S *= 2;
        }
        tree = new long[S * 2];
        initBU();
        // System.out.println(Arrays.toString(tree));

        // 합 출력, 변경 명령 입력받기
        for (int i = 0; i < M+K; i++) {
            st = new StringTokenizer(br.readLine());
            command = Integer.parseInt(st.nextToken());
            param1 = Integer.parseInt(st.nextToken());
            param2 = Long.parseLong(st.nextToken());

            if (command == 1) { // param1번째 수를 param2로 바꿈
                long diff = param2 - tree[S + param1 - 1];
                updateTopDown(1, S, 1, param1, diff);
            }
            else { // param1번째 수부터 param2번째 수까지의 합을 출력
                System.out.println(queryTopDown(1, S, 1, param1, (int)param2));
            }
        }
    }

    static void initBU() {
        // Leaf노드에 값(입력데이터) 반영
        for (int i = 0; i < N; i++) {
            tree[S + i] = nums[i];
        }
        // 내부노드를 자식노드의 합으로 채움 (밑에서부터 루트노드로)
        for (int i = S - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    static long queryTopDown(int left, int right, int node, int queryLeft, int queryRight) {
        // 연관이 없음 -> 결과에 영향이 없는 값 return
        if (queryRight < left || right < queryLeft) {
            return 0;
        }
        // 판단 가능 -> 현재 노드 값 return
        else if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }
        // 판단불가, 자식에게 위임, 자식에서 올라온 합을 return
        else {
            int mid = (left + right) / 2;
            long resultLeft = queryTopDown(left, mid, node * 2, queryLeft, queryRight);
            long resultRight = queryTopDown(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
            return resultLeft + resultRight;
        }
    }

    static void updateTopDown(int left, int right, int node, int target, long diff) {
        //연관없음
        if (target < left || right < target) {
            return;
        }
        //연관 있음 -> 현재 노드에 diff 반영 -> 자식에게 diff전달
        else {
            tree[node] += diff;
            if (left != right) {
                int mid = (left + right) / 2;
                updateTopDown(left, mid, node * 2, target, diff);
                updateTopDown(mid + 1, right, node * 2 + 1, target, diff);
            }
        }
    }

    static long queryBottomUp(int queryLeft, int queryRight) {
        // Leaf 에서 left, right 설정
        int left = S + queryLeft - 1;
        int right = S + queryRight - 1;
        long sum = 0;
        while (left <= right) {
            // 좌측 노드가 홀수이면 현재 노드 값 사용하고 한칸 옆으로
            if (left % 2 == 1) {
                sum += tree[left++];
            }
            // 우측 노드가 짝수이면 현재 노드 값 사용하고 한칸 옆으로
            if (right % 2 == 0) {
                sum += tree[right--];
            }
            // 좌측,우측 모두 부모로 이동
            left /= 2;
            right /= 2;
        }
        return sum;
    }

    static void queryBottomUp(int target, long value) {
        //Leaf에서 target을 찾음
        int node = S + target - 1;
        //value 반영
        tree[node] = value;
        //Root에 도달할 때 까지 부모에 값 반영
        node /= 2;
        while (node > 0) {
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
            node /= 2;
        }
    }
}
