package DAY01.P1062;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    static int N, K;
    static String[] words;
    static boolean[] visited;
    static int selectedCount = 0;
    static int max = 0;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/DAY01/P1062/input.txt"));
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt(); // 단어 수
        K =  sc.nextInt(); // 가르칠 글자 수

        words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = sc.next().replaceAll("[antic]", "");
        }

        visited = new boolean[26];
        visited['a' - 'a'] = true;
        visited['n' - 'a'] = true;
        visited['t' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['c' - 'a'] = true;

        selectedCount = 5;

        if (K < selectedCount) {
            System.out.println(0); // 5글자 antic를 모르면 읽을 수 있는 단어 없음
        }
        else if (K == selectedCount) { // 5글자만 가르칠 수 있다면, antic를 선택
            System.out.println(countWords());
        }
        else { // 보다 많이 가르칠 수 있다면 탐색
            for (int i = 0; i < 26; i++) {
                if (visited[i] == false) {
                    dfs(i);
                }
            }
            System.out.println(max);
        }
    }

    static void dfs(int index) {
        /* 1. 체크인 */
        visited[index] = true;
        selectedCount++;
        /* 2. 목적지인가? */
        if (selectedCount == K) { // 가르칠 글자가 K개가 되었을 경우
            max = Math.max(max, countWords()); // 읽을 수 있는 단어의 개수를 계산해보기
        }
        else { // 아직 K개가 되지 않았을 경우
            /* 3. 연결된 곳을 순회 : index + 1 ~ 25 */
            for (int i = index + 1; i <= 25; i++) {
                /* 4. 갈 수 있는가? : 방문 여부 */
                if (visited[i] == false) {
                    /* 5. 간다 : dfs() */
                    dfs(i);
                }
            }
        }
        // 6. 체크아웃
        visited[index] = false;
        selectedCount--;
    }

    static int countWords() {
        int count = 0;

        for (int n = 0; n < N; n++) {
            boolean isPossible = true;
            String word = words[n];
            for (int i = 0; i < word.length(); i++) {
                if (visited[word.charAt(i) - 'a'] == false) { // 'a' - 'a' = 0 // 'b'- 'a' = 1
                    isPossible = false;
                    break;
                }
            }
            if (isPossible) count++; // 읽을 수 있는 단어라면 count 증가
        }

        return count;
    }
}
//
