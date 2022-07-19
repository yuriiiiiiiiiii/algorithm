package DAY02.P1072;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long X, Y;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/DAY02/P1072/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        X = Long.parseLong(st.nextToken());
        Y = Long.parseLong(st.nextToken());

        long Z = Y / X * 100;
        System.out.println(X);
        System.out.println(Y);
        System.out.println(Z);
    }


}
