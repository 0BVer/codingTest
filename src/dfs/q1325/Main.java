package dfs.q1325;

import java.io.BufferedReader;
//import java.io.BufferedWriter;
import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    //    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder result = new StringBuilder();
    static ArrayList<Integer>[] graph = new ArrayList[10001];
    static int[] counts = new int[10001];
    static boolean[] visited = new boolean[10001];
    static int size, link;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        size = Integer.parseInt(st.nextToken());
        link = Integer.parseInt(st.nextToken());

        // 그래프 생성, 단방향 그래프
        for (int i = 1; i <= size; i++) graph[i] = new ArrayList<>(10001);
        for (int i = 0; i < link; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[b].add(a);
        }
        br.close();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= size; i++) {
            visited = new boolean[10001];
            visited[i] = true;
            queue.add(i);
            while (!queue.isEmpty()) {
                int temp = queue.poll();
                for (int j = 0; j < graph[temp].size(); j++) {
                    if (!visited[j]) {
                        visited[j] = true;
                        queue.add(j);
                        counts[i]++;
                    }
                }
            }
        }
        int max = -1;
        for (int i = 1; i <= size; i++) max = Math.max(max, counts[i]);
        for (int i = 1; i <= size; i++) if (counts[i] == max) result.append(i + " ");
//        bw.write(result.toString());
//        bw.flush();
//        bw.close();
        System.out.println(result);
    }
}