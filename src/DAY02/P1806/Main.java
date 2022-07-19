package DAY02.P1806;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, S;
    static int[] nums;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/DAY02/P1806/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        nums = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int low = 0, high = 0, sum = nums[0], minLength = Integer.MAX_VALUE;
        while (true) {
            // sum >= S -> length가 min보다 짧으면 업데이트, low++
            if (sum >= S) {
                minLength = Math.min(high - low + 1, minLength); // length = high - low + 1
                sum -= nums[low++];
            }
            // sum < S -> high++
            else {
                sum += nums[++high];
            }

            if (high == N) {
                break;
            }
        }

        if (minLength == Integer.MAX_VALUE) System.out.println(0);
        else System.out.println(minLength);
    }
}
