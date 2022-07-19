package DAY01.P1713;

import java.io.FileInputStream;
import java.util.*;

public class Main {
    static int N, K;
    static Nominee[] nominees;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/DAY01/P1713/input.txt"));
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt(); // 사진틀의 개수
        K =  sc.nextInt(); // 전체 학생의 총 추천 횟수

        nominees = new Nominee[101];

        List<Nominee> list = new ArrayList<>();
        for (int k = 0; k < K; k++) {
            int num = sc.nextInt();
            // 해당 후보가 최초 호출 시
            if (nominees[num] == null) {
                nominees[num] = new Nominee(num, 0, 0, false);
            }
            // 해당 후보가 사진틀에 있을 경우
            if (nominees[num].isIn == true) {
                nominees[num].count++;
            }
            // 해당 후보가 사진틀에 없을 경우
            else {
                // 사진틀에 여유가 없는 경우
                if (list.size() == N) {
                    // 정렬, 자동 후보 선정, 제거
                    Collections.sort(list);
                    //list.get(0).isIn = false;
                    Nominee nominee = list.remove(0);
                    nominee.isIn = false;
                }

                // 사진들에 여유가 있는 경우
                nominees[num].count++;
                nominees[num].isIn = true;
                nominees[num].timeStamp = k;
                list.add(nominees[num]);
            }
        }

        Collections.sort(list, new Comparator<Nominee>() {
            @Override
            public int compare(Nominee o1, Nominee o2) {
                return Integer.compare(o1.num, o2.num);
            }
        });

        for (int n = 0; n < list.size(); n++) {
            System.out.print(list.get(n).num + " ");
        }
    }
}

class Nominee implements Comparable<Nominee>{
    int num;
    int count;
    int timeStamp;
    boolean isIn;

    public Nominee(int num, int count, int timeStamp, boolean isIn) {
        this.num = num;
        this.count = count;
        this.timeStamp = timeStamp;
        this.isIn = isIn;
    }

    @Override
    public String toString() {
        return "{" + "num" + num + ", count=" + count + ", timeStamp=" + timeStamp  + ", isIn=" + isIn + "}";
    }

    @Override
    public int compareTo(Nominee o) {
        int comp = Integer.compare(count, o.count);
        if (comp == 0) return Integer.compare(timeStamp, o.timeStamp);
        else return comp;
    }
}