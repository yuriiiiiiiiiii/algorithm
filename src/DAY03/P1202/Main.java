package DAY03.P1202;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K;
    static Jewelry[] jewelries;
    static int[] bags;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/DAY03/P1202/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        jewelries = new Jewelry[N];
        bags = new int[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            jewelries[i] = new Jewelry(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        }

        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        // 가방 오름차순 정렬
        Arrays.sort(bags);
        // 보석 무게 오름차순 정렬
        Arrays.sort(jewelries, Comparator.comparingInt(Jewelry::getWeight));
        // 보석 가격 높은값 기준 힙
        PriorityQueue<Jewelry> pq = new PriorityQueue<>(Comparator.comparingInt(Jewelry::getPrice).reversed());

        int jIndex = 0;
        int result = 0;
        // 1. 남은 가방 중 제일 작은 가방을 선택 <- 정렬
        for (int i = 0; i < bags.length; i++) {
            // 2. 선택된 가방에 넣을 수 있는 남은 보석 중 가장 비싼 보석을 선택 <- 힙을 사용
            // 2-1. 현재 가방에 넣을 수 있는 무게의 보석 모두 pq에 삽입
            while (jIndex < N && jewelries[jIndex].weight <= bags[i]) {
                pq.add(jewelries[jIndex++]);
            }
            // 2-2. pq의 가장 앞에 있는 (가장 비싼) 보석을 훔침
            if (!pq.isEmpty()) result += pq.poll().price;
        }
        System.out.println(result);
    }
}
class Jewelry {
    int weight;
    int price;

    public int getWeight() { return weight; }

    public int getPrice() { return price; }

    public Jewelry(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }

    @Override
    public String toString() { return "Jewelry{" + "weight=" + weight + ", price=" + price + '}'; }
}
