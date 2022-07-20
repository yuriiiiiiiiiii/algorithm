package DAY03.P1927;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int N;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/DAY03/P1927/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        MinHeap mh = new MinHeap();

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());

            if (x == 0) System.out.println(mh.delete()); // x가 0이라면 배열에서 가장 작은 값을 출력하고 그 값을 배열에서 제거
            else mh.insert(x); // x가 자연수라면 배열에 x라는 값을 추가
        }
    }
}

class MinHeap {
    List<Integer> list;

    public MinHeap() {
        list = new ArrayList<>();
        list.add(0); // 1번 노드부터 시작하기 때문에 인덱스0 채워놓고 시작
    }

    public void insert(int val) {
        // 1. leaf노드에 값 삽입
        list.add(val);

        // 2. 현재 leaf노드에 있는 데이터 자리 찾아가기
        int current = list.size() - 1;
        int parent = current / 2;
        while (true) {
            // root노드에 도달하거나, 부모노드가 현재노드보다 작다면 정지
            if (parent == 0 || list.get(current) >= list.get(parent)) break;
            // 현재노드가 부모노드보다 작으면 swap
            int temp = list.get(parent);
            list.set(parent, list.get(current));
            list.set(current, temp);

            current = parent;
            parent = current / 2;
        }
    }

    public int delete() {
        if (list.size() == 1) return 0; // 배열이 비어 있는 경우 0을 출력

        // 1. root노드에 leaf노드의 데이터 넣기
        int top = list.get(1);
        list.set(1, list.get(list.size() -1));
        list.remove(list.size() -1);

        // 2. 현재 root노드에 있는 데이터 자리 찾아가기
        int currentPos = 1;
        while (true) {
            int leftPos = currentPos * 2;
            int rightPos = currentPos * 2 + 1;

            // 자식노드 중 작은 값 구하기
            if (leftPos >= list.size()) break; // 왼쪽 자식 없는 경우엔 오른쪽 자식도 없음
            // 왼쪽 자식 확인
            int minValue = list.get(leftPos);
            int minPos = leftPos;
            // 오른쪽 자식 확인
            if (rightPos < list.size() && minValue > list.get(rightPos)) {
                minValue = list.get(rightPos);
                minPos = rightPos;
            }

            // 자식 노드 중 현재 노드보다 더 작은 값이 있다면 Swap하고
            if (list.get(currentPos) > minValue) {
                int temp = list.get(currentPos);
                list.set(currentPos, list.get(minPos));
                list.set(minPos, temp);
                currentPos = minPos;
            }
            // 없다면 정렬 끝
            else break;
        }

       return top;
    }
}