package DAY01.P3055;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    // 좌, 우, 상, 하
    static final int[] MX = {-1, 1, 0, 0};
    static final int[] MY = {0, 0, -1, 1};

    static int R, C;
    static char[][] map;
    static int[][] dp;
    static Queue<Point> queue;
    static boolean foundAnswer;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/DAY01/P3055/input.txt"));
        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C =  sc.nextInt();

        map = new char[R][C];
        dp = new int[R][C];
        queue = new LinkedList<>();
        foundAnswer = false;

        Point st = null;
        for (int r = 0; r < R; r++) {
            String line = sc.next();
            for (int c = 0; c < C; c++) {
                map[r][c] = line.charAt(c);
                if (map[r][c] == '*') {
                    queue.add(new Point(r, c, '*')); // 물 넣어주고
                } else if (map[r][c] =='S') {
                    st = new Point(r, c, 'S');
                }
            }
        }
        queue.add(st); // 마지막에 고슴도치 위치 넣어준다

        while (!queue.isEmpty()) {
            // 1. 큐에서 꺼내옴 -> *, S, ., D
            Point p = queue.poll();
            // 2. 목적지인가? -> D
            if (p.type == 'D') {
                System.out.println(dp[p.y][p.x]); // 답 출력
                foundAnswer = true;
                break;
            }
            // 3. 연결된 곳을 순회 -> 좌우상하
            for (int i = 0; i < 4; i++) {
                int ty = p.y + MY[i];
                int tx = p.x + MX[i];

                // 4. 갈 수 있는가? -> (공통): map 안인가
                if (0 <= ty && ty < R && 0 <= tx && tx < C) {
                    // 4. 갈 수 있는가? -> (고슴도치): 방문하지 않은 . 이나 D
                    if (p.type == 'S' || p.type == '.') {
                        if ((map[ty][tx] == '.' || map[ty][tx] == 'D') && dp[ty][tx] == 0) {
                            // 5. 체크인 -> (고슴도치): dp[][] = 이동거리
                            dp[ty][tx] = dp[p.y][p.x] + 1;
                            // 6. 큐에 넣음
                            queue.add(new Point(ty, tx, map[ty][tx]));
                        }
                    }
                    // 4. 갈 수 있는가? -> (물): . 이나 S
                    else if (p.type == '*') {
                        if (map[ty][tx] == '.' || map[ty][tx] == 'S') {
                            // 5. 체크인 -> (물): map[][] = *
                            map[ty][tx] = '*';
                            // 6. 큐에 넣음
                            queue.add(new Point(ty, tx, '*'));
                        }
                    }
                }
            }
        }
        if (foundAnswer == false) {
            System.out.println("KAKTUS");
        }
    }
}

class Point {
    int x;
    int y;
    char type;

    public Point (int y, int x, char type) {
        this.y = y;
        this.x = x;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Point{" + "y" + y + ", x=" + x + ", type=" + type + "}";
    }
}
