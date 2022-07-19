package DAY01.P1713;

import java.util.*;

public class SortTest {
    public static  void main(String[] args) {
        Item item1 = new Item (1, 3, 1);
        Item item2 = new Item (1, 2, 1);
        Item item3 = new Item (1, 1, 2);

        List<Item> list = new ArrayList<>();
        list.add(item1);
        list.add(item2);
        list.add(item3);
        System.out.println(list);

        Collections.sort(list);
        System.out.println(list);

        Comparator<Item> comp = new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.c - o2.c;
            }
        };
        Collections.sort(list, comp);
        System.out.println(list);

        Collections.sort(list, Comparator.comparingInt(Item::getB).reversed());
        System.out.println(list);
    }
}

class Item implements Comparable<Item>{
    int a;
    int b;
    int c;

    public Item(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public String toString() {
        return "{" + "a" + a + ", b=" + b + ", c=" + c + "}";
    }

    @Override
    public int compareTo(Item o) {
        /* 오름차순 정렬
           음수 -> 원래 순서
           0
           양수 -> SWAP */

        // 방법1
        // if (b < o.b) return -1;
        // else if (b == o.b) return 0;
        // else return 1;

        // 방법2
        // return b - o.b;

        // 방법3
        return Integer.compare(b, o.b);
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }
}
