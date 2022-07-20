package DAY02.P2805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] trees;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/DAY02/P2805/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        trees = new int[N];

        int max = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            max = Math.max(trees[i], max);
        }

        long start = 0, end = max, mid, result = 0;

        while (true) {
            mid = (start + end) / 2;

            long sum = calc(mid);

            if (sum == M) { // sum == M : 정답, 탈출
                result = mid;
                break;
            }
            else if (sum > M) { // sum > M : 정답 후보
                result = mid;
                start = mid + 1;
            }
            else {  // sum < M : 더 짤라
                end = mid - 1;
            }

            if (start > end) break;
        }
        System.out.println(result);
    }

    static long calc(long mid) {
        long sum = 0;
        for (int i = 0; i < trees.length; i++) {
            if (trees[i] >= mid) sum += trees[i] - mid;
        }
        return sum;
    }
}

