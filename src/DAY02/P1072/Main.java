package DAY02.P1072;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long X, Y, Z;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/DAY02/P1072/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        X = Long.parseLong(st.nextToken()); // 게임 횟수
        Y = Long.parseLong(st.nextToken()); // 이긴 게임
        Z = Y * 100 / X ; // 승률

        if (Z >= 99) System.out.println(-1); // 만약 Z가 절대 변하지 않는다면 -1을 출력
        else {
            long start = 0;
            long end = X;
            while (start < end) {
                long mid = (start + end) / 2;
                long newZ = (100 * (Y + mid) / (X + mid));

                if (newZ == Z) start = mid + 1; // 승률이 변하지 않은 경우
                else end = mid; // 승률이 변한 경우 (답일 수도 있지만 더 작은 답이 있을 수도)
            }
            System.out.println(end);
        }
    }
}
