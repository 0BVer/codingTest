package bfs.q1697;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int start = scanner.nextInt();
        int end = scanner.nextInt();
        Deque<Integer> deque = new ArrayDeque<>();
        boolean[] visited = new boolean[100001];
        for (int i = 0; i < 100001; i++) visited[i] = false;
        int time = 0;
        deque.add(start);

        while (deque.stream().noneMatch(integer -> integer == end)) {
            int size = deque.size();
            for (int k = 0; k < size; k++) {
                int now = deque.poll();
                insert(deque, visited, now - 1);
                insert(deque, visited, now + 1);
                insert(deque, visited, now * 2);
            }
            time++;
        }
        System.out.println(time);
    }

    private static void insert(Deque<Integer> deque, boolean[] visited, int n) {
        if (100001 > n && n > -1)
            if (!visited[n]) {
                deque.add(n);
                visited[n] = true;
            }
    }
}