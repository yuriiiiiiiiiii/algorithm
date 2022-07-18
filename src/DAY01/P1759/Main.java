package DAY01.P1759;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
    static int L, C;
    static char[] data;
    static List<String> result;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/DAY01/P1759/input.txt"));
        Scanner sc = new Scanner(System.in);

        L = sc.nextInt(); // 암호가 구성된 문자 개수
        C = sc.nextInt(); // 암호로 사용했을 법한 문자 개수

        data = new char[C]; // 암호로 사용했을 법한 문자의 종류
        for (int i = 0; i < C; i++) {
            data[i] = sc.next().charAt(0);
        }

        result = new LinkedList<>(); // 가능성 있는 암호들

        Arrays.sort(data);
        dfs(0, 0, 0, -1, "");

        for (int r = 0; r < result.size(); r++) {
            System.out.println(result.get(r)); // 한 줄씩 출력
        }
    }

    static void dfs(int length, int ja, int mo, int current, String pwd) {
        /* 1. 체크인 - 생략 가능 */
        /* 2. 목적지인가? length == L => ja 개수, mo 개수 확인 */
        if (length == L) { // L개의 문자가
            if (ja >= 2 && mo >= 1) { // 최소 한 개의 모음(a, e, i, o, u)과 최소 두 개의 자음으로 구성되었다면
                result.add(pwd);
            }
        }
        else {
            /* 3. 연결된 곳을 순회 : current + 1 ~ C */
            for (int i = current + 1; i < C; i++) {
                /* 4. 갈 수 있는가? : 다 갈 수 있음 */
                /* 5. 간다 : ja, mo */
                if (data[i] == 'a' || data[i] == 'e' || data[i] == 'i' || data[i] == 'o' || data[i] == 'u') {
                    dfs(length + 1, ja, mo + 1, i, pwd + data[i]);
                }
                else {
                    dfs(length + 1, ja + 1, mo, i, pwd + data[i]);
                }
            }
        }
        /* 6. 체크아웃 - 생략 가능 */
    }
}